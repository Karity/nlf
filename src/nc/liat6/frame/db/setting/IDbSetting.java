package nc.liat6.frame.db.setting;

import java.io.Serializable;

/**
 * �������ýӿ�
 * @author 6tail
 *
 */
public interface IDbSetting extends Serializable {
	
	/**
	 * ��ȡ��������
	 * @return ��������
	 */
	public String getType();
	
	/**
	 * ��ȡ����
	 * @return ����
	 */
	public String getAlias();
	
	/**
	 * ��ȡ���ݿ�ʵ����
	 * @return ���ݿ�ʵ����
	 */
	public String getDbName();

}
