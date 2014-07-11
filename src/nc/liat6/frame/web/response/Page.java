package nc.liat6.frame.web.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;

/**
 * ���� - ҳ��
 * 
 * @author liat6
 * 
 */
public class Page{

  /** Ĭ�ϴ��ݶ����� */
  public static final String DEFAULT_ATTRIBUTE_NAME = "data";
  /** ҳ���ַ */
  private String uri;
  /** ��Ӧ״̬�� */
  private int status = 200;
  /** ���ݲ��� */
  private Map<String,Object> value = new HashMap<String,Object>();

  public Page(){}

  public Page(String uri){
    this.uri = uri;
  }

  public Page(String uri,Object o){
    this(uri);
    set(DEFAULT_ATTRIBUTE_NAME,o);
  }

  /**
   * ��ȡ��Ӧ״̬��
   * 
   * @return ״̬��
   */
  public int getStatus(){
    return status;
  }

  /**
   * ������Ӧ״̬��
   * 
   * @param status ״̬��
   */
  public void setStatus(int status){
    this.status = status;
  }

  /**
   * ��ȡ����ҳ���ַ
   * 
   * @return ����ҳ���ַ
   */
  public String getUri(){
    return uri;
  }

  /**
   * ���÷���ҳ���ַ
   * 
   * @param uri ����ҳ���ַ
   */
  public void setUri(String uri){
    this.uri = uri;
  }

  /**
   * ���ô��ݲ���
   * 
   * @param key ������
   * @param value ����ֵ
   */
  public void set(String key,Object value){
    this.value.put(key,value);
  }

  /**
   * ��ȡ���ݲ����Ĳ���������
   * 
   * @return
   */
  public Set<String> keySet(){
    return value.keySet();
  }

  /**
   * ��ȡ����ֵ
   * 
   * @param key ������
   * @return ����ֵ
   */
  public Object get(String key){
    return value.get(key);
  }

  /**
   * ���ú�ɽ���һ��ҳ�洫�����Ĳ����Զ����ݵ���һ��ҳ��
   */
  public void deliver(){
    Request request = Context.get(Statics.REQUEST);
    HttpServletRequest r = request.find("request");
    Map<String,String> m = request.getParams();
    for(String key:m.keySet()){
      r.setAttribute(key,m.get(key));
    }
  }
}