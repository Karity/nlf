package nc.liat6.frame.execute;

import java.util.HashMap;
import java.util.Map;

/**
 * ��Ӧ
 * @author 6tail
 *
 */
public class Response {
	
	private Map<String,Object> bundle = new HashMap<String,Object>();
	
	/**
	 * �󶨱���
	 * @param key ��
	 * @param value ֵ
	 */
	public void bind(String key,Object value){
		bundle.put(key,value);
	}
	
	/**
	 * ��ȡ�󶨵ı���ֵ
	 * @param key ��
	 * @return ֵ
	 */
	@SuppressWarnings("unchecked")
	public <T> T find(String key){
		return (T)bundle.get(key);
	}

}
