package nc.liat6.frame.xml.wrapper;


/**
 * XML��װ�ӿ�
 * @author 6tail
 *
 */
public interface IXMLWrapper {
	
	/**
	 * ��װ
	 * @param o ����
	 * @param tag ��ǩ
	 * @param level �㼶
	 * @return �ַ���
	 */
	public String wrap(Object o,String tag,int level);

}
