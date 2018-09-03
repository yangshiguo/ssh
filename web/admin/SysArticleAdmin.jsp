<%@ page language="java" import="java.util.*" import="cap.bean.*" import="cap.dao.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
Admin admin = (Admin)request.getSession().getAttribute("admin");
String deleSuccMsg = (String)request.getSession().getAttribute("deleSuccMsg");	//删除文章消息
String deleErrorMsg = (String)request.getSession().getAttribute("deleErrorMsg");

%>


<jsp:include page="frame/Header.jsp"></jsp:include>

<%
	if (null != admin) {
%>
      <div class="container">
      <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">       
        <div class="navbar-header">
          <a class="navbar-brand" href="<%=basePath%>admin/index">JavaEE博客管理系统</a>
        </div>

        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav side-nav">
            <li><a href="<%=basePath%>admin/index"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
            <li><a href="<%=basePath%>admin/useradmin"><i class="glyphicon glyphicon-cog"></i> 用户管理</a></li>
            <li  class="active"><a href="<%=basePath%>admin/sysArticalAdmin"><i class="glyphicon glyphicon-cog"></i> 文章管理</a></li>
            <li><a href="<%=basePath%>admin/sysCategoryAdmin"><i class="glyphicon glyphicon-edit"></i> 分类管理</a></li>
            
          </ul>

          <ul class="nav navbar-nav navbar-right navbar-user">
            <li class="dropdown user-dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> <%=admin.getUsername()%> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 设置</a></li>
                <li class="divider"></li>
                <li><a href="<%=basePath%>admin/logout"><i class="glyphicon glyphicon-off"></i> 登出</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </nav>
    

	<div id="page-wrapper">

       <div class="row">
         <div class="col-lg-12">
           <br>
           <ol class="breadcrumb">
           <li><a href="<%=basePath%>admin/index"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
             <li class="active"><i class="glyphicon glyphicon-edit"></i> 文章管理</li>
           </ol>
         </div>  
       </div>
       
		<%
       			if (null != deleSuccMsg) {
       		%>
	  	<div class="row">
         	<div class="col-lg-12">
	  		<div class="alert alert-success"><%=deleSuccMsg%></div>
	  		</div>
	  	</div>
	  	<%
	  		request.getSession().removeAttribute("deleSuccMsg");
	  		  	   }
	  	%>
	  	
	  	<%
	  		  		if (null != deleErrorMsg) {
	  		  	%>
	  	<div class="row">
         	<div class="col-lg-12">
	  		<div class="alert alert-error"><%=deleErrorMsg%></div>
	  		</div>
	  	</div>
	  	<%
	  		request.getSession().removeAttribute("deleErrorMsg"); 
	  		  	   }
	  	%>
			

        <div class="row">
          <div class="col-lg-12">
            <div class="table-responsive">
              <table class="table table-hover tablesorter">
                <thead>
                  <tr>
                    <th>文章标题 </th>
                    <th>作者</th>
                    <th>发布时间</th>
                    <th style="width: 50px;">操作 </th>
                  </tr>
                </thead>
           
                <tbody>
                <s:iterator value="pc.list" id="art">
				<tr>
                    <td>
                    
                    <a href="<%=basePath%>comment/post?artId=<s:property value="#art.id"/>&userId=<%=admin.getId() %>" target="_blank">
                    <s:property value="#art.title"/></a></td>
                    <td><s:property value="#art.user.username"/></td>
                    <td><s:property value="#art.publishTime"/></td>
                    <td>
		              <a href="<%=basePath %>admin/deleteSysArtical&artId=<s:property value="#art.id"/>"><i class="glyphicon glyphicon-floppy-remove"></i></a>
                    </td>
                  </tr>
     		    
     		    </s:iterator>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        
        <!-- pager -->
          <ul class="pager">
				<s:if test="%{pc.curPage > 1}">
				<li class="previous"><a
					href="<%=basePath%>admin/sysArticalAdmin?userId=<%=admin.getId()%>&curPage=<s:property value="%{pc.curPage-1}"/>">&larr;
						上一页</a></li>
				</s:if>

				<s:if test="%{pc.curPage< pc.totalPages}">
				<li class="next"><a
					href="<%=basePath%>admin/sysArticalAdmin?userId=<%=admin.getId() %>&curPage=<s:property value="%{pc.curPage+1}"/>">下一页
						&rarr;</a></li>
				</s:if>
			</ul>
        
		</div>
    </div>
    
<% } %>
<jsp:include page="frame/Footer.jsp"></jsp:include>

<script type="text/javascript">
function dele(deleUrl) {
	
	if (confirm("你确定要删除该分类吗？")) {
		location.href = deleUrl;
	
	}
}
</script>