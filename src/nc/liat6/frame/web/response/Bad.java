package nc.liat6.frame.web.response;

/**
 * ���� - ������Ϣ
 * 
 * @author 6tail
 * 
 */
public class Bad{

	/** ��Ϣ���� */
	private String thing;

	/** ���ݶ��� */
	private Object data;

	public Bad(String thing){
		setThing(thing);
	}

	public Bad(String thing,Object data){
		setThing(thing);
		setData(data);
	}

	/**
	 * ������Ϣ����
	 * @param thing ��Ϣ����
	 */
	public void setThing(String thing){
		this.thing = thing;
	}

	/**
	 * ��ȡ��Ϣ����
	 * @return ��Ϣ����
	 */
	public String getThing(){
		return thing;
	}

	public String toString(){
		return thing;
	}

	/**
	 * ��ȡ���ݶ���
	 * @return ���ݶ���
	 */
	public Object getData(){
		return data;
	}

	/**
	 * �������ݶ���
	 * @param data ���ݶ���
	 */
	public void setData(Object data){
		this.data = data;
	}

}
