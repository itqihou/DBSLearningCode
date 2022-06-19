package edu.cn.kluniv.sjz.sis.util;

public class StringUtil {
	public boolean isEmpty(String s) {
		if (s != null && !"".equals(s.trim())) {
			return false;
		}
		return true;
	}
}
