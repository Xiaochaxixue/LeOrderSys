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
		$("#addressInfo").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			地址信息管理
		</div>
				<button class="btn btn-success" type="button" style="margin-right: 50px;" data-toggle="modal" data-target="#myModal">新增地址</button><!-- onclick="javascript:window.location='receiptInfoServlet?action=add'" -->
		<div>
			<div class="data_form" >
			<c:forEach items="${tb_addresss}"  var="tb_address" varStatus="stat">
				<table class="table  table-bordered table-hover datatable"  style="width:1000px" align="center">
				<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
						<tr>
							<td width="50%" style="text-align:center"><strong>收货人:</strong></td>
							<td>${tb_address.acceptor}</td>
						</tr>	
						<tr>	
							<td width="50%" style="text-align:center"><strong>联系电话:</strong></td>
							<td>${tb_address.tel}</td>
						</tr>
						<tr>
							<td width="50%" style="text-align:center"><strong>收货地址:</strong></td>
							<td>${tb_address.adress}</td>
						</tr>	
				</table>
			</c:forEach>
				<div align="center">
					<font id="error" color="red">${error}</font>
				</div>
			</div>
	</div>
</div>
<!-- 增加发票模态框 -->
<!-- <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">开始演示模态框</button> -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        	<form name="myform" action="Tb_addressServlet?action=add" method="post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title">增加地址信息</h4>
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