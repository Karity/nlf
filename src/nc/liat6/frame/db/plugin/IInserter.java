package nc.liat6.frame.db.plugin;

import nc.liat6.frame.db.entity.Bean;

/**
 * ���ݲ���ӿ�
 * @author 6tail
 *
 */
public interface IInserter extends IExecuter{
	
	/**
	 * ����Ҫ�������ݵı�
	 * @param tableName ����
	 * @return ���ݲ���ӿ�
	 */
	public IInserter table(String tableName);
	
	/**
	 * �����е�ֵ
	 * @param column ����
	 * @param value �󶨱���
	 * @return ���ݲ���ӿ�
	 */
	public IInserter set(String column,Object value);
	
	/**
	 * ͨ��SQLֱ�������е�ֵ
	 * @param column ����
	 * @param valueSql ֵ�������ڴ�SQL����:sysdate
	 * @return ���ݲ���ӿ�
	 */
	public IInserter setSql(String column,String valueSql);
	
	/**
	 * ͨ��SQL���ô�������ֵ
	 * @param column ����
	 * @param valueSql ֵ�������ڴ�SQL����:to_date(?,'yyyy-mm-dd')
	 * @param values �󶨱���
	 * @return ���ݲ���ӿ�
	 */
	public IInserter setSql(String column,String valueSql,Object[] values);
	
	/**
	 * ���������������
	 * @return ����ļ�¼��
	 */
	public int insert();
	
	/**
	 * ����Ҫ�����Bean
	 * @param bean
	 * @return ���ݲ���ӿ�
	 */
	public IInserter setBean(Bean bean);

}
