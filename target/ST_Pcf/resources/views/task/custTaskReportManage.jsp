<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/resources/platform/inc.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.1"></script>
    <script src="${ctx.path}/resources/script/task/custTaskReportManage.js?v=1.2"></script>
    <script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.1"></script>
    <script type="text/javascript">
        $(function () {
            loadCriteria();
        });

        //加载查询条件
        function loadCriteria() {
        }
    </script>
</head>
<body>
<div class="main_con">
    <form class="tableform" action="${ctx.path}/manage/task/queryCustTaskReportList${ctx.pageSuffix}" method="post"
          id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <p:auth mcode="MENU_MANAGE_TASK_REPORT_UPDATEOK_LIST">
                        <button type="button" class="btn add_btn" onclick="openSettingOkPage();">
                            <i class="minicon exportoperation_icon"></i>
                            <span>批量设置完成</span>
                        </button>
                    </p:auth>
                    <span class="line">|</span>
                    <p:auth mcode="MENU_MANAGE_TASK_REPORT_UPDATESN_LIST">
                        <button type="button" class="btn add_btn" onclick="openSettingSnPage();">
                            <i class="minicon exportoperation_icon"></i>
                            <span>批量设置运单号</span>
                        </button>
                    </p:auth>
                    <span class="line">|</span>
                    <p:auth mcode="MENU_MANAGE_TASK_REPORT_EXPORT">
                        <button type="button" class="btn add_btn"
                                onclick="fun_export('${ctx.path}/manage/task/exportCustTaskReportList${ctx.bizSuffix}')">
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
                        <input type="text" id="searchCustName" name="cust.custName" class="inputtext" placeholder="客户名称"
                               value="${custTaskInfoVo.cust.custName}"/>
                    </li>
                    <li>
                        <label class="inputlabel">负责人：</label>
                        <st:commonSelect className="select" id="searchLiableUserId" name="task.liableUserId"
                                         selectValue="${custTaskInfoVo.task.liableUserId}"
                                         sql="select user_id as val, user_name as text from t_sys_pm_user where sys_type = 1 and user_id != 1 order by user_id"/>
                    </li>
                    <li>
                        <input type="text" id="searchItemId" name="product.itemId" class="inputtext" placeholder="产品ID"
                               value="${custTaskInfoVo.product.itemId}"/>
                    </li>
                    <li>
                        <input type="text" id="searchAccountsName" name="product.accountsName" class="inputtext"
                               placeholder="卖家账号（店铺名称）" value="${custTaskInfoVo.product.accountsName}"/>
                    </li>
                    <li>
                        <label class="inputlabel">电商平台：</label>
                        <select class="select" name="product.platformType" id="searchPlatformType" type="select">
                            <option value="">全部</option>
                            <option value="eBay"
                                    <c:if test="${custTaskInfoVo.product.platformType == 'eBay'}">selected</c:if> >eBay
                            </option>
                            <option value="Amazon"
                                    <c:if test="${custTaskInfoVo.product.platformType == 'Amazon'}">selected</c:if>>
                                Amazon
                            </option>
                            <option value="Wish"
                                    <c:if test="${custTaskInfoVo.product.platformType == 'Wish'}">selected</c:if>>Wish
                            </option>
                            <option value="Facebook"
                                    <c:if test="${custTaskInfoVo.product.platformType == 'Facebook'}">selected</c:if> >
                                Facebook
                            </option>
                            <option value="tiktok"
                                    <c:if test="${custTaskInfoVo.product.platformType == 'tiktok'}">selected</c:if>>
                                tiktok
                            </option>
                            <option value="instagram"
                                    <c:if test="${custTaskInfoVo.product.platformType == 'instagram'}">selected</c:if> >
                                instagram
                            </option>
                            <option value="Pinterest"
                                    <c:if test="${custTaskInfoVo.product.platformType == 'Pinterest'}">selected</c:if>>
                                Pinterest
                            </option>
                            <option value="etsy"
                                    <c:if test="${custTaskInfoVo.product.platformType == 'etsy'}">selected</c:if>>etsy
                            </option>
                            <option value="linkedin"
                                    <c:if test="${custTaskInfoVo.product.platformType == 'linkedin'}">selected</c:if> >
                                linkedin
                            </option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">需求类型：</label>
                        <select class="select" name="order.demandType" id="searchDemandType" type="select">
                            <option value="">全部</option>
                            <option value="1"
                                    <c:if test="${custTaskInfoVo.order.demandType == 1}">selected</c:if> >采购
                            </option>
                            <option value="2"
                                    <c:if test="${custTaskInfoVo.order.demandType == 2}">selected</c:if> >刷单
                            </option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">是否退款：</label>
                        <select class="select" name="isRefund" id="isRefundSearch" type="select">
                            <option value="">全部</option>
                            <option value="1"
                                    <c:if test="${custTaskInfoVo.isRefund == 1}">selected</c:if> >是
                            </option>
                            <option value="0"
                                    <c:if test="${custTaskInfoVo.isRefund == 0}">selected</c:if> >否
                            </option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">任务状态：</label>
                        <select class="select" name="task.taskState" id="searchTaskState" type="select">
                            <option value="">全部</option>
                            <option value="0"
                                    <c:if test="${custTaskInfoVo.task.taskState == 0}">selected</c:if> >未完成
                            </option>
                            <option value="1"
                                    <c:if test="${custTaskInfoVo.task.taskState == 1}">selected</c:if> >已完成
                            </option>
                            <option value="2"
                                    <c:if test="${custTaskInfoVo.task.taskState == 2}">selected</c:if> >已失败
                            </option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">完成时间状态：</label>
                        <select class="select" name="task.taskFinishNote" id="searchTaskFinishNote" type="select">
                            <option value="">全部</option>
                            <option value="15点之前"
                                <c:if test="${custTaskInfoVo.task.taskFinishNote == '15点之前'}">selected</c:if> >15点之前
                            </option>
                            <option value="15点之后"
                                    <c:if test="${custTaskInfoVo.task.taskFinishNote == '15点之后'}">selected</c:if> >15点之后
                            </option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">执行日期：</label>
                        <input type="text" id="execDateBegin" name="execDateBegin" class="inputtext"
                               onclick="WdatePicker({payTime:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'execDateEnd\')}'})"
                               style="min-width: 120px;"
                               value="<fmt:formatDate value='${custTaskInfoVo.execDateBegin}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                        <em class="inputto">至</em>
                        <input type="text" id="execDateEnd" name="execDateEnd" class="inputtext"
                               onclick="WdatePicker({payTime:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'execDateBegin\')}'})"
                               style="min-width: 120px;"
                               value="<fmt:formatDate value='${custTaskInfoVo.execDateEnd}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                    </li>
                    <%--<li>
                        <label class="inputlabel">报告时间：</label>
                        <input type="text" id="taskFinishTimeBegin" name="taskFinishTimeBegin" class="inputtext"
                               onclick="WdatePicker({payTime:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'taskFinishTimeEnd\')}'})"
                               style="min-width: 120px;"
                               value="<fmt:formatDate value='${custTaskInfoVo.taskFinishTimeBegin}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                        <em class="inputto">至</em>
                        <input type="text" id="taskFinishTimeEnd" name="taskFinishTimeEnd" class="inputtext"
                               onclick="WdatePicker({payTime:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'taskFinishTimeBegin\')}'})"
                               style="min-width: 120px;"
                               value="<fmt:formatDate value='${custTaskInfoVo.taskFinishTimeEnd}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                    </li>--%>
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
                                <th width="200">任务编号</th>
                                <th width="100">客户名称</th>
                                <th width="100">电商平台</th>
                                <th width="100">需求类型</th>
                                <th width="120">买家账号代码</th>
                                <th width="120">买家账户名称</th>
                                <th width="100">卖家账号</th>
                                <th width="100">产品ID</th>
                                <th width="80">订单数量</th>
                                <th width="100">商品单价($)</th>
                                <th width="80">税收($)</th>
                                <th width="100">订单金额(元)</th>
                                <th width="160">执行日期</th>
                                <th width="80">任务状态</th>
                                <th width="160">报告时间</th>
                                <th width="80">负责人</th>
                                <th width="100">完成时间状态</th>
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
                                <td title="${data.task.taskCode }">${data.task.taskCode }</td>
                                <td title="${data.cust.custName }">${data.cust.custName }</td>
                                <td title="${data.product.platformType }">${data.product.platformType }</td>
                                <td title="${data.demandTypeCn }">${data.demandTypeCn }</td>
                                <td title="${data.buyer.buyerCode }">${data.buyer.buyerCode }</td>
                                <td title="${data.buyer.buyerName }">${data.buyer.buyerName }</td>
                                <td title="${data.product.accountsName }">${data.product.accountsName }</td>
                                <td title="${data.product.itemId }">${data.product.itemId }</td>
                                <td title="${data.task.orderNum }">${data.task.orderNum }</td>
                                <td title="${data.task.unitPrice }">${data.task.unitPrice }</td>
                                <td title="${data.task.tax }">${data.task.tax }</td>
                                <td title="${data.task.orderMoney }">${data.task.orderMoney }</td>
                                <td title="<fmt:formatDate value='${data.task.execDate }' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                    <fmt:formatDate value="${data.task.execDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td title="${data.taskStateCn }">
                                    <c:if test="${data.task.taskState == 0 }">
                                        <span class="recharge_statue recharge_send"><em>${data.taskStateCn }</em></span>
                                    </c:if>
                                    <c:if test="${data.task.taskState == 1 }">
                                        <span class="recharge_statue recharge_success"><em>${data.taskStateCn }</em></span>
                                    </c:if>
                                    <c:if test="${data.task.taskState == 2 }">
                                        <span class="recharge_statue recharge_failed"><em>${data.taskStateCn }</em></span>
                                    </c:if>
                                </td>
                                <td title="<fmt:formatDate value='${data.task.taskFinishTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                    <fmt:formatDate value="${data.task.taskFinishTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td title="${data.liableUserName }">${data.liableUserName }</td>
                                <td title="${data.task.taskFinishNote }">${data.task.taskFinishNote }</td>

                                <td class="operation_td">
                                    <p:auth mcode="MENU_MANAGE_TASK_REPORT_VIEW">
                                        <button class="operationbtn" type="button"
                                                onclick="viewReportPage(${data.task.taskId})">
                                            <span>查看</span>
                                        </button>
                                    </p:auth>
                                    <c:if test="${data.task.taskState == 0 }">
                                    <p:auth mcode="MENU_MANAGE_TASK_REPORT_UPDATEOK">
                                        <button class="operationbtn" type="button"
                                                onclick="editPage(${data.task.taskId});">
                                            <span>设置完成</span>
                                        </button>
                                    </p:auth>
                                    </c:if>
                                    <p:auth mcode="MENU_MANAGE_TASK_REPORT_UPDATESN">
                                        <button class="operationbtn" type="button"
                                                onclick="editPageSN(${data.task.taskId});">
                                            <span>设置运单号</span>
                                        </button>
                                    </p:auth>
                                    <c:if test="${data.task.taskState == 1 }">
                                        <p:auth mcode="MENU_MANAGE_TASK_REPORT_REFUND">
                                            <button class="operationbtn" type="button"
                                                    onclick="refundPage(${data.task.taskId});">
                                                <span>退款</span>
                                            </button>
                                        </p:auth>
                                    </c:if>
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

<!--    查看   -->
<div id="viewId" class="detail_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
    <table width="100%">
        <thead>
        <tr>
            <th colspan="2">订单信息</th>
            <th colspan="2">订单编号：<span id="orderCodeView"></span></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="20%">客户名称：</th>
            <td width="30%" id="custNameView"></td>
            <th width="25%"></th>
            <td width="25%" id="custIdEdit"></td>
        </tr>
        <tr>
            <th>需求类型：</th>
            <td id="demandTypeCnView"></td>
            <th>电商平台：</th>
            <td id="platformTypeView"></td>
        </tr>
        <tr>
            <th>账号(店铺名称)：</th>
            <td id="accountsNameView"></td>
            <th>产品ID：</th>
            <td id="itemIdView"></td>
        </tr>
        <tr>
            <th>标题/关键词：</th>
            <td colspan="3" id="titleKeywordView"></td>
        </tr>
        <tr>
            <th>商品单价（$）：</th>
            <td id="unitPriceView"></td>
            <th>美元汇率：</th>
            <td id="dollarRateView"></td>
        </tr>
        <tr id="demandType_2_ViewId">
            <th>刷单频次：</th>
            <td id="frequencyView"></td>
            <th>刷单周期（天）：</th>
            <td id="cycleView"></td>
        </tr>
        <tr>
            <th>订单总额（元）：</th>
            <td id="orderTotalMoneyView"></td>
            <th>订单状态：</th>
            <td id="orderStateCnView"></td>
        </tr>
        </tbody>
    </table>
    <table width="100%">
        <thead>
        <tr>
            <th colspan="2">任务信息</th>
            <th colspan="2">任务编号：<span id="taskCodeView"></span></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="20%">客户编号：</th>
            <td width="30%" id="custIdView"></td>
            <th width="25%">执行日期：</th>
            <td width="25%" id="execDateView"></td>
        </tr>
        <tr>
            <th>订单数量：</th>
            <td id="orderNumView"></td>
            <th>税收：</th>
            <td id="taxView"></td>
        </tr>
        <tr>
            <th>订单金额：</th>
            <td id="orderMoneyView"></td>
            <th>订单号：</th>
            <td id="orderSnView"></td>
        </tr>
        <tr>
            <th>对应运单号：</th>
            <td id="freightSnView"></td>
            <th>地址信息：</th>
            <td id="addressView"></td>
        </tr>
        <tr>
            <th>退款金额：</th>
            <td id="returnMoneyView"></td>
            <th>当前余额：</th>
            <td id="accountMoneyView"></td>
        </tr>
        <tr>
            <th>卡号：</th>
            <td colspan="3" id="cardsCodeView"></td>
        </tr>
        <tr>
            <th>负责人：</th>
            <td id="liableUserNameView"></td>
            <th>任务状态：</th>
            <td id="taskStateCnView"></td>
        </tr>
        <tr>
            <th>任务状态完成时间状态：</th>
            <td id="taskFinishNoteView"></td>
            <th>应充金额：</th>
            <td id="depositMoneyView"></td>
        </tr>
        <tr>
            <th>备注：</th>
            <td colspan="3" id="remarkView"></td>
        </tr>
        </tbody>
    </table>
</div>

<!--设置完成-->
<div id="editId" class="detail_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
    <form method="post" id="editForm" action="${ctx.path}/manage/task/editCustTaskReport${ctx.bizSuffix}">
        <input type="hidden" id="taskIdOkEdit" name="task.taskId">
        <table width="100%">
            <thead>
            <tr>
                <th colspan="2">订单信息</th>
                <th colspan="2">订单编号：<span id="orderCodeOkEdit"></span></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">客户名称：</th>
                <td width="30%" id="custNameOkEdit"></td>
                <th width="25%">客户编号：</th>
                <td width="25%" id="custCodeOkEdit"></td>
            </tr>
            <tr>
                <th>需求类型：</th>
                <td id="demandTypeCnOkEdit"></td>
                <th>电商平台：</th>
                <td id="platformTypeOkEdit"></td>
            </tr>
            <tr>
                <th>卖家账号(店铺名称)：</th>
                <td id="accountsNameOkEdit"></td>
                <th>产品ID：</th>
                <td id="itemIdOkEdit"></td>
            </tr>
            <tr>
                <th>标题/关键词：</th>
                <td colspan="3" id="titleKeywordOkEdit"></td>
            </tr>
            <tr>
                <th>商品单价（$）：</th>
                <td id="unitPriceOkEdit"></td>
                <th>美元汇率：</th>
                <td id="dollarRateOkEdit"></td>
            </tr>
            <tr id="demandType_2_EditId">
                <th>刷单频次：</th>
                <td id="frequencyOkEdit"></td>
                <th>刷单周期（天）：</th>
                <td id="cycleOkEdit"></td>
            </tr>
            <tr>
                <th>订单总额（元）：</th>
                <td id="orderTotalMoneyOkEdit"></td>
                <th>订单状态：</th>
                <td id="orderStateCnOkEdit"></td>
            </tr>
            <tr>
                <th>任务开始日期：</th>
                <td id="taskStartDateOkEdit"></td>
                <th>发布日期：</th>
                <td id="releaseTimeOkEdit"></td>
            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3" id="orderRemarkOkEdit"></td>
            </tr>
            </tbody>
        </table>
        <table width="100%">
            <thead>
            <tr>
                <th colspan="2">任务信息</th>
                <th colspan="2">任务编号：<span id="taskCodeOkEdit"></span></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%"><em style="color: red;">*</em>订单数量：</th>
                <td width="30%">
                    <input type="text" name="task.orderNum" id="orderNumOkEdit"/>
                </td>
                <th width="25%"><em style="color: red;">*</em>税收（$）：</th>
                <td width="25%">
                    <input type="text" name="task.tax" id="taxOkEdit"/>
                </td>
            </tr>
            <tr>
                <th>订单金额（元）：</th>
                <td id="orderMoneyOkEdit">
                </td>
                <input type="hidden" id="orderMoneyHidOkEdit" name="task.orderMoney">
                <th><em style="color: red;">*</em>任务完成时间状态：</th>
                <td>
                    <select class="select" name="task.taskFinishNote" id="taskFinishNoteOkEdit" type="select">
                        <option value="15点之前">15点之前</option>
                        <option value="15点之后">15点之后</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>任务状态：</th>
                <td>
                    <select class="select" name="task.taskState" type="select">
                        <option value="1">已完成</option>
                        <option value="2">已失败</option>
                    </select>
                </td>
                <th>负责人：</th>
                <td id="liableUserNameOkEdit"></td>
            </tr>
            <tr>
                <th>执行日期：</th>
                <td id="execDateOkEdit"></td>
                <th>任务完成时间：</th>
                <td id="taskFinishTimeOkEdit"></td>
            </tr>
            </tbody>
        </table>
        <table width="100%">
            <thead>
            <tr>
                <th colspan="4">买家操作信息</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">买家账号代码：</th>
                <td width="30%" id="buyerCodeOkEdit"></td>
                <th width="25%">买家账户名称：</th>
                <td width="25%" id="buyerNameOkEdit"></td>
            </tr>
            <tr>
                <th>地址：</th>
                <td id="buyerAddressOkEdit"></td>
                <th>城市：</th>
                <td id="cityOkEdit"></td>
            </tr>
            <tr>
                <th>州：</th>
                <td id="provinceOkEdit"></td>
                <th>订单号：</th>
                <td id="orderSnOkEdit"></td>
            </tr>
            <tr>
                <th>对应运单号：</th>
                <td id="freightSnOkEdit"></td>
                <th>地址信息：</th>
                <td id="addressOkEdit"></td>
            </tr>
            <tr>
                <th>卡号：</th>
                <td colspan="3">
                    <input type="text" id="cardsCodeOkEdit" name="task.cardsCode" style="width: 600px;" maxlength="256" class="inputtext"/>
                </td>
            </tr>
            <tr>
                <th>当前余额（$）：</th>
                <td id="accountMoneyOkEdit"></td>
                <th>应充金额（$）：</th>
                <td id="depositMoneyOkEdit"></td>
            </tr>
            <tr>
                <th>退款金额（$）：</th>
                <td colspan="3" id="returnMoneyOkEdit"></td>
            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3" id="taskRemarkOkEdit"></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<!--设置运单号-->
<div id="editFreightSn" class="detail_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
    <form method="post" id="editFreightForm" action="${ctx.path}/manage/task/editCustTaskInfo${ctx.bizSuffix}">
        <input type="hidden" id="taskIdSnEdit" name="task.taskId">
        <table width="100%">
            <thead>
            <tr>
                <th colspan="4">订单信息</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">客户名称：</th>
                <td width="30%" id="custNameSnEdit"></td>
                <th width="25%"></th>
                <td width="25%"></td>
            </tr>
            <tr>
                <th>需求类型：</th>
                <td id="demandTypeCnSnEdit"></td>
                <th>电商平台：</th>
                <td id="platformTypeSnEdit"></td>
            </tr>
            <tr>
                <th>账号(店铺名称)：</th>
                <td id="accountsNameSnEdit"></td>
                <th>产品ID：</th>
                <td id="itemIdSnEdit"></td>
            </tr>
            <tr>
                <th>标题/关键词：</th>
                <td colspan="3" id="titleKeywordSnEdit"></td>
            </tr>
            <tr>
                <th>商品单价（$）：</th>
                <td id="unitPriceSnEdit"></td>
                <th>美元汇率：</th>
                <td id="dollarRateSnEdit"></td>
            </tr>
            <tr id="demandType_3_EditId">
                <th>刷单频次：</th>
                <td id="frequencySnEdit"></td>
                <th>刷单周期（天）：</th>
                <td id="cycleSnEdit"></td>
            </tr>
            <tr>
                <th>订单总额（元）：</th>
                <td id="orderTotalMoneySnEdit"></td>
                <th>订单状态：</th>
                <td id="orderStateCnSnEdit"></td>
            </tr>
            </tbody>
        </table>
        <table width="100%">
            <thead>
            <tr>
                <th colspan="4">任务信息</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">客户编号：</th>
                <td width="30%" id="custIdSnEdit"></td>
                <th width="25%">任务日期：</th>
                <td width="25%" id="execDateSnEdit"></td>
            </tr>
            <tr>
                <th>订单数量：</th>
                <td id="orderNumSnEdit"></td>
                <th>税收：</th>
                <td id="taxSnEdit"></td>
            </tr>
            <tr>
                <th>订单金额：</th>
                <td id="orderMoneySnEdit"></td>
                <th>订单号：</th>
                <td id="orderSnSnEdit"></td>
            </tr>
            <tr>
                <th><em style="color: red;">*</em>对应运单号：</th>
                <td>
                    <input type="text" id="freightSnSnEdit" name="task.freightSn" maxlength="128" class="inputtext"/>
                </td>
                <th>地址信息：</th>
                <td id="addressSnEdit"></td>
            </tr>
            <tr>
                <th>退款金额：</th>
                <td id="returnMoneySnEdit"></td>
                <th>当前余额：</th>
                <td id="accountMoneySnEdit"></td>
            </tr>
            <tr>
                <th>卡号：</th>
                <td colspan="3" id="cardsCodeSnEdit"></td>
            </tr>
            <tr>
                <th>负责人：</th>
                <td id="liableUserNameSnEdit"></td>
                <th>任务状态：</th>
                <td id="taskStateCnSnEdit"></td>
            </tr>
            <tr>
                <th>应充金额：</th>
                <td colspan="3" id="depositMoneySnEdit"></td>
            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3" id="taskRemarkSnEdit"></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>


<!-- 批量设置完成导入 -->
<div id="importSettingOk" class="add_box mCustomScrollbar_y">
    <form method="post" enctype="multipart/form-data" id="importSettingOkForm"
          action="${ctx.path}/manage/task/1/importReportSetting${ctx.bizSuffix}">
        <div class="add_list">
            <h5><em style="color: red;">*</em>导入文件：</h5>
            <div class="add_value">
                <input type="file" id="upload_file" name="file">
            </div>
        </div>
        <div class="add_list">
            <h5><em style="color: red;">*</em>导入模板：</h5>
            <div class="add_value">
                <a style="color:blue;" href="${ctx.path}/manage/task/1/downloadTemple${ctx.noAuthSuffix}">模板下载</a>
            </div>
        </div>
        <div class="add_list">
            <h5>操作说明：</h5>
            <div class="add_value" style="color:red;">
                <p>使用本工具进行导入应注意:</p>
                <p>1、请下载使用模板进行导入。</p>
                <p>2、操作前请按模板数据列进行填写。</p>
                <p>3、所有单元格应设置为【文本】格式。</p>
            </div>
        </div>
    </form>
</div>

<!-- 批量设置运单号导入 -->
<div id="importSettingSn" class="add_box mCustomScrollbar_y">
    <form method="post" enctype="multipart/form-data" id="importSettingSnForm"
          action="${ctx.path}/manage/task/2/importReportSetting${ctx.bizSuffix}">
        <div class="add_list">
            <h5><em style="color: red;">*</em>导入文件：</h5>
            <div class="add_value">
                <input type="file" id="upload_file" name="file">
            </div>
        </div>
        <div class="add_list">
            <h5><em style="color: red;">*</em>导入模板：</h5>
            <div class="add_value">
                <a style="color:blue;" href="${ctx.path}/manage/task/2/downloadTemple${ctx.noAuthSuffix}">模板下载</a>
            </div>
        </div>
        <div class="add_list">
            <h5>操作说明：</h5>
            <div class="add_value" style="color:red;">
                <p>使用本工具进行导入应注意:</p>
                <p>1、请下载使用模板进行导入。</p>
                <p>2、操作前请按模板数据列进行填写。</p>
                <p>3、所有单元格应设置为【文本】格式。</p>
            </div>
        </div>
    </form>
</div>

<!-- 设置退款 -->
<div id="refundId" class="detail_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
    <form method="post" id="refundForm" action="${ctx.path}/manage/task/editRefund${ctx.bizSuffix}">
        <input type="hidden" id="taskIdRefundEdit" name="task.taskId">
        <table width="100%">
            <thead>
            <tr>
                <th colspan="2">订单信息</th>
                <th colspan="2">订单编号：<span id="orderCodeRefundEdit"></span></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">客户名称：</th>
                <td width="30%" id="custNameRefundEdit"></td>
                <th width="25%">客户编号：</th>
                <td width="25%" id="custCodeRefundEdit"></td>
            </tr>
            <tr>
                <th>需求类型：</th>
                <td id="demandTypeCnRefundEdit"></td>
                <th>电商平台：</th>
                <td id="platformTypeRefundEdit"></td>
            </tr>
            <tr>
                <th>卖家账号(店铺名称)：</th>
                <td id="accountsNameRefundEdit"></td>
                <th>产品ID：</th>
                <td id="itemIdRefundEdit"></td>
            </tr>
            <tr>
                <th>标题/关键词：</th>
                <td colspan="3" id="titleKeywordRefundEdit"></td>
            </tr>
            <tr>
                <th>商品单价（$）：</th>
                <td id="unitPriceRefundEdit"></td>
                <th>美元汇率：</th>
                <td id="dollarRateRefundEdit"></td>
            </tr>
            <tr id="demandType_4_EditId">
                <th>刷单频次：</th>
                <td id="frequencyRefundEdit"></td>
                <th>刷单周期（天）：</th>
                <td id="cycleRefundEdit"></td>
            </tr>
            <tr>
                <th>订单总额（元）：</th>
                <td id="orderTotalMoneyRefundEdit"></td>
                <th>订单状态：</th>
                <td id="orderStateCnRefundEdit"></td>
            </tr>
            <tr>
                <th>任务开始日期：</th>
                <td id="taskStartDateRefundEdit"></td>
                <th>发布日期：</th>
                <td id="releaseTimeRefundEdit"></td>
            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3" id="orderRemarkRefundEdit"></td>
            </tr>
            </tbody>
        </table>
        <table width="100%">
            <thead>
            <tr>
                <th colspan="2">任务信息</th>
                <th colspan="2">任务编号：<span id="taskCodeRefundEdit"></span></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%"><em style="color: red;">*</em>订单数量：</th>
                <td width="30%" id="orderNumRefundEdit">
                </td>
                <th width="25%"><em style="color: red;">*</em>税收（$）：</th>
                <td width="25%" id="taxRefundEdit">
                </td>
            </tr>
            <tr>
                <th>订单金额（元）：</th>
                <td id="orderMoneyRefundEdit">
                </td>
                <input type="hidden" id="orderMoneyHidRefundEdit" name="task.orderMoney">
                <th><em style="color: red;">*</em>任务完成时间状态：</th>
                <td id="taskFinishNoteRefundEdit">
                </td>
            </tr>
            <tr>
                <th>任务状态：</th>
                <td id="taskStateRefundEdit">
                </td>
                <th>负责人：</th>
                <td id="liableUserNameRefundEdit"></td>
            </tr>
            <tr>
                <th>执行日期：</th>
                <td id="execDateRefundEdit"></td>
                <th>任务完成时间：</th>
                <td id="taskFinishTimeRefundEdit"></td>
            </tr>
            </tbody>
        </table>
        <table width="100%">
            <thead>
            <tr>
                <th colspan="4">买家操作信息</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">买家账号代码：</th>
                <td width="30%" id="buyerCodeRefundEdit"></td>
                <th width="25%">买家账户名称：</th>
                <td width="25%" id="buyerNameRefundEdit"></td>
            </tr>
            <tr>
                <th>地址：</th>
                <td id="buyerAddressRefundEdit"></td>
                <th>城市：</th>
                <td id="cityRefundEdit"></td>
            </tr>
            <tr>
                <th>州：</th>
                <td id="provinceRefundEdit"></td>
                <th>订单号：</th>
                <td id="orderSnRefundEdit"></td>
            </tr>
            <tr>
                <th>对应运单号：</th>
                <td id="freightSnRefundEdit"></td>
                <th>地址信息：</th>
                <td id="addressRefundEdit"></td>
            </tr>
            <tr>
                <th>卡号：</th>
                <td colspan="3" id="cardsCodeRefundEdit">
                </td>
            </tr>
            <tr>
                <th>当前余额（$）：</th>
                <td id="accountMoneyRefundEdit"></td>
                <th>应充金额（$）：</th>
                <td id="depositMoneyRefundEdit"></td>
            </tr>
            <tr>
                <th>退款金额（$）：</th>
                <td colspan="3">
                    <input name="task.returnMoney" type="text" id="returnMoneyRefundEdit"/>
                </td>
            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3" id="taskRemarkRefundEdit"></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>

