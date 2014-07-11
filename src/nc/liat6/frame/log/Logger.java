package nc.liat6.frame.log;

import java.util.List;
import nc.liat6.frame.Factory;

/**
 * ��־����
 * 
 * @author 6tail
 * 
 */
public class Logger{

  /** ������ */
  private static ILogAdapter adapter;
  static{
    List<String> l = Factory.getImpls(ILogAdapter.class.getName());
    for(String c:l){
      try{
        ILogAdapter a = (ILogAdapter)Class.forName(c).newInstance();
        if(a.isSupported()){
          adapter = a;
          break;
        }
      }catch(Exception e){}
    }
  }

  private Logger(){}

  /**
   * ��ȡ��־�ӿ�
   * 
   * @return ��־�ӿ�
   */
  public static ILog getLog(){
    StackTraceElement[] sts = Thread.currentThread().getStackTrace();
    return adapter.getLog(sts[2].getClassName());
  }

  /**
   * ��ȡ��־�ӿ�
   * 
   * @param klass ����
   * @return ��־�ӿ�
   */
  public static ILog getLog(String klass){
    return adapter.getLog(klass);
  }

  /**
   * ��ȡ��־�ӿ�
   * 
   * @param c ��
   * @return ��־�ӿ�
   */
  public static ILog getLog(Class<?> c){
    return adapter.getLog(c.getName());
  }
}
