package nc.liat6.frame.db.setting;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.liat6.frame.Factory;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.json.JSON;
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
	/** ���ݿ������ļ�Ŀ¼ */
	public static final String DB_DIR = "db";
	/** ��������ӳ�� */
	private static final Map<String, IDbSetting> SETTING_POOL = new HashMap<String, IDbSetting>();
	/** ���������б���ӳ���Ӧ */
	private static final List<IDbSetting> SETTING_LIST = new ArrayList<IDbSetting>();

	private DbSettingFactory(){}

	static {
		init();
	}
	
	private synchronized static void init(){
		List<IDbSetting> l = new ArrayList<IDbSetting>();
		File dir = new File(Factory.APP_PATH, DB_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File[] fs = dir.listFiles(new DbSettingFileFilter());
		List<String> impls = Factory.getImpls(IDbSettingProvider.class.getName());
		outer:for (File f : fs) {
			try {
				Bean o = JSON.toBean(Stringer.readFromFile(f,"utf-8"));
				String type = o.getString("type","");
				type = type.toUpperCase();
				for(String klass:impls){
					IDbSettingProvider dsp = Factory.getCaller().newInstance(klass);
					if(dsp.support(type)){
						l.add(dsp.getDbSetting(o));
						continue outer;
					}
				}
			} catch (Exception e) {
				throw new DaoException(L.get("db.config_file_fail")+f.getName(),e);
			}
		}
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
