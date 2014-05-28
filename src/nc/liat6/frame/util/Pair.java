package nc.liat6.frame.util;

import java.io.Serializable;

/**
 * �ַ�����ֵ��
 * 
 * @author 6tail
 * 
 */
public class Pair implements Serializable{

	private static final long serialVersionUID = 3519595853819456600L;

	/** �� */
	private String key;
	
	/** ֵ */
	private String value;

	public Pair(){}

	public Pair(String key,String value){
		this.key = key;
		this.value = value;
	}

	/**
	 * ��ȡ��
	 * @return ��
	 */
	public String getKey(){
		return key;
	}

	/**
	 * ���ü�
	 * @param key ��
	 */
	public void setKey(String key){
		this.key = key;
	}

	/**
	 * ��ȡֵ
	 * @return ֵ
	 */
	public String getValue(){
		return value;
	}

	/**
	 * ����ֵ
	 * @param value ֵ
	 */
	public void setValue(String value){
		this.value = value;
	}

}
