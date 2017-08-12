package utils;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public boolean checkname(String name) {
		String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,25}$";
		Pattern pattern = Pattern.compile(USERNAME_PATTERN);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches() && name != "") {
			return true;
		}
		return false;
	}

	public boolean checkphone(String phone) {

		String USERNAME_PATTERN1 = "^[0-9_-]{10,11}$";
		Pattern pattern = Pattern.compile(USERNAME_PATTERN1);
		Matcher matcher = pattern.matcher(phone);

		if (matcher.matches() && phone != "") {
			return true;
		}
		return false;
	}

	public boolean checkvietnamese(String viet) {
		String USERNAME_PATTERN = "[^$&+,:;=?@#|]*";
		Pattern pattern = Pattern.compile(USERNAME_PATTERN);
		Matcher matcher = pattern.matcher(viet);
		if (matcher.matches() && viet != "") {
			return true;
		}
		return false;
	}

	public boolean checkemail(String email) {

		String USERNAME_PATTERN1 = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(USERNAME_PATTERN1);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches() && email != "") {
			return true;
		}
		return false;
	}

	public String decode(String s) throws UnsupportedEncodingException {
		String result = java.net.URLDecoder.decode(s, "UTF-8");
		return result;
	}

	public String encode(String s) throws UnsupportedEncodingException {
		String result = java.net.URLEncoder.encode(s, "UTF-8");
		return result;
	}

	public Date convertdate(String string) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date util_date = sdf.parse(string);
		java.sql.Date sql_date = new java.sql.Date(util_date.getTime());
		return sql_date;
	}

	public String convertString(Date sql_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(sql_date);
		return date;
	}

	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

}
