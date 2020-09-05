<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#tianprice").addClass("active");
	});
	
</script>
<div class="data_list">
		<div class="data_list_title">
			费用管理
		</div>
			<form name="myForm" class="form-search" method="post" action="tianpriceManageServlet?action=list&id=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='tianpriceManageServlet?action=preAdd'">添加</button>
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='updatePaiServlet?action=preAdd'">排针方式费用修改</button>
				<span class="data_search">
					
					<button type="submit" class="btn btn-info">搜索</button>
				</span>
			</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable" style="width:600px">
				<thead>
					<tr>
						<th width="10%">天线类型</th>
						<th width="10%">单价</th>
						<td width="10%">操作</td>
					</tr>
				</thead>
				<tbody>
					<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
					<c:forEach items="${tianPrices}"  var="tianPrices" varStatus="stat">
						<tr>
							<td>${tianPrices.tiantype}cm</td>
							<td>${tianPrices.price}&yen;</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='tianpriceManageServlet?action=preUpdate&id=${tianPrices.tiantype}'">修改</button>&nbsp;
								<!--<button class="btn btn-mini btn-info" type="button" onclick="clientManageServlet?action=preAdd">增加</button>-->
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>