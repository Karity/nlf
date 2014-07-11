package nc.liat6.frame.xml.element;

import java.math.BigDecimal;

/**
 * XML�ַ������ͽڵ�
 * 
 * @author 6tail
 * 
 */
public class XmlString extends AbstractElement{

  private static final long serialVersionUID = 7645328175874847771L;
  private String o;

  public XmlString(){}

  public XmlString(String o){
    this.o = o;
  }

  /**
   * ���ýڵ�ֵ
   * 
   * @param s �ڵ�ֵ
   */
  public void set(String s){
    o = s;
  }

  /**
   * ��ȡ�ڵ�ֵ
   * 
   * @return �ڵ�ֵ
   */
  public String toString(){
    return o;
  }

  public XmlType type(){
    return XmlType.STRING;
  }

  public XmlString toXmlString(){
    return this;
  }

  public XmlNumber toXmlNumber(){
    XmlNumber n = new XmlNumber(new BigDecimal(o));
    return n;
  }
}
