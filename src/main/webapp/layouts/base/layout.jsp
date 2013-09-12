<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="header.jsp" />
<title><tiles:getAsString name="title" /></title>

</head>
<body>
	<div class="container">
		<div id="north">
			<jsp:include page="north.jsp" />
		</div>
		<div id="center" class="span-24 last">
			<jsp:include page="workspace.jsp" />
		</div>
		<div id="south">
			<jsp:include page="footer.jsp" />
		</div>
	</div>
</body>
</html>