package web.util;

/**
 * 
 * @author lj
 * @since jdk 1.5.11
 */
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SysConfigSingle {
	private Log log = LogFactory.getLog(SysConfigSingle.class);
	private static SysConfigSingle instance;
	public final String SYSUSERINFO = "SYSUSERINFO"; // �?存在session中的全局性系统用户标识
	public static String MAPPING_SUCCESS = "success";
	public static String MAPPING_ERROR = "error";
	public static String REQUEST_CURPAGE = "curpage";
	public static final int QAM_PAGE_DATE_VIEWCOUNT = 40;
	public static final String SRM_PAGINATION_PARAM_NAME="pager";
	public static final String QAMRECORDEDACTION_REQUEST_OPTIONQAMTYPE = "optionQamType";
	public static final String QAMRECORDEDACTION_REQUEST_PAGELIST = "pagelist";
	public static final String QAMRECORDEDACTION_REQUEST_SERVICEGROUPID = "serviceGroupId";
	public static final String QAMRECORDEDACTION_REQUEST_MAXPAGE = "maxpage";
	public static final String QAMRECORDEDACTION_REQUEST_MAXROWCOUNT = "maxRowCount";
	public static final String QAMRECORDEDACTION_REQUEST_ROWCOUNT = "rowCount";
	public static final String QAMRECORDEDACTION_REQUEST_OPTIONQAMLIST = "optionQamList";
	public static final String QAMRECORDINGACTION_REQUEST_PARAMSNAME = "paramsName";
	public static final String QAMRECORDINGACTION_REQUEST_PARAMSVALUES = "paramsValue";

	private SysConfigSingle() {
	}

	public static SysConfigSingle getInstance() {
		if (instance == null) {
			instance = new SysConfigSingle();
			return instance;
		} else {
			return instance;
		}
	}

	/**
	 * 转�?�字符串的内�?.
	 * 
	 * @param input
	 *            输入的字符串
	 * @param sourceEncoding
	 *            �?字符集�??称
	 * @param targetEncoding
	 *            目标字符集�??称
	 * @return 转�?�结果, 如果有错误�?�生, 则返回原�?�的值
	 */
	public String changeEncoding(String input, String sourceEncoding, String targetEncoding) {
		if (input == null || input.equals("")) {
			return input;
		}
		try {
			byte[] bytes = input.getBytes(sourceEncoding);
			return new String(bytes, targetEncoding);
		} catch (Exception ex) {
		}
		return input;
	}

	public String getXmlToString(String encoding) {
		// String readString = changeEncoding(encoding,"UTF-8","UTF-8");
		// String readString = changeEncoding(encoding,"GBK","UTF-8");
		InputStream in = IOUtils.toInputStream(encoding);
		String readString = "";
		try {
			readString = getFileToString(encoding, in);
			// readString=checkCodeingQuotes(readString);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return readString;
	}

	public String getXmlToStringByEncoding(String assetMetadata) {
		// String readString = changeEncoding(encoding,"UTF-8","UTF-8");
		// String readString = changeEncoding(encoding,"GBK","UTF-8");
		InputStream in = IOUtils.toInputStream(assetMetadata);
		String readString = "";
		try {
			readString = getFileToString(assetMetadata, in);
			// readString=checkCodeingQuotes(readString);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return readString;
	}

	public void downFile(InputStream in, String fileName) throws IOException {
		try {
			System.out.println("inputStream:" + in);
			FileOutputStream out = new FileOutputStream(fileName);
			byte[] b = new byte[2048];
			int length = 0;
			while ((length = in.read(b, 0, b.length)) != -1) {
				out.write(b, 0, length);
			}
		} catch (Exception e) {
			log.error("文件下载出错");
			e.printStackTrace();
		}
	}

	public String getFileToString(String encoding, InputStream in) throws UnsupportedEncodingException, IOException {
		String readString = "";
		int startCode = encoding.indexOf("encoding=");
		int endCode = encoding.indexOf("?>");
		if (startCode < 0) {
			// readString = new String(encoding.getBytes(),"UTF-8");
			readString = IOUtils.toString(in, "utf-8");
		} else if (encoding.subSequence(startCode + 10, endCode - 1).equals("UTF-8")
				|| encoding.subSequence(startCode + 10, endCode - 1).equals("utf-8")) {
			log.info("encoding:" + encoding.subSequence(startCode + 10, endCode - 1));
			// readString = changeEncoding(encoding,"GBK","UTF-8");
			// readString = new String(encoding.getBytes(),"UTF-8");
			readString = IOUtils.toString(in, "utf-8");
			log.info("readString:" + readString);
		} else {
			log.info("encoding:" + encoding.subSequence(startCode + 10, endCode - 1));
			// readString = changeEncoding(encoding,"UTF-8","UTF-8");
			readString = IOUtils.toString(in);
			log.info("readString:" + readString);
		}
		int locat = readString.indexOf("?xml");
		log.info("locat :" + locat);
		readString = "<?" + readString.substring(locat + 1);
		return readString;
	}

	public String getFileToStringByEncoding(String encoding, InputStream in) throws UnsupportedEncodingException,
			IOException {
		String readString = "";
		String headenconding = "";
		int startCode = encoding.indexOf("encoding=");
		int endCode = encoding.indexOf("?>");
		headenconding = encoding.subSequence(startCode + 10, endCode - 1).toString();
		if (startCode == -1) {
			startCode = encoding.indexOf("encoding =");
			headenconding = encoding.subSequence(startCode + 11, endCode - 1).toString();
		}
		log.info("encoding:" + headenconding);
		if (startCode < 0) {
			// readString = new String(encoding.getBytes(),"UTF-8");
			readString = IOUtils.toString(in);
		} else if (headenconding.indexOf("utf-8") != -1 || headenconding.indexOf("UTF-8") != -1) {
			readString = IOUtils.toString(in, "utf-8");
		} else if (headenconding.indexOf("gbk") != -1 || headenconding.indexOf("GBK") != -1) {
			readString = IOUtils.toString(in, "gbk");
		} else if (headenconding.indexOf("iso-8859-1") != -1 || headenconding.indexOf("ISO-8859-1") != -1) {
			readString = IOUtils.toString(in, "ISO-8859-1");
		} else if (headenconding.indexOf("gb2312") != -1 || headenconding.indexOf("GB2312") != -1) {
			readString = IOUtils.toString(in, "gb2312");
		} else {
			// readString = changeEncoding(encoding,"UTF-8","UTF-8");
			readString = IOUtils.toString(in, headenconding);

		}
		log.info("readString:" + readString);
		int locat = readString.indexOf("?xml");
		log.info("locat :" + locat);
		readString = "<?" + readString.substring(locat + 1);
		return readString;
	}

	public String md5(String s) throws Exception {
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		StringBufferInputStream stringbufferinputstream = new StringBufferInputStream(s);
		byte abyte0[] = new byte[stringbufferinputstream.available()];
		for (int i = 0; (i = stringbufferinputstream.read(abyte0)) > 0;)
			messagedigest.update(abyte0, 0, i);

		byte abyte1[] = messagedigest.digest();
		BigInteger biginteger = null;
		BigInteger biginteger1 = new BigInteger(abyte1);
		if (biginteger1.signum() == -1) {
			biginteger = new BigInteger(-1, abyte1);
			biginteger = biginteger.abs();
		} else {
			biginteger = biginteger1;
		}
		String s1 = biginteger.toString(16);
		return s1;
	}

	// 此方法兼容HTTP和FTP�??议
	public String getDocumentAtUrl(String urlString) {
		StringBuffer document = new StringBuffer();
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				document.append(line + "\n");
			}
			reader.close();
		} catch (MalformedURLException e) {
			log.error("Unable to connect to URL: " + urlString);
		} catch (IOException e) {
			log.error("IOException when connecting to URL: " + urlString);
		}
		return document.toString();
	}

	private String checkCodeingQuotes(String s) {

		String[] metaData_equals = s.split("=");
		String tmpstr = "";
		StringBuffer tempbuf = new StringBuffer();
		StringBuffer metaDatabuf = new StringBuffer();

		for (int i = 0; i < metaData_equals.length; i++) {
			tmpstr = metaData_equals[i];
			tempbuf = new StringBuffer(tmpstr);
			int itmp = tmpstr.lastIndexOf("\"");
			// System.out.println(itmp);
			if (itmp == 0) {
				int iescapeSpace = tmpstr.indexOf(" ");
				tempbuf.insert(iescapeSpace, "\"");
			}

			// System.out.println(tmpstr);
			// System.out.println(tempbuf);
			metaDatabuf.append(tempbuf);
			if (i < metaData_equals.length - 1)
				metaDatabuf.append("=");
		}
		return metaDatabuf.toString();
	}
}
