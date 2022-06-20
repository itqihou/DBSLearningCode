package edu.cn.kluniv.sjz.sis.util;

public class StringUtil {
	public static boolean isEmpty(String s) {
		if (s != null && !"".equals(s.trim())) {
			return false;
		}
		return true;
	}
}
