package nc.liat6.frame.db.connection;

import nc.liat6.frame.db.connection.impl.JdbcConnVarProvider;
import nc.liat6.frame.db.connection.impl.ProxoolConnVarProvider;
import nc.liat6.frame.db.custom.json.JsonConnVarProvider;
import nc.liat6.frame.db.custom.mongo.MongoConnVarProvider;
import nc.liat6.frame.db.custom.xml.XmlConnVarProvider;
import nc.liat6.frame.db.setting.ConnType;
import nc.liat6.frame.db.setting.ISetting;
import nc.liat6.frame.db.setting.SettingFactory;

/**
 * ���ӱ�������
 * @author 6tail
 *
 */
public class ConnVarFactory {

	private ConnVarFactory() {
	}

	/**
	 * ���ݱ�����ȡ���ӱ���
	 * @param alias ����
	 * @return ���ӱ���
	 */
	public static ConnVar getConnVar(String alias) {
		ISetting setting = SettingFactory.getSetting(alias);
		IConnVarProvider provider = null;
		if (ConnType.JDBC.equalsIgnoreCase(setting.getType())) {
			JdbcConnVarProvider p = new JdbcConnVarProvider();
			p.setSetting(setting);
			provider = p;
		} else if (ConnType.PROXOOL.equalsIgnoreCase(setting.getType())) {
			ProxoolConnVarProvider p = new ProxoolConnVarProvider();
			p.setSetting(setting);
			provider = p;
		} else if (ConnType.JSON.equalsIgnoreCase(setting.getType())) {
			JsonConnVarProvider p = new JsonConnVarProvider();
			p.setSetting(setting);
			provider = p;
		}else if (ConnType.XML.equalsIgnoreCase(setting.getType())) {
			XmlConnVarProvider p = new XmlConnVarProvider();
			p.setSetting(setting);
			provider = p;
		}else if (ConnType.MONGO.equalsIgnoreCase(setting.getType())) {
			MongoConnVarProvider p = new MongoConnVarProvider();
			p.setSetting(setting);
			provider = p;
		}
		return provider.getConnVar();
	}

	/**
	 * ��ȡĬ�����ӱ���
	 * @return Ĭ�����ӱ���
	 */
	public static ConnVar getConnVar() {
		return getConnVar(SettingFactory.getDefaultSetting().getAlias());
	}

}
