package nc.liat6.frame.web.response;

import nc.liat6.frame.paging.PageData;
import nc.liat6.frame.web.tags.paging.PagingTag;

/**
 * ���� - ��ҳ
 * 
 * @author liat6
 * 
 */
public class Paging extends Page{

  public Paging(){
    super();
  }

  public Paging(String uri){
    super(uri);
  }

  public Paging(String uri,PageData pd){
    super(uri);
    setPageData(pd);
  }

  /**
   * ���÷�ҳ����
   * 
   * @param pd ��ҳ���ݶ���
   */
  public void setPageData(PageData pd){
    set(PagingTag.PAGE_DATA_VAR,pd);
  }
}
