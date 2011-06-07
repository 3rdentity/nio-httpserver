package me.shenfeng.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class Util {

	private static String defaultType = "application/octet-stream";
	private static String mapFile = "mime.types";

	public static String getExtension(File file) {
		String name = file.getName();
		int index = name.lastIndexOf('.');
		if (index != -1)
			return name.substring(index + 1).toLowerCase();
		else
			return "";

	}

	public static String getContentType(File file) {

		InputStream ins = Util.class.getClassLoader().getResourceAsStream(
				mapFile);

		String exten = getExtension(file);
		Map<String, String> map = new HashMap<String, String>();

		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(ins));
			String line = null;
			while ((line = bis.readLine()) != null) {
				String[] tmp = line.split("\\s+");
				map.put(tmp[0], tmp[1]);
			}
		} catch (IOException e) {
		}

		if (map.get(exten) == null)
			return defaultType;
		else
			return map.get(exten);

	}

	public static void closeQuietly(Closeable is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 
	 * @param file
	 *            the absolute file path
	 * @param zip
	 *            gzip or not
	 * @return byte array of the file
	 * 
	 * @throws IOException
	 */
	public static byte[] file2ByteArray(File file, boolean zip)
			throws IOException {
		InputStream is = null;
		GZIPOutputStream gzip = null;
		byte[] buffer = new byte[8912];
		ByteArrayOutputStream baos = new ByteArrayOutputStream(8912);
		try {
			if (zip) {
				gzip = new GZIPOutputStream(baos);
			}
			is = new BufferedInputStream(new FileInputStream(file));
			int read = 0;
			while ((read = is.read(buffer)) != -1) {
				if (zip) {
					gzip.write(buffer, 0, read);
				} else {
					baos.write(buffer, 0, read);
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			closeQuietly(is);
			closeQuietly(gzip);
		}
		return baos.toByteArray();
	}

	/**
	 * same as {@link Util#subArray(byte[], byte[], int)},except find from end to
	 * start;
	 * 
	 * @param data
	 *            to search from
	 * @param tofind
	 *            target
	 * @param start
	 *            start index
	 * @return index of the first find if find, data.length if not find
	 */
	public static int subArrayFromEnd(byte[] data, byte[] tofind, int start) {
		int index = data.length;
		outer: for (int i = data.length - tofind.length; i > 0; --i) {
	
			for (int j = 0; j < tofind.length;) {
				if (data[i] == tofind[j]) {
					++i;
					++j;
					if (j == tofind.length) {
						index = i - tofind.length;
						break outer;
					}
				} else {
					i = i - j; // step back
					break;
				}
			}
		}
		return index;
	}

	/**
	 * 
	 * @param data
	 *            to search from
	 * @param tofind
	 *            target
	 * @param start
	 *            start index
	 * @return index of the first find if find, data.length if not find
	 */
	public static int subArray(byte[] data, byte[] tofind, int start) {
		int index = data.length;
		outer: for (int i = start; i < data.length; ++i) {
	
			for (int j = 0; j < tofind.length;) {
				if (data[i] == tofind[j]) {
					++i;
					++j;
					if (j == tofind.length) {
						index = i - tofind.length;
						break outer;
					}
				} else {
					i = i - j; // step back
					break;
				}
			}
		}
		return index;
	}
}
