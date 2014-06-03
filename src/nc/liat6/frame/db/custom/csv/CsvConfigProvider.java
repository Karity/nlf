package nc.liat6.frame.db.custom.csv;

import nc.liat6.frame.db.config.DbConfig;
import nc.liat6.frame.db.config.IDbConfigProvider;

/**
 * CSV���ݿ������ṩ��
 * @author 6tail
 *
 */
public class CsvConfigProvider implements IDbConfigProvider{

	@Override
	public DbConfig getDbConfig(){
		DbConfig dc = new DbConfig();
		dc.setDbType("CSV");
		dc.setDriverClassName(CsvDriver.class.getName());
		dc.setSuperInterfaceName(ICsv.class.getName());
		return dc;
	}

}
