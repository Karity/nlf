package nc.liat6.frame.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * CSV�ļ���ȡ
 * 
 * @author 6tail
 * 
 */
public class CSVFileReader{

	/** �ļ��������ǰ�CSV��ʽ���������������һ�����ı����� */
	private int lineCount = -1;

	/** �������ĵ�ǰ���� */
	private int lineNumber = 0;

	/** ��ȡ���ļ� */
	private File file;

	/** CSV��������ȡ */
	private CSVReader reader = null;

	public CSVFileReader(File file){
		this.file = file;
	}

	/**
	 * ��ȡCSV�ļ��е����������������ݿ����л��У��������������һ�����ı�������ͬ
	 * @return ����
	 * @throws IOException
	 */
	public int getLineCount() throws IOException{
		if(-1 != lineCount){
			return lineCount;
		}
		int n = lineNumber;
		if(null == reader){
			n = 0;
			reader = new CSVReader(new FileReader(file));
		}
		try{
			while(null != reader.readLine()){
				n++;
			}
			lineCount = n;
			return n;
		}finally{
			if(null != reader){
				try{
					reader.close();
				}catch(Exception e){}
				reader = null;
			}
			lineNumber = 0;
		}
	}

	/**
	 * ��ȡָ���е����ݣ����Ż������㷨�����Ч��
	 * @param index ��������0��ʼ
	 * @return ������
	 * @throws IOException
	 */
	public String[] getLine(int index) throws IOException{
		if(null != reader && index < lineNumber){
			reader.close();
			reader = null;
		}
		if(null == reader){
			lineNumber = 0;
			reader = new CSVReader(new FileReader(file));
		}
		String[] line;
		while(null != (line = reader.readLine())){
			if(lineNumber == index){
				lineNumber++;
				return line;
			}
			lineNumber++;
		}
		return null;
	}
	
	public void close() throws IOException{
		if(null!=reader){
			reader.close();
			reader =  null;
		}
	}

}
