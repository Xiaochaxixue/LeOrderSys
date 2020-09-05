<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#gujian").addClass("active");
	});
	
</script>
<div class="data_list">
		<div class="data_list_title">
			商品确认书管理
		</div>
			<form name="myForm" class="form-search" method="post" action="gujianInfoManageServlet?action=list&id=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='gujianInfoManageServlet?action=preAdd'">添加</button>
				<span class="data_search">
					<button type="submit" class="btn btn-info">搜索</button>
				</span>
			</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>固件编号</th>
						<th>用户名</th>
						<th>产品编号</th>
						<th>固件版本</th>
						<th>固件名</th>
						<th>固件状态</th>
						<th>固件备注</th>
						<th>产品确认书</th>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
					<c:forEach items="${guJians}"  var="guJian" varStatus="stat">
						<tr>
							<td>${guJian.gunum}</td>
							<td>${guJian.uid}</td>
							<td>${guJian.cnum}</td>
							<td>${guJian.guversion}</td>
							<td>${guJian.guname}</td>
							<%-- <td>${guJian.gustate}</td> --%>
							<c:if test="${guJian.gustate == 1 }"><td>状态1</td></c:if>
							<c:if test="${guJian.gustate == 2 }"><td>状态2</td></c:if>
							<c:if test="${guJian.gustate == 3 }"><td>状态3</td></c:if>
							<td>${guJian.gups}</td>
							<td>${guJian.qfile}</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='gujianInfoManageServlet?action=preUpdate&id=${guJian.gunum}'">修改</button>&nbsp;
							</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='gujianInfoManageServlet?action=check&id=${guJian.gunum}'">预览</button>&nbsp;
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>