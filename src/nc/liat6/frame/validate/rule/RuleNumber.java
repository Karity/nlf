package nc.liat6.frame.validate.rule;

import nc.liat6.frame.validate.RegUtil;

/**
 * ����
 * 
 * @author 6tail
 * 
 */
public class RuleNumber extends RuleRegex{
	
	/**
	 * ����
	 * @author 6tail
	 *
	 */
	public static class Integer extends RuleRegex{
		public Integer(String item){
			super(item,RegUtil.INTEGER);
		}
		
		public Integer(){
			this("");
		}
	}
	
	/**
	 * ������
	 * @author 6tail
	 *
	 */
	public static class IntegerPositive extends RuleRegex{
		public IntegerPositive(String item){
			super(item,RegUtil.INTEGER_POSITIVE);
		}
		
		public IntegerPositive(){
			this("");
		}
	}
	
	/**
	 * ������
	 * @author 6tail
	 *
	 */
	public static class IntegerNegtive extends RuleRegex{
		public IntegerNegtive(String item){
			super(item,RegUtil.INTEGER_NEGTIVE);
		}
		
		public IntegerNegtive(){
			this("");
		}
	}
	
	/**
	 * ��������
	 * @author 6tail
	 *
	 */
	public static class IntegerNotPositive extends RuleRegex{
		public IntegerNotPositive(String item){
			super(item,RegUtil.INTEGER_NOT_POSITIVE);
		}
		
		public IntegerNotPositive(){
			this("");
		}
	}
	
	/**
	 * �Ǹ�����
	 * @author 6tail
	 *
	 */
	public static class IntegerNotNegtive extends RuleRegex{
		public IntegerNotNegtive(String item){
			super(item,RegUtil.INTEGER_NOT_NEGTIVE);
		}
		
		public IntegerNotNegtive(){
			this("");
		}
	}
	
	/**
	 * ������
	 * @author 6tail
	 *
	 */
	public static class Float extends RuleRegex{
		public Float(String item){
			super(item,RegUtil.FLOAT);
		}
		
		public Float(){
			this("");
		}
	}
	
	/**
	 * ��������
	 * @author 6tail
	 *
	 */
	public static class FloatPositive extends RuleRegex{
		public FloatPositive(String item){
			super(item,RegUtil.FLOAT_POSITIVE);
		}
		
		public FloatPositive(){
			this("");
		}
	}
	
	/**
	 * ��������
	 * @author 6tail
	 *
	 */
	public static class FloatNegtive extends RuleRegex{
		public FloatNegtive(String item){
			super(item,RegUtil.FLOAT_NEGTIVE);
		}
		
		public FloatNegtive(){
			this("");
		}
	}
	
	/**
	 * ����������
	 * @author 6tail
	 *
	 */
	public static class FloatNotPositive extends RuleRegex{
		public FloatNotPositive(String item){
			super(item,RegUtil.FLOAT_NOT_POSITIVE);
		}
		
		public FloatNotPositive(){
			this("");
		}
	}
	
	/**
	 * �Ǹ�������
	 * @author 6tail
	 *
	 */
	public static class FloatNotNegtive extends RuleRegex{
		public FloatNotNegtive(String item){
			super(item,RegUtil.FLOAT_NOT_NEGTIVE);
		}
		
		public FloatNotNegtive(){
			this("");
		}
	}

	public RuleNumber(String item){
		super(item,RegUtil.NUMBER);
	}
	
	public RuleNumber(){
		this("");
	}

}
