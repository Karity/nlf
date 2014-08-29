package nc.liat6.frame.xml.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nc.liat6.frame.exception.NlfException;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.util.Stringer;
import nc.liat6.frame.xml.XmlFormatException;
import nc.liat6.frame.xml.element.IXmlElement;
import nc.liat6.frame.xml.element.XmlList;
import nc.liat6.frame.xml.element.XmlMap;
import nc.liat6.frame.xml.element.XmlString;
import nc.liat6.frame.xml.element.XmlType;

/**
 * XML������
 * <p>
 * ����ѭ����Լ����XML���ܻ����ʧ�ܣ�
 * <ul>
 * <li>�ӽڵ�Ϊ���Եģ����ڵ㽨������type="bean"���ԡ�</li>
 * <li>�ӽڵ�Ϊ����ģ����ڵ��������type="list"���ԡ�</li>
 * <li>�ַ����а���&lt;��&gt;����ʹ��&lt;![CDATA[��]]&gt;��װ��</li>
 * </ul>
 * </p>
 * 
 * @author 6tail
 * 
 */
public class XMLParser{

  /** CDATA��ʼ�� */
  public static final String CDATA_PREFIX = "![CDATA[";
  /** CDATA������ */
  public static final String CDATA_SUFFIX = "]]";
  /** ע����ʼ�� */
  public static final String ANNO_PREFIX = "!--";
  /** ע�ͽ����� */
  public static final String ANNO_SUFFIX = "--";
  /** BEAN�ڵ����� */
  public static final int TYPE_BEAN = 1;
  /** LIST�ڵ����� */
  public static final int TYPE_LIST = 2;
  /** �����ڵ����ͣ��ַ����� */
  public static final int TYPE_OTHER = 0;
  /** �ڵ㻺��ջ */
  private List<IXmlElement> stack = new ArrayList<IXmlElement>();
  /** ��ǰ�ַ� */
  private int c;
  /** ���������ַ��� */
  private String orgs;
  /** �ַ���ȡ�� */
  private Reader reader;
  /** ע�� */
  private String note;

  /**
   * ��XML�ַ���ת��Ϊ����
   * 
   * @param s XML�ַ���
   * @return ����
   */
  public IXmlElement parse(String s){
    orgs = s;
    if(null==s){
      return null;
    }
    s = s.trim();
    s = s.substring(s.indexOf("<"));
    reader = new StringReader(s);
    while(-1!=c){
      parseElement();
    }
    return stack.get(0);
  }

  private void parseElement(){
    skip();
    switch(c){
      case '<':// ����ʼ
        parseNode();
        break;
      default:
        String s = readUntil('<');
        IXmlElement p = stack.get(stack.size()-1);
        try{
          p.toXmlString().set(s.replace("&lt;","<").replace("&gt;",">"));
        }catch(Exception e){
          throw new XmlFormatException(s);
        }
        break;
    }
  }

  private void parseNode(){
    next();
    switch(c){
      case '?':
        readUntil('>');
        next();
        break;
      case '!':
        String s = readUntil('>').trim();
        String us = s.toUpperCase();
        if(us.startsWith(CDATA_PREFIX)){// ����CDATA
          while(!us.endsWith(CDATA_SUFFIX)){
            next();
            if(-1==c){
              throw new XmlFormatException(L.get("xml.cdata_not_end")+"\r\n"+orgs);
            }
            us = readUntil('>');
            s += ">"+us;
            us = us.toUpperCase();
          }
          s = s.substring(CDATA_PREFIX.length());
          s = s.substring(0,s.length()-CDATA_SUFFIX.length());
          if(stack.size()>0){
            IXmlElement p = stack.get(stack.size()-1);
            p.toXmlString().set(s);
          }
        }else if(us.startsWith(ANNO_PREFIX)){// ����ע��
          StringBuffer n = new StringBuffer();// ע��
          n.append(s);
          while(!us.endsWith(ANNO_SUFFIX)){
            next();
            if(-1==c){
              throw new XmlFormatException(L.get("xml.note_not_end")+"\r\n"+orgs);
            }
            us = readUntil('>');
            n.append(us);
            us = us.toUpperCase();
          }
          note = n.toString();
          note = note.substring(ANNO_PREFIX.length());
          note = note.substring(0,note.length()-ANNO_SUFFIX.length());
        }
        next();
        break;
      case '/':
        next();
        String tag = readUntil('>');
        next();
        if(stack.size()<2){
          break;
        }
        // ���һ���ڵ�
        IXmlElement el = stack.remove(stack.size()-1);
        IXmlElement p = stack.get(stack.size()-1);
        switch(p.type()){
          case LIST:
            p.toXmlList().add(el);
            break;
          case MAP:
            IXmlElement xe = p.toXmlMap().get(tag);
            if(null!=xe){
              String tagName = p.getName();
              String n = p.getNote();
              XmlList xl = new XmlList();
              Map<String,String> attrs = p.getAttributes();
              xl.setName(tagName);
              xl.setNote(n);
              xl.setAttributes(attrs);
              xl.add(xe);
              xl.add(el);
              stack.set(stack.size()-1,xl);
            }
            p.toXmlMap().set(tag,el);
            break;
          default:
            if(XmlType.STRING.equals(p.type())){
              String tagName = p.getName();
              String n = p.getNote();
              Map<String,String> attrs = p.getAttributes();
              p = new XmlMap();
              p.setName(tagName);
              p.setNote(n);
              p.setAttributes(attrs);
              stack.set(stack.size()-1,p);
            }
            p.toXmlMap().set(tag,el);
        }
        break;
      default:
        String startTag = readUntil('>');
        next();
        startTag = startTag.trim();
        if(startTag.endsWith("/")){
          break;
        }
        Map<String,String> attrs = new HashMap<String,String>();
        if(startTag.contains(" ")){
          String nodeName = Stringer.cut(startTag,""," ");
          String attr = Stringer.cut(startTag," ");
          int type = TYPE_OTHER;
          while(attr.contains("=\"")){
            String attrName = Stringer.cut(attr,"","=\"").trim();
            String attrValue = Stringer.cut(attr,"=\"","\"").trim();
            attrs.put(attrName,attrValue);
            attr = Stringer.cut(attr,"=\"");
            attr = Stringer.cut(attr,"\"");
            if("type".equals(attrName)){
              if("bean".equalsIgnoreCase(attrValue)){
                type = TYPE_BEAN;
              }else if("list".equalsIgnoreCase(attrValue)){
                type = TYPE_LIST;
              }
            }
          }
          switch(type){
            case TYPE_BEAN:
              XmlMap xm = new XmlMap();
              if(null!=note){
                xm.setNote(note);
                note = null;
              }
              xm.setName(nodeName);
              xm.setAttributes(attrs);
              stack.add(xm);
              break;
            case TYPE_LIST:
              XmlList xl = new XmlList();
              if(null!=note){
                xl.setNote(note);
                note = null;
              }
              xl.setName(nodeName);
              xl.setAttributes(attrs);
              stack.add(xl);
              break;
            case TYPE_OTHER:
              XmlString xs = new XmlString();
              if(null!=note){
                xs.setNote(note);
                note = null;
              }
              xs.setName(nodeName);
              xs.setAttributes(attrs);
              stack.add(xs);
              break;
          }
        }else{
          XmlString xs = new XmlString();
          if(null!=note){
            xs.setNote(note);
            note = null;
          }
          xs.setName(startTag);
          xs.setAttributes(attrs);
          stack.add(xs);
        }
    }
  }

  /**
   * һֱ��ȡ��ֱ������ָ���ַ���������ָ���ַ�
   * 
   * @param endTag ָ���ַ�
   * @return ��ȡ�����ַ���
   */
  private String readUntil(int endTag){
    StringBuilder s = new StringBuilder();
    while(-1!=c&&endTag!=c){
      s.append((char)c);
      next();
    }
    return s.toString();
  }

  /**
   * ��ȡ��һ���ַ�
   */
  private void next(){
    try{
      c = reader.read();
    }catch(IOException e){
      throw new NlfException(e);
    }
  }

  /**
   * �����������ַ���ע��
   */
  private void skip(){
    if(-1==c)
      return;
    if(0<=c&&32>=c){ // ����0��32֮���
      next();
      skip();
    }
    if(127==c||'\r'==c||'\n'==c){ // ����DEL���س�����
      next();
      skip();
    }
  }
}