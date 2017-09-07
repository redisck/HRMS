<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
  </head>
  
  <body style="background-color: #dfe8f6;"><div id="first_content" style="width: 100%; height: 100%; ">
  	<table class="fristTable" >
  		<tr>
  			<td style="width: 50%" rowspan="2">
  				<div class="title"><img src="img/user.png"/>用户信息</div>
  				<div class="userContent">
  					欢迎用户: <b>${user.userName}</b><br/><br/>
  					角色：${user.role.roleName}<br/><br/>
  					工号：${user.employee.empId}<br/><br/>
  				</div>
  			</td>
  			<td style="width: 50%;">
  				<div class="title"><img src="img/instruction.gif"/>系统介绍</div>
  				<div class="content">
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统主要分为部门管理、职员管理、培训管理、奖惩管理、
  					薪资管理、报表管理、系统管理七大管理模块，从各个方面记录、整合企业人员的信息。
  				</div>
  			</td>
  		</tr>
  		<tr>
  		</tr>
  	</table></div>
  </body>
</html>
