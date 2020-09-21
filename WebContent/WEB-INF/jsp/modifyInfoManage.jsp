<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function checkForm(){
		//检查用户是否输入用户名
		var name=document.getElementById("uid").value;
		var newpaw=document.getElementById("newpaw").value;
		var newpaw1=document.getElementById("newpaw1").value;
		var corname=document.getElementById("corname").value;
		var coraddress=document.getElementById("coraddress").value;
		var contact=document.getElementById("contact").value;
		var phone=document.getElementById("phone").value;
		var address0=document.getElementById("address0").value;
		if(name==""||corname==""||coraddress==""||contact==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
			
		} else if(name==null||name==""){
			document.getElementById("error").innerHTML="用户名不能为空！";
			return false;
		}else if(newpaw!=newpaw1){
			document.getElementById("error").innerHTML="两次密码不一致！";
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
		var tip="${sessionScope.tip}";
		toastr.options = {"closeButton": true,
				positionClass:"toast-center-center"};
		if((tip!=null)&&(tip!="")){
			if(tip.indexOf("不成功")!=-1){
				toastr.error(tip);
			}else{
				toastr.info(tip);
			}
			<%
			request.getSession().removeAttribute("tip");
			%>
		}
	});
	$(document).ready(function(){
		$("#modifyInfo").addClass("active");
	});	
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<div class="data_list">
		<div class="data_list_title">
			修改企业信息
		</div>
		<button class="btn btn-success" type="button" style="margin-right: 50px;" data-toggle="modal" data-target="#myModal">上传营业执照扫描件</button>
		<form action="ModifyInfoServlet?action=save&paw=${session_user.uid}" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<table align="center">
					<tr>
						<td><font color="red">*</font>用户名：</td>
						<td><input type="text" id="uid"  name="uid" value="${session_user.uid}"  style="margin-top:5px;height:30px;" readonly="true"/></td>
					</tr>
					<tr>
						<td><font color="red"></font>新密码：</td>
						<td><input type="password" id="newpaw"  name="newpaw" style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red"></font>确认密码：</td>
						<td><input type="password" id="newpaw1"  name="newpaw1" style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red"></font>公司名称：</td>
						<td><input type="text" id="corname"  name="corname" value="${client.corname}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red"></font>公司地址：</td>
						<td><input type="text" id="coraddress"  name="coraddress" value="${client.coraddress}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red"></font>联系人：</td>
						<td><input type="text" id="contact"  name=contact value="${client.contact}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red"></font>联系方式：</td>
						<td><input type="text" id="phone"  name="phone" value="${client.phone}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red"></font>默认收货地址：</td>
						<td><input type="text" id="address0"  name="address0" value="${client.address0}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<%-- <tr>
						<td>收货地址2：</td>
						<td><input type="text" id="address1"  name="address1" value="${client.address1}"  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td>收货地址3：</td>
						<td><input type="text" id="address2"  name="address2" value="${client.address2}"  style="margin-top:5px;height:30px;" /></td>
					</tr> --%>
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
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		        	<form name="myform" action="ModifyFileServlet?action=companyLicese&id=${session_user.uid}" method="post" ENCTYPE="multipart/form-data">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title">上传企业营业执照扫描件</h4>
			                <p>
				                <font size="2" color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                		请上传贵公司具有法律效力的营业执照扫描件！管理员会在5个工作日内审核完成该证件的有效性。
				                </font>
			                </p>
			            </div>
			            <div class="modal-body">
							<p>&nbsp;&nbsp;
								<strong>请选择相应的文件：</strong><input type="file" name="picture" id="picture" value="请选择文件..." style="margin-top:5px;height:30px;"/>
							</p>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                <button type="submit" class="btn btn-primary">提交</button>
			            </div>
		            </form>
		        </div>
		    </div>
		</div>
</div>
</html>