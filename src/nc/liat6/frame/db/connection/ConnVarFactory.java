package nc.liat6.frame.db.connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.liat6.frame.Factory;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.setting.DbSettingFactory;
import nc.liat6.frame.db.setting.IDbSetting;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.locale.LocaleFactory;

/**
 * ���ӱ�������
 * 
 * @author 6tail
 * 
 */
public class ConnVarFactory{

	/** �����ṩ������ */
	private static final Map<String,IConnVarProvider> pool = new HashMap<String,IConnVarProvider>();

	private ConnVarFactory(){}

	/**
	 * ���ݱ�����ȡ���ӱ���
	 * 
	 * @param alias ����
	 * @return ���ӱ���
	 */
	public static ConnVar getConnVar(String alias){
		IDbSetting setting = DbSettingFactory.getSetting(alias);
		String type = setting.getType();
		if(pool.containsKey(type)){
			return pool.get(type).getConnVar();
		}
		List<String> impls = Factory.getImpls(IConnVarProvider.class.getName());
		for(String klass:impls){
			IConnVarProvider cvp = Factory.getCaller().newInstance(klass);
			if(cvp.support(type)){
				pool.put(type,cvp);
				cvp.setSetting(setting);
				return cvp.getConnVar();
			}
		}
		throw new DaoException(L.get(LocaleFactory.locale,"db.conntype_not_support")+type);
	}

	/**
	 * ��ȡĬ�����ӱ���
	 * 
	 * @return Ĭ�����ӱ���
	 */
	public static ConnVar getConnVar(){
		return getConnVar(DbSettingFactory.getDefaultSetting().getAlias());
	}

}
