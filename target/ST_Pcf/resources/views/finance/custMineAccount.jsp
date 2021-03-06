<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/finance/custMineAccount.js?v=1.2"></script>
		<script src="${ctx.path}/resources/script/upload/cupload.js"></script>
		<script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.0"></script>
		<script type="text/javascript">
			$(function() {
				loadCriteria();
			});
			//加载查询条件
			function loadCriteria() {
			}
		</script>
	</head>
	<body>
		<div class="main_con">
			<form class="tableform" action="${ctx.path}/manage/finance/queryAccountByCustId${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
						</div>
					</div>
				</div>
				<div class="tablelist_box tablelistboxH">
						<div class="tablelist_con">
							<div class="tablelist_theadbox">
								<div class="tablelist_thead">
									<table>
										<tr>
											<th width="100">客户编号</th>
											<th width="120">客户名称</th>
											<th width="180">所属公司</th>
											<th width="80">美元汇率</th>
											<th width="110">账户余额（元）</th>
											<th width="110">授信余额（元）</th>
											<th width="180" class="operation_th">操作</th>
										</tr>
									</table>
								</div>
							</div>
							<div class="tablelist_tbody">
								<table>
									<tr>
										<td title="${data.custCode }">${data.custCode }</td>
										<td title="${data.custName }">${data.custName }</td>
										<td title="${data.companyName }">${data.companyName }</td>
										<td title="${data.dollarRate }">${data.dollarRate }</td>
										<td title="${data.accountMoney }">${data.accountMoney }</td>
										<td title="${data.creditMoney }">${data.creditMoney }</td>
										<td class="operation_td">
											<p:auth mcode="MENU_MANAGE_CUST_FINANCE_ACCOUNT_VIEW">
											<button class="operationbtn" type="button" onclick="viewPage(${data.accountId});">
												<span>查看</span>
											</button>
											</p:auth>
											<p:auth mcode="MENU_MANAGE_CUST_FINANCE_ACCOUNT_APPLY">
											<button class="operationbtn" type="button" onclick="openAddPage();">
												<span>充值申请</span>
											</button>
											</p:auth>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
			</form>
		</div>

		<!-- 查看 -->
		<div id="viewId" class="add_box mCustomScrollbar_y" >
			<form method="post" id="viewForm">
				<div class="add_list">
					<h5>客户编号：</h5>
					<div class="add_value">
						<span id="custCodeView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>客户名称：</h5>
					<div class="add_value">
						<span id="custNameView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>所属公司：</h5>
					<div class="add_value">
						<span id="companyNameView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>美元汇率：</h5>
					<div class="add_value">
						<span id="dollarRateView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>账户余额（元）：</h5>
					<div class="add_value">
						<span id="accountMoneyView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>授信余额（元）：</h5>
					<div class="add_value">
						<span id="creditMoneyView"></span>
					</div>
				</div>
			</form>
		</div>

		<!--充值申请-->
		<div id="addCustRecharge" class="add_box mCustomScrollbar_y">
			<form method="post" id="addCustRechargeForm" action="${ctx.path}/manage/finance/addCustRegisterOrder${ctx.bizSuffix}">
				<div class="add_list">
					<h5><em style="color: red;">*</em>充值金额（元）：</h5>
					<div class="add_value">
						<input type="text" id="rechargeMoneyAdd" name="rechargeMoney" placeholder="请输入充值金额" class="inputtext"/>
					</div>
				</div>
				<div class="add_list">
					<h5><em style="color: red;">*</em>充值方式：</h5>
					<div class="add_value">
						<select class="select" name="payType">
							<option value="">请选择</option>
							<option value="1">支付宝</option>
							<option value="2">微信</option>
							<option value="5">其他</option>
						</select>
					</div>
				</div>
				<div class="add_list">
					<h5><em style="color: red;">*</em>付款凭证：</h5>
					<div class="add_value">
						<div id="payImageAdd"></div>
					</div>
				</div>
				<div class="add_list">
					<h5><em style="color: red;">*</em>充值日期：</h5>
					<div class="add_value">
						<div id="payTimeAdd">
							<input type="text" id="payTimeAdds" name="payTime" class="inputtext" onclick="WdatePicker({payTime:'%y-%M-%d',dateFmt:'yyyy-MM-dd', maxDate:'%y-%M-%d'})" style="min-width: 120px;" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

