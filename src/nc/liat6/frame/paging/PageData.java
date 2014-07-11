package nc.liat6.frame.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.util.Pair;

/**
 * ��ҳ��ǰҳ���ݷ�װ
 * 
 * @author 6tail
 * 
 */
public class PageData implements Serializable{

  private static final long serialVersionUID = -5088644434609874737L;
  /** ÿҳ��¼�� */
  private int pageSize;
  /** �ܼ�¼�� */
  private int recordCount;
  /** �ڼ�ҳ */
  private int pageNumber;
  /** �����ֶΣ�ֵΪASC��DESC����д����NAME=ASC */
  private List<Pair> sorts = new ArrayList<Pair>();
  /** ��ҳ���� */
  private List<?> data;

  public PageData(){}

  public PageData(List<?> data,int pageSize,int pageNumber,int recordCount){
    setData(data);
    setPageSize(pageSize);
    setPageNumber(pageNumber);
    setRecordCount(recordCount);
  }

  /**
   * ��ȡÿҳ��¼��
   * 
   * @return ÿҳ��¼��
   */
  public int getPageSize(){
    return pageSize;
  }

  /**
   * ����ÿҳ��¼��
   * 
   * @param pageSize ÿҳ��¼��
   */
  public void setPageSize(int pageSize){
    this.pageSize = pageSize<1?1:pageSize;
  }

  /**
   * ��ȡ�ܼ�¼��
   * 
   * @return �ܼ�¼��
   */
  public int getRecordCount(){
    return recordCount;
  }

  /**
   * �����ܼ�¼��
   * 
   * @param recordCount �ܼ�¼��
   */
  public void setRecordCount(int recordCount){
    this.recordCount = recordCount<0?0:recordCount;
  }

  /**
   * ��ȡ��ҳ��
   * 
   * @return ��ҳ��
   */
  public int getPageCount(){
    return (recordCount<1||pageSize<1)?1:(int)Math.ceil(recordCount*1D/pageSize);
  }

  /**
   * ��ȡǰһҳҳ��
   * 
   * @return ǰһҳҳ��
   */
  public int getPreviousPageNumber(){
    return pageNumber-1<1?1:pageNumber-1;
  }

  /**
   * ��ȡ��һҳҳ��
   * 
   * @return ҳ��
   */
  public int getNextPageNumber(){
    return pageNumber+1>getPageCount()?getPageCount():pageNumber+1;
  }

  /**
   * ��ȡ��һҳҳ��
   * 
   * @return ҳ��
   */
  public int getFirstPageNumber(){
    return 1;
  }

  /**
   * ��ȡ���ҳҳ��
   * 
   * @return ҳ��
   */
  public int getLastPageNumber(){
    return getPageCount();
  }

  /**
   * ��ȡ���ڵ�ҳ��
   * 
   * @param size ҳ�����
   * @return ���ڵ�ҳ������
   */
  public int[] getNearPageNumbers(int count){
    int start = pageNumber-count;
    int end = pageNumber+count;
    start = start<1?1:start;
    end = end>getPageCount()?getPageCount():end;
    int[] m = new int[end+1-start];
    for(int i = 0;i<m.length;i++){
      m[i] = start+i;
    }
    return m;
  }

  /**
   * ��ȡ��ҳ��������
   * 
   * @return ��ҳ��������
   */
  public int getSize(){
    return null==data?0:data.size();
  }

  /**
   * ��ȡ��ҳҳ��
   * 
   * @return ҳ��
   */
  public int getPageNumber(){
    return pageNumber;
  }

  /**
   * ���ø�ҳҳ��
   * 
   * @param pageNumber ҳ��
   */
  public void setPageNumber(int pageNumber){
    this.pageNumber = pageNumber<1?1:pageNumber;
  }

  /**
   * ��ȡ��ҳ����
   * 
   * @return ��ҳ����
   */
  public List<?> getData(){
    return data;
  }

  /**
   * ���ø�ҳ����
   * 
   * @param data ��ҳ����
   */
  public void setData(List<?> data){
    this.data = data;
  }

  /**
   * ��ȡָ��Bean
   * 
   * @param index ����
   * @return Bean
   */
  public Bean getBean(int index){
    return (Bean)data.get(index);
  }

  /**
   * ��ȡ�����ֵ���б�
   * 
   * @return �����ֵ���б�
   */
  public List<Pair> getSorts(){
    return sorts;
  }

  /**
   * ���������ֵ���б�
   * 
   * @param sorts �����ֵ���б�
   */
  public void setSorts(List<Pair> sorts){
    this.sorts = sorts;
  }

  /**
   * ��ȡ������ĳ�е�ֵ
   * 
   * @param column �м���
   * @return ֵ
   */
  public String getSort(String column){
    for(Pair p:sorts){
      if(null!=p&&null!=p.getKey()&&p.getKey().equals(column)){
        return p.getValue();
      }
    }
    return null;
  }

  /**
   * ��������ֵ��
   * 
   * @param key ��
   * @param value ֵ
   */
  public void addSort(String key,String value){
    if(null!=key){
      sorts.add(new Pair(key,value));
    }
  }

  /**
   * ��������ֵ��
   */
  public void clearSorts(){
    sorts.clear();
  }

  /**
   * ��ȡ��ֵ�Ե��ַ�����ʾ
   * 
   * @return ��ֵ�Ե��ַ�����ʾ�����֮���Էֺż������ֵ֮����ð�ż��
   */
  public String getSortsAsString(){
    StringBuilder s = new StringBuilder();
    int n = 0;
    for(Pair p:sorts){
      if(n>0){
        s.append(";");
      }
      s.append(p.getKey());
      s.append(":");
      s.append(p.getValue());
      n++;
    }
    return s.toString();
  }
}