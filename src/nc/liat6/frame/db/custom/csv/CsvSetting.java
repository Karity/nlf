package nc.liat6.frame.db.custom.csv;

import nc.liat6.frame.db.setting.impl.SuperDbSetting;

/**
 * CSV��������
 * 
 * @author 6tail
 * 
 */
public class CsvSetting extends SuperDbSetting{

  private static final long serialVersionUID = 8889847573386316725L;
  /** Ĭ���������� */
  public static final String DEFAULT_TYPE = "csv";
  /** Ĭ�����ݿ����� */
  public static final String DEFAULT_DB_TYPE = "csv";
  /** Ĭ���������� */
  public static final String DEFAULT_DRIVER = CsvDriver.class.getName();

  public CsvSetting(){
    type = DEFAULT_TYPE;
    dbType = DEFAULT_DB_TYPE;
    driver = DEFAULT_DRIVER;
  }
}