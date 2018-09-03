<%@ page language="java" import="java.util.*" import="cap.bean.*" import="cap.dao.impl.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



    <div class="container">
      <hr>
      
      <footer>
        <div class="row">
          <div class="col-lg-12">
            <p >
            Copyright &copy; 2013 &middot; UI based on Bootstrap 3 
                                  &middot; <a href="<%=basePath %>AdminLogin.jsp" target="_blank">admin</a>
                                  &middot; starlee2008@163.com 
                                  &middot;访问人数：<s:property value="count"/>                       
                            
            </p>
          </div>
        </div>
      </footer>

    </div>
  
    <script src="<%=basePath %>bootstrap/js/bootstrap.js"></script>
    
  </body>
</html>
