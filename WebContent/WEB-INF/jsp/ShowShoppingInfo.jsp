<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#showshoppingInfo").addClass("active");
	});
	
</script>
<div class="data_list">
		<div class="data_list_title">
			商品信息管理
		</div>
			<!-- <th>可选状态</th> -->
			<!-- 该div中对可选状态进行操作，点击可选，则显示出所有的商品信息，默认为不可选，显示当前用户当前的商品信息 -->
			<form name="myForm" class="form-search" method="post" action="shoppingInfoManageServlet?action=list&id=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='ShowShoppingInfoServlet?action=sselect'">可选商品</button>
				<!-- <span class="data_search">
					<button type="submit" class="btn btn-info">搜索</button>
				</span> -->
			</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>产品图片</th>
						<th>产品编号</th>
						<c:if test="${flag==null}">
						<th>固件编号</th>
						</c:if>
						<th>入库时间</th>
						<th>单位</th>
						<th>单价</th>
						<th>商品状态</th>
						<th>购买数量</th>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
					<c:forEach items="${dingShoppings}"  var="dingShopping" varStatus="stat">
						<tr>
							<form class="form-search" method="post" action="ShowShoppingInfoServlet?action=add" target="targetIfr">
								<td><img class="img-rounded" src="${pageContext.request.contextPath}/upload/${dingShopping.picture}" alt="产品编号为：${dingShopping.cnum}" onerror="this.src='${pageContext.request.contextPath}/images/bg.jpg;this.onerror=null'" width="80px" height="80px"/></td>
								<input type="hidden" id="picture" name="picture" value="${dingShopping.picture}"/>
								<!--  -->
								<td>${dingShopping.cnum}</td><!-- 产品编号 -->
								<input type="hidden" id="cnum" name="cnum" value="${dingShopping.cnum}"/>
								<!--  -->
								<c:if test="${flag==null}">
								<td>${dingShopping.gunum}</td><!-- 固件编号 -->
								<input type="hidden" id="gunum" name="gunum" value="${dingShopping.gunum}"/>
								</c:if>
									<c:if test="${flag!=null}">
									<input type="hidden" id="id" name="id" value="${flag}"/>
								</c:if>
								<!--  -->
								<td>${dingShopping.ruDate}</td><!-- 入库时间 -->
								<input type="hidden" id="ruDate" name="ruDate" value="${dingShopping.ruDate}"/>
								<!--  -->
								<td>${dingShopping.danwei}</td><!-- 单位 -->
								<input type="hidden" id="danwei" name="danwei" value="${dingShopping.danwei}"/>
								<!--  -->
								<td>${dingShopping.price}</td><!-- 单价 -->
								<input type="hidden" id="price" name="price" value="${dingShopping.price}"/>
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
								<td><input class="form-control" type="number" id="number" name="number" value="1" style="margin-top:5px;height:30px;width:80px" /></td><!-- 数量 -->
								<td>
									<button class="btn btn-info" type="submit">加入购物车</button>&nbsp;
								</td>
							</form>
								<iframe name="targetIfr" style="display:none"></iframe> 
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>