package nc.liat6.frame.execute.upload;

/**
 * �ļ��ϴ��ӿڣ���֧�ֵ��ļ��ϴ�
 * 
 * @author 6tail
 * 
 */
public interface IUploader{

  /**
   * ��ȡ���ϴ��ļ���װ
   * 
   * @param allow ����ĸ�ʽ
   * @return ���ϴ��ļ���װ
   */
  public UploadedFile getFile(String... allow);

  /**
   * ��ȡ���ϴ��ļ���װ
   * 
   * @param maxSize ���������ֽ���
   * @param allow ����ĸ�ʽ
   * @return ���ϴ��ļ���װ
   */
  public UploadedFile getFile(int maxSize,String... allow);
}
