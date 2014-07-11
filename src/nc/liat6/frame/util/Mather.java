package nc.liat6.frame.util;

/**
 * ���㸨������
 * 
 * @author 6tail
 * 
 */
public class Mather{

  private Mather(){}

  /**
   * ����ת4λbyte
   * 
   * @param value ��������
   * @return 4λbyte
   */
  public static byte[] toByte(int value){
    byte[] b = new byte[4];
    for(int i = 0;i<4;i++){
      int offset = (b.length-1-i)*8;
      b[i] = (byte)((value>>>offset)&0xFF);
    }
    return b;
  }

  /**
   * 4λbyteת����
   * 
   * @param b 4λbyte
   * @return ����
   */
  public static int toInt(byte[] b){
    return (b[0]<<24)+((b[1]&0xFF)<<16)+((b[2]&0xFF)<<8)+(b[3]&0xFF);
  }

  /**
   * ����byte����Խ�
   * 
   * @param a ǰ
   * @param b ��
   * @return �ԽӺ������
   */
  public static byte[] merge(byte[] a,byte[] b){
    byte[] t = new byte[a.length+b.length];
    System.arraycopy(a,0,t,0,a.length);
    System.arraycopy(b,0,t,a.length,b.length);
    return t;
  }

  /**
   * ��ȡһ��byte�����һ����
   * 
   * @param src Դ����
   * @param from ��ʼ�㣬��0��ʼ����
   * @param to �����㣬��0��ʼ����
   * @return byte����
   */
  public static byte[] sub(byte[] src,int from,int to){
    byte[] t = new byte[to-from+1];
    System.arraycopy(src,from,t,0,to-from+1);
    return t;
  }
}