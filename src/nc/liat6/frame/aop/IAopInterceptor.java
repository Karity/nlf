package nc.liat6.frame.aop;

import java.lang.reflect.Method;

/**
 * AOP�������ؽӿ�
 * @author liat6
 *
 */
public interface IAopInterceptor {
	
	/**
	 * �����������ִ��
	 * @param o ����
	 * @param m ����
	 * @param args ����
	 */
	public void failed(Object o, Method m, Object[] args,Throwable e);
	
	/**
	 * ����ִ�гɹ���ִ��
	 * @param o ����
	 * @param m ����
	 * @param args ����
	 */
	public void succeed(Object o, Method m, Object[] args);
	
	/**
	 * ����ִ��ǰִ��
	 * @param o ����
	 * @param m ����
	 * @param args ����
	 */
	public void before(Object o, Method m, Object[] args);
	
	/**
	 * ����ִ�к�ִ��
	 * @param o ����
	 * @param m ����
	 * @param args ����
	 */
	public void after(Object o, Method m, Object[] args);
	
	/**
	 * �Ƿ�����ִ������
	 * @param o ����
	 * @param m ����
	 * @return true/false ����/������
	 */
	public boolean check(Object o,Method m);
	
}