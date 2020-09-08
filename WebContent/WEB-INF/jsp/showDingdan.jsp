<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#showDingdan").addClass("active");
	});
	
</script>
<div class="data_list">
		<div class="data_list_title">
			用户订单
		</div>
			
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>订单编号</th>
						<th>用户名</th>
						<th>下单时间</th>
						<th>总金额</th>
						<th>订单状态</th>
						<th>交货日期</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
					<c:forEach items="${dingDans}"  var="dingDan" varStatus="stat">
						<tr>
							<td>${dingDan.ddanNum}</td>
							<td>${dingDan.uid}</td>
							<td>${dingDan.dealDate}</td>
							<td>${dingDan.totalprice}</td>
							<c:if test="${dingDan.state==0}">
								<td>正在处理</td>
							</c:if>
							<c:if test="${dingDan.state==1}">
								<td>已审核</td>
							</c:if>
							
							<td>${dingDan.makedealDate}</td>
							<td>
								<button class="btn btn-success btn-mini"
								 type="button" onclick="javascript:window.location='ShowShoppingInfoServlet?action=comfirm&id=${dingDan.ddanNum}'">确认订单</button>&nbsp;
							</td>	
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>