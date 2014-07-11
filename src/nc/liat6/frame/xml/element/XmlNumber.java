package nc.liat6.frame.xml.element;

/**
 * XML�������ͽڵ�
 * 
 * @author 6tail
 * 
 */
public class XmlNumber extends AbstractElement{

  private static final long serialVersionUID = -6485047817119697116L;
  private Number n;

  public XmlNumber(Number n){
    set(n);
  }

  public XmlType type(){
    return XmlType.NUMBER;
  }

  public String toString(){
    return n+"";
  }

  /**
   * ���ýڵ�ֵ
   * 
   * @param n �ڵ�ֵ
   */
  public void set(Number n){
    this.n = n;
  }

  /**
   * ��ȡ�ڵ�ֵ
   * 
   * @return �ڵ�ֵ
   */
  public Number value(){
    return n;
  }

  public XmlNumber toXmlNumber(){
    return this;
  }
}
