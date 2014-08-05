package nc.liat6.frame.util;

import java.math.BigDecimal;

/**
 * ��¼ID������
 * <p>֧��ÿ������һ�����ID����֧�ֲַ�ʽӦ��</p>
 * 
 * @author ���ض�
 */
public class ID{

  /** �������� */
  private static int serial = 0;
  /** ��ǰʱ�� */
  private static long time = 0;
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
    long t = System.currentTimeMillis();
    if(time!=t){
      time = t;
      serial = 0;
    }else{
      int max = (int)Math.pow(10,DIGIT);
      if(serial>=max-1){
        while(t==time){
          t = System.currentTimeMillis();
        }
        time = t;
        serial = 0;
      }else{
        serial++;
      }
    }
    s += serial;
    while(s.length()<DIGIT){
      s = "0"+s;
    }
    return BigDecimal.valueOf(Long.parseLong(time+s));
  }
}