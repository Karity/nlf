package nc.liat6.frame.aop;

import java.lang.reflect.Method;

/**
 * AOP�������ú������
 * 
 * @author liat6
 * 
 */
public abstract class AopAfterManager extends AopManager {

	public AopAfterManager(String klass,String method){
		super(klass,method);
	}
	
	/**
	 * ����ִ�к����
	 * @param o ����
	 * @param m ����
	 * @param args ����
	 */
	public abstract void execute(Object o, Method m, Object[] args);
	
	@Override
	public void after(Object o, Method m, Object[] args){
		if(check(o,m)) execute(o,m,args);
	}

	@Override
	public void before(Object o, Method m, Object[] args) {
		
	}

	@Override
	public void failed(Object o, Method m, Object[] args, Throwable e) {
		
	}

	@Override
	public void succeed(Object o, Method m, Object[] args) {
		
	}

}