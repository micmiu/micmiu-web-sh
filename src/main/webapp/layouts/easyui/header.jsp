<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control"
	content="no-store,no-cache,must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">

<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/base.css'/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/js/jquery-validation/1.9.0/validate.css'/>" />

<link type="text/css" rel="stylesheet"
	href="<c:url value="/js/easyui/1.3.2/themes/default/easyui.css"/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/js/easyui/1.3.2/themes/icon.css"/>" />

<script type="text/javascript"
	src="<c:url value='/js/jquery/jquery-1.8.0.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery-validation/1.9.0/jquery.validate.min.js'/>"></script>

<script type="text/javascript"
	src="<c:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/easyui/1.3.2/jquery.easyui.min.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/js/easyui/MM.Ex.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/js/easyui/MM.utils.js'/>">
	
</script>

<c:set var="LOCALE" value="${sessionScope['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE']}"/>
<c:choose>
	<c:when test="${empty LOCALE}">
		<script type="text/javascript"
			src="<c:url value='/js/jquery-validation/1.9.0/messages_zh_CN.js' />"></script>
		<script type="text/javascript"
			src="<c:url value='/js/easyui/1.3.2/locale/easyui-lang-zh_CN.js'/>"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript"
			src="<c:url value='/js/jquery-validation/1.9.0/messages_${LOCALE}.js' />"></script>
		<script type="text/javascript"
			src="<c:url value='/js/easyui/1.3.2/locale/easyui-lang-${LOCALE}.js'/>"></script>
	</c:otherwise>
</c:choose>

</head>