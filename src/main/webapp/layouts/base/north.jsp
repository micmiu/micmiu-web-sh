<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/taglibs.jsp"%>
<div>
	<div id="title">
		<div class="title">springMVC(3.2)+Hibernate(4.1.9) 框架示例</div>
		<div>
			<span class="subtitle">--CRUD基本操作演示</span> <span class="right">Hello,
				${loninName} !!</span>
		</div>
	</div>
	<div id="menu">
		<ul>
			<li><a href='<c:url value="/index.do"/>'>首页</a></li>
			<li><a href='<c:url value="/system/user.do?method=list"/>'>用户列表</a></li>
		</ul>

	</div>
	<div id="pos">
		<h4 class="prepend-top">
			当前位置:[
			<tiles:getAsString name="position" />
			]
		</h4>
	</div>
</div>
