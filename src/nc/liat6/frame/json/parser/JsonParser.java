package nc.liat6.frame.json.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import nc.liat6.frame.exception.NlfException;
import nc.liat6.frame.json.JsonFormatException;
import nc.liat6.frame.json.element.IJsonElement;
import nc.liat6.frame.json.element.JsonBool;
import nc.liat6.frame.json.element.JsonList;
import nc.liat6.frame.json.element.JsonMap;
import nc.liat6.frame.json.element.JsonNumber;
import nc.liat6.frame.json.element.JsonString;
import nc.liat6.frame.locale.L;

/**
 * JSONת����
 * 
 * @author 6tail
 * 
 */
public class JsonParser{

  /** ��б�� */
  public static final int RIGHT_SLASH = 92;
  /** ��ǰ�ַ� */
  private int c;
  /** λ�� */
  private int pos = 0;
  /** ���������ַ��� */
  private String orgs;
  /** �ַ���ȡ�� */
  private Reader reader;
  /** ע�� */
  private String note;

  /**
   * ��JSON�ַ���ת��Ϊ����
   * 
   * @param s JSON�ַ���
   * @return ����
   */
  public IJsonElement parse(String s){
    orgs = s;
    if(null==s){
      return null;
    }
    s = s.trim();
    reader = new StringReader(s);
    return parseElement();
  }

  private IJsonElement parseElement(){
    skip();
    switch(c){
      case -1:// ����
        return null;
      case '{':// ����ʼ
        return parseMap();
      case '\'':// �ַ�����ʼ
        return parseString();
      case '"':// �ַ�����ʼ
        return parseString();
      case '[':// ���鿪ʼ
        return parseList();
      default:// �����������֣��������ͣ�null
        return parseElse();
    }
  }

  private JsonString parseString(){
    JsonString o = null;
    if('\''==c){// �����ſ�ʼ��
      next();// ������ʼ�ĵ�����
      o = new JsonString(readIgnoreSlash('\''));
    }else if('"'==c){ // ˫���ſ�ʼ��
      next();// ������ʼ��˫����
      o = new JsonString(readIgnoreSlash('"'));
    }
    next();// ������������
    if(null!=note&&null!=o){
      o.setNote(note);
      note = null;
    }
    return o;
  }

  private IJsonElement parseElse(){
    IJsonElement o = null;
    String s = readUntil(new int[]{' ',',','}',']'});
    s = s.trim();
    if("null".equals(s)){
      o = null;
    }else if("true".equals(s)){
      o = new JsonBool(true);
    }else if("false".equals(s)){
      o = new JsonBool(false);
    }else if(s.endsWith("f")||s.endsWith("F")){
      o = new JsonNumber(new Float(s));
    }else if(s.endsWith("d")||s.endsWith("D")){
      o = new JsonNumber(new Double(s));
    }else if(s.endsWith("l")||s.endsWith("L")){
      o = new JsonNumber(Long.parseLong(s.substring(0,s.length()-1)));
    }else{
      try{
        o = new JsonNumber(new BigDecimal(s));
      }catch(NumberFormatException e){
        o = new JsonString(orgs);
      }
    }
    if(null!=note&&null!=o){
      o.setNote(note);
      note = null;
    }
    return o;
  }

  private String readIgnoreSlash(int endTag){
    StringBuilder s = new StringBuilder();
    List<Integer> slash = new ArrayList<Integer>();
    while(-1!=c){
      if(c==RIGHT_SLASH){
        slash.add(c);
      }else{
        if(endTag==c){
          if(slash.size()%2==0){
            break;
          }
        }
        slash.clear();
      }
      s.append((char)c);
      next();
    }
    return s.toString().replace("\\\\","\\");
  }

  private String readUntil(int endTag){
    return readUntil(new int[]{endTag});
  }

  private String readUntil(int[] endTags){
    StringBuilder s = new StringBuilder();
    outer:while(-1!=c){
      for(int t:endTags){
        if(t==c){
          break outer;
        }
      }
      s.append((char)c);
      next();
    }
    return s.toString();
  }

  private void parseMapItem(JsonMap o){
    String key = "";
    skip();// ��������������ַ�
    if('}'==c){// ������������ֹ�������������ɷ���
      return;
    }
    if('\''==c){ // ������Ե����ſ�ʼ
      next(); // ������ʼ�ĵ�����
      key = readIgnoreSlash('\''); // һֱ����ֱ�����������ĵ����ŲŽ���
      next(); // ����������
      skip(); // �����������ַ�
      if(':'!=c){ // ����Ӧ���и�ð��
        throw new JsonFormatException(L.get("json.need_colon")+(char)c+L.get("json.pos")+pos+"\r\n"+orgs);
      }
      next(); // ����ð��
    }else if('"'==c){ // �������˫���ſ�ʼ
      next(); // ������ʼ��˫����
      key = readIgnoreSlash('"'); // һֱ����ֱ������������˫���ŲŽ���
      next(); // ����˫����
      skip(); // ������������ַ�
      if(':'!=c){ // ����Ӧ���и�ð��
        throw new JsonFormatException(L.get("json.need_colon")+(char)c+L.get("json.pos")+pos+"\r\n"+orgs);
      }
      next(); // ����ð��
    }else{ // ���ֱ�ӿ�ʼ
      key = readUntil(':'); // һֱ����ֱ������ð�ŲŽ���
      key = key.trim();
      next();// ����ð��
    }
    IJsonElement el = parseElement();
    if(null!=note&&null!=el){
      el.setNote(note);
      note = null;
    }
    o.set(key,el);
  }

  private JsonMap parseMap(){
    JsonMap o = new JsonMap();
    if(null!=note){
      o.setNote(note);
      note = null;
    }
    next();// ������ʼ����{
    parseMapItem(o);// ��������ĵ�һ�����ԣ�����еĻ�
    skip();
    while(','==c){// ��������ֵܽ���
      next();// �����������,
      parseMapItem(o);
      skip();
    }
    if('}'!=c){ // ����Ӧ���и�������}
      throw new JsonFormatException(L.get("json.need_right_brace")+(char)c+L.get("json.pos")+pos+"\r\n"+orgs);
    }
    next();// ����������}
    return o;
  }

  private void parseListItem(JsonList l){
    skip();
    l.add(parseElement());
  }

  private JsonList parseList(){
    JsonList l = new JsonList();
    if(null!=note){
      l.setNote(note);
      note = null;
    }
    next();// ������ʼ����[
    skip();
    if(']'==c){
      next();
      return l;
    }
    parseListItem(l);
    skip();
    while(','==c){// ��������ֵܽ���
      next();// �����������,
      parseListItem(l);
      skip();
    }
    next();// ������������]
    return l;
  }

  /**
   * ��ȡ��һ���ַ�
   */
  private void next(){
    try{
      c = reader.read();
      pos++;
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
    if('/'==c){
      next();
      if(-1==c)
        return;
      if('/'==c){ // ���Ե���ע��
        StringBuilder s = new StringBuilder();
        do{
          next();
          s.append((char)c);
        }while('\r'!=c&&'\n'!=c&&-1!=c);
        note = s.toString();
        note = note.substring(0,note.length()-1);
        skip();
      }else if('*'==c){ // ���Զ���ע��
        StringBuilder s = new StringBuilder();
        while(-1!=c){
          next();
          s.append((char)c);
          if('*'==c){
            next();
            s.append((char)c);
            if('/'==c){
              note = s.toString();
              note = note.substring(0,note.length()-2);
              break;
            }
          }
        }
        skip();
      }
    }
  }
}