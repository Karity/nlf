package nc.liat6.frame.db.plugin;

/**
 * ����ɾ���ӿ�
 * 
 * @author 6tail
 * 
 */
public interface IDeleter extends IExecuter{

  /**
   * ���ñ�
   * 
   * @param tableName ����
   * @return ����ɾ���ӿ�
   */
  public IDeleter table(String tableName);

  /**
   * SQL����
   * 
   * @param sql SQL���
   * @return ����ɾ���ӿ�
   */
  public IDeleter where(String sql);

  /**
   * ��������SQL����
   * 
   * @param sql SQL���
   * @param values �󶨱���
   * @return ����ɾ���ӿ�
   */
  public IDeleter whereSql(String sql,Object[] values);

  /**
   * ɾ������
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ����ɾ���ӿ�
   */
  public IDeleter where(String column,Object value);

  /**
   * ɾ������column in (...)
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ����ɾ���ӿ�
   */
  public IDeleter whereIn(String column,Object... value);

  /**
   * ɾ������column not in (...)
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ����ɾ���ӿ�
   */
  public IDeleter whereNotIn(String column,Object... value);

  /**
   * ɾ��������������
   * 
   * @return ɾ���ļ�¼��
   */
  public int delete();
}
