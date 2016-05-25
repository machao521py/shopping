package org.yyz.helper;

import org.springframework.util.DigestUtils;

public class PasswdDigestHelper {
	public static String digestPwd(String pwd){
		return DigestUtils.md5DigestAsHex(pwd.getBytes());		
	}
}
