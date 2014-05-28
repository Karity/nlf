package nc.liat6.frame.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ���ڹ���
 * 
 * @author ���ض�
 */
public class Dater {

	/** �·� */
	private static final Map<String, String> MONTH = new HashMap<String, String>();
	/** ���� */
	private static final Map<String, String> WEEK = new HashMap<String, String>();
	/** �������� */
	private static final Map<String, String> NUMBER = new HashMap<String, String>();

	static {
		MONTH.put("01", "JAN");
		MONTH.put("02", "FEB");
		MONTH.put("03", "MAR");
		MONTH.put("04", "APR");
		MONTH.put("05", "MAY");
		MONTH.put("06", "JUN");
		MONTH.put("07", "JUL");
		MONTH.put("08", "AUG");
		MONTH.put("09", "SEP");
		MONTH.put("10", "OCT");
		MONTH.put("11", "NOV");
		MONTH.put("12", "DEC");

		MONTH.put("JAN", "01");
		MONTH.put("FEB", "02");
		MONTH.put("MAR", "03");
		MONTH.put("APR", "04");
		MONTH.put("MAY", "05");
		MONTH.put("JUN", "06");
		MONTH.put("JUL", "07");
		MONTH.put("AUG", "08");
		MONTH.put("SEP", "09");
		MONTH.put("OCT", "10");
		MONTH.put("NOV", "11");
		MONTH.put("DEC", "12");

		WEEK.put(Calendar.SUNDAY + "", "��");
		WEEK.put(Calendar.MONDAY + "", "һ");
		WEEK.put(Calendar.TUESDAY + "", "��");
		WEEK.put(Calendar.WEDNESDAY + "", "��");
		WEEK.put(Calendar.THURSDAY + "", "��");
		WEEK.put(Calendar.FRIDAY + "", "��");
		WEEK.put(Calendar.SATURDAY + "", "��");

		NUMBER.put("0", "��");
		NUMBER.put("1", "һ");
		NUMBER.put("2", "��");
		NUMBER.put("3", "��");
		NUMBER.put("4", "��");
		NUMBER.put("5", "��");
		NUMBER.put("6", "��");
		NUMBER.put("7", "��");
		NUMBER.put("8", "��");
		NUMBER.put("9", "��");
	}

	private Dater() {
	}

	/**
	 * ��ȡ��ǰʱ��
	 * 
	 * @return ��ǰʱ��
	 */
	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * ��yyyy-MM-dd��yyyy/MM/dd��ʽ�������ַ���ת��Ϊ��������
	 * 
	 * @param ymd
	 *            yyyy-MM-dd��yyyy/MM/dd��ʽ���ַ���
	 * @return ����
	 * @throws ParseException
	 *             ����ת���쳣
	 */
	public static Date ymd2Date(String ymd) throws ParseException {
		String nymd = ymd.trim();
		if(!nymd.contains("-")&&!nymd.contains("/")){
			throw new ParseException("���ڸ�ʽ�޷�ת����" + ymd, 0);
		}
		String year = "";
		if(nymd.contains("-")){
			year = nymd.substring(0,nymd.indexOf("-"));
		}else if(nymd.contains("/")){
			year = nymd.substring(0,nymd.indexOf("/"));
		}
		if(year.length()==2){
			int ny = Integer.parseInt("20"+year);
			int ly = Integer.parseInt("19"+year);
			int ty = Dater.year(Dater.now());
			if(Math.abs(ny-ty)<Math.abs(ly-ty)){
				nymd = "20"+nymd;
			}else{
				nymd = "19"+nymd;
			}
		}
		String[] f = { "yyyy-MM-dd", "yyyy/MM/dd" };
		for (String o : f) {
			try {
				return parse(nymd, o);
			} catch (ParseException e) {
			}
		}
		throw new ParseException("���ڸ�ʽ�޷�ת����" + ymd, 0);
	}

	/**
	 * ��yyyy-MM-dd HH:mm:ss��ʽ�������ַ���ת��Ϊ��������
	 * 
	 * @param ymdhms
	 *            yyyy-MM-dd HH:mm:ss��ʽ���ַ���
	 * @return ����
	 * @throws ParseException
	 *             ����ת���쳣
	 */
	public static Date ymdhms2Date(String ymdhms)
			throws ParseException {
		return parse(ymdhms, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * ��ָ����ʽ�ַ���ת��Ϊ����
	 * 
	 * @param s
	 *            �ַ���
	 * @param pattern
	 *            ��ʽ
	 * @return ����
	 * @throws ParseException
	 */
	public static Date parse(String s, String pattern)
			throws ParseException {
		return new SimpleDateFormat(pattern).parse(s);
	}

	/**
	 * ������ת����yyyy-MM-dd��ʽ���ַ���
	 * 
	 * @param date
	 *            ����
	 * @return yyyy-MM-dd��ʽ���ַ���
	 */
	public static String ymd(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * ������ת����HH:mm:ss��ʽ���ַ���
	 * 
	 * @param date
	 *            ����
	 * @return HH:mm:ss��ʽ���ַ���
	 */
	public static String hms(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * ������ת����yyyy-MM-dd HH:mm:ss��ʽ���ַ���
	 * 
	 * @param date
	 *            ����
	 * @return yyyy-MM-dd HH:mm:ss��ʽ���ַ���
	 */
	public static String ymdhms(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * ������ת����ָ����ʽ���ַ���
	 * 
	 * @param date
	 *            ����
	 * @return ָ����ʽ���ַ���
	 */
	public static String format(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * ָ��������ǰ��
	 * 
	 * @param type
	 *            ���ͣ��ꡢ�¡��յ�
	 * @param date
	 *            �ο�����
	 * @param length
	 *            ����
	 * @return ��ǰ�Ƶ�����
	 */
	public static Date previous(int type, Date date, int length) {
		return next(type, date, 0 - length);
	}

	/**
	 * ָ������������
	 * 
	 * @param type
	 *            ���ͣ��ꡢ�¡��յ�
	 * @param date
	 *            �ο�����
	 * @param length
	 *            ����
	 * @return �����Ƶ�����
	 */
	public static Date next(int type, Date date, int length) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(type, length);
		return c.getTime();
	}

	/**
	 * ���ǰ���������
	 * 
	 * @param date
	 *            �ο�����
	 * @return ǰ���������
	 */
	public static Date previous(Date date, int days) {
		return previous(Calendar.DATE, date, days);
	}

	/**
	 * ��ú��������
	 * 
	 * @param date
	 *            �ο�����
	 * @return ���������
	 */
	public static Date next(Date date, int days) {
		return next(Calendar.DATE, date, days);
	}

	/**
	 * �·������Ƶ�����
	 * 
	 * @param date
	 *            �ο�����
	 * @param months
	 *            �ƺ󼸸���
	 * @return �·������Ƶ�����
	 */
	public static Date nextMonth(Date date, int months) {
		return next(Calendar.MONTH, date, months);
	}

	/**
	 * �·���ǰ�Ƶ�����
	 * 
	 * @param date
	 *            �ο�����
	 * @param months
	 *            ǰ�Ƽ�����
	 * @return �·���ǰ�Ƶ�����
	 */
	public static Date previousMonth(Date date, int months) {
		return previous(Calendar.MONTH, date, months);
	}

	/**
	 * ��������Ƶ�����
	 * 
	 * @param date
	 *            �ο�����
	 * @param months
	 *            �ƺ󼸸���
	 * @return �·������Ƶ�����
	 */
	public static Date nextYear(Date date, int years) {
		return next(Calendar.YEAR, date, years);
	}

	/**
	 * �����ǰ�Ƶ�����
	 * 
	 * @param date
	 *            �ο�����
	 * @param months
	 *            ǰ�Ƽ�����
	 * @return �·���ǰ�Ƶ�����
	 */
	public static Date previousYear(Date date, int years) {
		return previous(Calendar.YEAR, date, years);
	}

	/**
	 * ĳ���ڵ�ĳ���͵���ֵ
	 * 
	 * @param type
	 *            ���ͣ��ꡢ�¡��յ�
	 * @param date
	 *            ����
	 * @return ��ֵ
	 */
	public static int get(int type, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(type);
	}

	/**
	 * ��ȡ���
	 * 
	 * @param date
	 *            ����
	 * @return ���
	 */
	public static int year(Date date) {
		return get(Calendar.YEAR, date);
	}

	/**
	 * ��ȡ�·�
	 * 
	 * @param date
	 *            ����
	 * @return �·ݣ���01��10
	 */
	public static String month(Date date) {
		int m = get(Calendar.MONTH, date) + 1;
		return (m < 10 ? "0" : "") + m;
	}

	/**
	 * ��ȡ��
	 * 
	 * @param date
	 *            ����
	 * @return �죬��01��23
	 */
	public static String day(Date date) {
		int d = get(Calendar.DATE, date);
		return (d < 10 ? "0" : "") + d;
	}

	/**
	 * ����ת��ΪDDMMM��ʽ
	 * 
	 * @param ymd
	 *            YYYY-MM-DD��ʽ���ַ���
	 * @return 1MAY10 01MAY10 1MAY2010 01MAY2010
	 */
	public static String dm(String ymd) {
		String[] n = ymd.split("-");
		return n[2] + MONTH.get(n[1]) + n[0].substring(2);
	}

	/**
	 * �����ַ���ת��ΪYYYY-MM-DD��ʽ
	 * 
	 * @param dayMonthYear
	 *            ֧�ֵĲ�����ʽ: 1MAY 01MAY 1MAY10 01MAY10 1MAY2010
	 *            01MAY2010�������ִ�Сд������ת����Ϊ2010-05-01
	 * @return YYYY-MM-DD��ʽ���ַ���
	 */
	public static String ymd(String dayMonthYear) {
		return ymd(dayMonthYear, "20");
	}

	/**
	 * �����ַ���ת��ΪYYYY-MM-DD��ʽ
	 * 
	 * @param dayMonthYear
	 *            ֧�ֵĲ�����ʽ: 1MAY 01MAY 1MAY10 01MAY10 1MAY2010 01MAY2010
	 * @param yearPrefix
	 *            ���ǰ׺����yearPrefix=19,1MAY09=1909-05-01
	 * @return YYYY-MM-DD��ʽ���ַ���
	 */
	public static String ymd(String dayMonthYear, String yearPrefix) {
		String s = dayMonthYear.toUpperCase();
		switch (s.length()) {
		case 4:
			s = "0" + s;
			s = s + Dater.year(Dater.now());
			break;
		case 5:
			s = s + Dater.year(Dater.now());
			break;
		case 6:
			s = "0" + s;
			s = s.substring(0, 5) + yearPrefix + s.substring(5);
			break;
		case 7:
			s = s.substring(0, 5) + yearPrefix + s.substring(5);
			break;
		case 8:
			s = "0" + s;
			break;
		}
		s = s.substring(5) + "-" + MONTH.get(s.substring(2, 5)) + "-"
				+ s.substring(0, 2);
		return s;
	}

	/**
	 * ��ȡĳ���ڵ��������ı�ʾ
	 * 
	 * @param date
	 *            ����
	 * @return �ա�һ�����������ġ��塢��
	 */
	public static String weekCH(Date date) {
		return WEEK.get(get(Calendar.DAY_OF_WEEK, date)+"");
	}

	
	/**
	 * ��ȡĳ���ڵ��·����ı�ʾ
	 * 
	 * @param date
	 *            ����
	 * @return һ�����������ġ��塢��
	 */
	public static String monthCH(Date date) {
		return new String[] { "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ",
				"ʮһ", "ʮ��" }[get(Calendar.MONTH, date)];
	}

	/**
	 * ��ȡĳ���ڵ�������ı�ʾ
	 * 
	 * @param date
	 *            ����
	 * @return ��ݣ������һ��
	 */
	public static String yearCH(Date date) {
		String[] y = (year(date) + "").split("");
		StringBuffer s = new StringBuffer();
		for (String o : y) {
			s.append(null == NUMBER.get(o) ? "" : NUMBER.get(o));
		}
		return s.toString();
	}
	
}