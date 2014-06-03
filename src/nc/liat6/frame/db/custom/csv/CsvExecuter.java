package nc.liat6.frame.db.custom.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nc.liat6.frame.csv.CSVFileReader;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IExecuter;
import nc.liat6.frame.db.sql.ITemplate;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.log.Logger;
import nc.liat6.frame.util.Stringer;

/**
 * CSVִ����
 * @author 6tail
 *
 */
public abstract class CsvExecuter implements ICsv,IExecuter{

	/**����*/
	protected String tableName;
	protected ITemplate template;
	protected List<Object> params = new ArrayList<Object>();

	public Object[] getParam(){
		return params.toArray();
	}
	
	public String getSql(){
		return null;
	}

	public void setTemplate(ITemplate template){
		this.template = template;
	}

	public ITemplate getTemplate(){
		return template;
	}

	/**
	 * ��ʼ����
	 * @param tableName ����
	 */
	protected void initTable(String tableName){
		tableName = tableName.toUpperCase();
		this.tableName = tableName;
		getTableFile();
	}
	
	/**
	 * ��ȡ���ļ�����������ڣ��Զ�����
	 * @return ���ļ�
	 */
	protected File getTableFile(){
		File dir = new File(template.getConnVar().getSetting().getDbName());
		File file = new File(dir,tableName+".csv");
		if(!file.exists()){
			try{
				file.createNewFile();
				Logger.getLog().debug(L.get("sql.table_created")+file.getAbsolutePath());
			}catch(IOException e){
				throw new DaoException(L.get("sql.file_write_error")+file.getAbsolutePath(),e);
			}
		}
		return file;
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
	
	/**
	 * ��ȡ����
	 * @param cr
	 * @return ����
	 * @throws IOException 
	 */
	protected String[] readHead(CSVFileReader cr,File file) throws IOException{
		if(null == tableName){
			throw new DaoException(Stringer.print("??.?",L.get("sql.table_not_found"),template.getConnVar().getAlias(),tableName));
		}
		String[] head = null;
		try{
			if(cr.getLineCount()>0){
				head = cr.getLine(0);
			}
		}catch(IOException e){
			throw new DaoException(L.get("sql.file_read_error")+file.getAbsolutePath(),e);
		}
		if(null==head){
			throw new DaoException(L.get("sql.file_read_error")+file.getAbsolutePath());
		}
		return head;
	}
	
}
