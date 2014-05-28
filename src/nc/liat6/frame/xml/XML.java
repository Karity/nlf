package nc.liat6.frame.xml;

import nc.liat6.frame.xml.element.IXmlElement;
import nc.liat6.frame.xml.parser.BeanParser;
import nc.liat6.frame.xml.parser.XMLParser;
import nc.liat6.frame.xml.wrapper.XMLWrapper;

/**
 * XMLת������Ĭ�Ͽ����ϸ�ģʽ
 * <p>
 * ת������ѭ����Լ����
 * <ul>
 * <li>�����ϸ�ģʽ���ӽڵ�Ϊ���Եģ����ڵ�����type="bean"���ԡ�</li>
 * <li>�ӽڵ�Ϊ����ģ����ڵ��������type="list"���ԡ�</li>
 * </ul>
 * </p>
 * 
 * @author 6tail
 * 
 */
public class XML{

	/** Ĭ�ϸ��ڵ� */
	public static final String DEFAULT_ROOT_TAG = "data";

	/** �����õ�ȫ�ָ��ڵ� */
	public static String ROOT_TAG = DEFAULT_ROOT_TAG;

	/** �Ƿ񼫼򣨲����������У���Ĭ������ */
	public static final boolean DEFAULT_TINY = true;

	/** �Ƿ񼫼򣨲����������У���ȫ������ */
	public static boolean TINY = DEFAULT_TINY;

	/** Ĭ�Ͽ����ϸ�ģʽ */
	public static final boolean DEFAULT_STRICT = true;

	/** �Ƿ����ϸ�ģʽ��ȫ������ */
	public static boolean STRICT = DEFAULT_STRICT;

	private XML(){}

	/**
	 * ����ȫ�ָ��ڵ㣬������ת��ΪXML�ַ���
	 * 
	 * @param o ����
	 * @return XML�ַ���
	 */
	public static String toXML(Object o){
		return toXML(o,TINY);
	}

	/**
	 * ����ȫ�ָ��ڵ㣬������ת��ΪXML�ַ���
	 * 
	 * @param o ����
	 * @param tiny �Ƿ��Ǽ��򣨲����������У�
	 * @return XML�ַ���
	 */
	public static String toXML(Object o,boolean tiny){
		return toXML(o,tiny,ROOT_TAG);
	}

	/**
	 * ������ת��ΪXML�ַ���
	 * 
	 * @param o ����
	 * @param tiny �Ƿ��Ǽ��򣨲����������У�
	 * @param rootTag �Զ�����ڵ�
	 * @return XML�ַ���
	 */
	public static String toXML(Object o,boolean tiny,String rootTag){
		return toXML(o,tiny,rootTag,STRICT);
	}

	/**
	 * ������ת��ΪXML�ַ���
	 * 
	 * @param o ����
	 * @param tiny �Ƿ��Ǽ��򣨲����������У�
	 * @param rootTag �Զ�����ڵ�
	 * @param strict �Ƿ����ϸ�ģʽ������������鸸�ڵ�ǿ�����type="bean"����
	 * @return XML�ַ���
	 */
	public static String toXML(Object o,boolean tiny,String rootTag,boolean strict){
		StringBuilder s = new StringBuilder();
		s.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		if(!tiny){
			s.append("\r\n");
		}
		s.append(new XMLWrapper(tiny,strict).wrap(o,rootTag));
		return s.toString();
	}

	/**
	 * ��XML�ַ���ת��Ϊ���󣬿��ܻᶪʧ�ڵ����ԺͲ���ע��
	 * 
	 * @param s XML�ַ���
	 * @return ����
	 */
	public static <T>T toBean(String s){
		return toBean(fromXML(s));
	}

	/**
	 * ��XMLת��Ϊͨ�÷�װ
	 * 
	 * @param s XML�ַ���
	 * @return ����
	 */
	public static IXmlElement fromXML(String s){
		return new XMLParser().parse(s);
	}

	/**
	 * ��XMLͨ�÷�װת��Ϊ���󣬿��ܻᶪʧ�ڵ����ԺͲ���ע��
	 * 
	 * @param xe
	 * @return
	 */
	public static <T>T toBean(IXmlElement xe){
		return new BeanParser().parse(xe);
	}

}
