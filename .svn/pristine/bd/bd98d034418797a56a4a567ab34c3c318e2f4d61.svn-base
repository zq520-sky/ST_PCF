<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="${ctx.path}/resources/components/bootstrap/bootstrap-theme.min.css?v=2.2" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx.path}/resources/css/task/taskSetManage.css?v=1.2" rel="stylesheet" />
		<script src="${ctx.path}/resources/script/task/taskSetManage.js?v=1.1"></script>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script type="text/javascript">
			function checkSubmit() {
				$("#editForm").submit();
			}
		</script>
	</head>
	<body>

		<!-- 编辑 -->
		<div class="taskset">
		<div id="editId" name="editName" class="add_box mCustomScrollbar_y" data-mcs-theme="dark">
			<form method="post" id="editForm" action="${ctx.path}/manage/task/editCustTaskSet${ctx.bizSuffix}">
				<input type="hidden" id="taskSetId" name="taskSetId" value="${custTaskSet.taskSetId}" />
				<div class="add_list">
					<h5 style="width: 140px;line-height: 30px;">工作日分配比例设定：</h5>
					<div class="add_value_task">
						<input type="hidden" name="workdayUsers" id="workdayUsers" value="${custTaskSet.workdayUsers}">
						<input type="hidden" name="workdayUsersName" id="workdayUsersName" value="${custTaskSet.workdayUsersName}">
						<input type="hidden" name="workdayUsersRate" id="workdayUsersRate" value="${custTaskSet.workdayUsersRate}">
						<div onclick="taskRateSet()">
							<button type="button" class="btn btn-default"  >
								<span >设定</span>
							</button>
							<%--<span id="workdayUserNamesSpan" class="add_taskset">${custTaskSet.workdayUsersName}</span>--%>
						</div>
					</div>
				</div>
				<div class="add_list">
					<h5 style="width: 140px;line-height: 30px;">周末负责人：</h5>
					<div class="add_value_task">
						<input type="hidden" name="weekendUsers" id="weekendUsers" value="${custTaskSet.weekendUsers}">
						<input type="hidden" name="weekendUsersName" id="weekendUsersName" value="${custTaskSet.weekendUsersName}">
						<button type="button" class="btn btn-default"  onclick="taskWeekendSet()">
							<span>设定</span>
						</button>
						<%--<span id="weekendUserNamesSpan" class="add_taskset">${custTaskSet.weekendUsersName}</span>--%>
					</div>
				</div>
				<div class="add_list">
					<h5 style="width: 140px;line-height: 30px;">买家账号使用频率：</h5>
					<div class="add_value_task">
						<div class="add_tasksetmanage">
						<select class="select" name="buyerFrequency" id="buyerFrequency" type="select">
							<option value="">请选择</option>
							<option value="5" <c:if test="${custTaskSet.buyerFrequency == 5}">selected</c:if> >5天内未使用</option>
							<option value="4" <c:if test="${custTaskSet.buyerFrequency == 4}">selected</c:if> >4天内未使用</option>
							<option value="3" <c:if test="${custTaskSet.buyerFrequency == 3}">selected</c:if> >3天内未使用</option>
							<option value="2" <c:if test="${custTaskSet.buyerFrequency == 2}">selected</c:if> >2天内未使用</option>
							<option value="1" <c:if test="${custTaskSet.buyerFrequency == 1}">selected</c:if> >1天内未使用</option>
						</select>
						</div>
					</div>
				</div>
				<div class="add_list">
					<h5 style="width: 140px;line-height: 30px;">买家账号操作次数：</h5>
					<div class="add_value_task">
						<div class="add_tasksetmanage">
							<input class="checkbox" style="margin-top:10px;" type="checkbox" id="buyerOprNum" name="buyerOprNum" value="${custTaskSet.buyerOprNum}" <c:if test="${custTaskSet.buyerOprNum == 1}">checked</c:if>>
							<label class="taskem" for="buyerOprNum">是否优先使用操作次数少的</label>
					</div>
					</div>
				</div>

				<div class="search_btncon">
					<button type="button" class="btn add_btn" onClick="checkSubmit()">
<%--						<i class="minicon search_icon"></i>--%>
						<span>确定</span>
					</button>
				</div>


			</form>
		</div>
		</div>
	</body>
</html>

