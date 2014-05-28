package nc.liat6.frame.validate;

/**
 * ���򹤾�
 * 
 * @author 6tail
 * 
 */
public class RegUtil{

	/** 0��9������ */
	public static final String NUMBER = "^[0-9]*$";

	/** ������ */
	public static final String INTEGER_POSITIVE = "^[1-9]\\d*$";

	/** ������ */
	public static final String INTEGER_NEGTIVE = "^-[1-9]\\d*$";

	/** ���� */
	public static final String INTEGER = "^-?[1-9]\\d*$";

	/** �Ǹ����� */
	public static final String INTEGER_NOT_NEGTIVE = "^[1-9]\\d*|0$";

	/** �������� */
	public static final String INTEGER_NOT_POSITIVE = "^-[1-9]\\d*|0$";

	/** �������� */
	public static final String FLOAT_POSITIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";

	/** �������� */
	public static final String FLOAT_NEGTIVE = "^-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)$";

	/** ������ */
	public static final String FLOAT = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";

	/** �Ǹ������� */
	public static final String FLOAT_NOT_NEGTIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";

	/** ���������� */
	public static final String FLOAT_NOT_POSITIVE = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";

	/** ��ĸ */
	public static final String LETTER = "^[A-Za-z]+$";

	/** ��д��ĸ */
	public static final String LETTER_UPPERCASE = "^[A-Z]+$";

	/** Сд��ĸ */
	public static final String LETTER_LOWERCASE = "^[a-z]+$";

	/** �����ʼ� */
	public static final String EMAIL = "^\\w+([-+\\.]\\w+)*@\\w+([-\\.]\\w+)*\\.\\w+([-\\.]\\w+)*$";
	
	/** �ֻ����� */
	public static final String MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
}
