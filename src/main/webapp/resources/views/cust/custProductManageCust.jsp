<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
    <script src="${ctx.path}/resources/script/cust/custProductManage.js?v=1.2"></script>
    <script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.0"></script>
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
    <form class="tableform" action="${ctx.path}/manage/custProduct/queryListByCustId${ctx.pageSuffix}" method="post" id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <p:auth mcode="MENU_MANAGE_PRODUCT_CUST_EXPORT">
                        <button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/custProduct/exportListByCustId${ctx.bizSuffix}')">
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
                        <input type="text" id="searchaccountsName" name="accountsName" class="inputtext" placeholder="账号（店铺名称）" value="${custProductVo.accountsName}"/>
                    </li>
                    <li>
                        <input type="text" id="searchitemId" name="itemId" class="inputtext" placeholder="产品ID" value="${custProductVo.itemId}" />
                    </li>
                    <li>
                        <label class="inputlabel">电商平台：</label>
                        <select class="select" name="platformType" id="searchplatformType" type="select">
                            <option value="">全部</option>
                            <option value="eBay" <c:if test="${custProductVo.platformType == 'eBay'}">selected</c:if> >eBay</option>
                            <option value="Amazon" <c:if test="${custProductVo.platformType == 'Amazon'}">selected</c:if>>Amazon</option>
                            <option value="Facebook" <c:if test="${custProductVo.platformType == 'Facebook'}">selected</c:if> >Facebook</option>
                            <option value="tiktok" <c:if test="${custProductVo.platformType == 'tiktok'}">selected</c:if>>tiktok</option>
                            <option value="instagram" <c:if test="${custProductVo.platformType == 'instagram'}">selected</c:if> >instagram</option>
                            <option value="Pinterest" <c:if test="${custProductVo.platformType == 'Pinterest'}">selected</c:if>>Pinterest</option>
                            <option value="etsy" <c:if test="${custProductVo.platformType == 'etsy'}">selected</c:if>>etsy</option>
                            <option value="linkedin" <c:if test="${custProductVo.platformType == 'linkedin'}">selected</c:if> >linkedin</option>
                            <option value="Wish" <c:if test="${custProductVo.platformType == 'Pinterest'}">selected</c:if>>Wish</option>
                            <option value="ED eBay" <c:if test="${buyersVo.platformType == 'ED eBay'}">selected</c:if> >ED eBay</option>
                            <option value="paypal" <c:if test="${buyersVo.platformType == 'paypal'}">selected</c:if> >paypal</option>
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
                                <th width="160">电商平台</th>
                                <th width="160">账号(店铺名称)</th>
                                <th width="160">产品ID</th>
                                <th width="120">标题/关键词</th>
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
                                <td title="${data.platformType }">${data.platformType }</td>
                                <td title="${data.accountsName }">${data.accountsName }</td>
                                <td title="${data.itemId }">${data.itemId }</td>
                                <td title="${data.titleKeyword }">${data.titleKeyword }</td>

                                <td class="operation_td">
                                    <p:auth mcode="MENU_MANAGE_PRODUCT_CUST_VIEW">
                                        <button class="operationbtn" type="button" onclick="viewCustPage(${data.productId});">
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
<div id="viewId" class="add_box mCustomScrollbar_y" >
    <form method="post" id="viewForm">
        <div class="add_list">
            <h5 style="width: 143px;">客户编号：</h5>
            <div class="add_value" style="margin-left: 156px;">
                <span id="custCode"></span>
            </div>
        </div>
        <div class="add_list">
            <h5 style="width: 143px;">客户名称：</h5>
            <div class="add_value" style="margin-left: 156px;">
                <span id="custName"></span>
            </div>
        </div>
        <div class="add_list">
            <h5 style="width: 143px;">电商平台：</h5>
            <div class="add_value" style="margin-left: 156px;">
                <span id="platformType"></span>
            </div>
        </div>
        <div class="add_list">
            <h5 style="width: 143px;">卖家账号(店铺名称)：</h5>
            <div class="add_value" style="margin-left: 156px;">
                <span id="accountsName"></span>
            </div>
        </div>
        <div class="add_list">
            <h5 style="width: 143px;">产品ID：</h5>
            <div class="add_value" style="margin-left: 156px;">
                <span id="itemId"></span>
            </div>
        </div>
        <div class="add_list">
            <h5 style="width: 143px;">标题/关键词：</h5>
            <div class="add_value" style="margin-left: 156px;">
                <span id="titleKeyword"></span>
            </div>
        </div>
    </form>
</div>
</body>
</html>

