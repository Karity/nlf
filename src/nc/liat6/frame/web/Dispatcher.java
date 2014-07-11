package nc.liat6.frame.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc.liat6.frame.Factory;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.IExecute;
import nc.liat6.frame.execute.impl.AbstractExecute;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.locale.LocaleFactory;
import nc.liat6.frame.log.Logger;
import nc.liat6.frame.util.Stringer;
import nc.liat6.frame.web.config.ClassMethod;
import nc.liat6.frame.web.config.IWebConfig;
import nc.liat6.frame.web.config.IWebManager;

/**
 * WEBӦ�õ�����
 * 
 * @author 6tail
 * 
 */
public class Dispatcher implements Filter{

  /** WEB���� */
  public static IWebConfig config;

  public void destroy(){}

  private boolean contains(List<String> l,String s){
    for(String o:l){
      if(o.startsWith(s))
        return true;
    }
    return false;
  }

  public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException{
    HttpServletRequest req = (HttpServletRequest)request;
    HttpServletResponse res = (HttpServletResponse)response;
    // ���ñ���
    req.setCharacterEncoding(Statics.ENCODE);
    // ����ԭ����
    Context.set(WebExecute.HTTP_SERVLET_REQUEST,req);
    // ����ԭ��Ӧ
    Context.set(WebExecute.HTTP_SERVLET_RESPONSE,res);
    // ���ù�����
    Context.set(WebExecute.HTTP_FILTERCHAIN,chain);
    // ���ò���
    Map<String,String> args = new HashMap<String,String>();
    Enumeration<?> en = req.getParameterNames();
    while(en.hasMoreElements()){
      String key = (String)en.nextElement();
      args.put(key,req.getParameter(key));
    }
    Context.set(AbstractExecute.EXECUTE_ARGS,args);
    // ���ʵ�ַ
    String path = req.getServletPath();
    // ȥ������/
    while(path.startsWith("//")){
      path = path.substring(1);
    }
    // �������
    if(contains(config.getForbiddenPaths(),path)){
      if(!contains(config.getAllowPaths(),path)){
        return;
      }
    }
    // WEB������
    IWebManager wm = config.getWebManager();
    ClassMethod cm = wm.before(path);
    // ���ܹ����·��
    if(null==cm){
      chain.doFilter(request,response);
      return;
    }
    // WEBִ����
    IExecute executer = config.getExecuter();
    executer.begin();
    Object r = null;
    try{
      r = Factory.getCaller().execute(cm.getKlass(),cm.getMethod());
    }catch(Throwable e){
      r = wm.failed(e);
    }finally{
      wm.after();
    }
    Context.set(WebExecute.EXECUTE_RETURN,r);
    wm.filter();
    executer.end();
    Context.clear();
  }

  public void init(FilterConfig fc) throws ServletException{
    ServletContext ctx = fc.getServletContext();
    // ����ΪWEBӦ��
    WebContext.isWebApp = true;
    WebContext.REAL_PATH = ctx.getRealPath("");
    WebContext.CONTEXT_PATH = ctx.getContextPath();
    // ��ʼ������
    Factory.initWebApp(ctx.getRealPath("/WEB-INF/classes"));
    // WEB���ýӿڳ�ʼ��
    config = Factory.getCaller().newInstance(IWebConfig.class);
    // ���ó�ʼ��
    config.init();
    ctx.setAttribute(config.getAppRootTag(),WebContext.CONTEXT_PATH);
    if(null!=config.getGlobalVars()){
      for(String k:config.getGlobalVars().keySet()){
        Object v = config.getGlobalVars().get(k);
        ctx.setAttribute(k,v);
      }
    }
    Logger.getLog().info(Stringer.print("??\r\n??\r\n??\r\n${?} = ?\r\n",L.get(LocaleFactory.locale,"web.app_path"),WebContext.REAL_PATH,L.get(LocaleFactory.locale,"web.app_config"),config,L.get(LocaleFactory.locale,"web.error_page"),config.getErrorPage(),config.getAppRootTag(),WebContext.CONTEXT_PATH));
    config.start();
  }
}
