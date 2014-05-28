package nc.liat6.frame.proxy;

import nc.liat6.frame.aop.IAopInterceptor;

/**
 * ��̬����ӿ�
 * 
 * @author 6tail
 * 
 */
public interface IProxy{

	/** AOP���������� */
	public static final String AOP_INTERCEPTOR_LIST_NAME = "$NlfAopInterceptors";

	/** �������׺ */
	public static final String SUFFIX = "$NlfProxy";

	/**
	 * ����ָ����Ĵ������
	 * @param superClass ����
	 * @return �������
	 */
	public <T>T create(Class<?> superClass);

	/**
	 * ��ȡԭ����
	 * @return ԭ����
	 */
	public <T>T getOBean();

	/**
	 * ���AOP������
	 * @param ai AOP������
	 */
	public void addAopInterceptor(IAopInterceptor ai);

}
