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
		$("#showshoppingInfo").addClass("active");
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<div class="data_list">
		<div class="data_list_title">
				商品信息详情
		</div>
			<div class="data_form" >
				<table align="center">
					<tr>
						<td>
							<img class="img-responsive" src="${pageContext.request.contextPath}/upload/${shoppingInfo.picture}" alt="产品编号为：${shoppingInfo.cnum}"  onerror="this.src='${pageContext.request.contextPath}/images/bg.jpg;this.onerror=null'" width="600px" height="600px"/>
						</td>
						<td>
							<p><strong>产品编号：</strong>${shoppingInfo.ctype}
								<font><strong>-</strong></font>${shoppingInfo.cnum}
								<c:if test="${fn:contains(shoppingInfo.ctype,'MK')}">
									<font><strong>-</strong></font>${shoppingInfo.pt}
								</c:if>
							</p>
							<p><strong>商品单价：</strong>${shoppingInfo.price}元</p>
							<%--  --%>
							<p><strong>工艺说明：<c:if test="${!fn:contains(shoppingInfo.ctype,'MK')}"><font color="red">无特别说明</font></c:if></strong></p>
							<c:if test="${fn:contains(shoppingInfo.ctype,'MK')}">
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									排针数量:${craftInfoMap.pinNum}
								</p>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									排针大小:<strong>${craftInfoMap.pinSize}</strong>&nbsp;&nbsp;mm
								</p>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									排针形状:(<font color="red">${craftInfoMap.pinShape}</font>)
												<c:if test="${fn:contains(craftInfoMap.pinShape,'Z')}">直排针</c:if>
												<c:if test="${fn:contains(craftInfoMap.pinShape,'W')}">弯排针</c:if>
								</p>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									焊接方式:(<font color="red">${craftInfoMap.pinWeld}</font>)
												<c:if test="${fn:contains(craftInfoMap.pinWeld,'Z')}">正焊</c:if>
												<c:if test="${fn:contains(craftInfoMap.pinWeld,'F')}">反焊</c:if>
								</p>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									天线类型:(<font color="red">${craftInfoMap.antennaType}</font>)
												<c:if test="${fn:contains(craftInfoMap.antennaType,'B')}">标准铁氟龙天线</c:if>
												<c:if test="${fn:contains(craftInfoMap.antennaType,'C')}">硅胶长天线</c:if>
												<c:if test="${fn:contains(craftInfoMap.antennaType,'I')}">Ipex天线</c:if>
								</p>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									天线长度:<strong>${craftInfoMap.antennaLength}</strong>&nbsp;&nbsp;cm
								</p>
							</c:if>
						</td>
					</tr>	
				</table>
				<div align="center">
					<font id="error" color="red">${error}</font>
				</div>
			</div>
</div>
</html>