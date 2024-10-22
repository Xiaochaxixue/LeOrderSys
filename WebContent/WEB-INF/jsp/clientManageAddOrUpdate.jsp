<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function checkForm(){
		//检查用户是否输入用户名
		var name=document.getElementById("uid").value;
		var paw=document.getElementById("paw").value;
		var corname=document.getElementById("corname").value;
		var coraddress=document.getElementById("coraddress").value;
		var contact=document.getElementById("contact").value;
		var phone=document.getElementById("phone").value;
		var address0=document.getElementById("address0").value;
		if(name==""||paw==""||corname==""||coraddress==""||contact==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
			
		} else if(name==null||name==""){
			document.getElementById("error").innerHTML="用户名不能为空！";
			return false;
		}else if(paw==null||paw==""){
			document.getElementById("error").innerHTML="密码不能为空！";
			return false;
		}else if(corname==null||corname==""){
			document.getElementById("error").innerHTML="公司名称不能为空！";
			return false;
		}else if(coraddress==null||coraddress==""){
			document.getElementById("error").innerHTML="公司地址不能为空！";
			return false;
		}else if(contact==null||contact==""){
			document.getElementById("error").innerHTML="联系人不能为空！";
			return false;
		}else if(!/^1[34578]\d{9}$/.test(phone)){
			document.getElementById("error").innerHTML="手机号码格式不正确！";
			return false;
		}else if(address0==null||address0==""){
			document.getElementById("error").innerHTML="默认地址不能为空！";
			return false;
		}
		return true;
	}
	$(document).ready(function(){
		$("#client").addClass("active");
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
				添加客户信息
			</c:if>
			<c:if test="${id != null }">
				修改客户信息
			</c:if>
		</div>
		<form action="clientManageServlet?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				
				<table align="center">
					<tr>
						<c:if test="${id ==null}">
							<td><font color="red">*</font>用户名：</td>
							<td><input type="text" id="uid"  name="uid" value="${client.uid}"  style="margin-top:5px;height:30px;"/></td>
						</c:if>
						<c:if test="${id != null}">
							<td><font color="red">*</font>用户名：</td>
							<td><input type="text" id="uid"  name="uid" value="${client.uid}"  style="margin-top:5px;height:30px;" readonly="true"/></td>
						</c:if>
					</tr>
					<tr>
						<td><font color="red">*</font>密码：</td>
						<td><input type="text" id="paw"  name="paw" value="${client.paw}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>公司名称：</td>
						<td><input type="text" id="corname"  name="corname" value="${client.corname}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>公司地址：</td>
						<td><input type="text" id="coraddress"  name="coraddress" value="${client.coraddress}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>联系人：</td>
						<td><input type="text" id="contact"  name="contact" value="${client.contact}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>联系方式：</td>
						<td><input type="text" id="phone"  name="phone" value="${client.phone}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>默认地址：</td>
						<td><input type="text" id="address0"  name="address0" value="${client.address0}"  style="margin-top:5px;height:30px;" /></td>
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