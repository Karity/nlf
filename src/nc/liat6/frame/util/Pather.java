package nc.liat6.frame.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import nc.liat6.frame.Version;

/**
 * ·������
 * 
 * @author 6tail
 * 
 */
public class Pather{

  private Pather(){}
  /** ��ǰ��ܰ�����·�� */
  public static String FRAME_JAR_PATH;
  static{
    try{
      String p = null;
      URL url = Version.class.getProtectionDomain().getCodeSource().getLocation();
      p = URLDecoder.decode(url.getPath(),"UTF-8");
      // class����
      if(p.endsWith(".class")){
        p = p.replace(Version.PACKAGE.replace(".","/"),"");
        p = p.substring(0,p.lastIndexOf("/")+1);
      }
      FRAME_JAR_PATH = new File(p).getAbsolutePath();
    }catch(UnsupportedEncodingException e){
      throw new RuntimeException(e);
    }
  }

  /**
   * ָ�������ڵİ��ľ���·��������ദ���ļ����У��򷵻������ڰ��ĸ�·��;�����jar�������jarEnabled����ȷ������jar���ڵ��ļ��л���jar��
   * 
   * @param className ����
   * @param jarEnabled �Ƿ���jarģʽ true����jar false����jar����Ŀ¼
   * @return ·��
   */
  public static String getPath(String className,boolean jarEnabled){
    String p = null;
    Class<?> cls;
    try{
      cls = Class.forName(className);
    }catch(ClassNotFoundException e){
      throw new RuntimeException(e);
    }
    URL url = cls.getClassLoader().getResource("/");
    if(null==url){
      url = cls.getProtectionDomain().getCodeSource().getLocation();
    }
    try{
      p = URLDecoder.decode(url.getPath(),"UTF-8");
    }catch(UnsupportedEncodingException e){
      throw new RuntimeException(e);
    }
    if(jarEnabled){
      return new File(p).getAbsolutePath();
    }
    // jar����
    if(p.endsWith(".jar")){
      p = p.substring(0,p.lastIndexOf("/")+1);
    }
    return new File(p).getAbsolutePath();
  }
}