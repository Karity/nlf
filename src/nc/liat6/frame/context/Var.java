package nc.liat6.frame.context;
import java.util.HashMap;
import java.util.Map;

/**
 * �����ı���
 * @author liat6
 *
 */
final class Var {
	
	/** ֵ */
	private Map<Object,Object> value = new HashMap<Object,Object>();
	
	/**
	 * ����
	 * @param k ��
	 * @param v ֵ
	 */
	void set(Object k,Object v){
		value.put(k, v);
	}
	
	/**
	 * ��ȡ
	 * @param k ��
	 * @return ֵ
	 */
	Object get(Object k){
		return value.get(k);
	}
	
	/**
	 * �Ƴ�
	 * @param k ��
	 */
	void remove(Object k){
		value.remove(k);
	}
	
	/**
	 * ���
	 */
	void clear(){
		value.clear();
	}
	
	/**
	 * �Ƿ����
	 * @param k ��
	 * @return true/false ��/��
	 */
	boolean contains(Object k){
		return value.containsKey(k);
	}

}