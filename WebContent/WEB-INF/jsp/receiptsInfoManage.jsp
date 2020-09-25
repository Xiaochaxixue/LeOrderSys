<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	$(document).ready(function(){
		var tip="${sessionScope.tip}";
		toastr.options = {"closeButton": true,
				positionClass:"toast-center-center"};
		if((tip!=null)&&(tip!="")){
			toastr.error(tip);
			<%
			request.getSession().removeAttribute("tip");
			%>
		}
	});
	
	$(document).ready(function(){
		$("#receiptInfo").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			发票管理
		</div><!-- style="font-size : 20px; width: 100%; height: 100px;" -->
				<button class="btn btn-success" type="button" style="margin-right: 50px;" data-toggle="modal" data-target="#myModal">新增普通发票</button>
				<button class="btn btn-success" type="button" style="margin-right: 50px;" data-toggle="modal" data-target="#myModal2">增值税专用发票</button>
		<div>
			<div class="data_form" >
			<c:forEach items="${receipts}"  var="receipt" varStatus="stat">
				<table class="table  table-bordered table-hover datatable"  style="width:500px" align="center">
				<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
						<tr>
							<td width="50%" style="text-align:center"><strong>发票抬头:</strong></td>
							<td>${receipt.title}</td>
						</tr>	
						<tr>	
							<td width="50%" style="text-align:center"><strong>企业纳税人识别号:</strong></td>
							<td>${receipt.tax}</td>
						</tr>
						<c:if test="${not empty receipt.bank||!(receipt.bank eq null)}">
							<tr>
								<td width="50%" style="text-align:center"><strong>开户银行:</strong></td>
								<td>${receipt.bank}</td>
							</tr>	
							<tr>
								<td width="50%" style="text-align:center"><strong>银行账号:</strong></td>
								<td>${receipt.banknumber}</td>
							</tr>	
							<tr>
								<td width="50%" style="text-align:center"><strong>注册地址:</strong></td>
								<td>${receipt.registeadd}</td>
							</tr>	
							<tr>
								<td width="50%" style="text-align:center"><strong>注册电话:</strong></td>
								<td>${receipt.registecall}</td>
							</tr>	
						</c:if>
				</table>
			</c:forEach>
				<div align="center">
					<font id="error" color="red">${error}</font>
				</div>
			</div>
	</div>
</div>
<!-- 增加普通发票模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        	<form name="myform" action="receiptInfoServlet?action=add" method="post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title">增加发票信息</h4>
	                <p><font size="2" color="red">该功能目前只支持增加功能，暂时不支持客户自己修改，如要修改，请联系管理员！</font></p>
	            </div>
	            <div class="modal-body">
	            
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong><font color="red">*</font>发票抬头:</strong><input type="text" name="title" id="title" style="margin-top:5px;height:30px;"/>
					</p>
					<p>
						<strong><font color="red">*</font>企业纳税人识别号:</strong><input type="text" name="tax" id="tax" style="margin-top:5px;height:30px;"/>
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
<!-- 增加增值专用发票模态框 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        	<form name="myform" action="receiptInfoServlet?action=addplus" method="post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title">增加增值专用发票信息</h4>
	                <p><font size="2" color="red">该功能目前只支持增加功能，暂时不支持客户自己修改，如要修改，请联系管理员！</font></p>
	            </div>
	            <div class="modal-body">
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong><font color="red">*</font>发票抬头:</strong><input type="text" name="title" id="title" style="margin-top:5px;height:30px;"/>
					</p>
					<p>
						<strong><font color="red">*</font>企业纳税人识别号:</strong><input type="text" name="tax" id="tax" style="margin-top:5px;height:30px;"/>
					</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong><font color="red">*</font>开户银行:</strong><input type="text" name="bank" id="bank" style="margin-top:5px;height:30px;"/>
					</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong><font color="red">*</font>银行账号:</strong><input type="text" name="banknumber" id="banknumber" style="margin-top:5px;height:30px;"/>
					</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>注册地址:</strong><input type="text" name="registeadd" id="registeadd" style="margin-top:5px;height:30px;"/>
					</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>注册电话:</strong><input type="text" name="registecall" id="registecall" style="margin-top:5px;height:30px;"/>
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