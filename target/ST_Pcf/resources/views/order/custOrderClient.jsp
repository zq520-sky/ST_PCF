<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.samton.platform.framework.util.CurrentUtil" %>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
    <script src="${ctx.path}/resources/script/order/custOrderClient.js?v=1.5"></script>
    <script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.0"></script>
    <script type="text/javascript">
        //加载查询条件
        function doSearch() {
            $("#pageForm").submit();
            top.progressbar(frameId);
        }
    </script>
    <style type="text/css">
        .hideTr{
            display: none;
        }
    </style>
</head>
<body>
<div class="main_con">
    <form class="tableform" action="${ctx.path}/manage/cust/order/2/queryCustOrderList${ctx.pageSuffix}" method="post" id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <p:auth mcode="MENU_MANAGE_CLIENT_DEMAND_ORDER_ADD">
                        <button type="button" class="btn add_btn" onclick="openAddPage(<%=CurrentUtil.getCurrentUser().getEnterpriseId()%>)">
                            <i class="minicon addoperation_icon"></i><span>新增需求订单</span>
                        </button>
                    </p:auth>
                    <p:auth mcode="MENU_MANAGE_CLIENT_DEMAND_ORDER_IMPORT">
                        <button type="button" class="btn add_btn" onclick="openImportPage()">
                            <i class="minicon addoperation_icon"></i><span>批量导入需求</span>
                        </button>
                    </p:auth>
                    <p:auth mcode="MENU_MANAGE_CLIENT_DEMAND_ORDER_EXPORT">
                        <button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/cust/order/2/exportCustOrderList${ctx.bizSuffix}')">
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
                        <input type="text" id="accountsNameSearch" name="accountsName" class="inputtext" placeholder="卖家账号（店铺名称）" value="${custOrderVo.accountsName}"/>
                    </li>
                    <li>
                        <input type="text" id="itemIdSearch" name="itemId" class="inputtext" placeholder="产品ID" value="${custOrderVo.itemId}"/>
                    </li>
                    <li>
                        <label class="inputlabel">电商平台：</label>
                        <select class="select" name="platformType" id="searchplatformType" type="select">
                            <option value="">全部</option>
                            <option value="eBay" <c:if test="${custOrderVo.platformType == 'eBay'}">selected</c:if> >eBay</option>
                            <option value="Amazon" <c:if test="${custOrderVo.platformType == 'Amazon'}">selected</c:if>>Amazon</option>
                            <option value="Wish" <c:if test="${custOrderVo.platformType == 'Wish'}">selected</c:if>>Wish</option>
                            <option value="Facebook" <c:if test="${custOrderVo.platformType == 'Facebook'}">selected</c:if> >Facebook</option>
                            <option value="tiktok" <c:if test="${custOrderVo.platformType == 'tiktok'}">selected</c:if>>tiktok</option>
                            <option value="instagram" <c:if test="${custOrderVo.platformType == 'instagram'}">selected</c:if> >instagram</option>
                            <option value="Pinterest" <c:if test="${custOrderVo.platformType == 'Pinterest'}">selected</c:if>>Pinterest</option>
                            <option value="etsy" <c:if test="${custOrderVo.platformType == 'etsy'}">selected</c:if>>etsy</option>
                            <option value="linkedin" <c:if test="${custOrderVo.platformType == 'linkedin'}">selected</c:if> >linkedin</option>
                            <option value="ED eBay" <c:if test="${buyersVo.platformType == 'ED eBay'}">selected</c:if> >ED eBay</option>
                            <option value="paypal" <c:if test="${buyersVo.platformType == 'paypal'}">selected</c:if> >paypal</option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">需求类型：</label>
                        <select class="select" name="demandType" id="demandTypeSearch" type="select">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${custOrderVo.demandType == 1}">selected</c:if> >采购</option>
                            <option value="2" <c:if test="${custOrderVo.demandType == 2}">selected</c:if> >刷单</option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">订单状态：</label>
                        <select class="select" name="orderState" id="orderStateSearch" type="select">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${custOrderVo.orderState == 0}">selected</c:if> >未开始</option>
                            <option value="1" <c:if test="${custOrderVo.orderState == 1}">selected</c:if> >进行中</option>
                            <option value="2" <c:if test="${custOrderVo.orderState == 2}">selected</c:if> >已完成</option>
                            <option value="3" <c:if test="${custOrderVo.orderState == 3}">selected</c:if> >已取消</option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">发布日期：</label>
                        <input type="text" id="releaseTimeBeginSearch" name="releaseTimeBegin" class="inputtext" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'releaseTimeEndSearch\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custOrderVo.releaseTimeBegin}' pattern='yyyy-MM-dd'/>" />
                        <em class="inputto">至</em>
                        <input type="text" id="releaseTimeEndSearch" name="releaseTimeEnd" class="inputtext" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'releaseTimeBeginSearch\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custOrderVo.releaseTimeEnd}' pattern='yyyy-MM-dd'/>" />
                    </li>
                    <li>
                        <label class="inputlabel">任务开始日期：</label>
                        <input type="text" id="taskStartDateBeginSearch" name="taskStartDateBegin" class="inputtext" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'taskStartDateEndSearch\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custOrderVo.taskStartDateBegin}' pattern='yyyy-MM-dd'/>" />
                        <em class="inputto">至</em>
                        <input type="text" id="taskStartDateEndSearch" name="taskStartDateEnd" class="inputtext" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'taskStartDateBeginSearch\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custOrderVo.taskStartDateEnd}' pattern='yyyy-MM-dd'/>" />
                    </li>
                </ul>
            </div>
            <div class="search_btncon">
                <p:auth mcode="MENU_MANAGE_CLIENT_DEMAND_ORDER_SEARCH">
                    <button type="button" class="btn add_btn" onClick="doSearch()">
                        <i class="minicon search_icon"></i>
                        <span>查询</span>
                    </button>
                </p:auth>
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
                                <th width="200">订单编号</th>
                                <th width="120">需求类型</th>
                                <th width="120">电商平台</th>
                                <th width="160">卖家账号（店铺名称）</th>
                                <th width="120">产品ID</th>
                                <th width="120">标题（关键词）</th>
                                <th width="120">地址</th>
                                <th width="120">商品单价（$）</th>
                                <th width="120">订单数量</th>
                                <th width="120">税收（$）</th>
                                <th width="120">刷单频次</th>
                                <th width="120">刷单周期（天）</th>
                                <th width="120">美元汇率</th>
                                <th width="120">订单总额（元）</th>
                                <th width="120">任务开始日期</th>
                                <th width="120">订单状态</th>
                                <th width="120">发布人</th>
                                <th width="120">发布日期</th>
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
                                <td title="${data.orderCode }">${data.orderCode }</td>
                                <td title="${data.demandTypeCN }">${data.demandTypeCN }</td>
                                <td title="${data.platformType }">${data.platformType }</td>
                                <td title="${data.accountsName }">${data.accountsName }</td>
                                <td title="${data.itemId }">${data.itemId }</td>
                                <td title="${data.titleKeyword }">${data.titleKeyword }</td>
                                <td title="${data.address }">${data.address }</td>
                                <td title="${data.unitPrice }">${data.unitPrice }</td>
                                <td title="${data.orderNum }">${data.orderNum }</td>
                                <td title="${data.tax }">${data.tax }</td>
                                <td title="${data.frequency }">${data.frequency }</td>
                                <td title="${data.cycle }">${data.cycle }</td>
                                <td title="${data.dollarRate }">${data.dollarRate }</td>
                                <td title="${data.orderTotalMoney }">${data.orderTotalMoney }</td>
                                <td title="<fmt:formatDate value='${data.taskStartDate }' pattern='yyyy-MM-dd'/>">
                                    <fmt:formatDate value='${data.taskStartDate }' pattern='yyyy-MM-dd'/>
                                </td>
                                <td title="${data.orderStateCN }">${data.orderStateCN }</td>
                                <td title="${data.releaseUserName }">${data.releaseUserName }</td>
                                <td title="<fmt:formatDate value='${data.releaseTime }' pattern='yyyy-MM-dd'/>">
                                    <fmt:formatDate value='${data.releaseTime }' pattern='yyyy-MM-dd'/>
                                </td>
                                <td class="operation_td">
                                    <p:auth mcode="MENU_MANAGE_CLIENT_DEMAND_ORDER_VIEW">
                                        <button class="operationbtn" type="button" onclick="viewPage(${data.orderId});">
                                            <span>查看</span>
                                        </button>
                                    </p:auth>
                                    <c:choose>
                                        <c:when test="${data.orderState == 0}">
                                            <p:auth mcode="MENU_MANAGE_CLIENT_DEMAND_ORDER_EDIT">
                                                <button class="operationbtn" type="button" onclick="editPage(${data.orderId});">
                                                    <span>编辑</span>
                                                </button>
                                            </p:auth>
                                            <p:auth mcode="MENU_MANAGE_CLIENT_DEMAND_ORDER_CANCEL">
                                                <button class="operationbtn" type="button" onclick="cancel(${data.orderId}, '${data.orderCode}');">
                                                    <span>取消</span>
                                                </button>
                                            </p:auth>
                                            <p:auth mcode="MENU_MANAGE_CLIENT_DEMAND_ORDER_DEL">
                                                <button class="operationbtn" type="button" onclick="del(${data.orderId}, '${data.orderCode}');">
                                                    <span>删除</span>
                                                </button>
                                            </p:auth>
                                        </c:when>
                                    </c:choose>
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
<div id="addCustOrder" class="detail_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
    <form method="post" id="addCustOrderForm" action="${ctx.path}/manage/cust/order/2/addCustOrder${ctx.bizSuffix}">
        <table width="100%">
            <thead>
            <tr>
                <th colspan="4">订单信息</th>
<%--                <th colspan="2">订单编号：<span id="orderCodeAdd"></span></th>--%>
            </tr>
            </thead>

            <tbody>
                <tr>
                    <th><em style="color: red;">*</em>客户名称：<input type="hidden" id="custIdAdd" name="custId"/></th>
                    <td id="custNameAdd"></td>
                    <th>客户编号：</th>
                    <td>
                        <span id="custCodeAdd"></span>
                    </td>
                </tr>

                <tr>
                    <th><em style="color: red;">*</em>需求类型：</th>
                    <td>
                        <select class="select" name="demandType" id="demandTypeAdd" type="select" onchange="demandTyepChange(this.options[this.options.selectedIndex].value)">
                            <option value="">请选择</option>
                            <option value="1">采购</option>
                            <option value="2">刷单</option>
                        </select>
                    </td>
                    <th><em style="color: red;">*</em>电商平台：</th>
                    <td>
                        <select class="select" name="platformType" id="platformTypeAdd" type="select">
                            <option value="">请选择</option>
                            <option value="eBay">eBay</option>
                            <option value="Amazon">Amazon</option>
                            <option value="Wish">Wish</option>
                            <option value="Facebook">Facebook</option>
                            <option value="tiktok">tiktok</option>
                            <option value="instagram">instagram</option>
                            <option value="Pinterest">Pinterest</option>
                            <option value="etsy">etsy</option>
                            <option value="linkedin">linkedin</option>
                            <option value="ED eBay">ED eBay</option>
                            <option value="paypal">paypal</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <th><em style="color: red;">*</em>产品ID：</th>
                    <td>
                        <input type="text" id="itemIdAdd" name="itemId" maxlength="32" class="inputtext" onchange="itemIdChange(this.value)"/>
                    </td>
                    <th><em style="color: red;">*</em>卖家账号：</th>
                    <td>
                        <input type="text" id="accountsNameAdd" name="accountsName" maxlength="64" class="inputtext" />
                    </td>
                </tr>

                <tr>
                    <th><em style="color: red;">*</em>标题/关键词：</th>
                    <td colspan="3">
                        <textarea cols="55" class="textarea" id="titleKeywordAdd" name="titleKeyword" maxlength="256"></textarea>
                    </td>
                </tr>

                <tr>
                    <th>地址：</th>
                    <td colspan="3">
                        <textarea cols="55" class="textarea" id="addressAdd" name="address" maxlength="256"></textarea>
                    </td>
                </tr>

                <tr>
                    <th><em style="color: red;">*</em>商品单价（$）：</th>
                    <td>
                        <input type="text" id="unitPriceAdd" name="unitPrice" class="inputtext" onchange="changeMoneyAdd();" />
                    </td>
                    <th><em style="color: red;">*</em>订单数量：</th>
                    <td>
                        <input type="text" id="orderNumAdd" name="orderNum" class="inputtext" onchange="changeMoneyAdd();"/>
                    </td>
                </tr>

                <tr>
                    <th>美元汇率：</th>
                    <td>
                        <span id="dollarRateAdd"></span>
                    </td>
                    <th class="taxEmpTh"></th>
                    <td class="taxEmpTh"></td>
                    <th class="taxTh hideTr">税收（$）：</th>
                    <td class="taxTh hideTr">
                        <span id="taxAdd"></span>
                    </td>
                </tr>

                <tr id="demandTypeTr" class="hideTr">
                    <th><em style="color: red;">*</em>刷单频次：</th>
                    <td>
                        <input type="text" id="frequencyAdd" name="frequency" class="inputtext" onchange="changeMoneyAdd();" />
                    </td>
                    <th><em style="color: red;">*</em>刷单周期（天）：</th>
                    <td>
                        <input type="text" id="cycleAdd" name="cycle" class="inputtext" onchange="changeMoneyAdd();"/>
                    </td>
                </tr>

                <tr>
                    <th>订单总额（￥）：</th>
                    <td>
                        <span id="orderTotalMoneyAdd"></span>
                    </td>
                    <th>订单状态：</th>
                    <td>
                        <span id="orderStateAdd">未开始</span>
                    </td>
                </tr>

                <tr>
                    <th><em style="color: red;">*</em>任务开始日期：</th>
                    <td>
                        <input type="text" id="taskStartDateAdd" name="taskStartDate" class="inputtext" onclick="WdatePicker({taskStartDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="min-width: 120px;" />
                    </td>
                </tr>

                <tr>
                    <th>备注：</th>
                    <td colspan="3">
                        <textarea cols="55" class="textarea" id="remarkAdd" name="remark" maxlength="256"></textarea>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</div>


<!-- 编辑 -->
<div id="editCustOrder" class="detail_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
    <form method="post" id="editCustOrderForm" action="${ctx.path}/manage/cust/order/2/updateCustOrder${ctx.bizSuffix}">
        <table width="100%">
            <thead>
            <tr>
                <th colspan="2">订单信息<input type="hidden" id="orderIdEdit" name="orderId" /></th>
                <th colspan="2">订单编号：<span id="orderCodeEdit"></span><input type="hidden" id="orderCodeEditPut" name="orderCode" /></th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <th><em style="color: red;">*</em>客户名称：<input type="hidden" id="custIdEdit" name="custId"/></th>
                <td id="custNameEdit"></td>
                <th>客户编号：</th>
                <td>
                    <span id="custCodeEdit"></span>
                </td>
            </tr>

            <tr>
                <th><em style="color: red;">*</em>需求类型：</th>
                <td>
                    <select class="select" name="demandType" id="demandTypeEdit" type="select" onchange="demandTyepChange(this.options[this.options.selectedIndex].value)">
                        <option value="">请选择</option>
                        <option value="1">采购</option>
                        <option value="2">刷单</option>
                    </select>
                </td>
                <th><em style="color: red;">*</em>电商平台：</th>
                <td>
                    <select class="select" name="platformType" id="platformTypeEdit" type="select">
                        <option value="">请选择</option>
                        <option value="eBay">eBay</option>
                        <option value="Amazon">Amazon</option>
                        <option value="Wish">Wish</option>
                        <option value="Facebook">Facebook</option>
                        <option value="tiktok">tiktok</option>
                        <option value="instagram">instagram</option>
                        <option value="Pinterest">Pinterest</option>
                        <option value="etsy">etsy</option>
                        <option value="linkedin">linkedin</option>
                        <option value="ED eBay">ED eBay</option>
                        <option value="paypal">paypal</option>
                    </select>
                </td>
            </tr>

            <tr>
                <th><em style="color: red;">*</em>产品ID：</th>
                <td>
                    <input type="text" id="itemIdEdit" name="itemId" maxlength="32" class="inputtext"  onchange="itemIdChange(this.value)"/>
                </td>
                <th><em style="color: red;">*</em>卖家账号：</th>
                <td>
                    <input type="text" id="accountsNameEdit" name="accountsName" maxlength="64" class="inputtext" />
                </td>
            </tr>

            <tr>
                <th><em style="color: red;">*</em>标题/关键词：</th>
                <td colspan="3">
                    <textarea cols="55" class="textarea" id="titleKeywordEdit" name="titleKeyword" maxlength="256"></textarea>
                </td>
            </tr>

            <tr>
                <th><em style="color: red;">*</em>地址：</th>
                <td colspan="3">
                    <textarea cols="55" class="textarea" id="addressEdit" name="address" maxlength="256"></textarea>
                </td>
            </tr>

            <tr>
                <th><em style="color: red;">*</em>商品单价（$）：</th>
                <td>
                    <input type="text" id="unitPriceEdit" name="unitPrice" class="inputtext" onchange="changeMoneyEdit();"/>
                </td>
                <th><em style="color: red;">*</em>订单数量：</th>
                <td>
                    <input type="text" id="orderNumEdit" name="orderNum" class="inputtext" onchange="changeMoneyEdit();"/>
                </td>
            </tr>

            <tr>
                <th>美元汇率：</th>
                <td>
                    <span id="dollarRateEdit"></span>
                </td>
                <th class="taxEmpTh hideTr"></th>
                <td class="taxEmpTh hideTr"></td>
                <th class="taxTh">税收（$）：</th>
                <td class="taxTh">
                    <span id="taxEdit"></span>
                </td>
            </tr>

            <tr id="editDemandTypeTr" class="hideTr">
                <th><em style="color: red;">*</em>刷单频次：</th>
                <td>
                    <input type="text" id="frequencyEdit" name="frequency" class="inputtext" onchange="changeMoneyEdit();"/>
                </td>
                <th>刷单周期（天）：</th>
                <td>
                    <input type="text" id="cycleEdit" name="cycle" class="inputtext" onchange="changeMoneyEdit();"/>
                </td>
            </tr>

            <tr>
                <th>订单总额（￥）：</th>
                <td>
                    <span id="orderTotalMoneyEdit"></span>
                </td>
                <th>订单状态：</th>
                <td>
                    <span id="orderStateCNEdit"></span>
                </td>
            </tr>

            <tr>
                <th><em style="color: red;">*</em>任务开始日期：</th>
                <td>
                    <input type="text" id="taskStartDateEdit" name="taskStartDate" class="inputtext" onclick="WdatePicker({realDateFmt:'yyyy-MM-dd', taskStartDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="min-width: 120px;" />
                </td>
            </tr>

            <tr>
                <th>备注：</th>
                <td colspan="3">
                    <textarea cols="55" class="textarea" id="remarkEdit" name="remark" maxlength="256"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>


<!-- 查看 -->
<div id="viewCustOrder" class="detail_box mCustomScrollbar_y" >
    <form method="post" id="viewCustOrderForm">
        <table width="100%">
            <thead>
                <tr>
                    <th colspan="2">订单信息</th>
                    <th colspan="2">订单编号：<span id="orderCodeView"></span></th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <th>客户名称：</th>
                    <td id="custNameView"></td>
                    <th>客户编号：</th>
                    <td id="custCodeView"></td>
                </tr>

                <tr>
                    <th>需求类型：</th>
                    <td id="demandTypeCNView"></td>
                    <th>电商平台：</th>
                    <td id="platformTypeView"></td>
                </tr>

                <tr>
                    <th>卖家账号：</th>
                    <td id="accountsNameView"></td>
                    <th>产品ID：</th>
                    <td id="itemIdView"></td>
                </tr>

                <tr>
                    <th>标题/关键词：</th>
                    <td colspan="3" id="titleKeywordView"></td>
                </tr>

                <tr>
                    <th>地址：</th>
                    <td colspan="3" id="addressView"></td>
                </tr>

                <tr>
                    <th>商品单价（$）：</th>
                    <td id="unitPriceView"></td>
                    <th>订单数量：</th>
                    <td id="orderNumView"></td>
                </tr>

                <tr>
                    <th>美元汇率：</th>
                    <td id="dollarRateView"></td>
                    <th class="taxEmpTh hideTr"></th>
                    <td class="taxEmpTh hideTr"></td>
                    <th class="taxTh">税收（$）：</th>
                    <td class="taxTh" id="taxView"></td>
                </tr>

                <tr class="hideTr" id="viewDemandTypeTr">
                    <th>刷单频次：</th>
                    <td id="frequencyView"></td>
                    <th>刷单周期（天）：</th>
                    <td id="cycleView"></td>
                </tr>

                <tr>
                    <th>订单总额（￥）：</th>
                    <td id="orderTotalMoneyView"></td>
                    <th>订单状态：</th>
                    <td id="orderStateCNView"></td>
                </tr>

                <tr>
                    <th>任务开始日期：</th>
                    <td id="taskStartDateView"></td>
                    <th>任务结束日期：</th>
                    <td id="taskEndDateView"></td>
                </tr>

                <tr>
                    <th>发布人 ：</th>
                    <td id="releaseUserNameView"></td>
                    <th>发布日期：</th>
                    <td id="releaseTimeView"></td>
                </tr>

                <tr>
                    <th>备注：</th>
                    <td colspan="3" id="remarkView"></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>


<div id="importCustOrder" class="add_box mCustomScrollbar_y" >
    <form method="post" enctype="multipart/form-data" id="importOrderForm" action="${ctx.path}/manage/cust/order/2/importOrders${ctx.bizSuffix}">
        <div>
            <h5 id="noteText" style="margin:50px 0 0 50px;">尊敬的客户您好：</h5>
            <p id="tipText" style="margin:5px 20px 20px 50px;text-indent: 2em">请严格使用客户需求（模板）进行数据准备，导入前请仔细检查，谢谢！</p>
        </div>
        <div class="add_list">
            <h5><em style="color: red;">*</em>导入文件：</h5>
            <div class="add_value">
                <input type="file" id="upload_file" name="file">
                <a href="#" style="color:blue;" onclick="fun_export('${ctx.path}/manage/cust/order/2/downloadTemple${ctx.noAuthSuffix}')">模板下载</a>
            </div>
        </div>
        <div class="add_list">
            <h5 style="color:red;">注意：</h5>
            <div class="add_value" style="color:red;">
                <p>请参照模板进行数据导入：</p>
                <p>1、绿色为必填项</p>
                <p>2、黄色栏为需求为刷单时必填</p>
            </div>
        </div>
    </form>
</div>