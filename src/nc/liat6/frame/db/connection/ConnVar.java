package nc.liat6.frame.db.connection;

import nc.liat6.frame.db.setting.IDbSetting;

/**
 * ���ӱ���
 * 
 * @author 6tail
 * 
 */
public class ConnVar{

  private int level = 0;
  /** �������� */
  private IDbSetting setting;
  /** �������� */
  private IConnection connection;
  /** ���ݿ����� */
  private String dbType;
  /** ���� */
  private String alias;

  public int getLevel(){
    return level;
  }

  public void setLevel(int level){
    this.level = level;
  }

  public IDbSetting getSetting(){
    return setting;
  }

  public void setSetting(IDbSetting setting){
    this.setting = setting;
  }

  public IConnection getConnection(){
    return connection;
  }

  public void setConnection(IConnection connection){
    this.connection = connection;
  }

  public String getDbType(){
    return dbType;
  }

  public void setDbType(String dbType){
    this.dbType = dbType;
  }

  public String getAlias(){
    return alias;
  }

  public void setAlias(String alias){
    this.alias = alias;
  }

  public boolean equals(Object o){
    if(null==o){
      return false;
    }
    if(!(o instanceof ConnVar)){
      return false;
    }
    ConnVar cv = (ConnVar)o;
    if(null==cv.getAlias()){
      if(null==alias){
        return true;
      }else{
        return false;
      }
    }
    return cv.getAlias().equals(alias);
  }
}
