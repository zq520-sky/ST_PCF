<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
    <script src="${ctx.path}/resources/script/task/custTaskInfoManage.js?v=1.6"></script>

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
    <form class="tableform" action="${ctx.path}/manage/task/queryCustTaskInfoListByCustId${ctx.pageSuffix}" method="post" id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <p:auth mcode="MENU_MANAGE_CUST_TASK_INDEX_EXPORT">
                        <button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/task/exportCustTaskInfoListByCustId${ctx.bizSuffix}')">
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
                        <input type="text" id="searchCustName" name="cust.custName" class="inputtext" placeholder="客户名称" value="${custTaskInfoVo.cust.custName}"/>
                    </li>
                    <li>
                        <label class="inputlabel">负责人：</label>
                        <st:commonSelect className="select" id="searchLiableUserId" name="task.liableUserId" selectValue="${custTaskInfoVo.task.liableUserId}" sql="select user_id as val, user_name as text from t_sys_pm_user where sys_type = 1 and user_id != 1 order by user_id"/>
                    </li>
                    <li>
                        <input type="text" id="searchItemId" name="product.itemId" class="inputtext" placeholder="产品ID" value="${custTaskInfoVo.product.itemId}" />
                    </li>
                    <li>
                        <input type="text" id="searchAccountsName" name="product.accountsName" class="inputtext" placeholder="卖家账号（店铺名称）" value="${custTaskInfoVo.product.accountsName}" />
                    </li>
                    <li>
                        <label class="inputlabel">电商平台：</label>
                        <select class="select" name="product.platformType" id="searchPlatformType" type="select">
                            <option value="">全部</option>
                            <option value="eBay" <c:if test="${custTaskInfoVo.product.platformType == 'eBay'}">selected</c:if> >eBay</option>
                            <option value="Amazon" <c:if test="${custTaskInfoVo.product.platformType == 'Amazon'}">selected</c:if>>Amazon</option>
                            <option value="Wish" <c:if test="${custTaskInfoVo.product.platformType == 'Wish'}">selected</c:if>>Wish</option>
                            <option value="Facebook" <c:if test="${custTaskInfoVo.product.platformType == 'Facebook'}">selected</c:if> >Facebook</option>
                            <option value="tiktok" <c:if test="${custTaskInfoVo.product.platformType == 'tiktok'}">selected</c:if>>tiktok</option>
                            <option value="instagram" <c:if test="${custTaskInfoVo.product.platformType == 'instagram'}">selected</c:if> >instagram</option>
                            <option value="Pinterest" <c:if test="${custTaskInfoVo.product.platformType == 'Pinterest'}">selected</c:if>>Pinterest</option>
                            <option value="etsy" <c:if test="${custTaskInfoVo.product.platformType == 'etsy'}">selected</c:if>>etsy</option>
                            <option value="linkedin" <c:if test="${custTaskInfoVo.product.platformType == 'linkedin'}">selected</c:if> >linkedin</option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">需求类型：</label>
                        <select class="select" name="order.demandType" id="searchDemandType" type="select">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${custTaskInfoVo.order.demandType == 1}">selected</c:if> >采购</option>
                            <option value="2"<c:if test="${custTaskInfoVo.order.demandType == 2}">selected</c:if> >刷单</option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">任务状态：</label>
                        <select class="select" name="task.taskState" id="searchTaskState" type="select">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${custTaskInfoVo.task.taskState == 0}">selected</c:if> >未完成</option>
                            <option value="1"<c:if test="${custTaskInfoVo.task.taskState == 1}">selected</c:if> >已完成</option>
                            <option value="2"<c:if test="${custTaskInfoVo.task.taskState == 2}">selected</c:if> >已失败</option>
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
                                <th width="190">任务编号</th>
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
                                <th width="160">任务完成时间</th>
                                <th width="80">负责人</th>
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

                                <td class="operation_td">
                                    <p:auth mcode="MENU_MANAGE_CUST_TASK_INDEX_VIEW">
                                        <button class="operationbtn" type="button" onclick="viewCustPage(${data.task.taskId});">
                                            <span>查看</span>
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
            <th width="25%">客户编号：</th>
            <td width="25%" id="custCodeView"></td>
        </tr>
        <tr>
            <th>需求类型：</th>
            <td id="demandTypeCnView"></td>
            <th>电商平台：</th>
            <td id="platformTypeView"></td>
        </tr>
        <tr>
            <th>卖家账号(店铺名称)：</th>
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
        <tr>
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
        <tr>
            <th>任务开始日期：</th>
            <td id="taskStartDateView"></td>
            <th>发布日期：</th>
            <td id="releaseTimeView"></td>
        </tr>
        <tr>
            <th>备注：</th>
            <td colspan="3" id="orderRemarkView"></td>
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
            <th width="20%">订单数量：</th>
            <td width="30%" id="orderNumView"></td>
            <th width="25%">税收（$）：</th>
            <td width="25%" id="taxView"></td>
        </tr>
        <tr>
            <th>订单金额（元）：</th>
            <td colspan="3" id="orderMoneyView"></td>
        </tr>
        <tr>
            <th>任务状态：</th>
            <td id="taskStateCnView"></td>
            <th>负责人：</th>
            <td id="liableUserNameView"></td>
        </tr>
        <tr>
            <th>执行日期：</th>
            <td id="execDateView"></td>
            <th>任务完成时间：</th>
            <td id="taskFinishTimeView"></td>
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
            <td width="30%" id="buyerCodeView"></td>
            <th width="25%">买家账户名称：</th>
            <td width="25%" id="buyerNameView"></td>
        </tr>
        <tr>
            <th>地址：</th>
            <td id="buyerAddressView"></td>
            <th>城市：</th>
            <td id="cityView"></td>
        </tr>
        <tr>
            <th>州：</th>
            <td id="provinceView"></td>
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
            <th>卡号：</th>
            <td id="cardsCodeView"></td>
            <th>退款金额（$）：</th>
            <td id="returnMoneyView"></td>
        </tr>
        <tr>
            <th>当前余额（$）：</th>
            <td id="accountMoneyView"></td>
            <th>应充金额（$）：</th>
            <td id="depositMoneyView"></td>
        </tr>
        <tr>
            <th>备注：</th>
            <td colspan="3" id="taskRemarkView"></td>
        </tr>
        </tbody>
    </table>
</div>


</body>
</html>

