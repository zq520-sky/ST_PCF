<%--
  Desctiption：管理端客户登录日志jsp页面
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/17 0017
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
    <script src="${ctx.path}/resources/script/custLogLogin/custLogLogin.js?v=1.0"></script>
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
    <form class="tableform" action="${ctx.path}/manage/log/queryCustLogLoginInfoList${ctx.pageSuffix}" method="post" id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <p:auth mcode="MENU_MANAGE_LOG_LOGIN_CUST_EXPORT">
                        <button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/log/exportCustLogLoginInfoList${ctx.bizSuffix}')">
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
                        <input type="text" id="custCode" name="custCode" class="inputtext" placeholder="客户名称" value="${custLogLoginVo.custName}"/>
                        <%--<st:commonSelect className="select" name="sysRole.departId" sql="select depart_id as val, depart_name as text from t_sys_depart where status = 1 order by order_num"/>--%>
                    </li>
                    <li>
                        <label class="inputlabel">登录时间：</label>
                        <input type="text" id="loginDateBegin" name="loginDateBegin" class="inputtext" onclick="WdatePicker({payTime:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'loginDateEnd\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custLogLoginVo.loginDateBegin}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
                        <em class="inputto">至</em>
                        <input type="text" id="loginDateEnd" name="loginDateEnd" class="inputtext" onclick="WdatePicker({payTime:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'loginDateBegin\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${custLogLoginVo.loginDateEnd}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
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
                                <th width="120">用户名称</th>
                                <th width="120">ip地址</th>
                                <th width="120">登录时间</th>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="tablelist_tbody">
                    <table>
                        <c:if test="${empty pageData.data}">
                            <tr>
                                <td align="center" colspan="5">对不起，没有找到相关数据！</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${pageData.data }" var="data" varStatus="staus">
                            <tr>
                                <td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
                                        ${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
                                </td>
                                <td title="${data.custName }">${data.custName }</td>
                                <td title="${data.loginIp }">${data.loginIp }</td>
                                <td title="<fmt:formatDate value='${data.loginDate }' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                    <fmt:formatDate value='${data.loginDate }' pattern='yyyy-MM-dd HH:mm:ss'/>
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

</body>
</html>
