package nc.liat6.frame.db.plugin;

/**
 * ���ݼ����ӿ�
 * @author 6tail
 *
 */
public interface ICounter extends IExecuter{

	/**
	 * ���ñ�
	 * @param tableName ����
	 * @return ���ݼ����ӿ�
	 */
	public ICounter table(String tableName);

	/**
	 * count��
	 * @param column ��
	 * @return ���ݼ����ӿ�
	 */
	public ICounter column(String... column);
	
	/**
	 * SQL����
	 * @param sql SQL���
	 * @return ���ݼ����ӿ�
	 */
	public ICounter where(String sql);
	
	/**
	 * ��������SQL����
	 * @param sql SQL���
	 * @param values �󶨱���
	 * @return ���ݼ����ӿ�
	 */
	public ICounter whereSql(String sql,Object[] values);

	/**
	 * ����
	 * @param column ��
	 * @param value �󶨱���
	 * @return ���ݼ����ӿ�
	 */
	public ICounter where(String column,Object value);

	/**
	 * ģ������ like %value%
	 * @param column ��
	 * @param value �󶨱���
	 * @return ���ݼ����ӿ�
	 */
	public ICounter whereLike(String column,Object value);

	/**
	 * ģ������ like value%
	 * @param column ��
	 * @param value �󶨱���
	 * @return ���ݼ����ӿ�
	 */
	public ICounter whereLeftLike(String column,Object value);

	/**
	 * ģ������ like %value
	 * @param column ��
	 * @param value �󶨱���
	 * @return ���ݼ����ӿ�
	 */
	public ICounter whereRightLike(String column,Object value);

	/**
	 * ���� col!=value
	 * @param column ��
	 * @param value �󶨱���
	 * @return ���ݼ����ӿ�
	 */
	public ICounter whereNq(String column,Object value);

	/**
	 * ���� col in (...)
	 * @param column ��
	 * @param value �󶨱���
	 * @return ���ݼ����ӿ�
	 */
	public ICounter whereIn(String column,Object... value);

	/**
	 * ���� col not in (...)
	 * @param column ��
	 * @param value �󶨱���
	 * @return ���ݼ����ӿ�
	 */
	public ICounter whereNotIn(String column,Object... value);

	/**
	 * ����
	 * @return ��¼��
	 */
	public int count();

}
