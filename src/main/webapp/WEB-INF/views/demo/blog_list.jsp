<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<html>
<head>
</head>
<body>
	<table id="dg-list">
	</table>
	<div id="tb" style="padding: 5px; height: auto">
		<div>
			<form id="query-form" method="post">
				<fmt:message key="blog.gd.col.title" />
				: <input style="width: 80px" name="title"> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="MM_utils.formQuery()"><fmt:message
						key="global.btn.query" /></a><a href="javascript:void(0)"
					class="easyui-linkbutton" data-options="iconCls:'icon-redo'"
					onclick="MM_utils.formReset();"><fmt:message
						key="global.btn.reset" /></a>
			</form>
			<form id="export-form" method="post"></form>
		</div>
		<div style="margin-bottom: 5px">
			<shiro:hasPermission name="demo_common:read">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true"
					onclick="MM_utils.baseWinAdd({win_url:'blog.do?method=showForm'})"><fmt:message
						key="global.btn.create" /></a>
			</shiro:hasPermission>
			<shiro:hasPermission name="demo_common:read">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-edit',plain:true"
					onclick="MM_utils.baseWinEdit({win_url:'blog.do?method=showForm'});"><fmt:message
						key="global.btn.update" /></a>
			</shiro:hasPermission>
			<shiro:hasPermission name="demo_common:read">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove',plain:true"
					onclick="MM_utils.baseGDDel('#dg-list','blog.do?method=deleteBatch')"><fmt:message
						key="global.btn.delete" /></a>
			</shiro:hasPermission>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="MM_utils.baseView({win_url:'blog.do?method=getViewData'})"><fmt:message
					key="global.btn.read" /></a> <a href="javascript:void(0)" id="tb_mb"
				class="easyui-menubutton"
				data-options="iconCls:'icon-excel',plain:true"><fmt:message
					key="global.btn.export" /></a>
		</div>
		<div id="mb_mm" style="width: 50px;">
			<div data-options="iconCls:'icon-excel'"
				onclick="MM_utils.baseExport({actionURL:'blog.do?method=export&exportType=POI'})">POI</div>
			<div data-options="iconCls:'icon-excel'"
				onclick="MM_utils.baseExport({actionURL:'blog.do?method=export&exportType=JXL'})">JXL</div>
			<div data-options="iconCls:'icon-csv'"
				onclick="MM_utils.baseExport({actionURL:'blog.do?method=export&exportType=CSV'})">CSV</div>
			<div data-options="iconCls:'icon-pdf'"
				onclick="MM_utils.baseExport({actionURL:'blog.do?method=export&exportType=PDF'})">PDF</div>
		</div>
	</div>
	<div id="form-win"></div>
</body>
<script type="text/javascript">
	$('#tb_mb').menubutton({
		iconCls : 'icon-export',
		menu : '#mb_mm'
	});
	$('#dg-list').datagrid({
		title : '',
		url : 'blog.do?method=pageQuery',
		toolbar : '#tb',
		fit : true,
		rownumbers : true,
		singleSelect : false,
		iconCls : 'icon-table',
		pagination : true,
		sortName : 'title',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'title',
			title : '<fmt:message key="blog.gd.col.title" />',
			width : 100
		}, {
			field : 'category',
			title : '<fmt:message key="blog.gd.col.category" />',
			width : 100
		}, {
			field : 'author',
			title : '<fmt:message key="blog.gd.col.author" />',
			width : 100
		}, {
			field : 'url',
			title : '<fmt:message key="blog.gd.col.url" />',
			width : 100
		}, {
			field : 'other',
			title : '<fmt:message key="blog.gd.col.other" />',
			width : 100
		}, {
			field : 'publishDate',
			title : '<fmt:message key="blog.gd.col.publishDate" />',
			width : 100
		}, {
			field : 'creater',
			title : '<fmt:message key="blog.gd.col.creater" />',
			width : 100
		} ] ]
	});
</script>
</html>
