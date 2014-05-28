package nc.liat6.frame.klass;

import java.io.File;
import java.io.FileFilter;

import nc.liat6.frame.locale.LocaleFactory;

/**
 * ���ļ����ˣ�����.class�ļ����ļ���
 * @author 6tail
 *
 */
public class ClassFileFilter implements FileFilter {

	public boolean accept(File f) {
		return f.isDirectory()||f.getName().endsWith(".class")||f.getName().endsWith(LocaleFactory.FILE_SUFFIX);
	}

}
