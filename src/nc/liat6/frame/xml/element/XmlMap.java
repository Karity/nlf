package nc.liat6.frame.xml.element;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * XML Map�������ͽڵ�
 * @author 6tail
 *
 */
public class XmlMap extends AbstractElement{
	
	private static final long serialVersionUID = -6974703310055519851L;
	private Map<String,IXmlElement> o = new HashMap<String,IXmlElement>();
	
	/**
	 * �������ƻ�ȡ�ӽڵ㣬��������ڣ�����null
	 * @param key �ӽڵ�����
	 * @return �ӽڵ�
	 */
	public IXmlElement get(String key){
		return o.get(key);
	}
	
	/**
	 * �����ӽڵ㣬�������ָ�����ƣ��򸲸Ǹ��ӽڵ㣬��������ӽڵ�
	 * @param key �Ӽ�������
	 * @param value �ӽڵ�
	 */
	public void set(String key,IXmlElement value){
		o.put(key, value);
	}
	
	/**
	 * ��ȡ�ӽڵ�����Set
	 * @return �ӽڵ�����Set
	 */
	public Set<String> keySet(){
		return o.keySet();
	}

	public XmlType type() {
		return XmlType.MAP;
	}

	public XmlMap toXmlMap() {
		return this;
	}
	
	public String toString(){
		return o.toString();
	}
	
	public IXmlElement select(String path){
		if(null==path){
			return null;
		}
		path = path.trim();
		if(!path.contains(".")){
			return get(path);
		}
		String[] k = path.split("\\.");
		IXmlElement je = get(k[0]);
		if(XmlType.MAP!=je.type()){
			return null;
		}
		for(int i=1;i<k.length;i++){
			if(XmlType.MAP!=je.type()){
				return null;
			}
			je = je.toXmlMap().get(k[i]);
		}
		return je;
	}

}
