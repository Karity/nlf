package nc.liat6.frame.xml.element;

import java.io.Serializable;
import java.util.Map;

/**
 * XML����ӿ�
 * 
 * @author 6tail
 * 
 */
public interface IXmlElement extends Serializable{

  /**
   * ����
   * 
   * @return ����
   */
  public XmlType type();

  /**
   * ת��Ϊ{}����
   * 
   * @return
   */
  public XmlMap toXmlMap();

  /**
   * ת��Ϊ[]����
   * 
   * @return
   */
  public XmlList toXmlList();

  /**
   * ת��Ϊ����ֵ
   * 
   * @return
   */
  public XmlBool toXmlBool();

  /**
   * ת��Ϊ��������
   * 
   * @return
   */
  public XmlNumber toXmlNumber();

  /**
   * ת��Ϊ�ַ�������
   * 
   * @return
   */
  public XmlString toXmlString();

  /**
   * ����·����ȡ�ڵ�ֵ,ֻ��XmlMap��XmlList��ʵ�ָ÷���
   * 
   * @param path 
   *          ·������<a><b>1</b></a>��select("a.b")���Ϊ1����<as><a>1</a><a>2</a></as>��select("0")���Ϊ1������޷��ҵ�ָ���ڵ�
   *          ������null
   * @return �ڵ�ֵ
   */
  public IXmlElement select(String path);

  /**
   * ��ýڵ�����
   * 
   * @return �ڵ�����
   */
  public String getName();

  /**
   * ���ýڵ�����
   * 
   * @param name �ڵ�����
   */
  public void setName(String name);

  /**
   * ��ȡ�ڵ�ע��
   * 
   * @return ע��
   */
  public String getNote();

  /**
   * ���ýڵ�ע��
   * 
   * @param note ע��
   */
  public void setNote(String note);

  /**
   * ��ȡ�ڵ����ԣ����������������������null
   * 
   * @param attributeName ������
   * @return ����ֵ
   */
  public String getAttribute(String attributeName);

  /**
   * ���ýڵ�����
   * 
   * @param attributeName ������
   * @param value ����ֵ
   */
  public void setAttribute(String attributeName,String value);

  /**
   * ��ȡ�ڵ���������
   * 
   * @return ��������
   */
  public Map<String,String> getAttributes();

  /**
   * ���ǽڵ���������
   * 
   * @param attributes ��������
   */
  public void setAttributes(Map<String,String> attributes);
}
