package nc.liat6.frame.log;

/**
 * ��־�������ӿ�
 * 
 * @author 6tail
 * 
 */
public interface ILogAdapter{

  /**
   * �Ƿ�֧�ָ���־����
   * 
   * @return true/false ֧��/��֧��
   */
  public boolean isSupported();

  /**
   * ��ȡ��־��¼��
   * 
   * @param klass ����
   * @return ��־��¼�ӿ�
   */
  public ILog getLog(String klass);
}
