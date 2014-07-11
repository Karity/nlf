package nc.liat6.frame.paging;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * ��ҳ�����װ
 * 
 * @author ���ض�
 * 
 */
public class PagingParam implements Serializable{

  private static final long serialVersionUID = -7580050759495785747L;
  /** ��ǰ��������ַ */
  private String uri;
  /** ҳ������� */
  private Map<String,String> params = new HashMap<String,String>();

  /**
   * ��ȡ��ǰ��������ַ
   * 
   * @return ��ǰ��������ַ
   */
  public String getUri(){
    return uri;
  }

  /**
   * ���õ�ǰ��������ַ
   * 
   * @param uri ��ǰ��������ַ
   */
  public void setUri(String uri){
    this.uri = uri;
  }

  /**
   * ��ȡҳ�������
   * 
   * @return ҳ�������
   */
  public Map<String,String> getParams(){
    return params;
  }

  /**
   * ����ҳ�������
   * 
   * @param params ҳ�������
   */
  public void setParams(Map<String,String> params){
    this.params = params;
  }

  /**
   * ����ҳ�����
   * 
   * @param k ��
   * @param v ֵ
   */
  public void setParam(String k,String v){
    params.put(k,v);
  }

  /**
   * ��ȡҳ�����
   * 
   * @param k ��
   */
  public String getParam(String k){
    return params.get(k);
  }
}