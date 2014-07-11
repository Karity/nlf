package nc.liat6.frame.web.upload.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * �ϴ�����
 * 
 * @author 6tail
 * 
 */
public class UploadRule{

  /** ���������ֽ�����-1��ʽ������ */
  private int maxSize = -1;
  /** ������ļ������б��ޱ�ʾȫ������ */
  private List<String> allows = new ArrayList<String>();

  public List<String> getAllows(){
    return allows;
  }

  public void setAllows(List<String> allows){
    this.allows = allows;
  }

  public void addAllow(String allow){
    allows.add(allow);
  }

  public int getMaxSize(){
    return maxSize;
  }

  public void setMaxSize(int maxSize){
    this.maxSize = maxSize;
  }
}
