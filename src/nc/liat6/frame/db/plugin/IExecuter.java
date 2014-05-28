package nc.liat6.frame.db.plugin;

import nc.liat6.frame.db.sql.ITemplate;

/**
 * ����ִ�нӿ�
 * @author 6tail
 *
 */
public interface IExecuter{
	
	/**
	 * ��ȡSQL��䣬ע��ֻ�����ύǰ���ܻ�ȡ��
	 * @return ִ�е�SQL���
	 */
	public String getSql();
	
	/**
	 * ��ȡ�󶨱�����ע��ֻ�����ύǰ���ܻ�ȡ��
	 * @return �󶨱���
	 */
	public Object[] getParam();
	
	public void setTemplate(ITemplate template);
	
	public ITemplate getTemplate();

}
