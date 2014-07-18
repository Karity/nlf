package nc.liat6.frame.util;

import java.util.ArrayList;
import java.util.List;

/**
 * �ֽ�����
 * 
 * @author 6tail
 * 
 */
public class ByteArray{

  /** �ֽڻ��� */
  private List<Byte> l = new ArrayList<Byte>();

  public ByteArray(){}

  public ByteArray(byte[] d){
    add(d);
  }

  /**
   * ���
   */
  public void clear(){
    l.clear();
  }

  /**
   * ����
   * 
   * @param index �±�
   * @param b �ֽ�
   */
  public void set(int index,byte b){
    l.set(index,b);
  }

  /**
   * �ܴ�С
   * 
   * @return �ܴ�С
   */
  public int size(){
    return l.size();
  }

  /**
   * ��ȡָ���±���ֽ�
   * 
   * @param index �±�
   * @return �ֽ�
   */
  public byte get(int index){
    return l.get(index);
  }

  public byte tail(){
    return l.get(l.size()-1);
  }

  /**
   * ��ȡ
   * @param fromIndex 
   * @param toIndex
   * @return
   */
  public ByteArray sub(int fromIndex,int toIndex){
    ByteArray ba = new ByteArray();
    List<Byte> sl = l.subList(fromIndex,toIndex);
    for(int i = 0;i<sl.size();i++){
      ba.add(sl.get(i).byteValue());
    }
    return ba;
  }

  /**
   * ��ĩβ����ֽ�
   * 
   * @param b �ֽ�
   */
  public void add(byte b){
    l.add(b);
  }

  /**
   * ��ĩβ����ֽ�����
   * 
   * @param data �ֽ�����
   */
  public void add(byte[] data){
    for(byte b:data){
      add(b);
    }
  }

  /**
   * ת��Ϊ����
   * 
   * @return �ֽ�����
   */
  public byte[] toArray(){
    byte[] b = new byte[l.size()];
    for(int i = 0;i<l.size();i++){
      b[i] = l.get(i).byteValue();
    }
    return b;
  }

  /**
   * byte[]���ֵ��±꣬��������ڣ�����-1
   * @param bytes byte[]
   * @return
   */
  public int indexOf(byte[] bytes){
    int m = bytes.length;
    int n = l.size();
    int index = 0;
    for(int i = 0;i<n;i++){
      byte b = l.get(i);
      if(b!=bytes[index]){
        index = 0;
      }
      if(b==bytes[index]){
        index++;
        if(index>=m){
          return i-index+1;
        }
      }
    }
    return -1;
  }

  public String toString(){
    StringBuilder s = new StringBuilder();
    for(int i = 0;i<l.size();i++){
      s.append(l.get(i));
      if(i<l.size()-1){
        s.append(",");
      }
    }
    return s.toString();
  }

  public boolean equals(ByteArray ba){
    if(null==ba){
      return false;
    }
    int len = size();
    if(len!=ba.size()){
      return false;
    }
    for(int i = 0;i<len;i++){
      if(get(i)!=ba.get(i)){
        return false;
      }
    }
    return true;
  }
}