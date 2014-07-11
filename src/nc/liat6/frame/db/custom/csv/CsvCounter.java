package nc.liat6.frame.db.custom.csv;

import java.io.File;
import java.io.IOException;
import java.util.List;
import nc.liat6.frame.csv.CSVFileReader;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.ICounter;
import nc.liat6.frame.db.plugin.Rule;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.locale.LocaleFactory;
import nc.liat6.frame.log.Logger;
import nc.liat6.frame.util.Stringer;

/**
 * CSV������
 * 
 * @author 6tail
 * 
 */
public class CsvCounter extends CsvExecuter implements ICounter{

  public ICounter table(String tableName){
    initTable(tableName);
    return this;
  }

  public ICounter column(String... column){
    return this;
  }

  public ICounter where(String sql){
    Logger.getLog().warn(Stringer.print("??",L.get(LocaleFactory.locale,"sql.cond_not_support"),sql));
    return this;
  }

  public ICounter whereSql(String sql,Object[] values){
    Logger.getLog().warn(Stringer.print("??",L.get(LocaleFactory.locale,"sql.cond_not_support"),sql));
    return this;
  }

  public ICounter where(String column,Object value){
    Rule r = new Rule();
    r.setColumn(column);
    r.setOpStart("=");
    r.setOpEnd("");
    r.setTag("");
    wheres.add(r);
    paramWheres.add(value);
    return this;
  }

  public ICounter whereLike(String column,Object value){
    Rule r = new Rule();
    r.setColumn(column);
    r.setOpStart("like");
    r.setOpEnd("");
    r.setTag("");
    wheres.add(r);
    paramWheres.add(value);
    return this;
  }

  public ICounter whereLeftLike(String column,Object value){
    Rule r = new Rule();
    r.setColumn(column);
    r.setOpStart("left_like");
    r.setOpEnd("");
    r.setTag("");
    wheres.add(r);
    paramWheres.add(value);
    return this;
  }

  public ICounter whereRightLike(String column,Object value){
    Rule r = new Rule();
    r.setColumn(column);
    r.setOpStart("right_like");
    r.setOpEnd("");
    r.setTag("");
    wheres.add(r);
    paramWheres.add(value);
    return this;
  }

  public ICounter whereNq(String column,Object value){
    Rule r = new Rule();
    r.setColumn(column);
    r.setOpStart("!=");
    r.setOpEnd("");
    r.setTag("");
    wheres.add(r);
    paramWheres.add(value);
    return this;
  }

  public ICounter whereIn(String column,Object... value){
    Rule r = new Rule();
    r.setColumn(column);
    r.setOpStart("in");
    r.setOpEnd("");
    r.setTag("");
    wheres.add(r);
    paramWheres.add(objectsToList(value));
    return this;
  }

  public ICounter whereNotIn(String column,Object... value){
    Rule r = new Rule();
    r.setColumn(column);
    r.setOpStart("not_in");
    r.setOpEnd("");
    r.setTag("");
    wheres.add(r);
    paramWheres.add(objectsToList(value));
    return this;
  }

  public int count(){
    File file = getTableFile();
    CSVFileReader cr = new CSVFileReader(file);
    int count = 0;
    try{
      String[] head = readHead(cr,file);
      outer:for(int i = 1;i<cr.getLineCount();i++){
        String[] data = cr.getLine(i);
        Bean o = new Bean();
        for(int j = 0;j<head.length;j++){
          String s = head[j].toUpperCase();
          if(data.length>=j){
            o.set(s,data[j]);
          }else{
            o.set(s,"");
          }
        }
        // �����������������������������
        for(int j = 0;j<wheres.size();j++){
          Rule r = wheres.get(j);
          // ��������
          String op = r.getOpStart();
          // ���
          String v = o.getString(r.getColumn().toUpperCase(),"");
          // ����
          String p = paramWheres.get(j)+"";
          if("=".equals(op)){
            if(!v.equals(p)){
              continue outer;
            }
          }else if("!=".equals(op)){
            if(v.equals(p)){
              continue outer;
            }
          }else if("like".equalsIgnoreCase(op)){
            if(v.indexOf(p)<0){
              continue outer;
            }
          }else if("left_like".equalsIgnoreCase(op)){
            if(!v.startsWith(p)){
              continue outer;
            }
          }else if("right_like".equalsIgnoreCase(op)){
            if(!v.endsWith(p)){
              continue outer;
            }
          }else if("in".equalsIgnoreCase(op)){
            List<?> in = (List<?>)paramWheres.get(j);
            boolean isIn = false;
            in:for(Object m:in){
              if(v.equals(m+"")){
                isIn = true;
                break in;
              }
            }
            if(!isIn){
              continue outer;
            }
          }else if("not_in".equalsIgnoreCase(op)){
            List<?> in = (List<?>)paramWheres.get(j);
            boolean isIn = false;
            in:for(Object m:in){
              if(v.equals(m+"")){
                isIn = true;
                break in;
              }
            }
            if(isIn){
              continue outer;
            }
          }
        }
        count++;
      }
    }catch(IOException e){
      throw new DaoException(L.get("sql.file_read_error")+file.getAbsolutePath(),e);
    }
    reset();
    return count;
  }
}
