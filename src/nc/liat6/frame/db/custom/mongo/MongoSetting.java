package nc.liat6.frame.db.custom.mongo;

import nc.liat6.frame.db.setting.impl.SuperDbSetting;

/**
 * MONGO��������
 * 
 * @author 6tail
 * 
 */
public class MongoSetting extends SuperDbSetting{

  private static final long serialVersionUID = 5902769339352767337L;

  /** Ĭ���������� */
  public static final String DEFAULT_TYPE = "mongo";
  
  public MongoSetting(){
    type = DEFAULT_TYPE;
  }
}
