<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排针费用信息</title>
<script type="text/javascript">
	function checkForm(){
		//检查用户是否输入
		var pprice2=document.getElementById("pprice2").value;
		//var priceReg="/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/";
		if(pprice2==""||pprice2==null){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		} else if(!(/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/.test(pprice2))){
			//alert("请输入正确价格格式:整数或者保留两位小数");
			document.getElementById("error").innerHTML="请输入正确价格格式:整数或者保留两位小数";
			return false;
		}
		return true;
	}
	$(document).ready(function(){
		$("#tianprice").addClass("active");
	});	
</script>
</head>
	<div class="data_list">
		<div class="data_list_title">
				修改排针费用信息
		</div>
		<form action="updatePaiServlet?action=save&id=${Pai.pprice}" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<table align="center">
					<tr>
						<td><font color="red">*</font>原有价格：</td>
						<td><input type="text" id="pprice"  name="pprice" value="${Pai.pprice}"  style="margin-top:5px;height:30px;" readonly="true" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>现有价格：</td>
						<td><input type="text" id="pprice2"  name="pprice2" style="margin-top:5px;height:30px;" /></td>
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