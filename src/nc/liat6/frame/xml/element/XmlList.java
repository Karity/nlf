package nc.liat6.frame.xml.element;

import java.util.ArrayList;
import java.util.List;

/**
 * XML List���ͽڵ�
 * @author 6tail
 *
 */
public class XmlList extends AbstractElement{
	
	private static final long serialVersionUID = -5572136068816099645L;
	private List<IXmlElement> l = new ArrayList<IXmlElement>();
	
	/**
	 * ��ȡ�ӽڵ�����
	 * @return �ӽڵ�����
	 */
	public int size(){
		return l.size();
	}
	
	/**
	 * �Ƴ�ָ���±�Ľڵ�
	 * @param index �±�
	 * @return �ڵ�
	 */
	public IXmlElement remove(int index){
		return l.remove(index);
	}
	
	/**
	 * ��ȡָ���±�Ľڵ�
	 * @param index �±�
	 * @return �ڵ�
	 */
	public IXmlElement get(int index){
		return l.get(index);
	}

	public XmlType type() {
		return XmlType.LIST;
	}
	
	/**
	 * ��ĩβ�����һ���ڵ�
	 * @param o �ڵ�
	 */
	public void add(IXmlElement o){
		l.add(o);
	}
	
	public XmlList toXmlList() {
		return this;
	}
	
	public String toString(){
		return l.toString();
	}
	
	public IXmlElement select(String path){
		if(null==path){
			return null;
		}
		path = path.trim();
		return get(Integer.parseInt(path));
	}

}
