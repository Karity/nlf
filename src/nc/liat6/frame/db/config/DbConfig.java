package nc.liat6.frame.db.config;

/**
 * ���ݿ�����
 * 
 * @author 6tail
 * 
 */
public class DbConfig{

  /** ���ݿ����� */
  private String dbType;
  /** �������� */
  private String driverClassName;
  /** �����ӿ��� */
  private String superInterfaceName;
  /** �����ַ��� */
  private String url;
  /** ���ڲ��Ե�SQL��� */
  private String testSql;

  public String getDbType(){
    return dbType;
  }

  public void setDbType(String dbType){
    this.dbType = dbType;
  }

  public String getDriverClassName(){
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName){
    this.driverClassName = driverClassName;
  }

  public String getSuperInterfaceName(){
    return superInterfaceName;
  }

  public void setSuperInterfaceName(String superInterfaceName){
    this.superInterfaceName = superInterfaceName;
  }

  public String getUrl(){
    return url;
  }

  public void setUrl(String url){
    this.url = url;
  }

  public String getTestSql(){
    return testSql;
  }

  public void setTestSql(String testSql){
    this.testSql = testSql;
  }
}
