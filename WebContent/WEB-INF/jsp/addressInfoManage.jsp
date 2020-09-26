<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrapValidator.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		var tip="${sessionScope.tip}";
		toastr.options = {"closeButton": true,
				positionClass:"toast-center-center"};
		if((tip!=null)&&(tip!="")){
			toastr.info(tip);
			<%
			request.getSession().removeAttribute("tip");
			%>
		}
	});
	function getTip(){
		toastr.options = {"closeButton": true,
				positionClass:"toast-center-center"};
		toastr.info("默认地址只支持修改不支持删除哦！");
	}
	
	$(document).ready(function(){
		$("#addressInfo").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			地址信息管理
		</div>
				<button class="btn btn-success" type="button" style="margin-right: 50px;" data-toggle="modal" data-target="#myModal">新增地址</button>
		<div>
			<div class="data_form" >
			
				<table class="table  table-bordered table-hover datatable"  style="width:1500px" align="center">
				<thead>
					<tr>
						<th>收货人</th>
						<th>联系电话</th>
						<th>收货地址</th>
						<td>操作</td>
					</tr>
				</thead>
					<tr>
						<td>${Client.contact}</td>
						<td>${Client.phone}</td>
						<td>${Client.address0}</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='Tb_addressServlet?action=proupdate&id=default'">修改</button>&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="getTip()">删除</button>
							<button class="btn btn-mini btn-default btn-link" type="button">默认地址</button>
						</td>
					</tr>
					<c:forEach items="${tb_addresss}"  var="tb_address" varStatus="stat">
						<tr>
							<td>${tb_address.acceptor}</td>
							<td>${tb_address.tel}</td>
							<td>${tb_address.address}</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='Tb_addressServlet?action=proupdate&id=${session_user.uid}&id2=${tb_address.acceptor}&id3=${tb_address.tel}&id4=${tb_address.address}'">修改</button>&nbsp;
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='Tb_addressServlet?action=delete&id=${session_user.uid}&id2=${tb_address.acceptor}&id3=${tb_address.tel}&id4=${tb_address.address}'">删除</button>
								<button class="btn btn-mini btn-default" type="button" onclick="javascript:window.location='Tb_addressServlet?action=reset&id=${session_user.uid}&id2=${tb_address.acceptor}&id3=${tb_address.tel}&id4=${tb_address.address}'">设为默认地址</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
	</div>
</div>
<!-- 增加地址模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        	<form name="myform" action="Tb_addressServlet?action=add" method="post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title">增加地址信息</h4><font id="error" color="red">${error}</font>
	            </div>
	            <div class="modal-body">
	            
					<p>&nbsp;&nbsp;
						<strong>收货人:</strong><input type="text" name="acceptor" id="acceptor" style="margin-top:5px;height:30px;"/>
					</p>
					<p>
						<strong>联系电话:</strong><input type="text" name="tel" id="tel" style="margin-top:5px;height:30px;"/>
					</p>
					<p>
						<strong>收货地址:</strong><input type="text" name="address" id="address" style="margin-top:5px;height:30px;"/>
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
