package nc.liat6.frame.db.setting;

import java.io.File;
import java.io.FileFilter;

/**
 * �����ļ�����
 * @author 6tail
 *
 */
public class SettingFileFilter implements FileFilter {

	public boolean accept(File f) {
		return !f.isDirectory();
	}

}
