<%@ page language="java" import="java.util.*" import="cap.bean.*"
	import="cap.dao.impl.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
User u = (User)request.getSession().getAttribute("user");
String succMsg = (String)request.getSession().getAttribute("succMsg");	//新建文章消息
String errorMsg = (String)request.getSession().getAttribute("errorMsg");

String deleSuccMsg = (String)request.getSession().getAttribute("deleSuccMsg");	//删除文章消息
String deleErrorMsg = (String)request.getSession().getAttribute("deleErrorMsg");
%>

<jsp:include page="frame/Header.jsp"></jsp:include>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="<%=basePath %>index">JavaEE博客</a>
			</div>			
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<%=basePath %>index">首页</a></li>
				</ul>

				<%
					if (null != u) {
				%>
				<ul class="nav navbar-nav">
					<li><a
						href="<%=basePath%>user/myblog?userId=<%=u.getId()%>">我的博客</a></li>
				</ul>

				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">博客管理<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="<%=basePath%>article/manage?userId=<%=u.getId()%>"><i
									class="glyphicon glyphicon-cog"></i> 博文管理</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=basePath%>category/manage?userId=<%=u.getId()%>"><i
									class="glyphicon glyphicon-cog"></i> 分类管理</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=basePath%>comment/manage?userId=<%=u.getId()%>"><i
									class="glyphicon glyphicon-cog"></i> 评论管理</a></li>
						</ul></li>
				</ul>
				<%
					}
				%>

				<%
					if (null == u) {
				%>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=basePath %>Login.jsp" target="_blank">登录</a></li>
					<li><a href="<%=basePath %>Register.jsp" target="_blank">注册</a></li>
				</ul>
				<%
					} else {
				%>
				<div class="pull-right">
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">欢迎，<%=u.getUsername()%> <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href="<%=basePath%>user/profile?userId=<%=u.getId()%>"><i
										class="glyphicon glyphicon-cog"></i> 编辑个人信息</a></li>
								<%
									if (u.getIsApplied() == true) {
								%>
								<li class="divider"></li>
								<li><a
									href="<%=basePath%>user/bloginfo?userId=<%=u.getId()%>"><i
										class="glyphicon glyphicon-cog"></i> 编辑博客信息</a></li>
								<%
									}
								%>
								<li class="divider"></li>
								<li><a href="<%=basePath%>logout"><i
										class="glyphicon glyphicon-off"></i> 登出</a></li>
							</ul></li>
					</ul>
				</div>
				<%
					}
				%>

			</div>			
		</div>		
	</nav>

	<%
		if (null != succMsg) {
	%>
	<div class="container">
		<div class="alert alert-success"><%=succMsg%></div>
	</div>
	<%
		request.getSession().removeAttribute("succMsg");
	  	   }
	%>

	<%
		if (null != errorMsg) {
	%>
	<div class="container">
		<div class="alert alert-error"><%=errorMsg%></div>
	</div>
	<%
		request.getSession().removeAttribute("errorMsg"); 
	  	   }
	%>

	<%
		if (null != deleSuccMsg) {
	%>
	<div class="container">
		<div class="alert alert-success"><%=deleSuccMsg%></div>
	</div>
	<%
		request.getSession().removeAttribute("deleSuccMsg");
	  	   }
	%>

	<%
		if (null != deleErrorMsg) {
	%>
	<div class="container">
		<div class="alert alert-error"><%=deleErrorMsg%></div>
	</div>
	<%
		request.getSession().removeAttribute("deleErrorMsg"); 
	  	   }
	%>

	<%
		if (null != u) {
	%>
	<div class="container">
		<div class="btn-toolbar">
			<a class="btn btn-primary"
				href="<%=basePath%>article/add?userId=<%=u.getId()%>">新建文章</a>
		</div>
		<div class="well">
			<table class="table">
				<thead>
					<tr>
						<th>标题</th>
						<th>系统分类</th>
						<th>个人分类</th>
						<th>最近一次修改时间</th>
						<th style="width: 50px;">操作</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="pc.list" id="art">
					<tr>
						<td><a
							href="<%=basePath %>article/update?artId=<s:property value="#art.id"/>"><s:property value="#art.title"/></a></td>
						
						<td><s:property value="#art.sysCategory.categoryName"/></td>
						<td><s:property value="#art.category.categoryName"/></td>
						<td><s:property value="#art.publishTime"/></td>
						<td><a
							href="<%=basePath %>article/update?artId=<s:property value="#art.id"/>&userId=<%=u.getId() %>"><i
								class="glyphicon glyphicon-pencil"></i>
							</a>
							<a
							href="<%=basePath %>article/delete?artId=<s:property value="#art.id"/>"><i
								class="glyphicon glyphicon-remove"></i>
							</a>
							
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<div>
			<!-- pager -->
			<ul class="pager">
				<s:if test="%{pc.curPage > 1}">
				<li class="previous"><a
					href="<%=basePath%>article/manage?userId=<%=u.getId()%>&curPage=<s:property value="%{pc.curPage-1}"/>">&larr;
						上一页</a></li>
				</s:if>

				<s:if test="%{pc.curPage< pc.totalPages}">
				<li class="next"><a
					href="<%=basePath%>article/manage?userId=<%=u.getId() %>&curPage=<s:property value="%{pc.curPage+1}"/>">下一页
						&rarr;</a></li>
				</s:if>
			</ul>
		</div>


	</div>
	<!-- /container -->
	<% }%>

	<jsp:include page="frame/Footer.jsp"></jsp:include>

	<script type="text/javascript">
function dele(deleUrl) {
	
	if (confirm("你确定要删除这篇文章吗？")) {
		location.href = deleUrl;
	}
}
</script>