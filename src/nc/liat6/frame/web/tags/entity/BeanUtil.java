package nc.liat6.frame.web.tags.entity;

import nc.liat6.frame.db.entity.Bean;

/**
 * Bean����
 * <i>EL:${nlfe:bean(obj,"key")}</i>
 * 
 * @author 6tail
 * 
 */
public class BeanUtil{

  /**
   * ��ȡBean��ֵ
   * 
   * @param bean Bean����
   * @param key ��
   * @return ֵ
   */
  public static Object get(Bean bean,String key){
    if(null==bean){
      return null;
    }
    return bean.get(key);
  }
}
