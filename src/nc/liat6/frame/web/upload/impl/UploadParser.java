package nc.liat6.frame.web.upload.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.exception.BadUploadException;
import nc.liat6.frame.execute.upload.IProgressListener;
import nc.liat6.frame.execute.upload.UploadedFile;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.util.ByteArray;
import nc.liat6.frame.util.ID;
import nc.liat6.frame.util.Mather;
import nc.liat6.frame.util.Stringer;
import nc.liat6.frame.web.upload.IParser;
import nc.liat6.frame.web.upload.bean.UploadRule;

/**
 * ͨ�����̻�����ļ��ϴ�������
 * <p>
 * ֻʵ���˵����ļ����ϴ���������ļ��ᱻ���ԡ�
 * </p>
 * <p>
 * ����ֻ��ȡ�����������body�ܴ�С�������ļ����ϴ����ȵ���ֵ�����Ǵ����ļ��Ĵ�С��
 * </p>
 * 
 * @author 6tail
 * 
 */
public class UploadParser implements IParser{

  /** �س� */
  public static final byte CR = 0x0D;
  /** ���� */
  public static final byte LF = 0x0A;
  /** - */
  public static final byte DASH = 0x2D;
  /** ��������С */
  public static final int BUFFER_SIZE = 20480;
  /** ͷ���ָ� */
  public static final byte[] HEADER_SEPARATOR = {CR,LF,CR,LF};
  /** ��ָ� */
  protected static final byte[] FIELD_SEPARATOR = {CR,LF};
  /** boundaryǰ׺ */
  public static final byte[] BOUNDARY_PREFIX = {CR,LF,DASH,DASH};
  /** ��ʱ�ļ�Ŀ¼ */
  public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
  public static final String BOUNDARY_TAG = "boundary=";
  public static final String FILE_TAG = "filename=\"";
  /** ��ǰ�ֽ� */
  private int c;
  /** �ϴ����ȼ���������ʱû�кõİ취��ȡ�ļ���С���ʲ��Ǻ�׼ȷ */
  private IProgressListener listener;
  private HttpServletRequest request;
  private ServletInputStream reader;

  /**
   * ��ȡboundary
   * 
   * @return boundary
   */
  private String getBoundary(){
    String contentType = request.getContentType();
    if(null==contentType){
      return null;
    }
    if(!contentType.contains("multipart/form-data")){
      return null;
    }
    if(!contentType.contains(BOUNDARY_TAG)){
      return null;
    }
    return contentType.substring(contentType.indexOf(BOUNDARY_TAG)+BOUNDARY_TAG.length());
  }

  /**
   * ��ȡ��һ�ֽ�
   */
  private void next() throws IOException{
    c = reader.read();
  }

  /**
   * ��ȡ��ֱ������������ʶ���ֽ�����
   * 
   * @param endTags ������ʶ���ֽ�����
   * @return �Ѷ�ȡ���ֽ�����
   * @throws IOException
   */
  private byte[] readUntil(byte[] endTags) throws IOException{
    int index = 0;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    while(-1!=c){
      next();
      baos.write(c);
      if(c==endTags[index]){
        index++;
        if(index>=endTags.length){
          break;
        }
      }else{
        index = 0;
      }
    }
    byte[] r = baos.toByteArray();
    baos.close();
    return r;
  }

  /**
   * ������ֱ������������ʶ���ֽ�����
   * 
   * @param endTags ������ʶ���ֽ�����
   * @return l ���������ֽ���
   * @throws IOException
   */
  private int skipUntil(byte[] endTags) throws IOException{
    int l = 0;
    int index = 0;
    while(-1!=c){
      next();
      l++;
      if(c==endTags[index]){
        index++;
        if(index>=endTags.length){
          break;
        }
      }else{
        index = 0;
      }
    }
    return l;
  }

  public UploadedFile parseRequest(HttpServletRequest request,UploadRule rule){
    this.request = request;
    // ��ȡboundary
    String boundary = getBoundary();
    if(null==boundary){
      return null;
    }
    byte[] boundaryBytes = boundary.getBytes();
    // �ܵ�����body��С
    int total = request.getContentLength();
    if(rule.getMaxSize()>-1){
      if(total>rule.getMaxSize()){
        throw new BadUploadException(L.get("upload.max_size")+(rule.getMaxSize()*100/1024/100D)+"KB");
      }
    }
    // ���ϴ��ֽ���
    int uploaded = 0;
    // �Ƿ����ļ���
    boolean fileField = false;
    listener.update(uploaded,total);
    UploadedFile uf = new UploadedFile();
    try{
      reader = request.getInputStream();
      while(!fileField){
        // ��ȡÿ���ͷ����Ϣ
        byte[] headBytes = readUntil(HEADER_SEPARATOR);
        uploaded += headBytes.length;
        // ���зָ�
        String[] heads = new String(headBytes,Statics.ENCODE).split("\r\n");
        for(String s:heads){
          // �ļ��ж�
          if(s.contains(FILE_TAG)){
            fileField = true;
            String fileName = Stringer.cut(s,FILE_TAG,"\"");
            int index = fileName.lastIndexOf("\\");
            if(index>-1){
              fileName = fileName.substring(index+1);
            }
            index = fileName.lastIndexOf("/");
            if(index>-1){
              fileName = fileName.substring(index+1);
            }
            uf.setName(fileName);
            index = fileName.lastIndexOf(".");
            if(index>-1){
              uf.setSuffix(fileName.substring(index+1).toLowerCase());
            }
            if(rule.getAllows().size()>0){
              if(!rule.getAllows().contains(uf.getSuffix())){
                throw new BadUploadException(L.get("upload.formats")+Stringer.join(rule.getAllows(),","));
              }
            }
          }else if(s.contains("Content-Type:")){
            uf.setContentType(Stringer.cut(s,":").trim());
          }
        }
        // �������ļ��飬�Ժ���޸ģ������ݵĲ���
        if(!fileField){
          uploaded += skipUntil(FIELD_SEPARATOR);
        }
      }
      // ���������ı�ʶ
      byte[] endBytes = Mather.merge(BOUNDARY_PREFIX,boundaryBytes);
      ByteArray array = new ByteArray();
      short count = 0;
      byte[] buffer = new byte[BUFFER_SIZE];
      int l = 0;
      File tempFile = new File(TEMP_DIR,ID.next()+"");
      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile));
      while((l = reader.read(buffer))!=-1){
        uploaded += l;
        listener.update(uploaded,total);
        ByteArray temp = new ByteArray(buffer);
        temp = temp.sub(0,l);
        array.add(temp.toArray());
        if(count>0){
          int index = array.indexOf(endBytes);
          if(index>-1){
            array = array.sub(0,index);
          }
          count = 0;
          bos.write(array.toArray());
          bos.flush();
          array.clear();
        }else{
          count++;
        }
      }
      int index = array.indexOf(endBytes);
      if(index>-1){
        array = array.sub(0,index);
      }
      bos.write(array.toArray());
      bos.flush();
      bos.close();
      uf.setSize(tempFile.length());
      // �ϴ���ɣ���������
      listener.update(total,total);
      uf.setInputStream(new FileInputStream(tempFile));
    }catch(IOException e){
      throw new BadUploadException(e);
    }
    return uf;
  }

  public IProgressListener getProgressListener(){
    return listener;
  }

  public void setProgressListener(IProgressListener listener){
    this.listener = listener;
  }
}
