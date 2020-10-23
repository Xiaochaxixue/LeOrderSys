<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
	$(document).ready(function(){
		var tip="${sessionScope.error}";
		toastr.options = {"closeButton": true,
				positionClass:"toast-center-center"};
		if((tip!=null)&&(tip!="")){
			toastr.warning(tip);
			<%
			request.getSession().removeAttribute("error");
			%>
		}
	});
	$(document).ready(function(){
		$("#shoppingCart").addClass("active");
	});
	function shows(a) {
        $('.buttonText').text(a)
    }
</script>
<div class="data_list">
		<div class="data_list_title">
			采购清单
		</div>
		<div><!-- 购买意向清单主体div -->
			<table class="table table-striped table-bordered table-hover datatable" style="width:1300px" align="center">
				<thead>
					<tr>
						<th>购买产品信息</th>
						<th>商品单价</th>
						<th>购买数量</th>
						<th>金额总计</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<img class="img-rounded" src="${pageContext.request.contextPath}/upload/${dingShopping.picture}" alt="产品编号为：${dingShopping.cnum}" onerror="this.src='${pageContext.request.contextPath}/images/bg.jpg;this.onerror=null'" width="80px" height="80px"/>
						</td><!-- 产品图片 -->
						<td>${dingShopping.price}</td><!-- 商品价格 -->
						<td>${dingShopping.number}</td><!-- 购买数量 number-->
						<td>${dingShopping.total}</td><!-- 金额总计total -->
					</tr>
					<tr>
						<td colspan="4">
						<div>
						<p>产品信息:</p>
							<p></p><!-- 产品名称 -->
							<p></p><!-- 固件信息 -->
						</div>
						</td>
					</tr><!-- 产品信息 -->
					<tr>
						<td colspan="4">
							<div>
								<p>
									<font size="1" color="grey">
										排针数量：${dingShopping.pinNum}&nbsp;&nbsp;&nbsp;&nbsp;排针大小：${dingShopping.pinSize}
									</font>
								</p>
								<p>
									<font size="1" color="grey">
										排针形状：${dingShopping.pinShape}&nbsp;&nbsp;&nbsp;&nbsp;焊接方式：${dingShopping.pinWeld}
									</font>
								</p>
								<p>
									<font size="1" color="grey">
										天线类型：${dingShopping.antennaType}&nbsp;&nbsp;&nbsp;&nbsp;天线长度：${dingShopping.antennaLength}
									</font>
								</p>
							</div>
						</td>
					</tr><!-- 产品的工艺信息 -->
					<tr>
						<td colspan="4"><!-- 展示收货地址信息 -->
						<div>
							<div style="float:left;">
									<div style="float:left;">收货信息：</div>
									<div class="btn-group" style="float:left;">
									    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									        <span class="buttonText">收货人：${Client.contact}&nbsp;&nbsp;&nbsp;&nbsp;联系方式：${Client.phone}
									        		&nbsp;&nbsp;&nbsp;&nbsp;收货地址：${Client.address0}<font size="2" color="grey">(默认地址)</font></span>
									        <span class="caret"><p><font size="1" color="grey">修改</font></p></span><!--  -->
									    </button>
									    <ul class="dropdown-menu" role="menu"><!-- 根据servlet传过来的list数据表，循环将数据展示在前端 -->
									    	<li><!-- 默认的收货信息 -->
									        	<a href="#" onclick="shows($(this).text())">
									        		<p>收货人：${Client.contact}&nbsp;&nbsp;&nbsp;&nbsp;联系方式：${Client.phone}</p>
									        		<p>收货地址：${Client.address0}<font size="2" color="grey">(默认地址)</font></p>
									        	</a>
									        </li>
									        <c:forEach items="${Tb_addresses}"  var="Tb_address" varStatus="stat">
										        <li>
										        	<a href="#" onclick="shows($(this).text())">
										        		<p>收货人：${Tb_address.acceptor}&nbsp;&nbsp;&nbsp;&nbsp;联系方式：${Tb_address.tel}</p>
										        		<p>收货地址：${Tb_address.address}</p>
										        	</a>
										        </li>
									        </c:forEach>
									    </ul>
									</div>
								</div>
								<div style="float:left;margin-left:100px;">
									<div>
										发票信息：
									</div><!-- 展示发票信息 -->
									<div><!-- 选择是否需要发票信息，要的话要专用还是普通发票 -->
										<label class="radio-inline" style="float:left;">
											<input type="radio" name="optionsRadiosinline" id="optionsRadios0" value="option1" checked> &nbsp;&nbsp;不需要
										</label>
										<label class="radio-inline" style="float:left;">
											<input type="radio" name="optionsRadiosinline" id="optionsRadios1"  value="option2"> &nbsp;&nbsp;普通发票
										</label>
										<label class="radio-inline" style="float:left;">
											<input type="radio" name="optionsRadiosinline" id="optionsRadios2"  value="option2">&nbsp;&nbsp;专用发票
										</label>
									</div>	
									<div><!-- 当点击普通发票或者专用发票单选框时展示出发票详情div -->
										<!-- 发票详情： -->
									</div>
								</div>
							</div>
						</td>
					</tr><!-- 收货信息和发票信息 -->
					<tr>
						<td colspan="4">
							<div align="right">
								<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>&nbsp;
								<button class="btn btn-success" type="submit" style="margin-right: 50px;">购买</button>
							</div>
						</td>
					</tr><!-- 提交订单等操作 -->
				</tbody>
			</table>
		</div>
		<div align="center">
			<font id="error" color="red">${error}</font>
		</div>
		<div align="center"><font color="red"></font></div>
</div>