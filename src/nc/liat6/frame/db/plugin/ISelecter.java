package nc.liat6.frame.db.plugin;

import java.util.List;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.paging.PageData;

/**
 * ��ѯ�ӿ�
 * 
 * @author 6tail
 * 
 */
public interface ISelecter extends IExecuter{

  /**
   * ���ñ���
   * 
   * @param tableName ����
   * @return ��ǰ��ѯ�ӿ�
   */
  public ISelecter table(String tableName);

  /**
   * Ҫ���ص�����
   * 
   * @param column ��
   * @return ��ѯ�ӿ�
   */
  public ISelecter column(String... column);

  /**
   * SQL����
   * 
   * @param sql SQL���
   * @return ��ѯ�ӿ�
   */
  public ISelecter where(String sql);

  /**
   * ��������SQL����
   * 
   * @param sql SQL���
   * @param values �󶨱���
   * @return ��ѯ�ӿ�
   */
  public ISelecter whereSql(String sql,Object[] values);

  /**
   * ����
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ��ѯ�ӿ�
   */
  public ISelecter where(String column,Object value);

  /**
   * ģ����ѯ like %value%
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ��ѯ�ӿ�
   */
  public ISelecter whereLike(String column,Object value);

  /**
   * ģ����ѯ like value%
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ��ѯ�ӿ�
   */
  public ISelecter whereLeftLike(String column,Object value);

  /**
   * ģ����ѯ like %value
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ��ѯ�ӿ�
   */
  public ISelecter whereRightLike(String column,Object value);

  /**
   * ����column != value
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ��ѯ�ӿ�
   */
  public ISelecter whereNq(String column,Object value);

  /**
   * ���� column in (...)
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ��ѯ�ӿ�
   */
  public ISelecter whereIn(String column,Object... value);

  /**
   * ���� column not in (...)
   * 
   * @param column ��
   * @param value �󶨱���
   * @return ��ѯ�ӿ�
   */
  public ISelecter whereNotIn(String column,Object... value);

  /**
   * �������
   * 
   * @param column ��
   * @return ��ѯ�ӿ�
   */
  public ISelecter asc(String... column);

  /**
   * �������
   * 
   * @param column ��
   * @return ��ѯ�ӿ�
   */
  public ISelecter desc(String... column);

  /**
   * �����б�
   * 
   * @return �б�
   */
  public List<Bean> select();

  /**
   * ��ҳ��ѯ
   * 
   * @param pageNumber ҳ��
   * @param pageSize ÿҳ��¼��
   * @return ��ҳ����
   */
  public PageData page(int pageNumber,int pageSize);

  /**
   * ��ȡ������¼
   * 
   * @return ����¼����
   */
  public Bean one();
}
