package nc.liat6.frame.db.setting;

import nc.liat6.frame.db.entity.Bean;

/**
 * ���������ṩ�ӿ�
 * 
 * @author 6tail
 * 
 */
public interface IDbSettingProvider{

  /**
   * �Ƿ�֧��ָ����������
   * 
   * @param type ��������
   * @return true/false ֧��/��֧��
   */
  public boolean support(String type);

  /**
   * ��ȡ��������
   * 
   * @param o �����ļ�ת����Bean
   * @return ��������
   */
  public IDbSetting getDbSetting(Bean o);
}
