package nc.liat6.frame.db.connection;

public abstract class SuperConnection implements IConnection{

  /** ���ӱ��� */
  protected ConnVar connVar;

  public ConnVar getConnVar(){
    return connVar;
  }

  public void setConnVar(ConnVar connVar){
    this.connVar = connVar;
  }
}
