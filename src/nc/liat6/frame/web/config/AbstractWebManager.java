package nc.liat6.frame.web.config;

/**
 * WEB����������
 * 
 * @author 6tail
 * 
 */
public abstract class AbstractWebManager implements IWebManager{

  /** WEB���� */
  protected IWebConfig config;

  protected AbstractWebManager(IWebConfig config){
    this.config = config;
  }
}
