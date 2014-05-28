package nc.liat6.frame.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ��¼ID������
 * 
 * <p>
 * Created on 2010-04-01
 * </p>
 * 
 * @author ���ض�
 * @version 1.0
 */
public class ID{

	/** ÿ���������� */
    private static int serial = 0;

    /** ��ǰʱ���ַ��� */
    private static String time = "";

    /** ��������λ�� */
    private static final int DIGIT = 3;
    
    private ID(){}

    /**
     * ��ȡһ���µĲ��ظ���ID
     * 
     * @return ����������
     */
    public synchronized static BigDecimal next(){
        String s = "";
        String t = new SimpleDateFormat("yyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        if(!t.equals(time)){
            time = t;
            serial = (int)Math.round(Math.random()*5);
        }
        int max = (int) Math.pow(10, DIGIT);
        if(++serial >= max){
        	try{
        		Thread.sleep(1000L);
        	}catch(InterruptedException e){}
        	return next();
        }
        s += serial;
        while(s.length() < DIGIT){
            s = "0" + s;
        }
        long a =Long.parseLong(time + s);
        return  BigDecimal.valueOf(a);
    }
}