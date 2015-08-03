package com.company.shop.util;

import org.apache.commons.lang3.StringUtils;

public class ValidateUtil {
	
	public static boolean testUsername(String username){
		if(StringUtils.isNotBlank(username)){
			return username.matches("^[a-zA-Z]\\w{3,15}$");
		}
		return false;
	}
	
	public static boolean testEmail(String email){
		if(StringUtils.isNotBlank(email)){
			return email.matches("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
		}
		return false;
	}
	
	public static boolean testPhone(String phone){
		if(StringUtils.isNotBlank(phone)){
			return phone.matches("^0?(13|15|18)[0-9]{9}$");
		}
		return false;
	}
	
	
	public static boolean testPassword(String password){
		if(StringUtils.isNotBlank(password) && password.length() >= 6){
			return true;
		}
		return false;
	}
	
	
}
