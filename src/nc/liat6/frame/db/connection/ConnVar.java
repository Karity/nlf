package nc.liat6.frame.db.connection;

import nc.liat6.frame.db.DbType;
import nc.liat6.frame.db.setting.ISetting;

/**
 * ���ӱ���
 * 
 * @author 6tail
 * 
 */
public class ConnVar{

	private int level = 0;

	/** �������� */
	private ISetting setting;

	/** �������� */
	private IConnection connection;

	/** ���ݿ����� */
	private DbType dbType;

	/** ���� */
	private String alias;

	public int getLevel(){
		return level;
	}

	public void setLevel(int level){
		this.level = level;
	}

	public ISetting getSetting(){
		return setting;
	}

	public void setSetting(ISetting setting){
		this.setting = setting;
	}

	public IConnection getConnection(){
		return connection;
	}

	public void setConnection(IConnection connection){
		this.connection = connection;
	}

	public DbType getDbType(){
		return dbType;
	}

	public void setDbType(DbType dbType){
		this.dbType = dbType;
	}

	public String getAlias(){
		return alias;
	}

	public void setAlias(String alias){
		this.alias = alias;
	}

	public boolean equals(Object o){
		if(null == o){
			return false;
		}
		if(!(o instanceof ConnVar)){
			return false;
		}
		ConnVar cv = (ConnVar)o;
		if(null == cv.getAlias()){
			if(null == alias){
				return true;
			}else{
				return false;
			}
		}
		return cv.getAlias().equals(alias);
	}

}
