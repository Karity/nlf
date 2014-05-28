package nc.liat6.frame.execute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.exception.BadException;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.paging.PagingParam;
import nc.liat6.frame.util.Pair;
import nc.liat6.frame.util.Stringer;

/**
 * ����
 * 
 * @author 6tail
 * 
 */
public class Request{

	/** ��ҳҳ���ʶ */
	public static final String PAGE_NUMBER_VAR = "nlfPagingNumber";

	/** ��ҳÿҳ��С��ʶ */
	public static final String PAGE_SIZE_VAR = "nlfPagingSize";

	/** ��ҳ������ʶ */
	public static final String PAGE_PARAM_VAR = "nlfPagingParam";

	/** ���������ʶ */
	public static final String PAGE_SORT_VAR = "nlfSort";

	/** Ĭ�Ϸ�ҳ��С */
	public static final int DEFAULT_PAGE_SIZE = 20;

	/** ����Ĳ��� */
	private Map<String,String> params = new HashMap<String,String>();

	/** �󶨱��� */
	private Map<String,Object> bundle = new HashMap<String,Object>();

	/** ��ҳ���� */
	private PagingParam pagingParam;

	/** �ͻ������ͣ����� */
	public static final int CLIENT_TYPE_COMPUTER = 0;

	/** �ͻ������ͣ��ƶ��豸 */
	public static final int CLIENT_TYPE_MOBILE = 1;

	/** �ͻ������� */
	private int clientType = CLIENT_TYPE_COMPUTER;
	
	/**
	 * ��ȡ�ͻ�������
	 * @return �ͻ�������
	 */
	public int getClientType(){
		return clientType;
	}

	/**
	 * ���ÿͻ�������
	 * @param clientType �ͻ�������
	 */
	public void setClientType(int clientType){
		this.clientType = clientType;
	}

	/**
	 * ���ò���
	 * 
	 * @param params ����
	 */
	public void setParams(Map<String,String> params){
		this.params = params;
	}

	/**
	 * ���ò���
	 * 
	 * @param key ��
	 * @param value ֵ
	 */
	public void setParam(String key,String value){
		params.put(key,value);
	}

	/**
	 * �󶨱���
	 * 
	 * @param key ��
	 * @param value ֵ
	 */
	public void bind(String key,Object value){
		bundle.put(key,value);
	}

	/**
	 * ��ȡ�󶨱���
	 * 
	 * @param key ��
	 * @return ֵ
	 */
	@SuppressWarnings("unchecked")
	public <T>T find(String key){
		return (T)bundle.get(key);
	}

	/**
	 * ����ת��ΪBean������ȫ��ȥ���˿ո�
	 * 
	 * @return Bean
	 */
	public Bean toBean(){
		return toBean(true);
	}

	/**
	 * ����ת��ΪBean
	 * 
	 * @param trim �����Ƿ�ȥ���˿ո�
	 * @return Bean
	 */
	public Bean toBean(boolean trim){
		Bean o = new Bean();
		for(String key:params.keySet()){
			o.set(key,get(key,trim));
		}
		o.remove("_timestamp");
		return o;
	}

	/**
	 * ����ת��Ϊ���󣬲���ȫ��ȥ���˿ո�
	 * 
	 * @param klass ��
	 * @return ����
	 */
	public <T>T toObject(Class<?> klass){
		return toObject(klass,true);
	}

	/**
	 * ����ת��Ϊ����
	 * 
	 * @param klass ��
	 * @param trim �����Ƿ�ȫ��ȥ���˿ո�
	 * @return ����
	 */
	public <T>T toObject(Class<?> klass,boolean trim){
		return toBean(trim).toObject(klass);
	}

	/**
	 * ��ȡȥ�����˿ո��Ĳ���ֵ
	 * 
	 * @param key ��
	 * @return ֵ
	 */
	public String get(String key){
		return get(key,true);
	}

	/**
	 * ��ȡ����ֵ
	 * 
	 * @param key ��
	 * @param trim ֵ
	 * @return �Ƿ�ȥ�����˿ո�
	 */
	public String get(String key,boolean trim){
		String value = params.get(key);
		value = null == value?"":value;
		value = trim?value.trim():value;
		return value;
	}

	/**
	 * ��ȡ��������ֵ
	 * 
	 * @param key ������
	 * @return ����ֵ
	 */
	public int getInt(String key){
		String v = get(key);
		try{
			return Integer.parseInt(v);
		}catch(Exception e){
			throw new BadException(Stringer.print("??",L.get("valiadtor.integer"),key));
		}
	}

	/**
	 * ��ȡ�����Ͳ���ֵ
	 * 
	 * @param key ������
	 * @return ����ֵ
	 */
	public long getLong(String key){
		String v = get(key);
		try{
			return Long.parseLong(v);
		}catch(Exception e){
			throw new BadException(Stringer.print("??",L.get("valiadtor.long_integer"),key));
		}
	}

	/**
	 * ��ȡ��ֵ�Ͳ���ֵ
	 * 
	 * @param key ������
	 * @return ����ֵ
	 */
	public double getDouble(String key){
		String v = get(key);
		try{
			return Double.parseDouble(v);
		}catch(Exception e){
			throw new BadException(Stringer.print("??",L.get("valiadtor.double"),key));
		}
	}

	/**
	 * ��ȡ�����Ͳ���ֵ
	 * 
	 * @param key ������
	 * @return ����ֵ
	 */
	public float getFloat(String key){
		String v = get(key);
		try{
			return Float.parseFloat(v);
		}catch(Exception e){
			throw new BadException(Stringer.print("??",L.get("valiadtor.float"),key));
		}
	}

	/**
	 * ��ȡ�����Ͳ���ֵ
	 * 
	 * @param key ������
	 * @return ����ֵ
	 */
	public boolean getBoolean(String key){
		String v = get(key);
		try{
			return Boolean.parseBoolean(v);
		}catch(Exception e){
			throw new BadException(Stringer.print("??",L.get("valiadtor.bool"),key));
		}
	}

	/**
	 * ��ȡ���в���
	 * 
	 * @return ���в���
	 */
	public Map<String,String> getParams(){
		return getParams(true);
	}

	/**
	 * ��ȡ���в���
	 * 
	 * @param trim �Ƿ�ȥ�����˿ո�
	 * @return ���в���
	 */
	public Map<String,String> getParams(boolean trim){
		Map<String,String> ps = new HashMap<String,String>();
		for(String key:params.keySet()){
			ps.put(key,get(key,trim));
		}
		return ps;
	}

	/**
	 * ��ȡ��ǰҳҳ��
	 * 
	 * @return ��ǰҳҳ��
	 */
	public int getPageNumber(){
		try{
			return Integer.parseInt(pagingParam.getParam(PAGE_NUMBER_VAR));
		}catch(Exception e){
			return 1;
		}
	}

	/**
	 * ��ȡ��ҳ��С
	 * 
	 * @return ��ҳ��С
	 */
	public int getPageSize(){
		try{
			return Integer.parseInt(pagingParam.getParam(PAGE_SIZE_VAR));
		}catch(Exception e){
			return DEFAULT_PAGE_SIZE;
		}
	}

	/**
	 * ��ȡ��ͷ�������б�
	 * 
	 * @return �б���ʹΪ��Ҳ���᷵��null
	 */
	public List<Pair> getSorts(){
		List<Pair> l = new ArrayList<Pair>();
		String s = pagingParam.getParam(PAGE_SORT_VAR);
		if(null == s)
			return l;
		s = s.trim();
		if("".equals(s))
			return l;
		if(!s.contains(":"))
			return l;
		if(s.contains(";")){
			String[] ps = s.split(";",-1);
			for(String psit:ps){
				String[] pt = psit.split(":",-1);
				l.add(new Pair(pt[0],pt[1]));
			}
		}else{
			String[] pt = s.split(":",-1);
			l.add(new Pair(pt[0],pt[1]));
		}
		return l;
	}

	/**
	 * ��ȡ��ҳ����
	 * 
	 * @return ��ҳ����
	 */
	public PagingParam getPagingParam(){
		return pagingParam;
	}

	/**
	 * ���÷�ҳ����
	 * 
	 * @param pagingParam ��ҳ����
	 */
	public void setPagingParam(PagingParam pagingParam){
		this.pagingParam = pagingParam;
	}

}
