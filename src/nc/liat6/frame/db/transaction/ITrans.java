package nc.liat6.frame.db.transaction;

import nc.liat6.frame.db.DbType;
import nc.liat6.frame.db.plugin.ICounter;
import nc.liat6.frame.db.plugin.IDeleter;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.plugin.ISelecter;
import nc.liat6.frame.db.plugin.IUpdater;
import nc.liat6.frame.db.sql.ITemplate;

/**
 * ����ӿ�
 * @author 6tail
 *
 */
public interface ITrans {
	
	/**
	 * ��ʼ��
	 * @param alias ���ӱ���
	 */
	public void init(String alias);
	
	/**
	 * ��ȡִ����
	 * @return ִ����
	 */
	public IUpdater getUpdater();
	
	/**
	 * ��ȡɾ����
	 * @return ɾ����
	 */
	public IDeleter getDeleter();
	
	/**
	 * ��ȡ������
	 * @return ������
	 */
	public IInserter getInserter();
	
	/**
	 * ��ȡ��ѯ��
	 * @return ��ѯ��
	 */
	public ISelecter getSelecter();
	
	/**
	 * ��ȡ������
	 * @return ������
	 */
	public ICounter getCounter();

	/**
	 * ��ȡģ��
	 * 
	 * @return ģ��
	 */
	public ITemplate getTemplate();
	
	/**
	 * �ύ����
	 */
	public void commit();

	/**
	 * �ع�����
	 */
	public void rollback();

	/**
	 * �ر�����
	 */
	public void close();
	
	/**
	 * ��ȡ���ݿ�����
	 * @return ���ݿ�����
	 * @see DbType
	 */
	public DbType getDbType();

}
