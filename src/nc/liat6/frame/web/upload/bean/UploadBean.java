package nc.liat6.frame.web.upload.bean;

/**
 * �ϴ��ļ�״̬��װ
 * 
 * @author 6tail
 * 
 */
public class UploadBean{

  /** �ļ���ʶ */
  private String id;
  /** ���ϴ��ֽ��� */
  private long uploaded;
  /** ���ֽ��� */
  private long total;

  /**
   * ��ȡ�ļ���ʶ
   * 
   * @return Ψһ��ʶ
   */
  public String getId(){
    return id;
  }

  /**
   * �����ļ���ʶ
   * 
   * @param id Ψһ��ʶ
   */
  public void setId(String id){
    this.id = id;
  }

  /**
   * ��ȡ���ϴ��ֽ���
   * 
   * @return ���ϴ��ֽ���
   */
  public long getUploaded(){
    return uploaded;
  }

  /**
   * �������ϴ��ֽ���
   * 
   * @param uploaded ���ϴ��ֽ���
   */
  public void setUploaded(long uploaded){
    this.uploaded = uploaded;
  }

  /**
   * ��ȡ���ֽ���
   * 
   * @return ���ֽ���
   */
  public long getTotal(){
    return total;
  }

  /**
   * �������ֽ���
   * 
   * @param total ���ֽ���
   */
  public void setTotal(long total){
    this.total = total;
  }
}
