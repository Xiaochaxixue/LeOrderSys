<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="data_list">
		<div class="data_list_title">
			<strong>订单编号为：<font color="red">${dingDan.ddanNum}</font></strong>
		</div>
			<span class="data_search"> 
				总金额：<strong><font color="red" size="4">${dingDan.totalprice}</font></strong>&nbsp;&nbsp;元
			</span>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<tbody>
					<c:forEach items="${dingShoppings}"  var="dingShopping" varStatus="stat"><!-- 将订单详情循环展示出来 -->
					<!-- <form name="myForm" method="post" action="DingDanManageServlet?action=operation"> -->
						<form name ="myForm" method="post"  action="DingDanManageServlet?action=modifyPt">
							<tr>
								 <td> 												
									<p>
										<a onclick="this.href='ShowShoppingInfoServlet?action=shoppingDetaileInfo&id=${dingShopping.cnum}'">
											产品编号：${dingShopping.ctype}
												<font><strong>-</strong></font>${dingShopping.cnum}
												<c:if test="${fn:contains(dingShopping.ctype,'MK')}">
													<font><strong>-</strong></font>${dingShopping.pt}
												</c:if>
											</a>
									</p>
									<p>商品单价：${dingShopping.price}</p>
									<p>购买数量：${dingShopping.number}</p>
									<p>合计：${dingShopping.total}</p>
									<c:if test="${fn:contains(dingShopping.ctype,'MK')}">
										<input type="hidden" id="cnum" name="cnum" value="${dingShopping.cnum}">
										<input type="hidden" id="ddanNum" name="ddanNum" value="${dingDan.ddanNum}">
										<input type="text" id="pt" name="pt" value="${dingShopping.pt} " style="margin-top:5px;height:30px;width:120px;">&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-danger" value="修改工艺"/>
									</c:if>
								</td>
						</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var tip="${sessionScope.tip}";
		toastr.options = {"closeButton": true,
				positionClass:"toast-center-center"};
		if((tip!=null)&&(tip!="")){
			toastr.info(tip);
			<%
			request.getSession().removeAttribute("tip");
			%>
		}
	});
	$(document).ready(function(){
		$("#dingdanManage").addClass("active");
	});
</script>
<!--  -->