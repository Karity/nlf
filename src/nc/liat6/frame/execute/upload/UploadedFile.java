package nc.liat6.frame.execute.upload;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ���ϴ��ļ���װ
 * 
 * @author 6tail
 * 
 */
public class UploadedFile{

  /** ��������С */
  public static final int BUFFER_SIZE = 20480;
  /** �ļ�����,������׺������������·�� */
  private String name;
  /** �ļ���С����λ���ֽ� */
  private long size;
  /** �ļ����� */
  private String contentType;
  /** �ļ���׺��Сд��ĸ����������� */
  private String suffix = "";
  /** ������ */
  private InputStream inputStream;

  /**
   * ��ȡ�ļ�����
   * 
   * @return �ļ�����
   */
  public String getName(){
    return name;
  }

  /**
   * �����ļ�����
   * 
   * @param name �ļ�����
   */
  public void setName(String name){
    this.name = name;
  }

  /**
   * ��ȡ�ļ����ݣ�����ļ��ϴ󣬲�������ø÷���
   * 
   * @return �ļ�����
   */
  public byte[] getBody() throws IOException{
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buffer = new byte[BUFFER_SIZE];
    int l = 0;
    while((l = inputStream.read(buffer))!=-1){
      baos.write(buffer,0,l);
    }
    byte[] r = baos.toByteArray();
    baos.close();
    return r;
  }

  /**
   * д���ļ�
   * 
   * @param file Ŀ���ļ�
   * @throws IOException
   */
  public void writeTo(File file) throws IOException{
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
    byte[] buffer = new byte[BUFFER_SIZE];
    int l = 0;
    while((l = inputStream.read(buffer))!=-1){
      bos.write(buffer,0,l);
    }
    bos.close();
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public InputStream getInputStream(){
    return inputStream;
  }

  /**
   * ����������
   * 
   * @param inputStream
   */
  public void setInputStream(InputStream inputStream){
    this.inputStream = inputStream;
  }

  /**
   * ��ȡ�ļ���С
   * 
   * @return �ļ���С����λ���ֽ�
   */
  public long getSize(){
    return size;
  }

  /**
   * �����ļ���С
   * 
   * @param size �ļ���С����λ���ֽ�
   */
  public void setSize(long size){
    this.size = size;
  }

  /**
   * ��ȡ�ļ�����
   * 
   * @return �ļ�����
   */
  public String getContentType(){
    return contentType;
  }

  /**
   * �����ļ�����
   * 
   * @param contentType �ļ�����
   */
  public void setContentType(String contentType){
    this.contentType = contentType;
  }

  /**
   * ��ȡ�ļ���׺
   * 
   * @return �ļ���׺��Сд��ĸ�����������
   */
  public String getSuffix(){
    return suffix;
  }

  /**
   * �����ļ���׺
   * 
   * @param suffix �ļ���׺��Сд��ĸ�����������
   */
  public void setSuffix(String suffix){
    this.suffix = suffix;
  }
}
