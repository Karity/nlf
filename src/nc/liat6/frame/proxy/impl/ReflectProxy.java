package nc.liat6.frame.proxy.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import nc.liat6.frame.aop.IAopInterceptor;
import nc.liat6.frame.exception.NlfException;
import nc.liat6.frame.proxy.IProxy;

/**
 * JDK��̬����
 * @author 6tail
 *
 */
public class ReflectProxy implements IProxy,InvocationHandler {

	/** ԭʼ���� */
	private Object oBean;

	/** AOP�������б� */
	private List<IAopInterceptor> ais = new ArrayList<IAopInterceptor>();

	@SuppressWarnings("unchecked")
	public <T> T create(Class<?> superClass) {
		try {
			oBean = superClass.newInstance();
			T o = (T) Proxy.newProxyInstance(superClass.getClassLoader(),
					superClass.getInterfaces(), this);
			return o;
		} catch (Exception e) {
			throw new NlfException(e);
		}
	}

	/**
	 * ��ȡԭʼ����
	 * 
	 * @return ԭʼ����
	 */
	@SuppressWarnings("unchecked")
	public <T> T getOBean() {
		return (T) oBean;
	}

	/**
	 * ���AOP������
	 * 
	 * @param ai
	 */
	public void addAopInterceptor(IAopInterceptor ai) {
		ais.add(ai);
	}

	public Object invoke(Object o, Method m, Object[] args) throws Throwable {
		for (IAopInterceptor ai : ais) {
			ai.before(oBean, m, args);
		}
		Object r = null;
		try {
			r = m.invoke(oBean, args);
			for (int i = ais.size() - 1; i >= 0; i--) {
				IAopInterceptor ai = ais.get(i);
				ai.succeed(oBean, m, args);
			}
		} catch (Throwable e) {
			for (int i = ais.size() - 1; i >= 0; i--) {
				IAopInterceptor ai = ais.get(i);
				ai.failed(oBean, m, args, e);
			}
			throw e;
		} finally {
			for (int i = ais.size() - 1; i >= 0; i--) {
				IAopInterceptor ai = ais.get(i);
				ai.after(oBean, m, args);
			}
		}
		return r;
	}

}
