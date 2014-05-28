package nc.liat6.frame.validate.rule;

import nc.liat6.frame.validate.RegUtil;

/**
 * Ӣ����ĸ
 * @author 6tail
 *
 */
public class RuleLetter extends RuleRegex{

	public RuleLetter(String item){
		super(item,RegUtil.LETTER);
	}
	
	public RuleLetter(){
		this("");
	}
	
	/**
	 * ��д��ĸ
	 * @author 6tail
	 *
	 */
	public static class LetterUpperCase extends RuleRegex{
		public LetterUpperCase(String item){
			super(item,RegUtil.LETTER_UPPERCASE);
		}
		
		public LetterUpperCase(){
			this("");
		}
	}
	
	/**
	 * Сд��ĸ
	 * @author 6tail
	 *
	 */
	public static class LetterLowserCase extends RuleRegex{
		public LetterLowserCase(String item){
			super(item,RegUtil.LETTER_UPPERCASE);
		}
		
		public LetterLowserCase(){
			this("");
		}
	}

}
