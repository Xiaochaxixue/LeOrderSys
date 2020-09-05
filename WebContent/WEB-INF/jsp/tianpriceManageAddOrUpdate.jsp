<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function checkForm(){
		//检查输入是否正确
		var tiantype=document.getElementById("tiantype").value;
		var price=document.getElementById("price").value;
		if(tiantype==""||price==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
			
		} else if(tiantype==null||tiantype==""){
			document.getElementById("error").innerHTML="天线类型不能为空！";
			return false;
		}else if(price==null||price==""){
			document.getElementById("error").innerHTML="天线单价不能为空！";
			return false;
		}else if(!(/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/.test(price))){
			document.getElementById("error").innerHTML="请输入正确价格格式:整数或者保留两位小数";
			return false;
		}else if(!(/^[1-9]\d*$/.test(tiantype))){
			document.getElementById("error").innerHTML="请输入正确天线类型格式:正整数";
			return false;
		}
		return true;
	}
	$(document).ready(function(){
		$("#tianprice").addClass("active");
	});	
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>附加费用信息</title>
</head>

	<div class="data_list">
		<div class="data_list_title">
			<c:if test="${id == null }">
				添加附加费用信息
			</c:if>
			<c:if test="${id != null }">
				修改附加费用信息
			</c:if>
		</div>
		<form action="tianpriceManageServlet?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				
				<table align="center">
					<tr>
						<td><font color="red">*</font>天线类型：</td>
						<c:if test="${id != null }">
							<input type="hidden" id="id" name="id" value="${id}"/>
							<td><input type="text" id="tiantype"  name="tiantype" value="${tianPrice.tiantype}"  style="margin-top:5px;height:30px;" readonly="true"/></td>
						</c:if>
						<c:if test="${id == null }">
							<td><input type="text" id="tiantype"  name="tiantype" value="${tianPrice.tiantype}"  style="margin-top:5px;height:30px;" /></td>
						</c:if>
					</tr>
					<tr>
						<td><font color="red">*</font>天线单价：</td>
						<td><input type="text" id="price"  name="price" value="${tianPrice.price}"  style="margin-top:5px;height:30px;" /></td>
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