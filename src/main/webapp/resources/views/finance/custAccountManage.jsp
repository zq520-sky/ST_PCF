<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/finance/custAccountManage.js?v=1.3"></script>
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
			<form class="tableform" action="${ctx.path}/manage/finance/queryCustAccountList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_MANAGE_FINANCE_CUST_ACCOUNT_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/finance/exportCustAccountList${ctx.bizSuffix}')">
									<i class="minicon exportoperation_icon"></i><span>导出</span>
								</button>
							</p:auth>
						</div>
					</div>
				</div>
					<div class="search_box" id="search_box">
						<div class="search_nav">
							<ul>
								<li>
									<input type="text" id="custName" name="custName" class="inputtext" placeholder="客户名称" value="${custAccountVo.custName}"/>
								</li>
								<li>
									<input type="text" id="companyName" name="companyName" class="inputtext" placeholder="所属公司" value="${custAccountVo.companyName}" />
								</li>
							</ul>
						</div>
						<div class="search_btncon">
							<button type="button" class="btn add_btn" onClick="doSearch()">
								<i class="minicon search_icon"></i>
								<span>查询</span>
							</button>
							<button type="button" class="btn export_btn clearToolBtn">
								<i class="minicon reload_icon"></i>
								<span>重置</span>
							</button>
						</div>
					</div>

					<div class="tablelist_box tablelistboxH">
						<div class="tablelist_con">
							<div class="tablelist_theadbox">
								<div class="tablelist_thead">
									<table>
										<tr>
											<th width="50">序号</th>
											<th width="100">客户编号</th>
											<th width="120">客户名称</th>
											<th width="180">所属公司</th>
											<th width="100">美元汇率</th>
											<th width="120">账户余额（元）</th>
											<th width="120">授信余额（元）</th>
											<th width="180" class="operation_th">操作</th>
										</tr>
									</table>
								</div>
							</div>
							<div class="tablelist_tbody">
								<table>
									<c:if test="${empty pageData.data}">
										<tr>
											<td align="center" colspan="10">对不起，没有找到相关数据！</td>
										</tr>
									</c:if>
									<c:forEach items="${pageData.data }" var="data" varStatus="staus">
										<tr>
											<td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
												${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
											</td>
											<td title="${data.custCode }">${data.custCode }</td>
											<td title="${data.custName }">${data.custName }</td>
											<td title="${data.companyName }">${data.companyName }</td>
											<td title="${data.dollarRate }">${data.dollarRate }</td>
											<td title="${data.accountMoney }">${data.accountMoney }</td>
											<td title="${data.creditMoney }">${data.creditMoney }</td>

											<td class="operation_td">
												<p:auth mcode="MENU_MANAGE_FINANCE_CUST_ACCOUNT_VIEW">
												<button class="operationbtn" type="button" onclick="viewPage(${data.accountId});">
													<span>查看</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_FINANCE_CUST_ACCOUNT_UPDATE_CZ">
												<button class="operationbtn" type="button" onclick="editPage(${data.accountId}, 1, '充值');">
													<span>充值</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_FINANCE_CUST_ACCOUNT_UPDATE_SX">
												<button class="operationbtn" type="button" onclick="editPage(${data.accountId}, 2, '授信');">
													<span>授信</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_FINANCE_CUST_ACCOUNT_UPDATE_HL">
												<button class="operationbtn" type="button" onclick="editPage(${data.accountId}, 3, '设置汇率');">
													<span>设置汇率</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_FINANCE_CUST_ACCOUNT_UPDATE_HK">
												<button class="operationbtn" type="button" onclick="editPage(${data.accountId}, 4, '客户还款');">
													<span>客户还款</span>
												</button>
												</p:auth>

												<p:auth mcode="MENU_MANAGE_FINANCE_CUST_ACCOUNT_UPDATE_FM">
													<button class="operationbtn" type="button" onclick="editPage(${data.accountId}, 5, '设置运单费');">
														<span>设置运单费</span>
													</button>
												</p:auth>

											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				<jsp:include page="/resources/common/page.jsp"></jsp:include>
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

				<div class="add_list">
					<h5>运单费用（元）：</h5>
					<div class="add_value">
						<span id="freightMoneyView"></span>
					</div>
				</div>
			</form>
		</div>

		<!-- 编辑 -->
		<div id="editId" name="editName" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="editForm" action="${ctx.path}/manage/finance/editCustAccount${ctx.bizSuffix}">
				<input type="hidden" id="accountId" name="accountId" />
				<input type="hidden" id="oprType" name="oprType" />
				<input type="hidden" id="oprMoneyCn" />

				<div class="add_list">
					<h5>客户编号：</h5>
					<div class="add_value">
						<span id="custCodeEdit"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>客户名称：</h5>
					<div class="add_value">
						<span id="custNameEdit"></span>
						<input type="hidden" name="custName" id="custNameEditHid">
					</div>
				</div>
				<div class="add_list">
					<h5>所属公司：</h5>
					<div class="add_value">
						<span id="companyNameEdit"></span>
					</div>
				</div>
				<div class="add_list" id="dollarRateEditDivId" style="display:none;">
					<h5><em style="color: red;">*</em>美元汇率：</h5>
					<div class="add_value">
                        <input type="text" id="dollarRateEdit" name="dollarRate" maxlength="8" class="inputtext" />
					</div>
				</div>
				<div class="add_list" id="accountMoneyEditId" style="display:none;">
					<h5>账户余额（元）：</h5>
					<div class="add_value">
						<span id="accountMoneyEdit"></span>
					</div>
				</div>
				<div class="add_list" id="creditMoneyEditDivId" style="display:none;">
					<h5>授信余额（元）：</h5>
					<div class="add_value">
						<span id="creditMoneyEdit"></span>
					</div>
				</div>
				<div class="add_list" id="oprMoneyDivId" style="display:none;">
					<h5><em style="color: red;">*</em>充值金额（元）：</h5>
					<div class="add_value">
						<input type="text" id="oprMoney" name="oprMoney" maxlength="8" class="inputtext" />
					</div>
				</div>
				<div class="add_list" id="oprFreignMoneyDivId" style="display:none;">
					<h5><em style="color: red;">*</em>运单费用（元）：</h5>
					<div class="add_value">
						<input type="text" id="oprFreightMoney" name="freightMoney" maxlength="8" class="inputtext" />
					</div>
				</div>
				<div class="add_list" id="remarkEditDivId" style="display:none;">
					<h5>备注：</h5>
					<div class="add_value">
						<textarea class="textarea" id="remarkEdit" name="remark" maxlength="100"></textarea>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

