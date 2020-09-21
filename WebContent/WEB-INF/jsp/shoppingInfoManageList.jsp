<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#shoppingInfo").addClass("active");
	});
	
</script>
<div class="data_list">
		<div class="data_list_title">
			商品信息管理
		</div>
			<form name="myForm" class="form-search" method="post" action="shoppingInfoManageServlet?action=list&id=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='shoppingInfoManageServlet?action=preAdd'">添加</button>
				<span class="data_search">
					<button type="submit" class="btn btn-info">搜索</button>
				</span>
			</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>产品编号</th>
						<th>产品类型</th>
						<th>产品名称</th>
						<th>规格</th>
						<th>单位</th>
						<th>单价</th>
						<th>商品状态</th>
						<th>可选状态</th>
						<th>图片路径</th>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
					<c:forEach items="${shoppingInfos}"  var="shoppingInfo" varStatus="stat">
						<tr>
							<td>
							<a onclick="this.href='ShowShoppingInfoServlet?action=shoppingDetaileInfo&id=${shoppingInfo.cnum}'">
								${shoppingInfo.ctype}
									<font><strong>-</strong></font>${shoppingInfo.cnum}
									<c:if test="${fn:contains(shoppingInfo.ctype,'MK')}">
										<font><strong>-</strong></font>${shoppingInfo.pt}
									</c:if>
								</a>
							</td>
							
							<td>
								<c:if test="${shoppingInfo.ctype eq 'IC' }">芯片</c:if>
								<c:if test="${shoppingInfo.ctype eq 'MK' }">MK模块</c:if>
								<c:if test="${shoppingInfo.ctype eq 'MIMK' }">MIMK模块</c:if>
								<c:if test="${shoppingInfo.ctype eq 'JDMK' }">JDMK模块</c:if>
								<c:if test="${shoppingInfo.ctype eq 'PCBA' }">PCBA</c:if>
								<c:if test="${shoppingInfo.ctype eq '02' }">成品</c:if>
							</td>
							<td>${shoppingInfo.cname}</td>
							<td>${shoppingInfo.guige}</td>
							<td>${shoppingInfo.danwei}</td>
							<td>${shoppingInfo.price}</td>
							
							<td>
								<c:if test="${shoppingInfo.sstate == 1 }">在售</c:if>
								<c:if test="${shoppingInfo.sstate == 2 }">正在生产</c:if>
							</td>
							
							<td>
								<c:if test="${shoppingInfo.sselect == 1 }">可选</c:if>
								<c:if test="${shoppingInfo.sselect == 2 }">不可选</c:if>
							</td>
							
							<td>${shoppingInfo.picture}</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='shoppingInfoManageServlet?action=preUpdate&id=${shoppingInfo.cnum}'">修改</button>&nbsp;
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>