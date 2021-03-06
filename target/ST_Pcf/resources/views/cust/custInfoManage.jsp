<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/cust/custInfoManage.js?v=1.3"></script>
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
			<form class="tableform" action="${ctx.path}/manage/customer/queryCustomerInfoList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_ADD">
								<button type="button" class="btn add_btn" onclick="openAddPage();">
									<i class="minicon addoperation_icon"></i>
									<span>新增</span>
								</button>
							</p:auth>
							<span class="line">|</span>
							<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/customer/exportCustomerInfoList${ctx.bizSuffix}')">
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
									<input type="text" id="custName" name="custName" class="inputtext" placeholder="客户名称" value="${customerVo.custName}"/>
								</li>
								<li>
									<input type="text" id="companyName" name="companyName" class="inputtext" placeholder="所属公司" value="${customerVo.companyName}" />
								</li>
								<li>
									<input type="text" id="contactTel" name="contactTel" class="inputtext" placeholder="联系电话" value="${customerVo.contactTel}" />
								</li>
								<li>
									<label class="inputlabel">禁用状态：</label>
									<select class="select" name="isForbid" id="isForbid" type="select">
										<option value="">全部</option>
										<option value="0" <c:if test="${customerVo.isForbid == 0}">selected</c:if> >正常</option>
										<option value="1"<c:if test="${customerVo.isForbid == 1}">selected</c:if> >已禁用</option>
									</select>
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
											<th width="100">客户名称</th>
											<th width="100">登录账号</th>
											<th width="160">所属公司</th>
											<th width="120">联系电话</th>
											<th width="120">邮箱</th>
											<th width="80">美元汇率</th>
											<th width="80">禁用状态</th>
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
											<td title="${data.loginName }">${data.loginName }</td>
											<td title="${data.companyName }">${data.companyName }</td>
											<td title="${data.contactTel }">${data.contactTel }</td>
											<td title="${data.email }">${data.email }</td>
											<td title="${data.dollarRate }">${data.dollarRate }</td>
											<td title="${data.isForbidCn }">
												<c:if test="${data.isForbid == 0 }">
													<span class="recharge_statue recharge_success"><em>${data.isForbidCn }</em></span>
												</c:if>
												<c:if test="${data.isForbid == 1 }">
													<span class="recharge_statue recharge_failed"><em>${data.isForbidCn }</em></span>
												</c:if>
											</td>
											<td class="operation_td">
												<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_VIEW">
												<button class="operationbtn" type="button" onclick="viewPage(${data.custId});">
													<span>查看</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_UPDATE">
												<button class="operationbtn" type="button" onclick="editPage(${data.custId});">
													<span>编辑</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_SET">
												<button class="operationbtn" type="button" onclick="setPage(${data.custId}, ${data.isForbid}, '${data.custName}');">
													<span>
														<c:if test="${data.isForbid == 0}">禁用</c:if>
														<c:if test="${data.isForbid == 1}">启用</c:if>
													</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_RESETPWD">
												<button class="operationbtn" type="button" onclick="resetPwdPage(${data.userId}, '${data.loginName}');">
													<span>重置密码</span>
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

		<!-- 新增 -->
		<div id="addId" name="addName" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="addForm" action="${ctx.path}/manage/customer/addCustomer${ctx.bizSuffix}">
				<div class="add_list">
					<h5><em style="color: red;">*</em>客户名称：</h5>
					<div class="add_value">
						<input type="text" id="custNameAdd" name="custName" maxlength="32" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>公司名称：</h5>
					<div class="add_value">
						<input type="text" id="companyNameAdd" name="companyName" maxlength="64" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>联系电话：</h5>
					<div class="add_value">
						<input type="text" id="contactTelAdd" name="contactTel" maxlength="30" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>邮箱：</h5>
					<div class="add_value">
						<input type="text" id="emailAdd" name="email" maxlength="32" class="inputtext" />
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
					<h5>登录账号：</h5>
					<div class="add_value">
						<span id="loginNameView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>公司名称：</h5>
					<div class="add_value">
						<span id="companyNameView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>联系电话：</h5>
					<div class="add_value">
						<span id="contactTelView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>邮箱：</h5>
					<div class="add_value">
						<span id="emailView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>美元汇率：</h5>
					<div class="add_value">
						<span id="dollarRateView"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>禁用状态：</h5>
					<div class="add_value">
						<span id="isForbidCnView"></span>
					</div>
				</div>
			</form>
		</div>

		<!-- 编辑 -->
		<div id="editId" name="editName" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="editForm" action="${ctx.path}/manage/customer/editCustomer${ctx.bizSuffix}">
				<input type="hidden" id="custIdEdit" name="custId" />
				<input type="hidden" id="custCodeHid" name="custCode" />
				<div class="add_list">
					<h5>客户编号：</h5>
					<div class="add_value">
						<span id="custCodeEdit"></span>
					</div>
				</div>
				<div class="add_list">
					<h5><em style="color: red;">*</em>客户名称：</h5>
					<div class="add_value">
						<input type="text" id="custNameEdit" name="custName" maxlength="32" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>登录账号：</h5>
					<div class="add_value">
						<span id="loginNameEdit"></span>
					</div>
				</div>
				<div class="add_list">
					<h5><em style="color: red;">*</em>公司名称：</h5>
					<div class="add_value">
						<input type="text" id="companyNameEdit" name="companyName" maxlength="64" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>联系电话：</h5>
					<div class="add_value">
						<input type="text" id="contactTelEdit" name="contactTel" maxlength="30" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>邮箱：</h5>
					<div class="add_value">
						<input type="text" id="emailEdit" name="email" maxlength="32" class="inputtext" />
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

