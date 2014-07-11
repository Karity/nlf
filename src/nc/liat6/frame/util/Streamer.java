package nc.liat6.frame.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ������
 * 
 * @author 6tail
 * 
 */
public class Streamer{

  public static final int BUFFER_SIZE = 10240;

  private Streamer(){}

  /**
   * ��������ת��Ϊbyte����
   * 
   * @param is ������
   * @return byte����
   * @throws IOException
   */
  public static byte[] toByte(InputStream is) throws IOException{
    return toByte(is,BUFFER_SIZE);
  }

  /**
   * ��������ת��Ϊbyte����
   * 
   * @param is ������
   * @param bufferSize ��������С
   * @return byte����
   * @throws IOException
   */
  public static byte[] toByte(InputStream is,int bufferSize) throws IOException{
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    int n = 0;
    byte b[] = new byte[bufferSize];
    while((n = is.read(b))!=-1){
      os.write(b,0,n);
    }
    os.close();
    is.close();
    return os.toByteArray();
  }
}
