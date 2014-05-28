package nc.liat6.frame.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import nc.liat6.frame.exception.NlfException;

/**
 * �ַ�������
 * 
 * @author 6tail
 * 
 */
public class Stringer{

	private Stringer(){}
	
	/**
	 * �ü��ַ���
	 * @param s ԭʼ�ַ���
	 * @param start ��ʼ�ַ���
	 * @param end ����ʼ�ַ�����ʼ��һ�������Ľ����ַ���
	 * @return ����ʼ�ַ����������ַ���֮����ַ�������������ʼ�ַ����ͽ����ַ���
	 */
	public static String cut(String s,String start,String end){
		int idx = s.indexOf(start);
		if(idx > -1){
			String k = s.substring(idx + start.length());
			k = k.substring(0,k.indexOf(end));
			return k;
		}
		return "";
	}
	
	/**
	 * �ü��ַ���
	 * @param s ԭʼ�ַ���
	 * @param start ��ʼ�ַ���
	 * @return ����ʼ�ַ�����ʼ���ַ�������������ʼ�ַ���
	 */
	public static String cut(String s,String start){
		int idx = s.indexOf(start);
		if(idx > -1){
			String k = s.substring(idx + start.length());
			return k;
		}
		return "";
	}
	
	/**
	 * ���ַ��������Լ��������Ϊ�ַ���
	 * @param arrays �ַ�������
	 * @param tag �����
	 * @return ���Ӻ���ַ���
	 */
	public static String join(String[] arrays,String tag){
		if(null==arrays) return null;
		StringBuffer s = new StringBuffer();
		for(int i=0;i<arrays.length;i++){
			s.append(arrays[i]);
			if(i<arrays.length-1){
				s.append(tag);
			}
		}
		return s.toString();
	}
	
	/**
	 * ���ַ����б��Լ��������Ϊ�ַ���
	 * @param list �ַ����б�
	 * @param tag �����
	 * @return ���Ӻ���ַ���
	 */
	public static String join(List<String> list,String tag){
		if(null==list) return null;
		StringBuffer s = new StringBuffer();
		for(int i=0;i<list.size();i++){
			s.append(list.get(i));
			if(i<list.size()-1){
				s.append(tag);
			}
		}
		return s.toString();
	}

	/**
	 * �ַ����洮
	 * 
	 * @param os ԭ�ַ��������ΪNULL������NULL
	 * @param tag ��Ҫ�滻���ַ����б�
	 * @param rs �滻Ϊ
	 * @return �滻����ַ���
	 */
	public static String replace(String os,String[] tag,String rs){
		if(null == os){
			return os;
		}
		String ns = os;
		for(String t:tag){
			ns = ns.replace(t,rs);
		}
		return ns;
	}

	/**
	 * �ַ����滻
	 * 
	 * @param os ԭ�ַ��������ΪNULL������NULL
	 * @param tag ��Ҫ�滻���ַ���
	 * @param rs �滻Ϊ
	 * @return �滻����ַ���
	 */
	public static String replace(String os,String tag,String rs){
		return replace(os,new String[]{tag},rs);
	}

	/**
	 * �ַ�����ָ���ָ���ת��Ϊ�б�
	 * 
	 * @param os ԭ�ַ���
	 * @param spliter �ָ���
	 * @param allowRepeat �Ƿ������ظ�
	 * @return ת������б�
	 */
	public static List<String> list(String os,String spliter,boolean allowRepeat){
		List<String> l = new ArrayList<String>();
		if(null == os){
			return l;
		}
		String[] cl = os.split(spliter);
		for(String s:cl){
			s = s.trim();
			if(s.length() > 0){
				if(allowRepeat){
					l.add(s);
				}else{
					if(!l.contains(s)){
						l.add(s);
					}
				}
			}
		}
		return l;
	}

	/**
	 * �ַ�����ָ���ָ���ת��Ϊ���ظ����б�
	 * 
	 * @param os ԭ�ַ���
	 * @param spliter �ָ���
	 * @return ת������б�
	 */
	public static List<String> list(String os,String spliter){
		return list(os,spliter,false);
	}

	/**
	 * ��AJAX�ύ�������ݽ��н���
	 * 
	 * @param s ԭ�ַ���
	 * @return �������ַ���
	 */
	public static String ajax(String s){
		try{
			return null == s?null:java.net.URLDecoder.decode(s,"UTF-8");
		}catch(UnsupportedEncodingException e){
			throw new NlfException(e);
		}
	}

	/**
	 * ��ָ���ַ�������ת��
	 * 
	 * @param s Ҫת����ַ���
	 * @param ocharSet ԭ����
	 * @param ncharSet �±���
	 * @return ת�����ַ���
	 */
	public static String encode(String s,String ocharSet,String ncharSet){
		try{
			return s == null?s:new String(s.getBytes(ocharSet),ncharSet);
		}catch(UnsupportedEncodingException e){
			throw new NlfException(e);
		}
	}

	/**
	 * ռλ���
	 * 
	 * @param s
	 * @param obj
	 * @return
	 */
	public static String print(String s,Object... obj){
		String rs = s;
		if(null == s || s.length() < 1){
			rs = "";
			for(int i = 0;i < obj.length;i++){
				rs += "?";
				if(i < obj.length - 1){
					rs += " ";
				}
			}
		}
		StringBuffer r = new StringBuffer();
		for(int i = 0;i < obj.length;i++){
			int idx = rs.indexOf("?");
			r.append(rs.substring(0,idx));
			r.append(obj[i] + "");
			rs = rs.substring(idx + "?".length());
		}
		r.append(rs);
		return r.toString();
	}

	/**
	 * ���ַ�������MD5����
	 * 
	 * @param s ԭ��
	 * @return ����,��д��ʽ
	 * @throws Exception
	 */
	public static String md5(String s) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(s.getBytes());
		byte[] b = md.digest();
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < b.length;i++){
			String hex = Integer.toHexString(b[i] & 0xFF);
			hex = (hex.length() == 1?"0":"") + hex;
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * ��ȡ�ļ����ݵ��ַ���
	 * @param file �ļ�
	 * @param encode ����
	 * @return �ļ�����
	 * @throws IOException
	 */
	public static String readFromFile(File file,String encode) throws IOException{
		StringBuffer s = new StringBuffer();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encode));
			String line = null;
			while(null != (line = br.readLine())){
				s.append(line + "\r\n");
			}
			return s.toString();
		}finally{
			try{
				if(null != br){
					br.close();
				}
			}catch(Exception e){}
		}
	}
	
	/**
	 * ��ȡ�ļ����ݵ��ַ���
	 * @param file �ļ�·��
	 * @param encode ����
	 * @return �ļ�����
	 * @throws IOException
	 */
	public static String readFromFile(String file,String encode) throws IOException{
		return readFromFile(new File(file),encode);
	}
	
	public static String readFromFile(String file) throws IOException{
		return readFromFile(file,"GBK");
	}
	
	public static String readFromFile(File file) throws IOException{
		return readFromFile(file,"GBK");
	}
	
	/**
	 * ���ַ���д���ļ�
	 * @param s �ַ���
	 * @param file �ļ�
	 * @param encode ����
	 * @throws IOException
	 */
	public static void writeToFile(String s,File file,String encode) throws IOException{
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),encode));
			bw.write(s);
			bw.flush();
		}finally{
			try{
				if(null != bw){
					bw.close();
				}
			}catch(Exception e){}
		}
	}
	
	/**
	 * ���ַ���д���ļ�
	 * @param s �ַ���
	 * @param file �ļ�·��
	 * @param encode ����
	 * @throws IOException
	 */
	public static void writeToFile(String s,String file,String encode) throws IOException{
		writeToFile(s,new File(file),encode);
	}
	
	public static void writeToFile(String s,String file) throws IOException{
		writeToFile(s,file,"GBK");
	}
	
	public static void writeToFile(String s,File file) throws IOException{
		writeToFile(s,file,"GBK");
	}
}
