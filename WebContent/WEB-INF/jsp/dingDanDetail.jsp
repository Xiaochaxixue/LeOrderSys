<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#dingdanManage").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			<strong>订单编号为：<font color="red">${dingDan.ddanNum}</font></strong>
		</div>
			<span class="data_search"> 
				<strong><font color="red">总金额：${dingDan.totalprice}</font></strong>
			</span>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<tbody>
					<c:forEach items="${dingShoppings}"  var="dingShopping" varStatus="stat"><!-- 将订单详情循环展示出来 -->
						<tr>
							<!-- <td> -->
								<p>
									产品编号：${dingShopping.ctype}
										<font><strong>-</strong></font>${dingShopping.cnum}
										<c:if test="${fn:contains(dingShopping.ctype,'MK')}">
											<font><strong>-</strong></font>${dingShopping.pt}
										</c:if>
								</p>
								<p>商品单价：${dingShopping.price}</p>
								<p>购买数量：${dingShopping.number}</p>
								<p>合计：${dingShopping.total}</p>
								<!-- <div align="center"> -->
									<input type="button" class="btn btn-danger" value="修改工艺"/>
								<!-- </div> -->
							<!-- </td> -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>