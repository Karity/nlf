package nc.liat6.frame.db.connection;

import java.sql.Connection;

/**
 * ��������
 * @author 6tail
 *
 */
public interface IConnection{
	
	public ConnVar getConnVar();

	public void setConnVar(ConnVar connVar);
	
	public void commit();
	
	public void close();
	
	public void rollback();
	
	public boolean isClosed();
	
	public boolean isSupportsBatchUpdates();
	
	public Connection getSqlConnection();

}
