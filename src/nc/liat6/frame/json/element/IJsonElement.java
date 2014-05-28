package nc.liat6.frame.json.element;

import java.io.Serializable;

/**
 * JSON����ӿ�
 * @author 6tail
 *
 */
public interface IJsonElement extends Serializable{

	/**
	 * ����
	 * @return ����
	 */
	public JsonType type();

	/**
	 * ת��Ϊ{}����
	 * @return
	 */
	public JsonMap toJsonMap();

	/**
	 * ת��Ϊ[]����
	 * @return
	 */
	public JsonList toJsonList();
	
	/**
	 * ת��Ϊ����ֵ
	 * @return
	 */
	public JsonBool toJsonBool();
	
	/**
	 * ת��Ϊ��������
	 * @return
	 */
	public JsonNumber toJsonNumber();
	
	/**
	 * ת��Ϊ�ַ�������
	 * @return
	 */
	public JsonString toJsonString();
	
	/**
	 * ����·����ȡ�ڵ�ֵ,ֻ��JsonMap��JsonList��ʵ�ָ÷���
	 * @param path ·������{'a':{'b':1}}��select("a.b")���Ϊ1����[1,2,3,4]��select("0")���Ϊ1������޷��ҵ�ָ���ڵ㣬����null
	 * @return �ڵ�ֵ
	 */
	public IJsonElement select(String path);
	
	/**
	 * ����ע��
	 * @param note ע��
	 */
	public void setNote(String note);
	
	/**
	 * ��ȡע��
	 * @return ע��
	 */
	public String getNote();

}
