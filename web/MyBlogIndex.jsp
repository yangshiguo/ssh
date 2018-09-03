<%@ page language="java" import="java.util.*" import="java.text.*" import="cap.bean.*" import="cap.dao.impl.*"
pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%
int userId = (Integer)request.getAttribute("userId");
User u = (User)request.getSession().getAttribute("user");

%>
<jsp:include page="frame/Header.jsp"></jsp:include>

  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="index">SSH2 博客</a>
        </div>

        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav">
            <li><a href="<%=basePath%>index">网站首页</a></li>
          </ul>
          
          <%
                    	if ((null != u) && (u.getIsApplied() == true)) {
                    %>
          <ul class="nav navbar-nav">
            <li><a href="<%=basePath%>user/myblog?userId=<%=u.getId()%>">我的博客</a></li>
          </ul>
          
          <ul class="nav navbar-nav">
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">博客管理<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="<%=basePath%>article/manage?userId=<%=u.getId()%>"><i class="glyphicon glyphicon-cog"></i> 博文管理</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=basePath%>category/manage?userId=<%=u.getId()%>"><i class="glyphicon glyphicon-cog"></i> 分类管理</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=basePath%>comment/manage?userId=<%=u.getId()%>"><i class="glyphicon glyphicon-cog"></i> 评论管理</a></li>
                </ul>
            </li>
          </ul>
          <%
          	}
          %>
          
          <%
                    	if (null == u) {
                    %>
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="Login.jsp" target="_blank">登录</a></li>
          	<li><a href="Register.jsp" target="_blank">注册</a></li>
          </ul>
          <%
          	} else {
          %>
          <div class="pull-right">
                <ul class="nav pull-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">欢迎，<%=u.getUsername()%> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=basePath%>user/profile?userId=<%=u.getId()%>"><i class="glyphicon glyphicon-cog"></i> 编辑个人信息</a></li>
                            <%
                            	if (u.getIsApplied() == true) {
                            %>
                            <li class="divider"></li>
                            <li><a href="<%=basePath%>user/bloginfo?userId=<%=u.getId()%>"><i class="glyphicon glyphicon-cog"></i> 编辑博客信息</a></li>
                            <%
                            	}
                            %>
                            <li class="divider"></li>
                            <li><a href="<%=basePath%>logout"><i class="glyphicon glyphicon-off
glyphicon "></i> 登出</a></li>
                        </ul>
                    </li>
                </ul>
          </div>
          <%
          	}
          %>
          
        </div>
      </div>
    </nav>

    <div class="container">

      <div class="row">
        <div class="col-lg-8">
        
          <!-- blog entry -->
          <h1><a href="<%=basePath%>user/myblog?userId=<%=userId%>">        
          <s:property value="bi.blogName"/>        
          </a></h1>
          <p class="lead">        
          <s:property value="bi.annoucement"/>                     
          </p><br>
         
          <s:iterator value="pc.list" id="art" >
        
 		  <h3>
 		  <a href="<%=basePath%>comment/post?artId=<s:property value="#art.id"/>&userId=<s:property value="#art.user.id"/>">
 		  </a>
 		  </h3>
          <p>
          <i class="glyphicon glyphicon-user"></i> <a href="#"> <%=u.getUsername() %></a> 
		| <i class="glyphicon glyphicon-calendar"></i> <s:property value="#art.publishTume"/>
		| 阅读<s:property value="#art.count"/> 次
 		  </p>
          <hr>
          <p><s:property value="#art.summary"/></p>
          <a class="btn btn-primary" href="<%=basePath%>comment/post?artId=<s:property value="#art.id"/>&userId=<s:property value="#art.user.id"/>">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>               
          <hr>
          </s:iterator>
          
          <!-- pager -->
				<ul class="pager">
				<s:if test="%{pc.curPage > 1}">
				
				
					<li class="previous"><a
						href="<%=basePath%>user/index?curPage=<s:property value="%{pc.curPage-1}"/>">&larr;
							上一页</a>
					</li>
					
            </s:if>
            <s:if test="%{pc.curPage< pc.totalPages}">
					
					<li class="next"><a
						href="<%=basePath%>user/index?curPage=<s:property value="%{pc.curPage+1}"/>">下一页
							&rarr;</a>
					</li>
				
            </s:if>
				</ul>

        </div>
        
        <div class="col-lg-4">                  
          <form action="#" method="GET">
          <div class="well">
            <h4> 文章分类</h4>
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled">
                  
                  <s:iterator value="cgList" id="cg">
                  
                  	<li><a href="#"><s:property value="#cg.categoryName"/></a></li>
                  </s:iterator>
                  </ul>
                </div>
              </div>
          </div>
          </form> 
          
          <div class="well">
            <h4>公告</h4>
            <p><s:property value="bi.annoucement"/></p>
          </div>
        </div>
      </div>
    </div>
    
<jsp:include page="frame/Footer.jsp"></jsp:include>