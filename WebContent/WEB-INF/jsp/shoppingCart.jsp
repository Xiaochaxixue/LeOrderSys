<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#shoppingCart").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			商品信息管理
		</div>
			<!-- <th>可选状态</th> -->
			<!-- 该div中对可选状态进行操作，点击可选，则显示出所有的商品信息，默认为不可选，显示当前用户当前的商品信息 -->
			<!-- <form name="myForm" class="form-search" method="post" action="ShowShoppingServlet?action=buy">
				<button class="btn btn-success" type="button" style="margin-right: 50px;">购买</button>
			</form> -->
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>是否购买</th>
						<th>产品图片</th>
						<th>产品编号</th>
						<th>固件编号</th>
						<th>入库时间</th>
						<th>单位</th>
						<th>单价</th>
						<th>商品状态</th>
						<th>购买数量</th>
						<th>商品总金额</th>
						<!-- <td>操作</td> -->
					</tr>
				</thead>
				<tbody>
					<form name="myForm" class="form-search" method="post" action="ShowShoppingInfoServlet?action=buy" >
					<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
						<c:forEach items="${dingShoppings}"  var="dingShopping" varStatus="stat">
							<tr>
								<td>
									<input type="checkbox" name="Ischecked" value ="${dingShopping.cnum}">
								</td>
								<td><img class="img-rounded" src="${pageContext.request.contextPath}/upload/${dingShopping.picture}" alt="产品编号为：${dingShopping.cnum}" onerror="this.src='${pageContext.request.contextPath}/images/bg.jpg;this.onerror=null'" width="80px" height="80px"/></td>
								
								<td>${dingShopping.ctype}
								<font><strong>-</strong></font>${dingShopping.cnum}
								<c:if test="${fn:contains(dingShopping.ctype,'MK')}">
									<font><strong>-</strong></font>${dingShopping.pt}
								</c:if>
								</td><!-- 产品编号 -->
								
								<td>${dingShopping.gunum}</td><!-- 固件编号 -->
								
								<!--  -->
								<td>${dingShopping.ruDate}</td><!-- 入库时间 -->
								
								<!--  -->
								<td>${dingShopping.danwei}</td><!-- 单位 -->
								
								<!--  -->
								<td>${dingShopping.price}</td><!-- 单价 -->
								
								<!--  -->
								<c:if test="${dingShopping.sstate==1}">
									<td><font color="green">在售</font></td><!-- 商品状态 -->
									<input type="hidden" id="sstate" name="sstate" value="${dingShopping.sstate}"/>
								</c:if>
								<c:if test="${dingShopping.sstate==2}">
									<td><font color="red">正在 生产</font></td><!-- 商品状态 -->
									<input type="hidden" id="price" name="price" value="${dingShopping.price}"/>
								</c:if>
								<!--  -->
								<td>${dingShopping.number}</td><!-- 数量 -->
								<td>${dingShopping.total}</td><!-- 总金额 -->
							</tr>
						</c:forEach>
						<span>
							<c:if test="${not empty dingShoppings}">
								<button class="btn btn-success" type="submit" style="margin-right: 50px;">购买</button>
							</c:if>
							<c:if test="${empty dingShoppings}">
								<button class="btn btn-danger" type="button" style="margin-right: 50px;" disabled>购买</button>&nbsp;<font id="error" color="red"><strong>购物车空空如也！请去添加商品先！</strong></font>
							</c:if>
						</span>
					</form>
				</tbody>
			</table>
			<div align="center">
				<font id="error" color="red">${error}</font>
			</div>
		</div>
		<div align="center"><font color="red"></font></div>
</div>