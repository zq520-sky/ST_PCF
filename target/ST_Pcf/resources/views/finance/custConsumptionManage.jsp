<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/21 0021
  Time: 20:17
  To change this template use File | Settings | File Templates.
  description: 客户消费记录（客户端）jsp
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
    <script src="${ctx.path}/resources/script/finance/taskInfoManage.js?v=${ctx.version}"></script>
    <script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.0"></script>
    <script type="text/javascript">
        //加载查询条件
        function doSearch() {
            $("#pageForm").submit();
            top.progressbar(frameId);
        }
    </script>
</head>
<body>
<div class="main_con">
    <form class="tableform" action="${ctx.path}/manage/finance/queryConsumptionInfoListByCustId${ctx.pageSuffix}" method="post" id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <p:auth mcode="MENU_MANAGE_CUST_FINANCE_CONSUMPTION_EXPORT">
                        <button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/finance/exportConsumptionInfoListByCustId${ctx.bizSuffix}')">
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
                        <input type="text" id="se_custName" name="custName" class="inputtext" placeholder="客户名称" value="${custTaskVo.custName}"/>
                    </li>
                    <li>
                        <input type="text" id="se_itemId" name="itemId" class="inputtext" placeholder="产品ID" value="${custTaskVo.itemId}" />
                    </li>
                    <li>
                        <label class="inputlabel">电商平台：</label>
                        <select class="select" name="platformType" id="se_platformType" type="select">
                            <option value="">全部</option>
                            <option value="eBay">eBay</option>
                            <option value="Amazon">Amazon</option>
                            <option value="Facebook">Facebook</option>
                            <option value="tiktok">tiktok</option>
                            <option value="instagram">instagram</option>
                            <option value="Pinterest">Pinterest</option>
                            <option value="etsy">etsy</option>
                            <option value="linkedin">linkedin</option>
                            <option value="Wish">Wish</option>
                            <option value="ED eBay">ED eBay</option>
                            <option value="paypal">paypal</option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">需求类型：</label>
                        <select class="select" name="demandType" id="se_demandType" type="select">
                            <option value="">全部</option>
                            <option value="1">采购</option>
                            <option value="2">刷单</option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">任务完成时间：</label>
                        <input type="text" id="taskEndDateBegin" name="taskEndDateBegin" class="inputtext" onclick="WdatePicker({returnTime:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'taskEndDateBegin\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custTaskVo.taskEndDateBegin}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
                        <em class="inputto">至</em>
                        <input type="text" id="taskEndDateEnd" name="taskEndDateEnd" class="inputtext" onclick="WdatePicker({returnTime:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'taskEndDateEnd\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custTaskVo.taskEndDateEnd}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
                    </li>
                    <li>
                        <label class="inputlabel">还款状态：</label>
                        <select class="select" name="isClose" id="se_isClose" type="select">
                            <option value="">全部</option>
                            <option value="0">未还款</option>
                            <option value="1">已还款</option>
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
                                <th width="100">客户名称</th>
                                <th width="100">账号(店铺名称)</th>
                                <th width="100">电商平台</th>
                                <th width="100">需求类型</th>
                                <th width="100">还款状态</th>
                                <th width="100">产品ID</th>
                                <th width="100">订单数量(元)</th>
                                <th width="100">商品单价</th>
                                <th width="100">税收</th>
                                <th width="100">美元汇率</th>
                                <th width="100">订单金额</th>
                                <th width="100">任务完成时间</th>
                                <th width="120" class="operation_th">操作</th>
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
                                <td title="${data.custName }">${data.custName }</td>
                                <td title="${data.accountsName }">${data.accountsName }</td>
                                <td title="${data.platformType }">${data.platformType }</td>
                                <td title="${data.redemandType }">${data.redemandType }</td>
                                <td title="${data.reisClose }">${data.reisClose }</td>
                                <td title="${data.itemId }">${data.itemId }</td>
                                <td title="${data.orderNum }">${data.orderNum }</td>
                                <td title="${data.unitPrice }">${data.unitPrice }</td>
                                <td title="${data.tax }">${data.tax }</td>
                                <td title="${data.dollarRate }">${data.dollarRate }</td>
                                <td title="${data.orderPrice }">${data.orderPrice }</td>
                                <td title="<fmt:formatDate value='${data.taskFinishTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                    <fmt:formatDate value='${data.taskFinishTime }' pattern='yyyy-MM-dd HH:mm:ss'/>
                                </td>
                                <td class="operation_td">
                                    <p:auth mcode="MENU_MANAGE_CUST_FINANCE_CONSUMPTION_VIEW">
                                        <button class="operationbtn" type="button" onclick="viewConsumptionPage(${data.taskId},${data.orderId});">
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
<div id="viewId" class="add_box mCustomScrollbar_y">
    <form method="post" id="viewForm">
        <div class="add_list">
            <h5>任务编号：</h5>
            <div class="add_value">
                <span id="taskCode"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>客户编号：</h5>
            <div class="add_value">
                <span id="custCode"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>客户名称：</h5>
            <div class="add_value">
                <span id="custName"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>账号(店铺名称)：</h5>
            <div class="add_value">
                <span id="accountsName"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>平台类型：</h5>
            <div class="add_value">
                <span id="platformType"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>需求类型：</h5>
            <div class="add_value">
                <span id="redemandType"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>产品ID：</h5>
            <div class="add_value">
                <span id="itemId"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>订单数量：</h5>
            <div class="add_value">
                <span id="orderNum"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>商品单价：</h5>
            <div class="add_value">
                <span id="unitPrice">美元</span>
            </div>
        </div>
        <div class="add_list">
            <h5>美元汇率：</h5>
            <div class="add_value">
                <span id="dollarRate"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>税收：</h5>
            <div class="add_value">
                <span id="tax">美元</span>
            </div>
        </div>
        <div class="add_list">
            <h5>订单金额：</h5>
            <div class="add_value">
                <span id="orderPrice">元</span>
            </div>
        </div>
        <div class="add_list">
            <h5>任务完成时间：</h5>
            <div class="add_value">
                <span id="taskFinishTime"></span>
            </div>
        </div>
    </form>
</div>
</body>
</html>

