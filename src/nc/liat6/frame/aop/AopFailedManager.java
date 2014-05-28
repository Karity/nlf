package nc.liat6.frame.aop;

import java.lang.reflect.Method;

/**
 * AOP����ʧ�ܺ������
 * 
 * @author liat6
 * 
 */
public abstract class AopFailedManager extends AopManager {

	public AopFailedManager(String klass,String method){
		super(klass,method);
	}
	
	/**
	 * ����ִ��ʧ�ܺ����
	 * @param o ����
	 * @param m ����
	 * @param args ����
	 * @param e �쳣
	 */
	public abstract void execute(Object o, Method m, Object[] args,Throwable e);
	
	@Override
	public void failed(Object o, Method m, Object[] args,Throwable e){
		if(check(o,m)) execute(o,m,args,e);
	}

	@Override
	public void before(Object o, Method m, Object[] args) {
		
	}

	@Override
	public void succeed(Object o, Method m, Object[] args) {
		
	}

	@Override
	public void after(Object o, Method m, Object[] args) {
		
	}

}