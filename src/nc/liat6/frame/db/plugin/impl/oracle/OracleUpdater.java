package nc.liat6.frame.db.plugin.impl.oracle;

import nc.liat6.frame.db.config.IOracle;
import nc.liat6.frame.db.plugin.IUpdater;
import nc.liat6.frame.db.plugin.Rule;
import nc.liat6.frame.db.plugin.impl.CommonUpdater;

/**
 * Oracle���ݿ�ִ����
 * @author 6tail
 *
 */
public class OracleUpdater extends CommonUpdater implements IOracle{
	
	public IUpdater where(String column,Object value){
		Rule r = new Rule();
		r.setColumn(column);
		if(null!=value){
			r.setOpStart("=");
			paramWheres.add(value);
		}else{
			r.setOpStart(" IS NULL");
			r.setTag("");
		}
		wheres.add(r);
		return this;
	}
	
	public IUpdater whereNq(String column,Object value){
		Rule r = new Rule();
		r.setColumn(column);
		if(null!=value){
			r.setOpStart("!=");
			paramWheres.add(value);
		}else{
			r.setOpStart(" IS NOT NULL");
			r.setTag("");
		}
		wheres.add(r);
		return this;
	}

}
