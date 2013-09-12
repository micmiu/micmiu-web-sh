<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<html>
<head>
<title>webapp登录页</title>
<jsp:include page="layouts/base/header.jsp" />
<script>
	$(document).ready(function() {
		$("#loginForm").validate();
	});
</script>
</head>

<body>
	<form:form id="loginForm" action="${ctx}/login.do?method=submit" method="post">
		<fieldset class="prepend-top">
			<legend>登录</legend>
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
</body>
</html>