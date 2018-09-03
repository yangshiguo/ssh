<%@ page language="java" import="java.util.*" import="cap.bean.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
User u = (User)request.getSession().getAttribute("user");
String errorUpdateMsg = (String)request.getSession().getAttribute("errorUpdateMsg");
String succUpdateMsg = (String)request.getSession().getAttribute("succUpdateMsg");
 %>

<jsp:include page="frame/Header.jsp"></jsp:include>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=basePath %>index">SSH2博客</a>
			</div>

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="index">首页</a></li>
				</ul>

				<% if (null != u) { %>
				<ul class="nav navbar-nav">
					<li><a
						href="<%=basePath %>user/myblog?userId=<%=u.getId() %>">我的博客</a></li>
				</ul>

				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">博客管理<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="<%=basePath %>article/manage?userId=<%=u.getId() %>"><i
									class="icon-cog"></i> 博文管理</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=basePath %>category/manage?userId=<%=u.getId() %>"><i
									class="icon-cog"></i> 分类管理</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=basePath %>comment/manage?userId=<%=u.getId()%>"><i
									class="icon-cog"></i> 评论管理</a></li>
						</ul></li>
				</ul>
				<% } %>

				<% if (null == u) { %>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Login.jsp" target="_blank">登录</a></li>
					<li><a href="Register.jsp" target="_blank">注册</a></li>
				</ul>
				<% } else { %>
				<div class="pull-right">
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">欢迎，<%=u.getUsername() %> <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href="<%=basePath %>user/profile?userId=<%=u.getId() %>"><i
										class="icon-cog"></i> 编辑个人信息</a></li>
								<% if (u.getIsApplied() == true) { %>
								<li class="divider"></li>
								<li><a
									href="<%=basePath %>user/bloginfo?userId=<%=u.getId() %>"><i
										class="icon-cog"></i> 编辑博客信息</a></li>
								<% } %>
								<li class="divider"></li>
								<li><a href="<%=basePath %>logout"><i
										class="icon-off"></i> 登出</a></li>
							</ul></li>
					</ul>
				</div>
				<% } %>

			</div>			
		</div>
	</nav>

	
	    <div class="container">
	    <% if (null != succUpdateMsg) { %>
		<%-- 提示更新分类失败 --%>	
			<div class="alert alert-success"><%=succUpdateMsg %></div>
	    </div>	
		<%     
	  	       request.getSession().removeAttribute("succUpdateMsg"); 
	  	   } 
	  	%>
	  <div class="container">
		<% if (null != errorUpdateMsg) { %>
		<%-- 提示更新分类失败 --%>
		
			<div class="alert alert-error"><%=errorUpdateMsg %></div>
		</div>
		<%     
	  	       request.getSession().removeAttribute("errorUpdateMsg"); 
	  	   } 
	  	%>
		<div class="row col-md-6">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a
						href="<%=basePath%>category/manage?userId=<%=u.getId() %>">分类管理</a></li>
					<li class="active">编辑分类</li>
				</ol>
			</div>
			<div class="col-md-12">
			<form class="form-horizontal" name="save_edit_category_form" action="<%=basePath %>category/update?userId=<%=u.getId() %>&cgId=<s:property value="cg.id"/>" method="post" onsubmit="return isValidate(save_edit_category_form)">
				<div class="form-group">
				
					
					<input class="form-control" id="category_id" name="cg.id" value="<s:property value="cg.id"/>" type="hidden">
				</div>
				<div class="form-group">
				
					<label for="category_name">分类名：</label> 
					<input class="form-control" id="category_name" name="cg.categoryName" value="<s:property value="cg.categoryName"/>" type="text">
				</div>
				

				<div class="form-group">
					<button id="add_category_submit" type="submit" class="btn btn-primary">更新</button>
				</div>
			</form>
			</div>
		</div>
		</div>
		
		<jsp:include page="frame/Footer.jsp"></jsp:include>

		<script type="text/javascript">
function isValidate(form) {
	var category_name = form.category_name.value;
	
	if (category_name == "") {
		alert("请填写分类名！");	
		
		return false;
	}
	return true;
}
</script>