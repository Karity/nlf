package nc.liat6.frame.db.setting.impl;

/**
 * JDBC��������
 * 
 * @author 6tail
 * 
 */
public class JdbcSetting extends SuperDbSetting{

  private static final long serialVersionUID = 5902760339352767337L;
  /** Ĭ���������� */
  public static final String DEFAULT_TYPE = "jdbc";

  public JdbcSetting(){
    type = DEFAULT_TYPE;
  }
}