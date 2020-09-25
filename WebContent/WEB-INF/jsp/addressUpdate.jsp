<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改地址信息</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#addressInfo").addClass("active");
	});	
</script>
</head>
	<div class="data_list">
		<div class="data_list_title">
				修改地址信息
		</div>
		<form action="Tb_addressServlet?action=update" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<table align="center">
					<tr>
						<td>收货人：</td>
						<td>
							<c:if test="${id eq 'default'}">
								<input type="text" id="acceptor"  name="acceptor" value="${Client.contact}" style="margin-top:5px;height:30px;"/>
								<input type="hidden"  id="id"  name="id" value="${id}"  >
							</c:if>
							<c:if test="${empty id}">
								<input type="text" id="acceptor"  name="acceptor" value="${Tb_address.acceptor}"style="margin-top:5px;height:30px;"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>收货电话号码：</td>
						<td>
							<c:if test="${id eq 'default'}">
								<input type="text" id="tel"  name="tel" value="${Client.phone}"style="margin-top:5px;height:30px;"/>
							</c:if>
							<c:if test="${empty id}">
								<input type="text" id="tel"  name="tel" value="${Tb_address.tel}"style="margin-top:5px;height:30px;" />
							</c:if>
						</td>
					</tr>
					<tr>
						<td>收货地址：</td>
						<td>
							<c:if test="${id eq 'default'}">
								<input type="text" id="address"  name="address" value="${Client.address0}" style="margin-top:5px;height:30px;"/>
							</c:if>
							<c:if test="${empty id}">
								<input type="text" id="address"  name="address" value="${Tb_address.address}" style="margin-top:5px;height:30px;" />
							</c:if>
						</td>
					</tr>
				</table>
				<div align="center">
					<!--type="submit"  意味着会将表单中的内容提交到action属性值dormBuild.action?action=save的请求处理类中  -->
					<input type="submit" class="btn btn-primary" value="保存"/>
					&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
				</div>
				<div align="center">
					<font id="error" color="red">${error}</font>
				</div>
			</div>
		</form>
	</div>
</html>