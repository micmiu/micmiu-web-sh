<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<html>
<head>
</head>

<body>

	<p>jstl tag:
		<fmt:message key="test.message.name"></fmt:message>
	</p>
	---------------------------------------------------------
	<p>spring tag:
		<spring:message code="test.message.name"></spring:message>
	</p>
</body>

</html>
