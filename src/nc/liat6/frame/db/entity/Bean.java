package nc.liat6.frame.db.entity;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.exception.NlfException;
import nc.liat6.frame.klass.BeanPool;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.util.Streamer;

/**
 * ͨ�ö����װ
 * 
 * @author 6tail
 * 
 */
public class Bean implements Serializable{

	private static final long serialVersionUID = -1432802131869099829L;

	/** ��ֵ�� */
	private Map<String,Object> values = new HashMap<String,Object>();

	/** ע�Ͷ� */
	private Map<String,String> notes = new HashMap<String,String>();

	/**
	 * �Ƿ����ָ�����Ķ���
	 * 
	 * @param key ��
	 * @return true/false ����/������
	 */
	public boolean containsKey(String key){
		return values.containsKey(key);
	}

	/**
	 * ��ȡֵ
	 * 
	 * @param key ��
	 * @return ֵ
	 */
	@SuppressWarnings("unchecked")
	public <T>T get(String key){
		return (T)values.get(key);
	}

	/**
	 * ��ȡע��
	 * 
	 * @param key ��
	 * @return ע��
	 */
	public String getNote(String key){
		return notes.get(key);
	}

	/**
	 * ����ֵ
	 * 
	 * @param key ��
	 * @param value ֵ
	 * @return �Լ�
	 */
	public Bean set(String key,Object value){
		values.put(key,value);
		return this;
	}

	/**
	 * ���ô�ע�͵�ֵ
	 * 
	 * @param key ��
	 * @param value ֵ
	 * @param note ע��
	 * @return �Լ�
	 */
	public Bean set(String key,Object value,String note){
		values.put(key,value);
		notes.put(key,note);
		return this;
	}

	/**
	 * ����ע��
	 * 
	 * @param key ��
	 * @param note ע��
	 * @return �Լ�
	 */
	public Bean setNote(String key,String note){
		notes.put(key,note);
		return this;
	}

	/**
	 * �Ƴ�
	 * 
	 * @param key ��
	 * @return �Լ�
	 */
	public Bean remove(String key){
		values.remove(key);
		notes.remove(key);
		return this;
	}

	/**
	 * ���
	 * @return �Լ�
	 */
	public Bean clear(){
		values.clear();
		notes.clear();
		return this;
	}

	/**
	 * ��ȡ���ļ���
	 * 
	 * @return ������
	 */
	public Set<String> keySet(){
		return values.keySet();
	}

	public String toString(){
		return values.toString();
	}

	/**
	 * ��ȡintֵ�������ȡ�������������Ĭ��ֵ�����׳��쳣
	 * 
	 * @param key ��
	 * @param defaultValue Ĭ��ֵ
	 * @return ֵ
	 */
	public int getInt(String key,int defaultValue){
		try{
			return Integer.parseInt(String.valueOf(values.get(key)));
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * ��ȡlongֵ�������ȡ�������������Ĭ��ֵ�����׳��쳣
	 * 
	 * @param key ��
	 * @param defaultValue Ĭ��ֵ
	 * @return ֵ
	 */
	public long getLong(String key,long defaultValue){
		try{
			return Long.parseLong(String.valueOf(values.get(key)));
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * ��ȡdoubleֵ�������ȡ�������������Ĭ��ֵ�����׳��쳣
	 * 
	 * @param key ��
	 * @param defaultValue Ĭ��ֵ
	 * @return ֵ
	 */
	public double getDouble(String key,double defaultValue){
		try{
			return Double.parseDouble(String.valueOf(values.get(key)));
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * ��ȡfloatֵ�������ȡ�������������Ĭ��ֵ�����׳��쳣
	 * 
	 * @param key ��
	 * @param defaultValue Ĭ��ֵ
	 * @return ֵ
	 */
	public float getFloat(String key,float defaultValue){
		try{
			return Float.parseFloat(String.valueOf(values.get(key)));
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * ��ȡbooleanֵ�������ȡ�������������Ĭ��ֵ�����׳��쳣
	 * 
	 * @param key ��
	 * @param defaultValue Ĭ��ֵ
	 * @return ֵ
	 */
	public boolean getBoolean(String key,boolean defaultValue){
		try{
			return Boolean.parseBoolean(String.valueOf(values.get(key)));
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * ��ȡStringֵ�����Ϊnull,����null
	 * 
	 * @param key ��
	 * @return ֵ
	 */
	public String getString(String key){
		return getString(key,null);
	}

	/**
	 * ��ȡStringֵ�����Ϊnull,����Ĭ��ֵ
	 * 
	 * @param key ��
	 * @param defaultValue Ĭ��ֵ
	 * @return ֵ
	 */
	public String getString(String key,String defaultValue){
		Object o = values.get(key);
		if(null == o){
			return defaultValue;
		}
		if(o instanceof Blob){
			Blob m = (Blob)o;
			try{
				return new String(Streamer.toByte(m.getBinaryStream()));
			}catch(Exception e){
				throw new DaoException(L.get("bean.data_convert_fail"),e);
			}
		}
		return o + "";
	}

	@SuppressWarnings("unchecked")
	public <T> T toObject(Class<?> klass){
		try{
			Object o = klass.newInstance();
			BeanInfo info = BeanPool.getBeanInfo(klass);
			PropertyDescriptor[] props = info.getPropertyDescriptors();
			for(int i = 0;i < props.length;i++){
				PropertyDescriptor desc = props[i];
				String name = desc.getName();
				Method method = desc.getWriteMethod();
				if(null != method){
					try{
						if(null != values && values.containsKey(name)){
							method.invoke(o,values.get(name));
						}else{
							try{
								Object arg = desc.getPropertyType().newInstance();
								method.invoke(o,arg);
							}catch(NlfException ex){}
						}
					}catch(Exception e){
						throw new NlfException(e);
					}
				}
			}
			return (T)o;
		}catch(Exception e){
			throw new NlfException(e);
		}
	}

}
