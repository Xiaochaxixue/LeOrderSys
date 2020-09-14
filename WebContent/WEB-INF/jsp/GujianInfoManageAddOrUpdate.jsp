<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	toastr.options = {"closeButton": true,
			positionClass:"toast-center-center"};
	function checkForm(){
		//检查用户是否输入用户名
		var guversion=document.getElementById("guversion").value;
		var guname=document.getElementById("guname").value;
		
		if(guversion==""||guname==""||guversion==null||guname==null){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		}else if(document.getElementById("qfile").value==""){
			 toastr.info("请上传文件!"); 
			return false;
			} 
		return true;
	}
	
	$(document).ready(function(){
		$("#gujian").addClass("active");
	});	
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<div class="data_list">
		<div class="data_list_title">
			<c:if test="${id == null }">
				添加固件信息
			</c:if>
			<c:if test="${id != null }">
				修改固件信息
			</c:if> 
		</div>
		<c:if test="${id == null }">
		<form action="gujianFileUploadServlet" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
		</c:if>
		<c:if test="${id != null }">
		<form action="gujianInfoManageServlet?action=save" method="post" onsubmit="return checkForm()">
		</c:if>
			<div class="data_form" >
				
				<table align="center">
					<tr>
					<c:if test="${id != null }">
						<input type="hidden" id="id" name="id" value="${id}"/>
						<td><font color="red">*</font>固件编号：</td>
						<td><input type="text" id="gunum"  name="gunum" value="${guJian.gunum}"  style="margin-top:5px;height:30px;" readonly="true"/></td>
					</c:if>
					<c:if test="${id == null }">
						<td><font color="red">*</font>固件编号：</td>
						<td><input type="text" id="gunum"  name="gunum" value="${gunum}"  style="margin-top:5px;height:30px;" readonly="true"/></td>
					</c:if>
					</tr>
					<tr>
						<td><font color="red">*</font>用户名：</td>
						<td>
							<select id="uid" name="uid">
								<c:forEach items="${clients}" var="client">
									<c:if test="${guJian.uid==client.uid }">
										<option value="${guJian.uid}" selected="selected" style="margin-top:5px;height:30px;">${guJian.uid}</option>
									</c:if>
									<c:if test="${guJian.uid!=client.uid }">
										<option value="${client.uid}" style="margin-top:5px;height:30px;">${client.uid}</option>
									</c:if>
								</c:forEach>		
							</select>
						</td>
						<%-- <td><input type="text" id="uid"  name="uid" value="${guJian.uid}"  style="margin-top:5px;height:30px;" /></td> --%>
					</tr>
					<tr>
						<td><font color="red">*</font>产品编号：</td>
						<td>
							<select id="cnum" name="cnum">
								<c:forEach items="${shoppingInfos}" var="shoppingInfo">
									<c:if test="${guJian.cnum==shoppingInfo.cnum }">
										<option value="${guJian.cnum}" selected="selected" style="margin-top:5px;height:30px;">${guJian.cnum}</option>
									</c:if>
									<c:if test="${guJian.cnum!=shoppingInfo.cnum }">
										<option value="${shoppingInfo.cnum}" style="margin-top:5px;height:30px;">${shoppingInfo.cnum}</option>
									</c:if>
								</c:forEach>		
							</select>
						</td>
					</tr>
					<tr>
						<td><font color="red">*</font>固件版本：</td>
						<td><input type="text" id="guversion"  name="guversion" value="${guJian.guversion}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>固件名：</td>
						<td><input type="text" id="guname"  name="guname" value="${guJian.guname}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>固件状态：</td>
						<%-- <td><input type="text" id="gustate"  name="gustate" value="${guJian.gustate}"  style="margin-top:5px;height:30px;" /></td> --%>
						<td>
						<select  id="gustate" name="gustate">
							  <option value="1" selected="selected">状态1</option>
							  <option value="2">状态2</option>
							  <option value="3">状态3</option>
						</select>
						</td>
					</tr>
					<tr>
						<td><font color="red"></font>固件备注：</td>
						<td><input type="text" id="gups"  name="gups" value="${guJian.gups}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td>产品确认书：</td>
						<c:if test="${id == null }">
							<td><input type="file" id="qfile"  name="qfile" value="请选择文件..."   style="margin-top:5px;height:30px;" /></td>
						</c:if>
						<c:if test="${id != null }"><!-- 当修改文件时 -->
							<td>
								<button class="btn btn-default" type="button" onclick="javascript:window.location='ModifyFileServlet?action=jump&id=${guJian.gunum}'">文件修改</button>
							</td>
						</c:if>
					</tr>
				</table>
				<div align="center">
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