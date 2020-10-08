<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">

	$(document).ready(function(){
		var tip="${sessionScope.error}";
		toastr.options = {"closeButton": true,
				positionClass:"toast-center-center"};
		if((tip!=null)&&(tip!="")){
			toastr.warning(tip);
			<%
			request.getSession().removeAttribute("error");
			%>
		}
	});
	$(document).ready(function(){
		$("#shoppingCart").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			购物车管理
		</div>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<!-- <th>是否购买</th> -->
						<th>产品图片</th>
						<th>产品信息</th>
						<th>工艺说明</th>
						<th>产品名称</th>
						<th>单位</th>
						<th>单价</th>
						<th>商品状态</th>
						<th>购买数量</th>
						<th>商品总金额</th>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<!-- <form name="myForm" class="form-search" method="post" action="ShowShoppingInfoServlet?action=buy" > -->
					<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
						<c:forEach items="${dingShoppings}"  var="dingShopping" varStatus="stat">
							<tr>
								<%-- <td>
									<input type="checkbox" name="Ischecked" value ="${dingShopping.cnum}">
								</td> --%>
								<td><img class="img-rounded" src="${pageContext.request.contextPath}/upload/${dingShopping.picture}" alt="产品编号为：${dingShopping.cnum}" onerror="this.src='${pageContext.request.contextPath}/images/bg.jpg;this.onerror=null'" width="80px" height="80px"/></td>
								<td>
									<a onclick="this.href='ShowShoppingInfoServlet?action=shoppingDetaileInfo&id=${dingShopping.cnum}'">
										${dingShopping.ctype}
										<font><strong>-</strong></font>${dingShopping.cnum}
										<c:if test="${fn:contains(dingShopping.ctype,'MK')}">
											<font><strong>-</strong></font>${dingShopping.pt}
										</c:if>
									</a>
									<!-- 固件信息 -->
									<div>
										<p>
											<font size="1" color="grey">
											固件版本：${dingShopping.guversion}
											</font>
										</p>
										<p>
											<font size="1" color="grey">
											固件编号：${dingShopping.gunum}
											</font>
										</p>
									</div>
								</td><!-- 产品信息 -->
								<!-- 工艺信息显示 -->
								<td>
									<div>
										<p>
											<font size="1" color="grey">
												排针数量：${dingShopping.pinNum}&nbsp;&nbsp;&nbsp;&nbsp;排针大小：${dingShopping.pinSize}
											</font>
										</p>
										<p>
											<font size="1" color="grey">
												排针形状：${dingShopping.pinShape}&nbsp;&nbsp;&nbsp;&nbsp;焊接方式：${dingShopping.pinWeld}
											</font>
										</p>
										<p>
											<font size="1" color="grey">
												天线类型：${dingShopping.antennaType}&nbsp;&nbsp;&nbsp;&nbsp;天线长度：${dingShopping.antennaLength}
											</font>
										</p>
									</div>
								</td>
								<td>${dingShopping.cname}</td><!-- 产品名称 -->
								<td>${dingShopping.danwei}</td><!-- 单位 -->
								<td>${dingShopping.price}</td><!-- 单价 -->
								<c:if test="${dingShopping.sstate==1}">
									<td><font color="green">在售</font></td><!-- 商品状态 -->
									<input type="hidden" id="sstate" name="sstate" value="${dingShopping.sstate}"/>
								</c:if>
								<c:if test="${dingShopping.sstate==2}">
									<td><font color="red">正在生产</font></td><!-- 商品状态 -->
									<input type="hidden" id="price" name="price" value="${dingShopping.price}"/>
								</c:if>
								<!--  -->
								<td>${dingShopping.number}</td><!-- 数量 -->
								<td>${dingShopping.total}</td><!-- 总金额 -->
								<td>
									<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='ShowShoppingInfoServlet?action=buy&id={dingShopping.gunum}'">购买</button>
								</td>
							</tr>
						</c:forEach>
						<span>
							<c:if test="${not empty dingShoppings}">
								<!-- <button class="btn btn-success" type="submit" style="margin-right: 50px;">购买</button> -->
								&nbsp;<font id="error" color="green" size="3"><strong>欢迎购买商品，更多商品可以去左侧“商品信息”一栏进行选购！</strong></font>
							</c:if>
							<c:if test="${empty dingShoppings}">
								<!-- <button class="btn btn-danger" type="button" style="margin-right: 50px;" disabled>购买</button> -->
								&nbsp;<font id="error" color="red" size="3"><strong>购物车空空如也！请去添加商品先！</strong></font>
							</c:if>
						</span>
				</tbody>
			</table>
			<div align="center">
				<font id="error" color="red">${error}</font>
			</div>
		</div>
		<div align="center"><font color="red"></font></div>
</div>