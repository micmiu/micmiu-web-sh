<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<html>
<head>
</head>
<body>
	<fieldset class="prepend-top">

		<legend>详细信息</legend>
		<input type="hidden" name="id" value="${role.id}" />

		<div>
			<label for="loginName" class="field">角色:</label>${role.roleName}
		</div>
		<div>
			<label for="name" class="field">权限:</label> ${role.permissionNames}
		</div>

	</fieldset>
</body>
</html>
