package nc.liat6.frame.web.config;

/**
 * WEB�������ӿ�
 * @author 6tail
 *
 */
public interface IWebManager{
	
	/**
	 * ִ��ʧ��ʱ�Ĵ���
	 * @param e �쳣
	 * @return ���ؽ��
	 */
	public Object failed(Throwable e);
	
	/**
	 * ִ��ǰ����
	 * @param path ����·��
	 * @return ��&����
	 */
	public ClassMethod before(String path);
	
	/**
	 * ִ�гɹ�����
	 */
	public void after();
	
	/**
	 * ���ؽ���Ĺ���
	 */
	public void filter();

}
