package nc.liat6.frame.web.tags.paging;

import nc.liat6.frame.paging.PageData;
import nc.liat6.frame.paging.PagingParam;

/**
 * ��ҳģ���ṩ��
 * 
 * @author 6tail
 * 
 */
public interface IPagingTemplateProvider{

  /**
   * ��ȡ��ͨҳ��ķ�ҳģ��
   * 
   * @param pd ��ҳ����
   * @param pr ��ҳ����
   * @param near ����ҳ��
   * @return html����
   */
  public String getNormalTemplate(PageData pd,PagingParam pr,int near);

  /**
   * ��ȡAJAX����ҳ��ķ�ҳģ��
   * 
   * @param pd ��ҳ����
   * @param pr ��ҳ����
   * @param near ����ҳ��
   * @return html����
   */
  public String getAjaxTemplate(PageData pd,PagingParam pr,int near);
}
