package nc.liat6.frame.aop;

import java.lang.reflect.Method;
import java.util.List;
import nc.liat6.frame.util.Stringer;

/**
 * ����AOP������
 * 
 * @author liat6
 * 
 */
public abstract class AbstractManager implements IAopInterceptor{

  private static final String[] TAG_DROP = new String[]{"\r","\n"," "};
  private static final String TAG_SPLIT = ",";
  /** ��Ӧ�����б� */
  private String klass;
  /** ��Ӧ�ķ����б� */
  private String method;
  /** ��Ҫ����������б� */
  protected List<String> klasses;
  /** ��Ҫ����ķ������б� */
  protected List<String> methods;

  public void setMethod(String method){
    this.method = Stringer.replace(method,TAG_DROP,"");
    this.methods = Stringer.list(this.method,TAG_SPLIT);
  }

  public void setKlass(String klass){
    this.klass = Stringer.replace(klass,TAG_DROP,"");
    this.klasses = Stringer.list(this.klass,TAG_SPLIT);
  }

  public AbstractManager(String klass,String method){
    setKlass(klass);
    setMethod(method);
  }

  /**
   * ����ִ�к����
   * 
   * @param o ����
   * @param m ����
   * @param args ����
   */
  public abstract void after(Object o,Method m,Object[] args);

  /**
   * ����ִ��ǰ����
   * 
   * @param o ����
   * @param m ����
   * @param args ����
   */
  public abstract void before(Object o,Method m,Object[] args);

  /**
   * ����ִ��ʧ�ܺ����
   * 
   * @param o ����
   * @param m ����
   * @param args ����
   * @param e �쳣
   */
  public abstract void failed(Object o,Method m,Object[] args,Throwable e);

  /**
   * ����ִ�гɹ������
   * 
   * @param o ����
   * @param m ����
   * @param args ����
   * @param e �쳣
   */
  public abstract void succeed(Object o,Method m,Object[] args);
}
