<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息管理</title>
</head>

	<div class="data_list">
		<div class="data_list_title">
			<c:if test="${id == null }">
				添加商品信息
			</c:if>
			<c:if test="${id != null }">
				修改商品信息/上传图片
			</c:if>
		</div>
		<form id="app" action="shoppingInfoManageServlet?action=save" method="post" onsubmit="return checkForm()">
		
			<div class="data_form" >
				
				<table align="center">
					<c:if test="${id != null }">
						<input type="hidden" id="ctype" name="ctype" value="${shoppingInfo.ctype}"/>
					</c:if>
					<tr>
						<c:if test="${id == null }"><td><font color="red">*</font>产品类型：</td></c:if>
						<td>
							<c:if test="${id == null }">
								<select id="ctype" name="ctype" onchange="tianNext()">
									<c:if test="${shoppingInfo.ctype eq 'IC'}"><option value="IC" selected="selected">芯片</option></c:if>
									<c:if test="${shoppingInfo.ctype ne 'IC'}"><option value="IC">芯片</option></c:if>
									
									<c:if test="${shoppingInfo.ctype eq 'MK'}"><option value="MK" selected="selected">MK模块</option></c:if>
									<c:if test="${shoppingInfo.ctype ne 'MK'}"><option value="MK">MK模块</option></c:if>
									
									<c:if test="${shoppingInfo.ctype eq 'MIMK'}"><option value="MIMK" selected="selected">MIMK模块</option></c:if>
									<c:if test="${shoppingInfo.ctype ne 'MIMK'}"><option value="MIMK">MIMK模块</option></c:if>
									
									<c:if test="${shoppingInfo.ctype eq 'JDMK'}"><option value="JDMK" selected="selected">JDMK模块</option></c:if>
									<c:if test="${shoppingInfo.ctype ne 'JDMK'}"><option value="JDMK">JDMK模块</option></c:if>
									
									<c:if test="${shoppingInfo.ctype eq 'PCBA'}"><option value="PCBA" selected="selected">PCBA</option></c:if>
									<c:if test="${shoppingInfo.ctype ne 'PCBA'}"><option value="PCBA">PCBA</option></c:if>
									
									<c:if test="${shoppingInfo.ctype eq '02'}"><option value="02" selected="selected">成品</option></c:if>
									<c:if test="${shoppingInfo.ctype ne '02'}"><option value="02">成品</option></c:if>
								</select>
							</c:if>
						</td>
						<%-- <c:if test="${id != null }">
								<td><input type="text" id="ctype" name="ctype" value="${shoppingInfo.ctype}" readonly="true" style="margin-top:5px;height:30px;width:60px;"/></td>
						</c:if> --%>
					</tr>
					<tr>
						<td><font color="red">*</font>产品编号：</td>
						<c:if test="${id != null }">
							<input type="hidden" id="id" name="id" value="${id}"/>
							<td>
								<input type="text" id="showCtype" name="showCtype" value="${shoppingInfo.ctype}" style="margin-top:5px;height:30px;width:60px;"readonly="true">
								<strong>-</strong>
								<input type="text" id="cnum"  name="cnum" value="${shoppingInfo.cnum}"  style="margin-top:5px;height:30px;width:130px;" readonly="true"/>
								<c:if test="${shoppingInfo.pt != null&& not empty  shoppingInfo.pt}">
									<strong>-</strong>
								<input type="text" id="pt"  name="pt" value="${shoppingInfo.pt}"  style="margin-top:5px;height:30px;width:130px;" readonly="true"/>
								</c:if>
							</td>
						</c:if>
						<c:if test="${id == null }">
							<td>
								<input type="text" id="showCtype" name="showCtype" value="IC" style="margin-top:5px;height:30px;width:60px;"readonly="true">
								<strong>-</strong><input type="text" id="cnum"  name="cnum" value="${shoppingInfo.cnum}" style="margin-top:5px;height:30px;width:150px;" placeholder="程序等编号..." />
								<input type="hidden" id="pt" name="pt" value="" style="margin-top:5px;height:30px;width:60px;" placeholder="工艺...">
							</td>
						</c:if>
					</tr>
					<tr>
						<td><font color="red">*</font>产品名称：</td>
						<td><input type="text" id="cname"  name="cname" value="${shoppingInfo.cname}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>规格：</td>
						<td><input type="text" id="guige"  name="guige" value="${shoppingInfo.guige}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>单位：</td>
						<td><input type="text" id="danwei"  name="danwei" value="${shoppingInfo.danwei}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>单价：</td>
						<td><input type="text" id="price"  name="price" value="${shoppingInfo.price}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>商品状态：</td>
						<c:if test="${id != null && shoppingInfo.sstate==1}">
						<td><input type="text" id="sstate"  name="sstate" value="在售"  style="margin-top:5px;height:30px;" /></td>
						</c:if>
						<c:if test="${id != null && shoppingInfo.sstate==2}">
						<td><input type="text" id="sstate"  name="sstate" value="正在生产"  style="margin-top:5px;height:30px;" /></td>
						</c:if>
						<c:if test="${id == null }">
						<td>
							<select id="sstate" name="sstate">
							  <option value="1" selected="selected">在售</option>
							  <option value="2">正在生产</option>
							</select>
						</td>
						</c:if>
						<td><label><strong>1:在售,2:正在生产</strong></label></td>
					</tr>
					<tr>
						<td><font color="red">*</font>可选状态：</td>
						<c:if test="${id != null && shoppingInfo.sselect==1}">
						<td><input type="text" id="sselect"  name="sselect" value="可选"  style="margin-top:5px;height:30px;" /></td>
						</c:if>
						<c:if test="${id != null && shoppingInfo.sselect==2}">
						<td><input type="text" id="sselect"  name="sselect" value="不可选"  style="margin-top:5px;height:30px;" /></td>
						</c:if>
						<c:if test="${id == null }">
						<td>
							<select id="sselect" name="sselect" >
							  <option value="1" selected="selected">可选</option>
							  <option value="2">不可选</option>
							</select>
						</td>
						</c:if>
						<td><label><strong>1:可选,2:不可选</strong></label></td>
					</tr>
				<c:if test="${id!=null }">
					<tr>
						<td>产品图片：</td>
						<td><button class="btn btn-default" type="button" onclick="javascript:window.location='ModifyFileServlet?action=jump2&id=${shoppingInfo.cnum}'">上传文件</button></td>
					</tr>
				</c:if>
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
<script type="text/javascript">
	toastr.options = {"closeButton": true,
			positionClass:"toast-center-center"};
	//console.log(app);
	function checkForm(){
	//检查用户是否输入
	//console.log(app[0].value);
	
	var cnum=document.getElementById("cnum").value;
	var ctype=document.getElementById("ctype").value;
	var cname=document.getElementById("cname").value;
	var guige=document.getElementById("guige").value;
	var price=document.getElementById("price").value;
	/* var tiantype=document.getElementById("tiantype").value; */
	var danwei=document.getElementById("danwei").value;
	var sstate=document.getElementById("sstate").value;
	var sselect=document.getElementById("sselect").value;
	if(cnum==""||cname==""||guige==""||danwei==""||sstate==""||sselect==""){
		document.getElementById("error").innerHTML="信息填写不完整！";
		return false;
	}else if(!(/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/.test(price))){
		document.getElementById("error").innerHTML="请输入正确价格格式:整数或者保留两位小数";
		return false;
	}else if(!(sstate=='在售'||sstate=='正在生产'||sstate==1||sstate==2)){
		document.getElementById("error").innerHTML="商品状态输入错误，输入在售或正在生产！";
		return false;
	}else if(!(sselect=='可选'||sselect=='不可选'||sselect==1||sselect==2)){
		document.getElementById("error").innerHTML="可选状态输入错误，输入可选或不可选！";
		return false;
	}
	return true;
}
    function tianNext(){
    	document.all["showCtype"].value=document.all["ctype"].value;
    	if(document.all["ctype"].value.indexOf("MK") != -1){
    		/* $("#pt").attr("type","text"); */
    		$("#pt").attr("type","text");
    		
    		document.getElementById("pt").setAttribute("placeholder","工艺...");
    	}else{
    		$("#pt").attr("type","hidden");
    	}
    }
$(document).ready(function(){
	$("#shoppingInfo").addClass("active");
});	
</script>