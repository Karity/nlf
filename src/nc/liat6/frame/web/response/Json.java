package nc.liat6.frame.web.response;

/**
 * ���� - JSON
 * 
 * @author liat6
 * 
 */
public class Json{

	/** �Ƿ�ɹ� */
	private boolean success = true;

	/** ���ݶ��� */
	private Object data;

	/** ��Ϣ���� */
	private String msg;

	public Json(){}

	public Json(Object data){
		setData(data);
	}
	
	public Json(Object data,String msg){
		setData(data);
		setMsg(msg);
	}
	
	public Json(Object data,String msg,boolean success){
		setData(data);
		setMsg(msg);
		setSuccess(success);
	}

	/**
	 * �������ݶ���
	 * 
	 * @param data ���ݶ���
	 */
	public void setData(Object data){
		this.data = data;
	}

	/**
	 * ��ȡ���ݶ���
	 * 
	 * @return ���ݶ���
	 */
	public Object getData(){
		return data;
	}

	/**
	 * ���óɹ���ʶ
	 * @param success �Ƿ�ɹ�
	 */
	public void setSuccess(boolean success){
		this.success = success;
	}

	/**
	 * ��ȡ�ɹ���ʶ
	 * @return �Ƿ�ɹ�
	 */
	public boolean isSuccess(){
		return success;
	}

	public String toString(){
		return success+":"+msg+":"+data;
	}

	/**
	 * ��ȡ��Ϣ����
	 * @return ��Ϣ����
	 */
	public String getMsg(){
		return msg;
	}

	/**
	 * ������Ϣ����
	 * @param msg ��Ϣ����
	 */
	public void setMsg(String msg){
		this.msg = msg;
	}

}
