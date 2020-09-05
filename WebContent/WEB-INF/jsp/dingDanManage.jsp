<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	
	$(document).ready(function(){
		$('.form_date').datetimepicker({
			language:'zh-CN',/*语言  默认值：’en’ */
		    weekStart: 1,/* 一周从哪一天开始。0（星期日）到6（星期六） */
		    todayBtn:  1,/*当天日期将会被选中。  */
			autoclose: 1,//选择后自动关闭当前时间控件
			todayHighlight: 1,/*高亮当天日期。  */
			startView: 2,/* 从月视图开始，选中那一天  3为选月份*/
			minView: 2,/* 从月视图开始，选天   选完天后，不在出现下级时分秒时间选择 */
			forceParse: 0,/*就是你输入的可能不正规，但是它胡强制尽量解析成你规定的格式（format）  */
			format: "yyyy-mm-dd", //时间格式  yyyy-mm-dd hh:ii:ss */
		});
		
	});
	
	
	
	$(document).ready(function(){
		$("#dingdanManage").addClass("active");
	});
	
</script>
<div class="data_list">
		<div class="data_list_title">
			订单管理
		</div>
			<!-- <form name="myForm" class="form-search" method="post" action="gujianInfoManageServlet?action=list&id=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='gujianInfoManageServlet?action=preAdd'">添加</button>
				<span class="data_search">
					<button type="submit" class="btn btn-info">搜索</button>
				</span>
			</form> -->
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>订单编号</th>
						<th>用户名</th>
						<th>下单时间</th>
						<th>总金额</th>
						<th>备注</th>
						<th>订单状态</th>
						<th><font color="red">*</font>交货日期</th>
						<th>操作人</th>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
				
					<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
					<c:forEach items="${dingDans}"  var="dingDan" varStatus="stat">
						<form name="myForm" method="post" action="DingDanManageServlet?action=operation">
						<tr>
							<td>${dingDan.ddanNum}
								<input type="hidden" id="ddanNum" name="ddanNum" value="${dingDan.ddanNum}"/>
							</td>
							
							<td>${dingDan.uid}
								<input type="hidden" id="uid" name="uid" value="${dingDan.uid}"/>
							</td>
								
							<td>${dingDan.dealDate}
								<input type="hidden" id="dealDate" name="dealDate" value="${dingDan.dealDate}"/>
							</td>
							<td>${dingDan.totalprice}
								<input type="hidden" id="totalprice" name="totalprice" value="${dingDan.totalprice}"/>
							</td>
							<td>
								<input type="text" id="ps" name="ps" value="${dingDan.ps}" style="margin-top:5px;height:30px;">
							</td><!-- 添加备注，input -->
							<td>
								<c:if test="${dingDan.state==0}"><font color="red">待处理</font></c:if>
								<c:if test="${dingDan.state==1}"><font color="green">已确认</font></c:if>
								<c:if test="${dingDan.state==2}"><font color="green">正在生产</font></c:if>
								<c:if test="${dingDan.state==3}"><font color="green">step4</font></c:if>
								<c:if test="${dingDan.state==4}"><font color="green">step5</font></c:if>
								<input type="hidden" id="state" name="state" value="${dingDan.state}"/>
							</td>
							<td>${dingDan.makedealDate}
								<c:if test="${dingDan.makedealDate==null||empty dingDan.makedealDate}">
									<input id="makedealDate" name="makedealDate" value="<fmt:formatDate value="${dingDan.makedealDate}"  pattern="yyyy-MM-dd"/>" style="margin-top:5px;height:30px;width:120px;" placeholder="请输入交货日期" type="text" class="controls input-append date form_date" readonly="true"/>
								</c:if>
								<input type="hidden" id="makedealDate" name="makedealDate" value="${dingDan.makedealDate}"/>
							</td><!-- 需要手工输入交货日期 -->
							<td>${User.uid}
								<input type="hidden" id="opa" name="uid" value="${User.uid}"/>
							</td>
							<c:if test="${dingDan.state<4}">
								<td>
									<button class="btn btn-link" type="submit">设置下一步订单状态</button>&nbsp;
								</td>
								<td>
									<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='DingDanManageServlet?action=view'">预览</button>&nbsp;
								</td>
							</c:if>
							<c:if test="${dingDan.state>=4}">
								<td>
									没有更多操作了&nbsp;
								</td>
								<td>
									预览
								</td>
							</c:if>
						</tr>
						</form>
					</c:forEach>
					
				</tbody>
			</table>
			<div align="center">
					<font id="error" color="red">${error}</font>
				</div>
		</div>
		<div align="center"><font color="red"></font></div>
</div>