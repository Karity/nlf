package nc.liat6.frame.db.sql;

import java.util.List;

import nc.liat6.frame.db.connection.ConnVar;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.paging.PageData;

/**
 * SQLִ��ģ��ӿ�
 * @author 6tail
 *
 */
public interface ITemplate{
	
	/**
	 * ��ѯһ����¼
	 * @param sql SQL���
	 * @return ��ֵ����
	 */
	public Object[] one(String sql);

	/**
	 * �������Ĳ�ѯһ����¼
	 * @param sql SQL���
	 * @param param �󶨱��������������飬Ҳ�����ǵ�ֵ
	 * @return ��ֵ����
	 */
	public Object[] one(String sql,Object param);

	/**
	 * ��ѯ�б�
	 * @param sql SQL���
	 * @return ������ֵ������б�
	 */
	public List<Object[]> query(String sql);

	/**
	 * ��ѯ�б�
	 * @param sql SQL���
	 * @param param �󶨱��������������飬Ҳ�����ǵ�ֵ
	 * @return ������ֵ������б�
	 */
	public List<Object[]> query(String sql,Object param);

	/**
	 * ��ѯʵ���б�
	 * @param sql SQL���
	 * @return ����Bean���б�
	 */
	public List<Bean> queryEntity(String sql);

	/**
	 * ��ѯʵ���б�
	 * @param sql SQL���
	 * @param param �󶨱��������������飬Ҳ�����ǵ�ֵ
	 * @return ����Bean���б�
	 */
	public List<Bean> queryEntity(String sql,Object param);

	/**
	 * ��ѯ����ʵ��
	 * @param sql SQL���
	 * @return Bean
	 */
	public Bean oneEntity(String sql);

	/**
	 * ��������ѯ����ʵ��
	 * @param sql SQL���
	 * @param param �󶨱���
	 * @return Bean
	 */
	public Bean oneEntity(String sql,Object param);

	/**
	 * ��ȡ��¼��
	 * @param sql SQL���
	 * @return ��¼��
	 */
	public int count(String sql);

	/**
	 * ��ȡ��¼��
	 * @param sql SQL���
	 * @param param �󶨱��������������飬Ҳ�����ǵ�ֵ
	 * @return ��¼��
	 */
	public int count(String sql,Object param);

	/**
	 * ���ݸ���
	 * @param sql SQL���
	 * @return ���µļ�¼��
	 */
	public int update(String sql);

	/**
	 * �����������ݸ���
	 * @param sql SQL���
	 * @param param �󶨱��������������飬Ҳ�����ǵ�ֵ
	 * @return ���µļ�¼��
	 */
	public int update(String sql,Object param);

	/**
	 * ��ҳ��ѯ
	 * @param sql SQL���
	 * @param pageNumber ҳ�룬��1��ʼ
	 * @param pageSize ÿҳ��¼��
	 * @return ��ҳ���ݷ�װ
	 */
	public PageData query(String sql,int pageNumber,int pageSize);

	/**
	 * �������ķ�ҳ��ѯ
	 * @param sql SQL���
	 * @param pageNumber ҳ�룬��1��ʼ
	 * @param pageSize ÿҳ��¼��
	 * @param param �󶨱��������������飬Ҳ�����ǵ�ֵ
	 * @return ��ҳ���ݷ�װ
	 */
	public PageData query(String sql,int pageNumber,int pageSize,Object param);

	/**
	 * ʵ���ҳ��ѯ
	 * @param sql SQL���
	 * @param pageNumber ҳ�룬��1��ʼ
	 * @param pageSize ÿҳ��¼��
	 * @return ʵ���ҳ���ݷ�װ
	 */
	public PageData queryEntity(String sql,int pageNumber,int pageSize);

	/**
	 * ��������ʵ���ҳ��ѯ
	 * @param sql SQL���
	 * @param pageNumber ҳ�룬��1��ʼ
	 * @param pageSize ÿҳ��¼��
	 * @param param �󶨱��������������飬Ҳ�����ǵ�ֵ
	 * @return ʵ���ҳ���ݷ�װ
	 */
	public PageData queryEntity(String sql,int pageNumber,int pageSize,Object param);

	/**
	 * ��ȡ���ӱ���
	 * @return ���ӱ���
	 */
	public ConnVar getConnVar();
	
	/**
	 * �����ύ
	 */
	public int[] flush();
	
	/**
	 * ����ʹ�õ����ӱ���
	 * @param alias ���ӱ���
	 */
	public void setAlias(String alias);
	
	/**
	 * �����вδ洢���̣������ؽ������������صģ����ȡConnectionִ�д洢����
	 * @param procName �洢��������
	 * @param param �����������ǵ�����Ҳ���Զ�������ж������������Object[]����
	 */
	public void call(String procName,Object param);
	
	/**
	 * �����޲δ洢���̣������ؽ������������صģ����ȡConnectionִ�д洢����
	 * @param procName �洢��������
	 */
	public void call(String procName);

}
