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
			采购清单
		</div>
		<div><!-- 购买意向清单主体div -->
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>购买产品信息</th>
						<th>商品单价</th>
						<th>购买数量</th>
						<th>金额总计</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<img class="img-rounded" src="${pageContext.request.contextPath}/upload/${dingShopping.picture}" alt="产品编号为：${dingShopping.cnum}" onerror="this.src='${pageContext.request.contextPath}/images/bg.jpg;this.onerror=null'" width="80px" height="80px"/>
						</td><!-- 产品图片 -->
						<td>${dingShopping.price}</td><!-- 商品价格 -->
						<td>${dingShopping.number}</td><!-- 购买数量 number-->
						<td>${dingShopping.total}</td><!-- 金额总计total -->
					</tr>
					<tr>
						<td colspan="4">
						<div>
						<p>产品信息</p>
							<p></p><!-- 产品名称 -->
							<p></p><!-- 固件信息 -->
						</div>
						</td>
					</tr><!-- 产品信息 -->
					<tr>
						<td colspan="4">
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
					</tr><!-- 产品的工艺信息 -->
					<tr></tr><!-- 收货信息和发票信息 -->
					<tr>
						<!-- <td> -->
						<button class="btn btn-success" type="submit" style="margin-right: 50px;">购买</button>
						<!-- </td> -->
					</tr><!-- 提交订单等操作 -->
				</tbody>
			</table>
		</div>
		<div align="center">
			<font id="error" color="red">${error}</font>
		</div>
		<div align="center"><font color="red"></font></div>
</div>