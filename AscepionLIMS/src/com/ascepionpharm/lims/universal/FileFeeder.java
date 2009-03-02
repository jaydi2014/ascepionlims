package com.ascepionpharm.lims.universal;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import oracle.sql.BLOB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * FileFeeder: operate the out file about LIMS.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class FileFeeder {
	private static final Log logger = LogFactory.getLog(FileFeeder.class);

	public static void writeFile(File file, String context) {
		try {
			FileOutputStream fp = new FileOutputStream(file, true);
			String line = context + "\r\n";
			fp.write(line.getBytes());
			fp.flush();
			fp.close();
		} catch (FileNotFoundException e) {
			logger.error("can not find file" + e.getMessage());
		} catch (IOException e) {
			logger.error("io exception when writeFile" + e.getMessage());
		}
	}

	public static File writeBFile(Blob blob, String fileName) {
		File file = new File(System.getProperty("catalina.home")
				+ "\\webapps\\AscepionLIMS\\Temp\\" + fileName);
		try {
			InputStream in = blob.getBinaryStream();
			FileOutputStream out = new FileOutputStream(file);
			byte[] bytes = new byte[1024];
			int len = 0;
			while ((len = in.read(bytes)) != -1)
				out.write(bytes, 0, len);
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			logger.error("can not find file" + e.getMessage());
		} catch (IOException e) {
			logger.error("io exception when writeBFile" + e.getMessage());
		} catch (SQLException e) {
			logger.error("sql exception when writeBFile" + e.getMessage());
		}
		return file;
	}

	public static Object readObject(Blob blob) {
		Object obj = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(blob.getBytes(
					1, (int) blob.length()));
			ObjectInputStream obis = new ObjectInputStream(bais);
			obj = obis.readObject();
			bais.close();
			obis.close();

		} catch (ClassNotFoundException e) {
			logger.error("can not find class" + e.getMessage());
		} catch (IOException e) {
			logger.error("io exception when readObject" + e.getMessage());
		} catch (SQLException e) {
			logger.error("sql exception when readObject" + e.getMessage());
		}
		return obj;
	}

	public static void writeObjectToDB(Object obj, BLOB blob) {
		try {
			OutputStream out = blob.getBinaryOutputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			ByteArrayInputStream bis = new ByteArrayInputStream(bos
					.toByteArray());
			int size = blob.getBufferSize();
			byte[] buffer = new byte[size];
			int len;
			while ((len = bis.read(buffer)) != -1)
				out.write(buffer, 0, len);
			bos.close();
			oos.close();
			bis.close();
			out.close();
		} catch (IOException e) {
			logger.error("io exception when readObject" + e.getMessage());
		} catch (SQLException e) {
			logger.error("sql exception when readObject" + e.getMessage());
		}
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
}
