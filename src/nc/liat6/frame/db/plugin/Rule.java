package nc.liat6.frame.db.plugin;

/**
 * ����
 * @author 6tail
 *
 */
public class Rule{

	/**������ʼ*/
	private String opStart = "";
	
	/**�ֶ�*/
	private String column;
	
	/**��������*/
	private String opEnd = "";
	
	/**��ֵ*/
	private String tag = "?";

	public String getOpStart(){
		return opStart;
	}

	public void setOpStart(String opStart){
		this.opStart = opStart;
	}

	public String getOpEnd(){
		return opEnd;
	}

	public void setOpEnd(String opEnd){
		this.opEnd = opEnd;
	}

	public String getColumn(){
		return column;
	}

	public void setColumn(String column){
		this.column = column;
	}

	public String getTag(){
		return tag;
	}

	public void setTag(String tag){
		this.tag = tag;
	}

}
