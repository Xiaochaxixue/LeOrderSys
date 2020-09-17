<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#receiptInfo").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			发票管理
		</div>
				<button class="btn btn-success" type="button" style="margin-right: 50px;" data-toggle="modal" data-target="#myModal">新增发票</button><!-- onclick="javascript:window.location='receiptInfoServlet?action=add'" -->
		<div>
			<div class="data_form" >
				<table class="table  table-bordered table-hover datatable"  style="width:500px" align="center">
				<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
					<c:forEach items="${receipts}"  var="receipt" varStatus="stat">
						<tr>
							<td width="50%" style="text-align:center"><strong>发票抬头:</strong></td>
							<td>${receipt.title}</td>
						</tr>	
						<tr>	
							<td width="50%" style="text-align:center"><strong>企业纳税人识别号:</strong></td>
							<td>${receipt.tax}</td>
						</tr>
					</c:forEach>
			</table>
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
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">增加发票信息</h4>
            </div>
            <div class="modal-body">
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<strong>发票抬头:</strong><input type="text" name="title" id="title" style="margin-top:5px;height:30px;"/>
				</p>
				<p>
					<strong>企业纳税人识别号:</strong><input type="text" name="tax" id="tax" style="margin-top:5px;height:30px;"/>
				</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>