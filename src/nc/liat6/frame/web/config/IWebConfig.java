package nc.liat6.frame.web.config;

import java.util.List;
import java.util.Map;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.IExecute;

/**
 * WEB���ýӿ�
 * 
 * @author 6tail
 * 
 */
public interface IWebConfig{

  /**
   * ��ȡӦ�ø�·����ʶ���緵��PATH����jspҳ���п�ʹ��${PATH}����Ӧ��·��
   * 
   * @return Ӧ�ø�·����ʶ,�������null����ʹ�ÿ��Ĭ�ϱ�ʶ
   * @see Statics
   */
  public String getAppRootTag();

  /**
   * ��ȡ����ҳ���ַ
   * 
   * @return ����ҳ���ַ���������null����ʹ�ÿ��Ĭ��
   */
  public String getErrorPage();

  /**
   * ��ȡȫ�ֱ��������صĽ�������õ�application�У�������WEB_NAME="NLF"����jspҳ����${WEB_NAME}="NLF"
   * 
   * @return ȫ�ֱ������������null����ʹ�ÿ��Ĭ��
   */
  public Map<String,Object> getGlobalVars();

  /**
   * ��ȡ��ֹ���ʵ�·���б�
   * 
   * @return ��ֹ���ʵ�·���б�
   */
  public List<String> getForbiddenPaths();

  /**
   * ��ȡ������ʵ�·���б����ȼ��Ƚ�ֹ���ʵĸ�
   * 
   * @return ������ʵ�·���б�
   */
  public List<String> getAllowPaths();

  /**
   * ��ȡWEB������
   * 
   * @return WEB������
   */
  public IWebManager getWebManager();

  /**
   * WEBӦ�ó�ʼ��ʱ�Ĳ���
   */
  public void init();

  /**
   * WEBӦ��������ִ�еĲ���
   */
  public void start();

  /**
   * ��ȡִ����
   * 
   * @return ִ����
   */
  public IExecute getExecuter();
}
