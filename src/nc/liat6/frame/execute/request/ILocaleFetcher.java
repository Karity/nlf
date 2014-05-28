package nc.liat6.frame.execute.request;

import java.util.Locale;


/**
 * �ͻ���locale��ȡ�ӿ�
 * @author 6tail
 *
 */
public interface ILocaleFetcher{
	
	/**
	 * ��ȡ�ͻ���locale
	 * @return locale
	 */
	public Locale getLocale();
	
	/**
	 * ��ȡ�ͻ���locale�ַ���
	 * @return locale�ַ�������zh-CN
	 */
	public String getLocaleString();
	
	/**
	 * ����locale
	 * @param locale locale
	 */
	public void setLocale(Locale locale);
	
	/**
	 * ����locale
	 * @param locale locale�ַ�������zh-CN
	 */
	public void setLocale(String locale);

}
