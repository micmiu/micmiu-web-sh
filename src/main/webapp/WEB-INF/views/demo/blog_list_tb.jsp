<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<html>
<head>
</head>
<body>
	<table id="demotb_dg-list">
	</table>
	<div id="demotb_tb" style="padding: 5px; height: auto">
		<div>
			<form id="demotb_query-form" method="post">
				<fmt:message key="blog.gd.col.title" />
				: <input style="width: 80px" name="title"> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'"
					onclick="MM_utils.formQuery({form_id:'#demotb_query-form',dg_id:'#demotb_dg-list'})"><fmt:message
						key="global.btn.query" /></a> <a href="javascript:void(0)"
					class="easyui-linkbutton" data-options="iconCls:'icon-redo'"
					onclick="MM_utils.formReset({form_id:'#demotb_query-form'});"><fmt:message
						key="global.btn.reset" /></a>
			</form>
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
					key="global.btn.read" /></a>
		</div>

	</div>
</body>
<script type="text/javascript">
	$('#demotb_dg-list').datagrid({
		title : '',
		url : 'blog.do?method=pageQuery',
		toolbar : '#demotb_tb',
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
