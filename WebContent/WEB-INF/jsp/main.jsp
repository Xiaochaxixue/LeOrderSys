<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>乐式订单管理系统</title>
<link href="${pageContext.request.contextPath}/css/dorm.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet"><!--（含有bootstrap 所有css）  -->
<!-- 日期控件所需的样式表 -->
<link href="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<!-- 日期控件所需的js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<!-- 如需支持简体中文的显示，就需要加载中文的资源文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<!-- toastr插件，进行轻量级的信息显示 -->
<script src="${pageContext.request.contextPath}/toastr/toastr.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/toastr/toastr.min.css">

<script type="text/javascript">
	$(document).ready(function(){
		$("ul li:eq(0)").addClass("active");
	});
</script>

<style type="text/css">
	  .bs-docs-sidenav {
    background-color: #fff;
    border-radius: 6px;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
    padding: 0;
    width: 228px;
}

.bs-docs-sidenav > li > a {
    border: 1px solid #e5e5e5;
    display: block;
    padding: 8px 14px;
    margin: 0 0 -1px;
}
.bs-docs-sidenav > li:first-child > a {
    border-radius: 6px 6px 0 0;
}
.bs-docs-sidenav > li:last-child > a {
    border-radius: 0 0 6px 6px;
}
.bs-docs-sidenav > .active > a {
    border: 0 none;
    box-shadow: 1px 0 0 rgba(0, 0, 0, 0.1) inset, -1px 0 0 rgba(0, 0, 0, 0.1) inset;
    padding: 9px 15px;
    position: relative;
    text-shadow: 0 1px 0 rgba(0, 0, 0, 0.15);
    z-index: 2;
}
.bs-docs-sidenav .icon-chevron-right {
    float: right;
    margin-right: -6px;
    margin-top: 2px;
    opacity: 0.25;
}
.bs-docs-sidenav > li > a:hover {
    background-color: #f5f5f5;
}
.bs-docs-sidenav a:hover .icon-chevron-right {
    opacity: 0.5;
}
.bs-docs-sidenav .active .icon-chevron-right, .bs-docs-sidenav .active a:hover .icon-chevron-right {
    background-image: url("${pageContext.request.contextPath}/bootstrap/img/glyphicons-halflings-white.png");
    opacity: 1;
}
</style>

</head>
<body>
<div class="container-fluid" style="padding-right: 0px;padding-left: 0px;">
	<div region="north" style="height: 100px;background-image: url('images/bg.jpg')">
		<div align="left" style="width: 80%;height:100px ;float: left;padding-top: 40px;padding-left: 30px;" ><font color="white" size="6" >乐式订单管理系统</font></div>
		<div style="padding-top: 70px;padding-right: 20px;font-size:16px">
			当前用户：&nbsp;<font color="red" >${session_user.uid}</font>
		</div>
	</div>
</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2 bs-docs-sidebar" >
				<ul class="nav nav-list bs-docs-sidenav">

					<c:if test="${session_user.type == 0}">
						<li><a href="indexServlet"><i class="icon-chevron-right"></i>首页</a></li>
						<li id="dingdanManage"><a href="DingDanManageServlet?action=list"><i class="icon-chevron-right"></i>订单管理</a></li>
						<li id="client"><a href="clientManageServlet?action=list"><i class="icon-chevron-right"></i>客户管理</a></li>
						<li id="shoppingInfo"><a href="shoppingInfoManageServlet?action=list"><i class="icon-chevron-right"></i>商品管理</a></li>
						<li id="tianprice"><a href="tianpriceManageServlet?action=list"><i class="icon-chevron-right"></i>费用管理</a></li>
						<li id="gujian"><a href="gujianInfoManageServlet?action=list"><i class="icon-chevron-right"></i>商品确认书管理</a></li>
						<li id="logOut"><a href="LogOutServlet"><i class="icon-chevron-right"></i>退出系统</a></li>
					</c:if>
						
					<c:if test="${session_user.type == 1}">
						<li><a href="indexServlet"><i class="icon-chevron-right"></i>首页</a></li>
						<li id="showshoppingInfo"><a href="ShowShoppingInfoServlet?action=list"><i class="icon-chevron-right"></i>商品信息</a></li>
						<li id="shoppingCart"><a href="ShowShoppingInfoServlet?action=cartshow"><i class="icon-chevron-right"></i>购物车管理</a></li>
						<li id="showDingdan"><a href="ShowShoppingInfoServlet?action=showDingdan"><i class="icon-chevron-right"></i>订单管理</a></li>
						<li id="modifyInfo"><a href="ModifyInfoServlet?action=list"><i class="icon-chevron-right"></i>修改个人信息</a></li>
						<li id="logOut"><a href="LogOutServlet"><i class="icon-chevron-right"></i>退出系统</a></li>
					</c:if>
					<c:if test="${session_user.type == 2}">
						<li><a href="indexServlet"><i class="icon-chevron-right"></i>首页</a></li>
						<li id="dingdanManage"><a href="DingDanManageServlet?action=list"><i class="icon-chevron-right"></i>订单管理</a></li>
						<li id="client"><a href="clientManageServlet?action=list"><i class="icon-chevron-right"></i>客户管理</a></li>
						<li id="shoppingInfo"><a href="shoppingInfoManageServlet?action=list"><i class="icon-chevron-right"></i>商品管理</a></li>
						<li id="tianprice"><a href="tianpriceManageServlet?action=list"><i class="icon-chevron-right"></i>费用管理</a></li>
						<li id="gujian"><a href="#"><i class="icon-chevron-right"></i>商品确认书管理</a></li>
						<li id="logOut"><a href="LogOutServlet"><i class="icon-chevron-right"></i>退出系统</a></li>
					</c:if>
					<c:if test="${session_user.type == 3}">
						<li><a href="indexServlet"><i class="icon-chevron-right"></i>首页</a></li>
						<li id="dingdanManage"><a href="#"><i class="icon-chevron-right"></i>订单管理</a></li>
						<li id="client"><a href="#"><i class="icon-chevron-right"></i>客户管理</a></li>
						<li id="shoppingInfo"><a href="#"><i class="icon-chevron-right"></i>商品管理</a></li>
						<li id="tianprice"><a href="tianpriceManageServlet?action=list"><i class="icon-chevron-right"></i>费用管理</a></li>
						<li id="gujian"><a href="gujianInfoManageServlet?action=list"><i class="icon-chevron-right"></i>商品确认书管理</a></li>
						<li id="logOut"><a href="LogOutServlet"><i class="icon-chevron-right"></i>退出系统</a></li>
					</c:if>
				</ul>
			</div>
			<div class="span10">
				 <!--右侧内容--> 
				<jsp:include page="${mainRight==null? 'blank.jsp':mainRight}"></jsp:include>
			</div>
			</div>
		</div>
</body>
</html>