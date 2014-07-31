package nc.liat6.frame.db.plugin;

import nc.liat6.frame.db.entity.Bean;

/**
 * ���ݸ��½ӿ�
 * 
 * @author 6tail
 * 
 */
public interface IUpdater extends IExecuter{

  /**
   * ����Ҫ���µı���
   * 
   * @param tableName ����
   * @return ���ݸ��½ӿ�
   */
  public IUpdater table(String tableName);

  /**
   * SQL�����ʽ������
   * 
   * @param sql SQL���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater where(String sql);

  /**
   * SQL�����󶨱�����ʽ������
   * 
   * @param sql SQL���
   * @param values �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater whereSql(String sql,Object[] values);

  /**
   * ��=ֵ ��ʽ������
   * 
   * @param column ����
   * @param value �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater where(String column,Object value);

  /**
   * �� like %ֵ% ��ʽ������
   * 
   * @param column ����
   * @param value �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater whereLike(String column,Object value);

  /**
   * �� like ֵ% ��ʽ������
   * 
   * @param column ����
   * @param value �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater whereLeftLike(String column,Object value);

  /**
   * �� like %ֵ ��ʽ������
   * 
   * @param column ����
   * @param value �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater whereRightLike(String column,Object value);

  /**
   * �� != ֵ ��ʽ������
   * 
   * @param column ����
   * @param value �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater whereNq(String column,Object value);

  /**
   * �� in(...) ��ʽ������
   * 
   * @param column ����
   * @param value �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater whereIn(String column,Object... value);

  /**
   * �� not in(...) ��ʽ������
   * 
   * @param column ����
   * @param value �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater whereNotIn(String column,Object... value);

  /**
   * �и�ֵ
   * 
   * @param column ����
   * @param value �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater set(String column,Object value);

  /**
   * SQL�����ʽ���и�ֵ
   * 
   * @param sql SQL��䣬�磺col=sysdate
   * @return ���ݸ��½ӿ�
   */
  public IUpdater set(String sql);

  /**
   * ���󶨱�����SQL�����ʽ���и���
   * 
   * @param sql SQL���
   * @param values �󶨱���
   * @return ���ݸ��½ӿ�
   */
  public IUpdater setSql(String sql,Object[] values);

  /**
   * ���²�����������
   * 
   * @return ���¼�¼��
   */
  public int update();

  /**
   * ����Ҫ���µ�Bean
   * 
   * @param bean
   * @return ���ݸ��½ӿ�
   */
  public IUpdater setBean(Bean bean);
}
