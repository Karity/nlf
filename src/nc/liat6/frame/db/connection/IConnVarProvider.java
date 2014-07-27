package nc.liat6.frame.db.connection;

import nc.liat6.frame.db.setting.IDbSetting;

/**
 * ���ӱ����ṩ���ӿ�
 * 
 * @author 6tail
 * 
 */
public interface IConnVarProvider{

  /**
   * �Ƿ�֧��ָ����������
   * 
   * @param connType ��������
   * @return true/false ֧��/��֧��
   */
  public boolean support(String connType);

  /**
   * ��ȡ���ӱ���
   * 
   * @return ���ӱ���
   */
  public ConnVar getConnVar();

  /**
   * ������������
   * 
   * @param setting ��������
   */
  public void initSetting(IDbSetting setting);
}
