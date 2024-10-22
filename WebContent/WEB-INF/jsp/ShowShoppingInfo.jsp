<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#showshoppingInfo").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			商品信息管理
		</div>
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='ShowShoppingInfoServlet?action=sselect'">可选商品</button>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>产品图片</th>
						<th>产品信息</th>
						<th>工艺说明</th>
						<th>产品名称</th>
						<th>单位</th>
						<th>单价</th>
						<th>商品状态</th>
						<th>购买数量</th>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dingShoppings}"  var="dingShopping" varStatus="stat">
						<form class="form-search" method="post" action="ShowShoppingInfoServlet?action=add">
						<tr>
								<td><img class="img-rounded" src="${pageContext.request.contextPath}/upload/${dingShopping.picture}" alt="产品编号为：${dingShopping.cnum}" onerror="this.src='${pageContext.request.contextPath}/images/bg.jpg;this.onerror=null'" width="80px" height="80px"/></td>
								<input type="hidden" id="picture" name="picture" value="${dingShopping.picture}"/>
								<td>
									<a onclick="this.href='ShowShoppingInfoServlet?action=shoppingDetaileInfo&id=${dingShopping.cnum}'">
										${dingShopping.ctype}
										<font><strong>-</strong></font>${dingShopping.cnum}
										<c:if test="${fn:contains(dingShopping.ctype,'MK')}">
											<font><strong>-</strong></font>${dingShopping.pt}
										</c:if>
									</a>
									<!-- 固件信息显示 -->
									<div>
										<p>
											<font size="1" color="grey">
											固件版本：${dingShopping.guversion}
											<input type="hidden" id="guversion" name="guversion" value="${dingShopping.guversion}"/>
											</font>
										</p>
										<p>
											<font size="1" color="grey">
											固件编号：${dingShopping.gunum}
												<c:if test="${flag==null}">
													<input type="hidden" id="gunum" name="gunum" value="${dingShopping.gunum}"/>
												</c:if>
												<c:if test="${flag!=null}">
													<input type="hidden" id="gunum" name="gunum" value="${dingShopping.gunum}"/>
													<input type="hidden" id="id" name="id" value="${flag}"/>
												</c:if>
											</font>
										</p>
									</div>
								</td>
								<input type="hidden" id="ctype" name="ctype" value="${dingShopping.ctype}"/>
								<input type="hidden" id="cnum" name="cnum" value="${dingShopping.cnum}"/>
								<input type="hidden" id="pt" name="pt" value="${dingShopping.pt}"/>
								<!-- 工艺信息显示 -->
								<td>
									<div>
										<p>
											<font size="1" color="grey">
												排针数量：${dingShopping.pinNum}&nbsp;&nbsp;&nbsp;&nbsp;排针大小：${dingShopping.pinSize}
												<input type="hidden" id="pinNum" name="pinNum" value="${dingShopping.pinNum}"/>
												<input type="hidden" id="pinSize" name="pinSize" value="${dingShopping.pinSize}"/>
											</font>
										</p>
										<p>
											<font size="1" color="grey">
												排针形状：${dingShopping.pinShape}&nbsp;&nbsp;&nbsp;&nbsp;焊接方式：${dingShopping.pinWeld}
												<input type="hidden" id="pinShape" name="pinShape" value="${dingShopping.pinShape}"/>
												<input type="hidden" id="pinWeld" name="pinWeld" value="${dingShopping.pinWeld}"/>
											</font>
										</p>
										<p>
											<font size="1" color="grey">
												天线类型：${dingShopping.antennaType}&nbsp;&nbsp;&nbsp;&nbsp;天线长度：${dingShopping.antennaLength}
												<input type="hidden" id="antennaType" name="antennaType" value="${dingShopping.antennaType}"/>
												<input type="hidden" id="antennaLength" name="antennaLength" value="${dingShopping.antennaLength}"/>
											</font>
										</p>
									</div>
								</td>
								<!-- 入库时间暂时不开放给用户查看 -->
								<%-- <td>${dingShopping.ruDate}</td><!-- 入库时间 --> --%>
								<input type="hidden" id="ruDate" name="ruDate" value="${dingShopping.ruDate}"/>
								<!-- 产品名称 -->
								<td>
									${dingShopping.cname}
									<input type="hidden" id="cname" name="cname" value="${dingShopping.cname}"/>
								</td>
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
									<td><font color="red">正在生产</font></td><!-- 商品状态 -->
									<input type="hidden" id="sstate" name="sstate" value="${dingShopping.sstate}"/>
								</c:if>
								<!--  -->
								<td><input class="form-control" type="number" id="number" name="number" value="1" style="margin-top:5px;height:30px;width:80px" /></td><!-- 数量 -->
								<td>
									<c:if test="${dingShopping.sstate==1}">
										<button class="btn btn-success" type="submit">加入购物车</button>&nbsp;
									</c:if>
									<c:if test="${dingShopping.sstate==2}">
										<button class="btn btn-info" type="submit">加入购物车</button>&nbsp;
									</c:if>
								</td>
						</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
			<div align="center">
				<font id="error" color="red">${error}</font>
			</div>
		</div>
		<div align="center"><font color="red"></font></div>
</div>