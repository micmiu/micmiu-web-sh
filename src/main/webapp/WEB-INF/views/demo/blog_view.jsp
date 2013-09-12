<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<table id="pg-view" class="easyui-propertygrid"
	data-options="fit:true,border:false,url:'blog.do?method=getViewData&id=${blog.id}',showGroup:true,scrollbarSize:0"></table>
