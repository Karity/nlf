package nc.liat6.frame.context;

/**
 * ������
 * 
 * @author liat6
 * 
 */
public class Context{

  /** �����ı��� */
  private final static ThreadLocal<Var> VAR = new ThreadLocal<Var>(){

    public Var initialValue(){
      return new Var();
    }
  };

  protected Context(){}

  /**
   * ���ñ���
   * 
   * @param k ��
   * @param v ֵ
   */
  public synchronized static void set(Object k,Object v){
    VAR.get().set(k,v);
  }

  /**
   * ��ȡ����
   * 
   * @param k ��
   * @return ֵ
   */
  @SuppressWarnings("unchecked")
  public synchronized static <T>T get(Object k){
    return (T)VAR.get().get(k);
  }

  /**
   * �Ƴ�����
   * 
   * @param k ��
   */
  public synchronized static void remove(Object k){
    VAR.get().remove(k);
  }

  /**
   * �Ƿ���ڱ���
   * 
   * @param k ��
   * @return ֵ
   */
  public static boolean contains(Object k){
    return VAR.get().contains(k);
  }

  /**
   * ��������ı���
   */
  public synchronized static void clear(){
    VAR.get().clear();
  }
}