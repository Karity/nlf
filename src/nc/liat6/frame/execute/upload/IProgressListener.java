package nc.liat6.frame.execute.upload;

/**
 * �ļ��ϴ����ȼ���
 * 
 * @author 6tail
 * 
 */
public interface IProgressListener{

	/**
	 * �����ϴ�״̬
	 * 
	 * @param uploadedBytes ���ϴ��ֽ�
	 * @param totalBytes ���ֽ�
	 */
	public void update(long uploadedBytes,long totalBytes);

}
