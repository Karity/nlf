package nc.liat6.frame.db.plugin.impl;

import java.util.ArrayList;
import java.util.List;

import nc.liat6.frame.db.sql.ITemplate;

/**
 * ִ��������
 * @author 6tail
 *
 */
public abstract class SuperExecuter{
	
	protected ITemplate template = null;
	protected StringBuffer s = new StringBuffer();
	protected List<Object> params = new ArrayList<Object>();
	
	public SuperExecuter(){}
	
	public Object[] getParam(){
		return params.toArray();
	}

	public ITemplate getTemplate(){
		return template;
	}

	public void setTemplate(ITemplate template){
		this.template = template;
	}
	
	/**
	 * ȫ�����ã�����SQL���Ͱ󶨱���
	 */
	protected void reset(){
		resetSql();
		resetParams();
	}
	
	/**
	 * ����SQL
	 */
	protected void resetSql(){
		s = new StringBuffer();
	}
	
	/**
	 * ���ð󶨱���
	 */
	protected void resetParams(){
		params.clear();
	}
	
	/**
	 * �ɱ䳤����תList��Object����Ҳ��ת��
	 * @param value Object����Object����
	 * @return List
	 */
	protected List<Object> objectsToList(Object... value){
		List<Object> l = new ArrayList<Object>();
		for(int i = 0;i < value.length;i++){
			if (value[i] instanceof Object[]) {
				Object[] ps = (Object[]) value[i];
				for(Object p:ps){
					l.add(p);
				}
			}else{
				l.add(value[i]);
			}
		}
		return l;
	}

}
