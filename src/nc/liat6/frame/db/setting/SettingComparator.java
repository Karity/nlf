package nc.liat6.frame.db.setting;

import java.util.Comparator;

/**
 * �������ñȽ�����������alias�Ƚϣ�Խ���Խǰ
 * @author 6tail
 *
 */
public class SettingComparator implements Comparator<ISetting>{

	public int compare(ISetting o1,ISetting o2){
		return o2.getAlias().compareTo(o1.getAlias());
	}

}
