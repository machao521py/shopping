<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员登录</title>
<%@include file="common/taglibs.jsp"%>
<link media="all" href="${ctxr}/login/css/module.css"
	type="text/css" rel="stylesheet" />
<style>
.fangshua {
	FONT-WEIGHT: bold;
	FONT-SIZE: 14px;
	COLOR: red
}
</style>
<!--[if !IE]> -->
<script src="${ctx}/resources/jquery/jquery-2.1.1.min.js"></script>
<!-- <![endif]-->

<!--[if IE]>-->
<script src="${ctx}/resources/jquery/jquery-1.11.1.min.js"></script>
<!--<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
	window.jQuery|| document.write("<script src='http://code.jquery.com/jquery-2.1.1.min.js'>"+ "<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>-->
<script type="text/javascript">
	window.jQuery|| document.write("<script src='http://code.jquery.com/jquery-1.11.1.min.js'>"+ "<"+"/script>");
</script>
<!-- <![endif]-->
<script language="javascript">
	function checkform(thisform) {
		if (thisform.username.value == "") {
			alert("用户名不能为空！");
			thisform.user.focus();
			return (false);
		}
		if (thisform.userpassword.value == "") {
			alert("密码不能为空！");
			thisform.password.focus();
			return (false);
		}

		if (thisform.SecurityCode.value == "") {
			alert("请输入验证码！");
			thisform.SecurityCode.focus();
			return (false);
		}
		return true;
	}
	$(document).ready(function() {
		$("#VerifyCode").click(function() {
			$(this).attr("src", "${ctxr}/login/image.jsp?=" + new Date());
		});
	})
</script>
</head>
<body class="login_bg">
	<form method="post" name="form1" action="${ctxa}/login" onsubmit="return checkform(this);">
		<div class="login_main">
			<div class="login_logo">
			</div>
			<div class="login_left">
				<img class="login_pic" src="${ctxr}/login/images/login_pic.png"/>
			</div>
			<div class="login_right">
				<dl>
					<dt class="uesr">
						<label>用户名：</label> <input name="loginname" type="text"
							id="username" tabindex="1" class="text_nor"
							style="width: 100px; font-size: 12px" size="10" alt="验证码" />
					</dt>
					<dt class="passw">
						<label>密 码：</label> <input name="password" type="password"
							id="userpassword" tabindex="2" class="text_nor"
							style="width: 100px; font-size: 12px" size="10" alt="验证码" />
					</dt>
					<dt class="yzm">
						<label>附加码：</label> <input name="SecurityCode" type="text"
							id="SecurityCode" style="font-size: 12px" size="10" /> &nbsp;<img
							src="${ctxr}/login/image.jsp" alt="验证码" id="VerifyCode" />
					</dt>
					<dt>
						<label style="color:red;">${message}</label>
					</dt>
					<dt></dt>
					<dd>
						<input type="submit" name="submit" class="login_submit" value=""/>
						<input type="reset" name="Submit" class="login_reset" value=""/>
						<p></p>
					</dd>
				</dl>
			</div>
			
		</div>
	</form>
</body>
</html>

