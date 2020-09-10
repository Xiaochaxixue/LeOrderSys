<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	/* toastr.options = {"closeButton": true,
		positionClass:"toast-center-center"}; */
	
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
	
	/* $(document).ready(function(){
		if($("#error").text()!=null||$("#error").text()!=""||$("#error").text()!="undefined"){
			toastr.error($("#error").text()); 
		}
	});
	 */
	$(document).ready(function(){
		$("#dingdanManage").addClass("active");
	});
	
</script>
<div class="data_list">
		<div class="data_list_title">
			订单管理
		</div>
			
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
							<td><a onclick="this.href='DingDanManageServlet?action=dingdandetail&id=${dingDan.ddanNum}'">${dingDan.ddanNum}</a>
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
								<c:if test="${dingDan.state==0}"><font color="red">订单待处理</font></c:if>
								<c:if test="${dingDan.state==1}"><font color="green">已审核</font></c:if>
								<c:if test="${dingDan.state==2}"><font color="green">用户已确认</font></c:if>
								<c:if test="${dingDan.state==3}"><font color="green">用户已付款</font></c:if>
								<c:if test="${dingDan.state==4}"><font color="green">订单已完成</font></c:if>
								<c:if test="${dingDan.state==99}"><font color="red">订单被驳回</font></c:if>
								<input type="hidden" id="state" name="state" value="${dingDan.state}"/>
							</td>
							<td>${dingDan.makedealDate}
								<c:if test="${dingDan.makedealDate==null||empty dingDan.makedealDate}">
									<input id="makedealDate" name="makedealDate" value="<fmt:formatDate value="${dingDan.makedealDate}"  pattern="yyyy-MM-dd"/>" style="margin-top:5px;height:30px;width:120px;" placeholder="请输入交货日期" type="text" class="controls input-append date form_date" readonly="true"/>
								</c:if>
								<input type="hidden" id="makedealDate" name="makedealDate" value="${dingDan.makedealDate}"/>
							</td><!-- 需要手工输入交货日期 -->
							<td>
								<c:if test="${dingDan.state==0}"><font color="red">待处理</font></c:if>
								<c:if test="${dingDan.state!=0}">
									${User.uid}
								</c:if>
								<input type="hidden" id="opa" name="opa" value="${User.uid}"/>
							</td>
							
							<c:if test="${dingDan.state<4}">
								
								<td>
									<c:if test="${dingDan.state==0}"><button class="btn btn-link" type="submit"><font color="red">请核对信息，设置交期等</font></button>&nbsp;</c:if>
									<c:if test="${dingDan.state==1}"><button class="btn btn-link" type="button"><font color="green">请等候用户确认订单</font></button>&nbsp;</c:if>
									<c:if test="${dingDan.state==2}"><button class="btn btn-link" type="submit"><font color="red">标记用户已付款</font></button>&nbsp;</c:if>
									<c:if test="${dingDan.state==3}"><button class="btn btn-link" type="submit"><font color="red">标记订单已完成</font></button>&nbsp;</c:if>
								</td>
								<td>
									<button class="btn btn-mini btn-danger" type="button" onclick="javascript:window.location='DingDanManageServlet?action=withdraw&id=${dingDan.ddanNum}'">驳回订单</button>&nbsp;
								</td>
							</c:if>
							<c:if test="${dingDan.state>=4}">
								<td>
									订单已经完成&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</c:if>
						</tr>
						</form>
					</c:forEach>
					<div align="center">
						<font id="error" color="red">${error}</font>
					</div>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>