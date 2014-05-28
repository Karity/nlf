package nc.liat6.frame.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * CSV��ʽд�룬֧����������ļ����
 * @author 6tail
 *
 */
public class CSVWriter{

	/** �س��� */
	public static String CR = "\r";

	/** ���з� */
	public static String LF = "\n";

	/** �м���� */
	public static final String SPACE = ",";

	/** ˫���� */
	public static final String QUOTE = "\"";

	private BufferedWriter writer;

	public CSVWriter(OutputStreamWriter writer){
		this.writer = new BufferedWriter(writer);
	}

	public CSVWriter(File file) throws IOException{
		this(new FileWriter(file));
	}

	/**
	 * flush
	 * @throws IOException
	 */
	public void flush() throws IOException{
		writer.flush();
	}

	/**
	 * close
	 * @throws IOException
	 */
	public void close() throws IOException{
		writer.close();
	}

	/**
	 * д��һ������
	 * @param cols һ������
	 * @throws IOException
	 */
	public void writeLine(String[] cols) throws IOException{
		StringBuffer s = new StringBuffer();
		for(int i = 0;i < cols.length;i++){
			String o = cols[i];
			String ro = o;
			if(null == o){
				ro = "";
			}else{
				boolean needQuote = false;
				if(o.contains(QUOTE)){
					ro = o.replace(QUOTE,QUOTE + QUOTE);
					needQuote = true;
				}
				if(o.contains(CR) || o.contains(LF) || o.contains(SPACE)){
					needQuote = true;
				}
				if(needQuote){
					ro = QUOTE + ro + QUOTE;
				}
			}

			s.append(ro);
			if(i < cols.length - 1){
				s.append(SPACE);
			}
		}
		s.append(CR);
		s.append(LF);
		writer.write(s.toString());
	}

	/**
	 * д��һ������
	 * @param cols һ������
	 * @throws IOException
	 */
	public void writeLine(List<String> cols) throws IOException{
		String[] line = new String[cols.size()];
		for(int i = 0;i < cols.size();i++){
			line[i] = cols.get(i);
		}
		writeLine(line);
	}

}
