package nc.liat6.frame.web.tags.entity;

import nc.liat6.frame.db.entity.Bean;

/**
 * Bean����
 * @author 6tail
 *
 */
public class BeanUtil{
	
	/**
	 * ��ȡBean��ֵ
	 * @param bean Bean����
	 * @param key ��
	 * @return ֵ
	 */
	public static Object get(Bean bean,String key){
		if(null==bean) return null;
		Object r = bean.get(key);
		//if(r instanceof String){
			//return ((String)r).replace("<","&lt;").replace(">","&gt;");
		//}
		return r;
	}

}
