package nc.liat6.frame.validate;

import nc.liat6.frame.exception.BadException;

/**
 * ��֤��
 * @author 6tail
 *
 */
public class Validator{

	/**
	 * ��飬��֤ʧ�ܣ��׳�BadException
	 * @param s ����ֵ
	 * @param rd �����ݣ����ڷ���
	 * @param rule ��֤���򣬿��Զ��
	 */
	public static void check(String s,Object rd,IValidatorRule... rule){
		for(IValidatorRule r:rule){
			if(!r.validate(s)){
				BadException be = new BadException(r.getErrorMessage());
				be.setData(rd);
				throw be;
			}
		}
	}
	
	/**
	 * ��飬��֤ʧ�ܣ��׳�BadException
	 * @param s ����ֵ
	 * @param rule ��֤���򣬿��Զ��
	 */
	public static void check(String s,IValidatorRule... rule){
		check(s,null,rule);
	}

}
