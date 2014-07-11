package nc.liat6.frame.db.custom.mongo;

import nc.liat6.frame.db.config.DbConfig;
import nc.liat6.frame.db.config.IDbConfigProvider;

/**
 * Sybase���ݿ������ṩ��
 * 
 * @author 6tail
 * 
 */
public class MongoConfigProvider implements IDbConfigProvider{

  @Override
  public DbConfig getDbConfig(){
    DbConfig dc = new DbConfig();
    dc.setDbType("MONGO");
    dc.setDriverClassName(MongoDriver.class.getName());
    dc.setUrl("?:?");
    dc.setSuperInterfaceName(IMongo.class.getName());
    return dc;
  }
}
