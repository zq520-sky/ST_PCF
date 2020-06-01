<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
    <script src="${ctx.path}/resources/script/finance/custRechargeManage.js?v=${ctx.version}9"></script>
    <script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.0"></script>
    <script src="${ctx.path}/resources/script/upload/cupload.js"></script>
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
    <form class="tableform" action="${ctx.path}/manage/finance/queryRechargeApplicationList${ctx.pageSuffix}" method="post" id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <%--<p:auth mcode="MENU_MANAGE_CUST_FINANCE_RECHARGE_ADD">
                        <button type="button" class="btn add_btn" onclick="openAddPage()">
                            <i class="minicon exportoperation_icon"></i><span>充值申请</span>
                        </button>
                    </p:auth>--%>
                    <p:auth mcode="MENU_MANAGE_CUST_FINANCE_RECHARGE_EXPORT">
                        <button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/finance/exportRechargeApplicationList${ctx.bizSuffix}')">
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
                        <label class="inputlabel">充值方式：</label>
                        <select class="select" name="payType" id="payTypeSearch" type="select">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${custDepositOrderVo.payType == 1}">selected</c:if> >支付宝</option>
                            <option value="2" <c:if test="${custDepositOrderVo.payType == 2}">selected</c:if> >微信</option>
                            <option value="5" <c:if test="${custDepositOrderVo.payType == 5}">selected</c:if> >其他</option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">审核状态：</label>
                        <select class="select" name="approveState" id="approveStateSearch" type="select">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${custDepositOrderVo.approveState == 0}">selected</c:if> >待审核</option>
                            <option value="1" <c:if test="${custDepositOrderVo.approveState == 1}">selected</c:if> >审核通过</option>
                            <option value="2" <c:if test="${custDepositOrderVo.approveState == 2}">selected</c:if> >审核驳回</option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">充值时间：</label>
                        <input type="text" id="payTimeBeginSearch" name="payTimeBegin" class="inputtext" onclick="WdatePicker({payTime:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'payTimeEndSearch\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custDepositOrderVo.payTimeBegin}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
                        <em class="inputto">至</em>
                        <input type="text" id="payTimeEndSearch" name="payTimeEnd" class="inputtext" onclick="WdatePicker({payTime:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'payTimeBeginSearch\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custDepositOrderVo.payTimeEnd}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
                    </li>
                </ul>
            </div>
            <div class="search_btncon">
                <p:auth mcode="MENU_MANAGE_CUST_FINANCE_RECHARGE_SEARCH">
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
                                <th width="100">充值订单编号</th>
                                <th width="100">客户编号</th>
                                <th width="100">客户名称</th>
                                <th width="100">充值金额(元)</th>
                                <th width="100">付款凭证</th>
                                <th width="100">充值方式</th>
                                <th width="100">充值时间</th>
                                <th width="100">审核状态</th>
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
                                <td title="${data.custCode }">${data.custCode }</td>
                                <td title="${data.custName }">${data.custName }</td>
                                <td title="${data.rechargeMoney }">${data.rechargeMoney }</td>
                                <c:choose>
                                    <c:when test="${data.payImage == null || data.payImage ==''}">
                                        <td title="无"><span>无</span></td>
                                    </c:when>
                                    <c:when test="${data.payImage !=null || data.payImage !='' }">
                                        <td>
                                            <img src="${ctx.path}${data.payImage }" height="30px" width="50px">
                                        </td>
                                    </c:when>
                                </c:choose>
                                <td title="${data.payTypeCN }">${data.payTypeCN }</td>
                                <td title="<fmt:formatDate value='${data.payTime }' pattern='yyyy-MM-dd'/>">
                                    <fmt:formatDate value='${data.payTime }' pattern='yyyy-MM-dd'/>
                                </td>
                                <td title="${data.approveStateCN }">${data.approveStateCN }</td>
                                <td class="operation_td">
                                    <p:auth mcode="MENU_MANAGE_CUST_FINANCE_RECHARGE_VIEW">
                                        <button class="operationbtn" type="button" onclick="viewPage(${data.orderId});">
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


<!-- 查看 -->
<div id="viewId" class="add_box mCustomScrollbar_y" >
    <form method="post" id="viewForm">
        <div class="add_list">
            <h5>充值订单编号：</h5>
            <div class="add_value">
                <span id="orderCodeView"></span>
            </div>
        </div>
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
            <h5>充值方式：</h5>
            <div class="add_value">
                <span id="payTypeCNView"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>充值金额（元）：</h5>
            <div class="add_value">
                <span id="rechargeMoneyView"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>付款凭证：</h5>
            <div class="add_value">
                <span id="payImageView"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>充值时间：</h5>
            <div class="add_value">
                <span id="payTimeView"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>审核状态：</h5>
            <div class="add_value">
                <span id="approveStateCNView"></span>
            </div>
        </div>
    </form>
</div>
