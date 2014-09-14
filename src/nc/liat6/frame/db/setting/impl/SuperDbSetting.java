package nc.liat6.frame.db.setting.impl;

import nc.liat6.frame.db.setting.IDbSetting;

/**
 * �������ó���
 * 
 * @author 6tail
 * 
 */
public abstract class SuperDbSetting implements IDbSetting{

  private static final long serialVersionUID = 2192392729884362173L;
  
  /** �������ͣ���jdbc��proxool��csv��mongo */
  protected String type;
  
  /** ���ӱ��� */
  protected String alias;
  
  /** ����URL */
  protected String url;
  
  /** �û��� */
  protected String user;
  
  /** ���� */
  protected String password;
  
  /** ������ */
  protected String driver;
  
  /** ���ݿ����ͣ���oracle��mysql��sqlserver */
  protected String dbType;
  
  /** ���ݿ�ʵ���� */
  protected String dbName;

  /**
   * ��ȡ��������
   * @return �������ͣ���jdbc��proxool��csv��mongo
   */
  public String getType(){
    return type;
  }

  /**
   * ������������
   * @param type �������ͣ���jdbc��proxool��csv��mongo
   */
  public void setType(String type){
    this.type = type;
  }

  /**
   * ��ȡ���ӱ���
   * @return ���ӱ���
   */
  public String getAlias(){
    return alias;
  }

  /**
   * �������ӱ���
   * @param alias ���ӱ���
   */
  public void setAlias(String alias){
    this.alias = alias;
  }

  /**
   * ��ȡ����URL
   * @return url
   */
  public String getUrl(){
    return url;
  }

  /**
   * ��������URL
   * @param url ����url
   */
  public void setUrl(String url){
    this.url = url;
  }

  /**
   * ��ȡ�û���
   * @return �û���
   */
  public String getUser(){
    return user;
  }

  /**
   * �����û���
   * @param user �û���
   */
  public void setUser(String user){
    this.user = user;
  }

  /**
   * ��ȡ����
   * @return ����
   */
  public String getPassword(){
    return password;
  }

  /**
   * ��������
   * @param password ����
   */
  public void setPassword(String password){
    this.password = password;
  }

  /**
   * ��ȡ��������
   * @return ��������
   */
  public String getDriver(){
    return driver;
  }

  /**
   * ������������
   * @param driver ��������
   */
  public void setDriver(String driver){
    this.driver = driver;
  }

  /**
   * ��ȡ���ݿ�����
   * @return ���ݿ����ͣ���oracle��mysql��sqlserver
   */
  public String getDbType(){
    return dbType;
  }

  /**
   * �������ݿ�����
   * @param dbType ���ݿ����ͣ���oracle��mysql��sqlserver
   */
  public void setDbType(String dbType){
    this.dbType = dbType;
  }

  /**
   * ��ȡ���ݿ�ʵ����
   * @return ���ݿ�ʵ����
   */
  public String getDbName(){
    return dbName;
  }

  /**
   * �������ݿ�ʵ����
   * @param dbName
   */
  public void setDbName(String dbName){
    this.dbName = dbName;
  }
}