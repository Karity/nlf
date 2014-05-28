package nc.liat6.frame.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nc.liat6.frame.exception.NlfException;

/**
 * ���乤��
 * @author 6tail
 *
 */
public class Reflector{

	private Reflector(){}
	
	/**
	 * ��ȡ���ʵ��
	 * @param klass ����
	 * @return ʵ������
	 */
	public static Object newInstance(String klass){
		try{
			Class<?> c = Class.forName(klass);
			return newInstance(c);
		}catch(ClassNotFoundException e){
			throw new NlfException(e);
		}
	}
	
	/**
	 * ��ȡ���ʵ��
	 * @param o ��
	 * @return ʵ������
	 */
	public static Object newInstance(Class<?> o){
		try{
			return o.newInstance();
		}catch(InstantiationException e){
			throw new NlfException(e);
		}catch(IllegalAccessException e){
			throw new NlfException(e);
		}
	}

	/**
	 * ִ�з���
	 * @param o ����
	 * @param method ������
	 * @param types ��������
	 * @param args ����ֵ
	 * @return ִ�н��
	 */
	public static Object execute(Object o,String method,Class<?>[] types,Object[] args){
		try{
			Class<?> c = o.getClass();
			Method m = c.getMethod(method,types);
			return m.invoke(o,args);
		}catch(SecurityException e){
			throw new NlfException(e);
		}catch(NoSuchMethodException e){
			throw new NlfException(e);
		}catch(IllegalArgumentException e){
			throw new NlfException(e);
		}catch(IllegalAccessException e){
			throw new NlfException(e);
		}catch(InvocationTargetException e){
			throw new NlfException(e);
		}

	}

	/**
	 * ִ�з���
	 * @param klass ����
	 * @param method ������
	 * @param types ��������
	 * @param args ����ֵ
	 * @param isStatic �Ƿ�̬����
	 * @return ���ؽ��
	 */
	private static Object execute(String klass,String method,Class<?>[] types,Object[] args,boolean isStatic){
		try{
			Class<?> c = Class.forName(klass);
			return execute(c,method,types,args,isStatic);
		}catch(ClassNotFoundException e){
			throw new NlfException(e);
		}
	}

	/**
	 * ִ�з���
	 * @param klass ��
	 * @param method ������
	 * @param types ��������
	 * @param args ����ֵ
	 * @param isStatic �Ƿ�̬����
	 * @return ���ؽ��
	 */
	private static Object execute(Class<?> klass,String method,Class<?>[] types,Object[] args,boolean isStatic){
		try{
			Method m = klass.getMethod(method,types);
			return m.invoke(isStatic?klass:klass.newInstance(),args);
		}catch(SecurityException e){
			throw new NlfException(e);
		}catch(NoSuchMethodException e){
			throw new NlfException(e);
		}catch(IllegalArgumentException e){
			throw new NlfException(e);
		}catch(IllegalAccessException e){
			throw new NlfException(e);
		}catch(InvocationTargetException e){
			throw new NlfException(e);
		}catch(InstantiationException e){
			throw new NlfException(e);
		}
	}

	/**
	 * ���÷Ǿ�̬����
	 * @param klass ����
	 * @param method ������
	 * @param types ��������
	 * @param args ����ֵ
	 * @return ���ؽ��
	 */
	public static Object execute(String klass,String method,Class<?>[] types,Object[] args){
		return execute(klass,method,types,args,false);
	}

	/**
	 * ���÷Ǿ�̬����
	 * @param klass ��
	 * @param method ������
	 * @param types ��������
	 * @param args ����ֵ
	 * @return ���ؽ��
	 */
	public static Object execute(Class<?> klass,String method,Class<?>[] types,Object[] args){
		return execute(klass,method,types,args,false);
	}
	
	/**
	 * ���þ�̬����
	 * @param klass ����
	 * @param method ������
	 * @param types ��������
	 * @param args ����ֵ
	 * @return ���ؽ��
	 */
	public static Object executeStatic(String klass,String method,Class<?>[] types,Object[] args){
		return execute(klass,method,types,args,true);
	}

	/**
	 * ���þ�̬����
	 * @param klass ��
	 * @param method ������
	 * @param types ��������
	 * @param args ����ֵ
	 * @return ���ؽ��
	 */
	public static Object executeStatic(Class<?> klass,String method,Class<?>[] types,Object[] args){
		return execute(klass,method,types,args,true);
	}
	
	/**
	 * ���÷Ǿ�̬�޲η���
	 * @param klass ����
	 * @param method ������
	 * @return ���
	 */
	public static Object execute(String klass,String method){
		return execute(klass,method,new Class[0],new Object[0],false);
	}
	
	/**
	 * ���÷Ǿ�̬�޲η���
	 * @param klass ��
	 * @param method ������
	 * @return ���
	 */
	public static Object execute(Class<?> klass,String method){
		return execute(klass.getName(),method);
	}
	
	/**
	 * �����޲η���
	 * @param o ����
	 * @param method ������
	 * @return ���
	 */
	public static Object execute(Object o,String method){
		return execute(o,method,new Class[0],new Object[0]);
	}

}
