package nc.liat6.frame.db.setting;

import java.util.Comparator;

/**
 * �������ñȽ�����������alias�Ƚϣ�Խ���Խǰ
 * @author 6tail
 *
 */
public class DbSettingComparator implements Comparator<IDbSetting>{

	public int compare(IDbSetting o1,IDbSetting o2){
		return o2.getAlias().compareTo(o1.getAlias());
	}

}
