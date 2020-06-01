<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
    <script src="${ctx.path}/resources/script/finance/returnInfoManage.js?v=${ctx.version}"></script>
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
    <form class="tableform" action="${ctx.path}/manage/finance/queryReturnInfoList${ctx.pageSuffix}" method="post" id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <p:auth mcode="MENU_MANAGE_FINANCE_RETURN_EXPORT">
                        <button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/finance/exportReturnInfoList${ctx.bizSuffix}')">
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
                        <input type="text" id="se_custName" name="custName" class="inputtext" placeholder="客户名称" value="${custAccountReturnVo.custName}"/>
                    </li>
                    <li>
                        <input type="text" id="se_companyName" name="companyName" class="inputtext" placeholder="所属公司" value="${custAccountReturnVo.companyName}" />
                    </li>
                    <li>
                        <label class="inputlabel">授信时间：</label>
                        <input type="text" id="returnTimeBegin" name="returnTimeBegin" class="inputtext" onclick="WdatePicker({returnTime:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'returnTimeEnd\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custAccountReturnVo.returnTimeBegin}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
                        <em class="inputto">至</em>
                        <input type="text" id="returnTimeEnd" name="returnTimeEnd" class="inputtext" onclick="WdatePicker({returnTime:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'returnTimeBegin\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custAccountReturnVo.returnTimeEnd}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
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
                                <th width="100">所属公司</th>
                                <th width="100">还款金额(元)</th>
                                <th width="100">备注</th>
                                <th width="100">还款时间</th>
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
                                <td title="${data.custCode }">${data.custCode }</td>
                                <td title="${data.custName }">${data.custName }</td>
                                <td title="${data.companyName }">${data.companyName }</td>
                                <td title="${data.returnMoney }">${data.returnMoney }</td>
                                <td title="${data.remark }">${data.remark }</td>
                                <td title="<fmt:formatDate value='${data.returnTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                    <fmt:formatDate value='${data.returnTime }' pattern='yyyy-MM-dd HH:mm:ss'/>
                                </td>
                                <td class="operation_td">
                                    <p:auth mcode="MENU_MANAGE_RETURN_INFO_VIEW">
                                        <button class="operationbtn" type="button" onclick="viewReturnPage(${data.returnId});">
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
            <h5>所属公司：</h5>
            <div class="add_value">
                <span id="companyName"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>美元汇率：</h5>
            <div class="add_value">
                <span id="dollarRate"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>还款金额(元)：</h5>
            <div class="add_value">
                <span id="returnMoney"></span>
            </div>
        </div>
        <!--
        <div class="add_list">
            <h5>操作前账户余额：</h5>
            <div class="add_value">
                <span id="beforeReturnMoney"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>操作后账户余额：</h5>
            <div class="add_value">
                <span id="afterReturnMoney"></span>
            </div>
        </div>
        -->
        <div class="add_list">
            <h5>还款时间：</h5>
            <div class="add_value">
                <span id="returnTime"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>备注：</h5>
            <div class="add_value">
                <span id="remark"></span>
            </div>
        </div>
    </form>
</div>
</body>
</html>
