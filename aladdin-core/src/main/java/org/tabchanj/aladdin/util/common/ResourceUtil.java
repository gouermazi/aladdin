package org.tabchanj.aladdin.util.common;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceUtil {
	public static Integer getInt(String propertiesFileName, String key) throws IOException {
		InputStream in = new FileInputStream(new File(
				ResourceUtil.class.getClassLoader().getResource(propertiesFileName + ".properties").getFile()));
		Properties properties = new Properties();
		properties.load(in);
		int res = Integer
				.parseInt(StringUtils.isEmpty(properties.getProperty(key)) ? "0" : properties.getProperty(key));
		return res;
	}

	public static String getString(String propertiesFileName, String key) throws IOException {
		InputStream in = new FileInputStream(new File(
				ResourceUtil.class.getClassLoader().getResource(propertiesFileName + ".properties").getFile()));
		Properties properties = new Properties();
		properties.load(in);
		String res = properties.getProperty(key);
		return res;
	}
}
