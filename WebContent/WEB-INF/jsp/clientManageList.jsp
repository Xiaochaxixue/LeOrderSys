<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#client").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			客户管理
		</div>
			<form name="myForm" class="form-search" method="post" action="clientManageServlet?action=list&id=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='clientManageServlet?action=preAdd'">添加</button>
				<span class="data_search">
					<button type="submit" class="btn btn-info">搜索</button>
				</span>
			</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th width="10%">用户名</th>
						<th>公司名称</th>
						<th>公司地址</th>
						<th>联系人</th>
						<th>联系方式</th>
						<th width="20%">默认收货地址</th>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
					<c:forEach items="${clients}"  var="client" varStatus="stat">
						<tr>
							<td>${client.uid}</td>
							<td>${client.corname}</td>
							<td>${client.coraddress}</td>
							<td>${client.contact}</td>
							<td>${client.phone}</td>
							<td>${client.address0}</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='clientManageServlet?action=preUpdate&id=${client.uid}'">修改</button>&nbsp;
								<!--<button class="btn btn-mini btn-info" type="button" onclick="clientManageServlet?action=preAdd">增加</button>-->
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>