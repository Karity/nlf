package nc.liat6.frame.validate;

/**
 * ��֤������ӿ�
 * @author 6tail
 *
 */
public interface IValidatorRule{
	
	/**
	 * ��ȡ������Ϣ
	 * @return ������Ϣ
	 */
	public String getErrorMessage();
	
	/**
	 * ���
	 * @param key Ҫ��֤��ֵ
	 * @return �Ƿ���֤ͨ��
	 */
	public boolean validate(String key);

}
