package nc.liat6.frame.db.custom.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.liat6.frame.db.custom.bean.Table;

/**
 * XML���ݿ⻺��
 * @author 6tail
 *
 */
public class XmlCache{
	
	/** ����أ������ӱ���.�����洢 */
	private static final ThreadLocal<Map<String,Table>> pool = new ThreadLocal<Map<String,Table>>(){
		public HashMap<String,Table> initialValue() {
			return new HashMap<String,Table>();
		}
	};
	
	private XmlCache(){}
	
	/**
	 * ���»����
	 * @param alias ���ӱ���
	 * @param table �����
	 */
	public synchronized static void update(String alias,Table table){
		String key = alias+"."+table.getTable().getName().toUpperCase(); 
		pool.get().put(key,table);
	}
	
	/**
	 * �Ƴ������
	 * @param alias ���ӱ���
	 * @param tableName ����
	 */
	public synchronized static void remove(String alias,String tableName){
		pool.get().remove(alias+"."+tableName.toUpperCase());
	}
	
	/**
	 * �Ƿ���ڻ����
	 * @param alias ���ӱ���
	 * @param tableName
	 * @return true/false
	 */
	public static boolean contains(String alias,String tableName){
		return pool.get().containsKey(alias+"."+tableName.toUpperCase());
	}
	
	/**
	 * ��ȡ�����
	 * @param alias ���ӱ���
	 * @param tableName ����
	 * @return �����
	 */
	public static Table getTable(String alias,String tableName){
		return pool.get().get(alias+"."+tableName.toUpperCase());
	}
	
	
	/**
	 * ��ȡָ�����ӵ����б���
	 * @param alias ����
	 * @return ����
	 */
	public static List<String> getTableNames(String alias){
		List<String> l = new ArrayList<String>();
		for(String k:pool.get().keySet()){
			if(k.startsWith(alias+".")){
				l.add(pool.get().get(k).getTable().getName());
			}
		}
		return l;
	}

}
