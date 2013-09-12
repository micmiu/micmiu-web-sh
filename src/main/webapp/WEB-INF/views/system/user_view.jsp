<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<html>
<head>
</head>
<body>
	<fieldset class="prepend-top">

		<legend>详细信息</legend>
		<input type="hidden" name="id" value="${user.id}" />

		<div>
			<label for="loginName" class="field">登录名:</label>${user.loginName}
		</div>
		<div>
			<label for="name" class="field">用户名:</label> ${user.name}
		</div>

		<div>
			<label for="email" class="field">邮箱:</label> ${user.email}
		</div>
		<div>
			<label for="other" class="field">备注:</label>
			<textarea rows="3" cols="30" id="other" name="other"
				style="height: 50px; width: 200px" readonly="readonly">${user.other}</textarea>
		</div>
	</fieldset>
</body>
</html>
