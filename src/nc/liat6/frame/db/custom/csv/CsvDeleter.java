package nc.liat6.frame.db.custom.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nc.liat6.frame.csv.CSVFileReader;
import nc.liat6.frame.csv.CSVWriter;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IDeleter;
import nc.liat6.frame.db.plugin.Rule;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.locale.LocaleFactory;
import nc.liat6.frame.log.Logger;
import nc.liat6.frame.util.Stringer;

/**
 * CSVɾ����
 * 
 * @author 6tail
 * 
 */
public class CsvDeleter extends CsvExecuter implements IDeleter{

	protected List<Rule> conds = new ArrayList<Rule>();

	public IDeleter table(String tableName){
		initTable(tableName);
		return this;
	}

	public IDeleter where(String sql){
		Logger.getLog().warn(Stringer.print("??",L.get(LocaleFactory.locale,"sql.cond_not_support"),sql));
		return this;
	}

	public IDeleter whereSql(String sql,Object[] values){
		Logger.getLog().warn(Stringer.print("??",L.get(LocaleFactory.locale,"sql.cond_not_support"),sql));
		return this;
	}

	public IDeleter where(String column,Object value){
		Rule r = new Rule();
		r.setColumn(column);
		r.setOpStart("=");
		r.setOpEnd("");
		r.setTag("");
		conds.add(r);
		params.add(value);
		return this;
	}

	public IDeleter whereIn(String column,Object... value){
		Rule r = new Rule();
		r.setColumn(column);
		r.setOpStart("in");
		r.setOpEnd("");
		r.setTag("");
		conds.add(r);
		List<Object> l = objectsToList(value);
		params.add(l);
		return this;
	}

	public IDeleter whereNotIn(String column,Object... value){
		Rule r = new Rule();
		r.setColumn(column);
		r.setOpStart("not_in");
		r.setOpEnd("");
		r.setTag("");
		conds.add(r);
		List<Object> l = objectsToList(value);
		params.add(l);
		return this;
	}

	public int delete(){
		File file = getTableFile();
		CSVFileReader cr = new CSVFileReader(file);
		int deleted = 0;
		try{
			String[] head = readHead(cr,file);

			File f = new File(file.getAbsolutePath() + ".tmp");
			CSVWriter cw = new CSVWriter(f);
			cw.writeLine(head);
			if(conds.size() > 0){
				outer:for(int i = 1;i < cr.getLineCount();i++){
					String[] data = cr.getLine(i);
					Bean o = new Bean();
					for(int j = 0;j < head.length;j++){
						String s = head[j].toUpperCase();
						if(data.length >= j){
							o.set(s,data[j]);
						}else{
							o.set(s,"");
						}
					}
					// ��������������������д���ļ�
					for(int j = 0;j < conds.size();j++){
						Rule r = conds.get(j);
						// ��������
						String op = r.getOpStart();
						// ���
						String v = o.getString(r.getColumn().toUpperCase(),"");
						// ����
						String p = params.get(j) + "";
						if("=".equals(op)){
							if(v.equals(p)){
								continue outer;
							}
						}else if("!=".equals(op)){
							if(!v.equals(p)){
								continue outer;
							}
						}else if("like".equalsIgnoreCase(op)){
							if(v.indexOf(p) > -1){
								continue outer;
							}
						}else if("left_like".equalsIgnoreCase(op)){
							if(v.startsWith(p)){
								continue outer;
							}
						}else if("right_like".equalsIgnoreCase(op)){
							if(v.endsWith(p)){
								continue outer;
							}
						}else if("in".equalsIgnoreCase(op)){
							List<?> in = (List<?>)params.get(j);
							boolean isIn = false;
							in:for(Object m:in){
								if(v.equals(m + "")){
									isIn = true;
									break in;
								}
							}
							if(isIn){
								continue outer;
							}
						}else if("not_in".equalsIgnoreCase(op)){
							List<?> in = (List<?>)params.get(j);
							boolean isIn = false;
							in:for(Object m:in){
								if(v.equals(m + "")){
									isIn = true;
									break in;
								}
							}
							if(!isIn){
								continue outer;
							}
						}
					}
					cw.writeLine(data);
				}
			}
			cr.close();
			cw.flush();
			cw.close();

			cw = new CSVWriter(file);
			cr = new CSVFileReader(f);
			for(int i = 0;i < cr.getLineCount();i++){
				String[] data = cr.getLine(i);
				cw.writeLine(data);
			}
			cr.close();
			cw.flush();
			cw.close();
			f.delete();
		}catch(IOException e){
			throw new DaoException(L.get("sql.file_write_error") + file.getAbsolutePath(),e);
		}
		reset();
		return deleted;
	}

	public void reset(){
		conds.clear();
		params.clear();
	}

}
