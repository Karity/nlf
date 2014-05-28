package nc.liat6.frame.klass;

import java.util.Comparator;
import nc.liat6.frame.Version;

/**
 * ��Ƚ������������Ƚϣ�Խ���Խǰ������������Ƚϣ���������������⣨����ڵ���ʼ�����ڿ���������棩��
 * @author 6tail
 *
 */
public class ClassNameComparator implements Comparator<String>{

	public int compare(String o1,String o2){
		String pkg = Version.class.getPackage().getName();
		if(o2.startsWith(pkg)&&!o1.startsWith(pkg)){
			return -1;
		}
		if(o1.startsWith(pkg)&&!o2.startsWith(pkg)){
			return 1;
		}
		String s2 = o2.contains(".")?o2.substring(o2.lastIndexOf(".")):o2;
		String s1 = o2.contains(".")?o1.substring(o1.lastIndexOf(".")):o1;
		return s2.compareTo(s1);
	}

}
