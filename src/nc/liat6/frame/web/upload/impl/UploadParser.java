package nc.liat6.frame.web.upload.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import nc.liat6.frame.context.Statics;
import nc.liat6.frame.exception.BadUploadException;
import nc.liat6.frame.execute.upload.IProgressListener;
import nc.liat6.frame.execute.upload.UploadedFile;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.util.ID;
import nc.liat6.frame.util.Mather;
import nc.liat6.frame.web.upload.IParser;
import nc.liat6.frame.web.upload.bean.UploadRule;

/**
 * ͨ�����̻�����ļ��ϴ�������
 * <p>ֻʵ���˵����ļ����ϴ���������ļ��ᱻ���ԡ�</p>
 * <p>����ֻ��ȡ�����������body�ܴ�С�������ļ����ϴ����ȵ���ֵ�����Ǵ����ļ��Ĵ�С��</p>
 * <p>�ϴ��ļ�ͬʱpost�Ĳ����ᱻ���ԡ�</p>
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

	/** ��ǰ�ֽ� */
	private int c;
	/** �ϴ����ȼ���������ʱû�кõİ취��ȡ�ļ���С���ʲ��Ǻ�׼ȷ */
	private IProgressListener progressListener;
	private HttpServletRequest request;
	private ServletInputStream reader;

	/**
	 * ��ȡboundary
	 * 
	 * @return boundary
	 */
	private String getBoundary(){
		String contentType = request.getContentType();
		if(null == contentType){
			return null;
		}
		if(!contentType.contains("multipart/form-data")){
			return null;
		}
		if(!contentType.contains("boundary=")){
			return null;
		}
		return contentType.substring(contentType.indexOf("boundary=") + "boundary=".length());
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
		while(-1 != c){
			next();
			baos.write(c);
			if(c == endTags[index]){
				index++;
				if(index >= endTags.length){
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
		while(-1 != c){
			next();
			l++;
			if(c == endTags[index]){
				index++;
				if(index >= endTags.length){
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
		if(null == boundary){
			return null;
		}
		byte[] boundaryBytes = boundary.getBytes();
		
		// ���������ı�ʶ
		byte[] endBytes = Mather.merge(BOUNDARY_PREFIX,boundaryBytes);
		
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
		progressListener.update(uploaded,total);
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
					if(s.contains("filename=")){
						fileField = true;
						String fileName = s.substring(s.indexOf("filename=\"") + "filename=\"".length());
						fileName = fileName.substring(0,fileName.indexOf("\""));
						if(fileName.indexOf("\\")>-1){
							fileName = fileName.substring(fileName.lastIndexOf("\\")+"\\".length());
						}
						if(fileName.indexOf("/")>-1){
							fileName = fileName.substring(fileName.lastIndexOf("/")+"/".length());
						}
						uf.setName(fileName);
						if(fileName.contains(".")){
							uf.setSuffix(fileName.substring(fileName.lastIndexOf(".") + ".".length()).toLowerCase());
						}
						if(rule.getAllows().size()>0){
							if(!rule.getAllows().contains(uf.getSuffix())){
								StringBuilder sb = new StringBuilder();
								for(int i=0;i<rule.getAllows().size();i++){
									if(i>0){
										sb.append(",");
									}
									sb.append(rule.getAllows().get(i));
								}
								throw new BadUploadException(L.get("upload.formats")+sb.toString());
							}
						}
					}else if(s.contains("Content-Type:")){
						uf.setContentType(s.split(":")[1].trim());
					}
				}
				
				//�������ļ��飬�Ժ���޸ģ������ݵĲ���
				if(!fileField){
					uploaded += skipUntil(FIELD_SEPARATOR);
				}
			}

			int endIndex = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int l = 0;
			File tempFile = new File(TEMP_DIR,ID.next() + "");
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			out:while((l = reader.read(buffer)) != -1){
				byte[] b = new byte[l];
				System.arraycopy(buffer,0,b,0,l);

				uploaded += l;
				progressListener.update(uploaded,total);

				for(int i = 0;i < l;i++){
					if(b[i] == endBytes[endIndex]){
						baos.write(b[i]);
						endIndex++;
						if(endIndex >= endBytes.length){
							//�����ȫ������
							break out;
						}
					}else{
						endIndex = 0;
						bos.write(baos.toByteArray());
						bos.write(b[i]);
						baos.reset();
					}
				}
			}
			baos.close();
			bos.close();
			uf.setSize(tempFile.length());
			// �ϴ���ɣ���������
			progressListener.update(total,total);
			InputStream inputStream = new FileInputStream(tempFile);
			uf.setInputStream(inputStream);
		}catch(IOException e){
			throw new BadUploadException(e);
		}
		return uf;
	}

	public IProgressListener getProgressListener(){
		return progressListener;
	}

	public void setProgressListener(IProgressListener progressListener){
		this.progressListener = progressListener;
	}

}
