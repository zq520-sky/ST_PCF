<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/resources/platform/inc.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
    <script src="${ctx.path}/resources/script/buyer/buyerAccountManage.js?v=1.4"></script>
    <script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.0"></script>
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
    <form class="tableform" action="${ctx.path}/manage/buyerAccount/queryList${ctx.pageSuffix}" method="post"
          id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <p:auth mcode="MENU_MANAGE_ACCOUNT_BUYER_IMPORT">
                        <button type="button" class="btn add_btn" onclick="openImportPage();">
                            <i class="minicon addoperation_icon"></i><span>导入买家账号</span>
                        </button>
                    </p:auth>
                    <p:auth mcode="MENU_MANAGE_ACCOUNT_BUYER_EXPORT">
                        <button type="button" class="btn add_btn"
                                onclick="fun_export('${ctx.path}/manage/buyerAccount/exportList${ctx.bizSuffix}')">
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
                        <input type="text" id="searchbuyerCode" name="buyerCode" class="inputtext"
                               placeholder="买家姓名\账号代码" value="${buyersVo.buyerCode}"/>
                    </li>
                    <li>
                        <input type="text" id="searchbuyerName" name="buyerName" class="inputtext" placeholder="账户名称"
                               value="${buyersVo.buyerName}"/>
                    </li>
                    <li>
                        <input type="text" id="searchaddress" name="address" class="inputtext" placeholder="地址"
                               value="${buyersVo.address}"/>
                    </li>
                    <li>
                        <label class="inputlabel">电商平台：</label>
                        <select class="select" name="platformType" id="searchplatformType" type="select">
                            <option value="">全部</option>
                            <option value="eBay"
                                    <c:if test="${buyersVo.platformType == 'eBay'}">selected</c:if> >eBay
                            </option>
                            <option value="Amazon" <c:if test="${buyersVo.platformType == 'Amazon'}">selected</c:if>>
                                Amazon
                            </option>
                            <option value="Wish" <c:if test="${buyersVo.platformType == 'Wish'}">selected</c:if>>Wish
                            </option>
                            <option value="Facebook"
                                    <c:if test="${buyersVo.platformType == 'Facebook'}">selected</c:if> >Facebook
                            </option>
                            <option value="tiktok" <c:if test="${buyersVo.platformType == 'tiktok'}">selected</c:if>>
                                tiktok
                            </option>
                            <option value="instagram"
                                    <c:if test="${buyersVo.platformType == 'instagram'}">selected</c:if> >instagram
                            </option>
                            <option value="Pinterest"
                                    <c:if test="${buyersVo.platformType == 'Pinterest'}">selected</c:if>>Pinterest
                            </option>
                            <option value="etsy" <c:if test="${buyersVo.platformType == 'etsy'}">selected</c:if>>etsy
                            </option>
                            <option value="linkedin"
                                    <c:if test="${buyersVo.platformType == 'linkedin'}">selected</c:if> >linkedin
                            </option>
                            <option value="ED eBay"
                                    <c:if test="${buyersVo.platformType == 'ED eBay'}">selected</c:if> >ED eBay
                            </option>
                            <option value="paypal"
                                    <c:if test="${buyersVo.platformType == 'paypal'}">selected</c:if> >paypal
                            </option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">余额筛选：</label>
                        <select class="select" name="accountMoneyOprType" id="searchaccountMoneyOprType" type="select">
                            <option value="">全部</option>
                            <option value="1"
                                    <c:if test="${buyersVo.accountMoneyOprType == '1'}">selected</c:if> >0-100
                            </option>
                            <option value="2" <c:if test="${buyersVo.accountMoneyOprType == '2'}">selected</c:if>>>100
                            </option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">余额排序：</label>
                        <select class="select" name="accountMoneyOrder" id="searchaccountMoneyOrder" type="select">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${buyersVo.accountMoneyOrder == '1'}">selected</c:if>>升序
                            </option>
                            <option value="2" <c:if test="${buyersVo.accountMoneyOrder == '2'}">selected</c:if>>降序
                            </option>
                        </select>
                    </li>
                    <li>
                        <label class="inputlabel">禁用状态：</label>
                        <select class="select" name="isForbid" id="searchisForbid" type="select">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${buyersVo.isForbid == '1'}">selected</c:if>>正常</option>
                            <option value="2" <c:if test="${buyersVo.isForbid == '2'}">selected</c:if>>已禁用</option>
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
                                <th width="100">电商平台</th>
                                <th width="100">账号代码</th>
                                <th width="100">账户名称</th>
                                <th width="100">买家姓名</th>
                                <th width="120">现paypal</th>
                                <th width="120">邮箱</th>
                                <th width="120">信用卡</th>

                                <th width="100">地址</th>
                                <th width="100">城市</th>
                                <th width="100">州</th>
                                <th width="100">邮编</th>
                                <th width="80">历史余额</th>

                                <th width="80">刷单充值</th>
                                <th width="100">采购充值</th>
                                <th width="80">充值总额</th>
                                <th width="100">单月可用额度</th>
                                <th width="80">刷单次数</th>

                                <th width="80">采购次数</th>
                                <th width="80">刷单总额</th>
                                <th width="80">采购总额</th>
                                <th width="80">余额</th>
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
                                <td title="${data.platformType }">${data.platformType }</td>
                                <td title="${data.buyerCode }">${data.buyerCode }</td>
                                <td title="${data.buyerName }">${data.buyerName }</td>
                                <td title="${data.userName }">${data.userName }</td>
                                <td title="${data.payPal }">${data.payPal }</td>
                                <td title="${data.email }">${data.email }</td>
                                <td title="${data.creditCard }">${data.creditCard }</td>

                                <td title="${data.address }">${data.address }</td>
                                <td title="${data.city }">${data.city }</td>
                                <td title="${data.province }">${data.province }</td>
                                <td title="${data.postcode }">${data.postcode }</td>
                                <td title="${data.accountMoneyHis }">${data.accountMoneyHis }</td>

                                <td title="${data.brushRecharge }">${data.brushRecharge }</td>
                                <td title="${data.purchaseRecharge }">${data.purchaseRecharge }</td>
                                <td title="${data.rechargeTotal }">${data.rechargeTotal }</td>
                                <td title="${data.quotaMonth }">${data.quotaMonth }</td>
                                <td title="${data.brushNum }">${data.brushNum }</td>

                                <td title="${data.purchaseNum }">${data.purchaseNum }</td>
                                <td title="${data.brushTotalMoney }">${data.brushTotalMoney }</td>
                                <td title="${data.purchaseTotalMoney }">${data.purchaseTotalMoney }</td>
                                <td title="${data.accountMoney }">${data.accountMoney }</td>
                                <td title="${data.isForbidCN }">
                                    <c:if test="${data.isForbid == 0 }">
                                        <span class="recharge_statue recharge_success"><em>${data.isForbidCN }</em></span>
                                    </c:if>
                                    <c:if test="${data.isForbid == 1 }">
                                        <span class="recharge_statue recharge_failed"><em>${data.isForbidCN }</em></span>
                                    </c:if>
                                </td>

                                <td class="operation_td">
                                    <p:auth mcode="MENU_MANAGE_ACCOUNT_BUYER_VIEW">
                                        <button class="operationbtn" type="button"
                                                onclick="viewPage(${data.recordId});">
                                            <span>查看</span>
                                        </button>
                                    </p:auth>
                                    <p:auth mcode="MENU_MANAGE_ACCOUNT_BUYER_UPDATE">
                                        <button class="operationbtn" type="button"
                                                onclick="editPage(${data.recordId});">
                                            <span>编辑</span>
                                        </button>
                                    </p:auth>
                                    <p:auth mcode="MENU_MANAGE_ACCOUNT_BUYER_SET">
                                        <button class="operationbtn" type="button"
                                                onclick="setPage(${data.buyerId},${data.isForbid},'${data.buyerCode }');">
											<span>
												<c:if test="${data.isForbid == 0}">禁用</c:if>
												<c:if test="${data.isForbid == 1}">启用</c:if>
											</span>
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
        <tbody>
        <tr>
            <th width="20%">买家姓名：</th>
            <td width="30%" id="userNameView"></td>
            <th width="25%">电商平台：</th>
            <td width="25%" id="platformTypeView"></td>
        </tr>
        <tr>
            <th>email：</th>
            <td colspan="3" id="emailView"></td>
        </tr>
        <tr>
            <th>信用卡：</th>
            <td colspan="3" id="creditCardView"></td>
        </tr>
        <tr>
            <th>账户名称：</th>
            <td colspan="3" id="buyerNameView"></td>
        </tr>
        <tr>
            <th>账号代码：</th>
            <td id="buyerCodeView"></td>
            <th>现paypal：</th>
            <td id="payPalView"></td>
        </tr>
        <tr>
            <th>地址：</th>
            <td colspan="3" id="addressView"></td>
        </tr>
        <tr>
            <th>城市：</th>
            <td id="cityView"></td>
            <th>州：</th>
            <td id="provinceView"></td>
        </tr>
        <tr>
            <th>邮编：</th>
            <td id="postcodeView"></td>
            <th>历史余额：</th>
            <td id="accountMoneyHisView"></td>
        </tr>
        <tr>
            <th>刷单充值：</th>
            <td id="brushRechargeView"></td>
            <th>采购充值：</th>
            <td id="purchaseRechargeView"></td>
        </tr>
        <tr>
            <th>充值总额：</th>
            <td id="rechargeTotalView"></td>
            <th>单月可用额度：</th>
            <td id="quotaMonthView"></td>
        </tr>
        <tr>
            <th>刷单次数：</th>
            <td id="brushNumView"></td>
            <th>采购次数：</th>
            <td id="purchaseNumView"></td>
        </tr>
        <tr>
            <th>刷单总额：</th>
            <td id="brushTotalMoneyView"></td>
            <th>采购总额：</th>
            <td id="purchaseTotalMoneyView"></td>
        </tr>
        <tr>
            <th>余额：</th>
            <td id="accountMoneyView"></td>
            <th>退款金额：</th>
            <td id="refundMoneyView"></td>
        </tr>
        <tr>
            <th>禁用状态：</th>
            <td id="isForbidCNView"></td>
        </tr>

        </tbody>
    </table>
</div>

<!-- 编辑 -->
<div id="editId" name="editName"  class="detail_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
    <form method="post" id="editForm" action="${ctx.path}/manage/buyerAccount/editAccount${ctx.bizSuffix}">
        <input type="hidden" id="recordIdHidEdit" name="recordId"/>
        <input type="hidden" id="buyerIdHidEdit" name="buyerId"/>
        <input type="hidden" id="buyerCodeHidEdit" name="buyerCode"/>

        <table width="100%">
            <tbody>
            <tr>
                <th width="20%">买家姓名：</th>
                <td width="30%" id="userNameEdit"></td>
                <th width="25%">电商平台：</th>
                <td width="25%" id="platformTypeEdit"></td>
            </tr>
            <tr>
                <th>email：</th>
                <td colspan="3" id="emailEdit"></td>
            </tr>
            <tr>
                <th>信用卡：</th>
                <td colspan="3">
                    <input type="text" id="creditCardEdit" name="creditCard" maxlength="32" class="inputtext"/>
                </td>
            </tr>
            <tr>
                <th>账户名称：</th>
                <td colspan="3" id="buyerNameEdit"></td>
            </tr>
            <tr>
                <th>账号代码：</th>
                <td id="buyerCodeEdit"></td>
                <th>现paypal：</th>
                <td>
                    <input type="text" id="payPalEdit" name="payPal" maxlength="64" class="inputtext"/>
                </td>
            </tr>
            <tr>
                <th>地址：</th>
                <td colspan="3">
                    <input type="text" id="addressEdit" name="address" maxlength="128" class="inputtext"/>
                </td>
            </tr>
            <tr>
                <th>城市：</th>
                <td>
                    <input type="text" id="cityEdit" name="city" maxlength="50" class="inputtext"/>
                </td>
                <th>州：</th>
                <td>
                    <input type="text" id="provinceEdit" name="province" maxlength="50" class="inputtext"/>
                </td>
            </tr>
            <tr>
                <th>邮编：</th>
                <td>
                    <input type="text" id="postcodeEdit" name="postcode" maxlength="16" class="inputtext"/>
                </td>
                <th><em style="color: red;">*</em>历史余额：</th>
                <td>
                    <input type="text" id="accountMoneyHisEdit" name="accountMoneyHis" maxlength="10" class="inputtext"/>
                </td>
            </tr>
            <tr>
                <th>刷单充值：</th>
                <td id="brushRechargeEdit"></td>
                <th>采购充值：</th>
                <td id="purchaseRechargeEdit"></td>
            </tr>
            <tr>
                <th>充值总额：</th>
                <td id="rechargeTotalEdit"></td>
                <th>单月可用额度：</th>
                <td id="quotaMonthEdit"></td>
            </tr>
            <tr>
                <th>刷单次数：</th>
                <td id="brushNumEdit"></td>
                <th>采购次数：</th>
                <td id="purchaseNumEdit"></td>
            </tr>
            <tr>
                <th>刷单总额：</th>
                <td id="brushTotalMoneyEdit"></td>
                <th>采购总额：</th>
                <td id="purchaseTotalMoneyEdit"></td>
            </tr>
            <tr>
                <th>余额：</th>
                <td id="accountMoneyEdit"></td>
                <th>退款金额：</th>
                <td id="refundMoneyEdit"></td>
            </tr>
            <tr>
                <th>禁用状态：</th>
                <td id="isForbidCNEdit"></td>
            </tr>

            </tbody>
        </table>
    </form>
</div>

<!-- 账号导入 -->
<div id="importBuyerAccount" class="add_box mCustomScrollbar_y">
    <form method="post" enctype="multipart/form-data" id="importBuyerAccountForm"
          action="${ctx.path}/manage/buyerAccount/importAccount${ctx.bizSuffix}">
        <div class="add_list">
            <h5><em style="color: red;">*</em>导入文件：</h5>
            <div class="add_value">
                <input type="file" id="upload_file" name="file">
            </div>
        </div>
        <div class="add_list">
            <h5><em style="color: red;">*</em>导入模板：</h5>
            <div class="add_value">
                <a style="color:blue;" href="${ctx.path}/manage/buyerAccount/downloadTemple${ctx.noAuthSuffix}">模板下载</a>
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
</body>
</html>

