package nc.liat6.frame.web.response;

/**
 * ���� - �Զ����װ��������ǰ��UI�йصĶ���ĸ���
 * 
 * @author 6tail
 * 
 */
public abstract class AbstractType extends Json{

	/** UI������� */
	private String xtype;

	public AbstractType(String xtype){
		this.xtype = xtype;
	}

	public String getXtype(){
		return xtype;
	}

}
