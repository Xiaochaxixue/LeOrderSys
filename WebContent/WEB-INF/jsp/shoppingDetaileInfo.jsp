<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<script type="text/javascript">
		$(document).ready(function(){
		$("#shoppingInfo").addClass("active");
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<div class="data_list">
		<div class="data_list_title">
				商品信息详情
		</div>
		<form action="clientManageServlet?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<table align="center">
					<tr>
						<td>
							<img class="img-responsive" src="${pageContext.request.contextPath}/upload/${shoppingInfo.picture}" alt="产品编号为：${shoppingInfo.cnum}"  onerror="this.src='${pageContext.request.contextPath}/images/bg.jpg;this.onerror=null'" width="600px" height="600px"/>
						</td>
						<td>
							<p>产品编号：${shoppingInfo.ctype}
								<font><strong>-</strong></font>${shoppingInfo.cnum}
								<c:if test="${fn:contains(shoppingInfo.ctype,'MK')}">
									<font><strong>-</strong></font>${shoppingInfo.pt}
								</c:if>
							</p>
							<p>商品单价：${shoppingInfo.price}元</p>
							<%-- <c:if test="${fn:contains(shoppingInfo.ctype,'MK')}"></c:if> --%>
							<p>工艺说明：</p>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;排针数量:${craftInfoMap.pinNum}</p>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;排针大小:${craftInfoMap.pinSize}</p>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;排针形状:${craftInfoMap.pinShape}</p>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;焊接方式:${craftInfoMap.pinWeld}</p>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;天线类型:${craftInfoMap.antennaType}</p>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;天线长度:${craftInfoMap.antennaLength}</p>
						</td>
					</tr>	
				</table>
				<div align="center">
					<font id="error" color="red">${error}</font>
				</div>
			</div>
		</form>
</div>
</html>