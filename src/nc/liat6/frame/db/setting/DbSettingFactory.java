package nc.liat6.frame.db.setting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.liat6.frame.Factory;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.locale.LocaleFactory;
import nc.liat6.frame.log.Logger;
import nc.liat6.frame.util.Stringer;

/**
 * �������ù���
 * 
 * @author 6tail
 * 
 */
public class DbSettingFactory {
	
	/** ��������ӳ�� */
	private static final Map<String, IDbSetting> SETTING_POOL = new HashMap<String, IDbSetting>();
	/** ���������б���ӳ���Ӧ */
	private static final List<IDbSetting> SETTING_LIST = new ArrayList<IDbSetting>();

	private DbSettingFactory(){}

	static {
		init();
	}
	
	private synchronized static void init(){
		IDbSettingManager dsm = Factory.getCaller().newInstance(IDbSettingManager.class);
		List<IDbSetting> l = dsm.getDbSettings();
		StringBuilder s = new StringBuilder();
		for(IDbSetting o:l){
			s.append("\r\n\t");
			s.append(o.getAlias());
			SETTING_POOL.put(o.getAlias(), o);
			SETTING_LIST.add(o);
		}
		if(l.size()>0){
			Logger.getLog().debug(Stringer.print("??",L.get(LocaleFactory.locale,"db.load_config"),s.toString()));
		}
		//����
		Collections.sort(SETTING_LIST,new DbSettingComparator());
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param alias
	 *            ����
	 * @return ��������
	 */
	public static IDbSetting getSetting(String alias) {
		if (SETTING_POOL.containsKey(alias)) {
			return SETTING_POOL.get(alias);
		}
		throw new DaoException(L.get("db.config_not_found") + alias);
	}

	/**
	 * ������������
	 * 
	 * @return ������������
	 */
	public static int size() {
		return SETTING_POOL.size();
	}

	/**
	 * ��ȡĬ���������ã�����ж�����������ļ������ر���alias��������
	 * 
	 * @return Ĭ���������ã�����ж�����������ļ������ر���alias��������
	 */
	public static IDbSetting getDefaultSetting() {
		if (SETTING_LIST.size() < 1) {
			throw new DaoException(L.get("db.config_not_found"));
		}
		return SETTING_LIST.get(0);
	}

}
