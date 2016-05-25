package org.yyz.helper;

public class AjaxError extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5434566421914125177L;
	boolean success;
	String message;
	public AjaxError(){
		success=false;
		message="登陆超时";
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
