<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ include file="/include/taglibs.jsp"%>
<html>
<head>
<title>登录页</title>
</head>
<body style="width: 98%">
	<div>
		<c:choose>
			<c:when
				test="${shiroLoginFailure eq 'com.hx.web.excep.CaptchaException'}">
				<div class="error-msg prepend-top">验证码错误，请重试.</div>
			</c:when>
			<c:when
				test="${shiroLoginFailure eq 'org.apache.shiro.authc.UnknownAccountException'}">
				<div class="error-msg prepend-top">该用户不存在.</div>
			</c:when>
			<c:when
				test="${shiroLoginFailure eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
				<div class="error-msg prepend-top">用户或密码错误.</div>
			</c:when>
			<c:when test="${shiroLoginFailure ne null}">
				<div class="error-msg prepend-top">登录认证错误，请重试.</div>
			</c:when>
		</c:choose>
		<form:form id="loginForm" action="${ctx}/login.do" method="post">
			<fieldset class="prepend-top">
				<legend>系统登录</legend>
				<div>
					<label for="username" class="field">名称:</label> <input type="text"
						id="username" name="username" size="25" value="${username}"
						class="required" />
				</div>
				<div>
					<label for="password" class="field">密码:</label> <input
						type="password" id="password" name="password" size="25"
						class="required" />
				</div>
				<div class="field">
					<label for="captcha" class="field">验证码:</label> <input type="text"
						id="captcha" name="captcha" size="4" maxlength="4"
						class="required" />
				</div>
				<div class="field">
					<label for="codeImg" class="field"></label> <img title="点击更换"
						id="img_captcha" onclick="javascript:refreshCaptcha();"
						src="servlet/captchaCode">(看不清<a href="javascript:void(0)"
						onclick="javascript:refreshCaptcha()">换一张</a>)
				</div>
			</fieldset>
			<div>
				<input type="checkbox" id="rememberMe" name="rememberMe" /> <label
					for="rememberMe">记住我</label> <span style="padding-left: 10px;"><input
					id="submit" class="button" type="submit" value="登录" /></span>
			</div>
			<div>
				(管理员<b>admin/admin</b>, 普通用户<b>user/user</b>)
			</div>
		</form:form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate();
	});
	var _captcha_id = "#img_captcha";
	function refreshCaptcha() {
		$(_captcha_id).attr("src", "servlet/captchaCode?t=" + Math.random());
	}
</script>
</html>
