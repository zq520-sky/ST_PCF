var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var editFreightSnValidate;
var editValidate;
var refundValidate;
var importSettingOkValidate;
var importSettingSnValidate;
var loadingData;

$(function () {
    editValidate = $("#editForm").validate({
        rules: {
            "task.taskState": {
            },
            "task.cardsCode": {
            },
            "task.taskFinishNote":{
                required: true
            },
            "order.tax":{
                required: true,
                money: true
            },
            "order.orderNum":{
                required: true,
                negative : true
            }
        },
        messages: {
            "task.taskFinishNote":{
                required: "请选择任务完成时间状态！"
            },
            "order.tax":{
                required: "请输入税收！",
                money: "请输入正确的金额！"
            },
            "order.orderNum":{
                required: "请输入订单数量！",
                negative : "请输入正整数！"
            }
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        top.toastr.success("编辑任务状态成功！");
                        FormUtil.resetForm("editForm");
                        doSearch();
                        layer.close(index); //再执行关闭
                    }
                }
            })

        }
    });
    editFreightSnValidate = $("#editFreightForm").validate({
        rules: {
            "task.freightSn": {
                required: true
            }
        },
        messages: {
            "task.freightSn": {
                required: "请输入对应运单号"
            }
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        top.toastr.success("编辑运单号成功！");
                        FormUtil.resetForm("editFreightForm");
                        doSearch();
                        layer.close(index); //再执行关闭
                    }
                }
            })

        }
    });
    importSettingOkValidate = $("#importSettingOkForm").validate({
        rules: {
            file: {
                required: true
            }
        },
        messages: {
            file: {
                required: "请选择导入的文件！"
            }
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        if(responseText.data['error'] != null){
                            top.toastr.error(responseText.data['error']);
                        }else{
                            top.toastr.success("批量导入成功！");
                            FormUtil.resetForm("importSettingOkForm");
                            doSearch();
                            layer.close(index); //再执行关闭
                        }
                        layer.close(loadingData);
                    }
                }
            })

        }
    })
    importSettingSnValidate = $("#importSettingSnForm").validate({
        rules: {
            file: {
                required: true
            }
        },
        messages: {
            file: {
                required: "请选择导入的文件！"
            }
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        if(responseText.data['error'] != null){
                            top.toastr.error(responseText.data['error']);
                        }else{
                            top.toastr.success("批量导入成功！");
                            FormUtil.resetForm("importSettingSnForm");
                            doSearch();
                            layer.close(index); //再执行关闭
                        }
                        layer.close(loadingData);
                    }
                }
            })

        }
    })
    refundValidate = $("#refundForm").validate({
        rules: {
            "task.returnMoney": {
                required: true,
                money: true
            }
        },
        messages: {
            "task.returnMoney": {
                required: "请输入退款金额！",
                money: "请输入正确的金额！"
            }
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        top.toastr.success("设置退款成功！");
                        FormUtil.resetForm("refundForm");
                        doSearch();
                        layer.close(index); //再执行关闭
                    }
                }
            })
        }
    });

})

function viewReportPage(id) {
    if (Number(id)) {
        //清除原有数据
        $("#orderCodeView").text('');
        $("#taskCodeView").text('');
        $.each($("#viewId td[id$='View']"), function (i, area) {
            $(area).text('');
            $(area).val();
        });

        $.ajax({
            url: ctx.path + '/manage/task/viewCustTaskReport' + ctx.bizSuffix,
            async: true,
            dataType: 'json',
            type: 'POST',
            data: {taskId: id},
            success: function (data) {
                if (data.rs == -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                //对页面属性赋值（要求页面id与map的key值保持一致）
                var cust = dataRet.cust;
                var order = dataRet.order;
                var task = dataRet.task;
                var product = dataRet.product;
                var account = dataRet.account;
                var buyer = dataRet.buyer;

                //如果是刷单，则显示刷单频次和刷单周期；采购显示税收
                if (order.demandType == 2) {
                    $("#demandType_2_ViewId").removeAttr("style");
                    $("#taxView").attr("style", "display:none;");
                } else {
                    $("#demandType_2_ViewId").attr("style", "display:none;");
                    $("#taxView").removeAttr("style");
                }

                for (var key in dataRet) {
                    $("#viewId #" + key + "View").text(dataRet[key]);
                }
                for (var key in cust) {
                    $("#viewId #" + key + "View").text(cust[key]);
                }
                for (var key in order) {
                    $("#viewId #" + key + "View").text(order[key]);
                }
                for (var key in task) {
                    $("#viewId #" + key + "View").text(task[key]);
                }
                for (var key in product) {
                    $("#viewId #" + key + "View").text(product[key]);
                }
                for (var key in buyer) {
                    $("#viewId #" + key + "View").text(buyer[key]);
                }
                for (var key in account) {
                    $("#viewId #" + key + "View").text(account[key]);
                }

                $("#viewId #taskFinishNoteView").val(task.taskFinishiNote);

                parentIndex = layer.open({
                    title: '查看任务信息',
                    type: 1,
                    area: ['60%', '98%'], //宽高
                    content: $('#viewId'),
                    btn: ['关闭'],
                    cancel: function (index) {
                    }
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                top.toastr.error("操作失败");
            }
        });
    } else {
        top.toastr.error("数据异常！");
    }
}

//执行查询
function doSearch() {
    $("#pageForm").submit();
    top.progressbar(frameId);
}

//打开编辑页面
function editPage(id) {
    if (Number(id)) {
        //清除原有数据
        $("#orderCodeView").text('');
        $("#taskCodeView").text('');
        $.each($("#editId td[id$='Edit']"), function (i, area) {
            $(area).text('');
            $(area).val();
        });

        $.ajax({
            url: ctx.path + '/manage/task/viewCustTaskInfo' + ctx.bizSuffix,
            async: true,
            dataType: 'json',
            type: 'POST',
            data: {taskId: id},
            success: function (data) {
                if (data.rs == -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                //对页面属性赋值（要求页面id与map的key值保持一致）
                var cust = dataRet.cust;
                var order = dataRet.order;
                var task = dataRet.task;
                var product = dataRet.product;
                var account = dataRet.account;
                var buyer = dataRet.buyer;

                //如果是刷单，则显示刷单频次和刷单周期；采购显示税收
                if (order.demandType == 2) {
                    $("#demandType_2_EditId").removeAttr("style");
                    $("#taxOkEdit").attr("style", "display:none;");
                } else {
                    $("#demandType_2_EditId").attr("style", "display:none;");
                    $("#taxOkEdit").removeAttr("style");
                }

                for (var key in dataRet) {
                    $("#editId #" + key + "OkEdit").text(dataRet[key]);
                }
                for (var key in cust) {
                    $("#editId #" + key + "OkEdit").text(cust[key]);
                }
                for (var key in order) {
                    $("#editId #" + key + "OkEdit").text(order[key]);
                }
                for (var key in task) {
                    $("#editId #" + key + "OkEdit").text(task[key]);
                }
                for (var key in product) {
                    $("#editId #" + key + "OkEdit").text(product[key]);
                }
                for (var key in buyer) {
                    $("#editId #" + key + "OkEdit").text(buyer[key]);
                }
                for (var key in account) {
                    $("#editId #" + key + "OkEdit").text(account[key]);
                }

                $("#editId #cardsCodeOkEdit").val(task.cardsCode);
                $("#editId #taskIdOkEdit").val(task.taskId);
                $("#editId #taskStateOkEdit").val("已完成");
                $("#editId #orderNumOkEdit").val(task.orderNum);
                $("#editId #taxOkEdit").val(task.tax);
                $("#editId #orderMoneyHidOkEdit").val(task.orderMoney);

                //编辑打开页面，计算任务中的订单金额（元）
                calcOrderMoney();

                parentIndex = layer.open({
                    title: '设置任务完成',
                    type: 1,
                    area: ['60%', '98%'], //宽高
                    btn: ['保存', '关闭'],
                    yes: function (index, layero) {
                        $("#editForm").submit();
                    },
                    content: $('#editId'),
                    cancel: function (index) {
                        FormUtil.resetForm("editForm");
                        editValidate.resetForm();
                    }
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                top.toastr.error("操作失败");
            }
        });
    } else {
        top.toastr.error("数据异常！");
    }
}
$(function() {
    //订单数量修改后触发
    $("#editId #orderNumOkEdit").blur(function () {
        calcOrderMoney();
    });

    //税收修改后触发
    $("#editId #taxOkEdit").blur(function () {
        calcOrderMoney();
    });
});

function calcOrderMoney(){

    var orderNumNew = parseInt($("#editId #orderNumOkEdit").val());
    var taxNew = parseFloat($("#editId #taxOkEdit").val());
    if(isNaN(taxNew)) {taxNew = 0;}


    var demandType = $("#editId #demandTypeCnOkEdit").text();
    var unitPrice = parseFloat($("#editId #unitPriceOkEdit").text());
    var dollarRate = parseFloat($("#editId #dollarRateOkEdit").text());
    var cycle = parseInt($("#editId #cycleOkEdit").text());
    var frequency = parseInt($("#editId #frequencyOkEdit").text());

    /**
     * 订单金额(元)，每次编辑时需要再计算
     * 刷单订单金额(元)=刷单数量（默认为1）*商品单价*美元汇率*刷单周期*刷单频次
     * 采购订单金额(元)=(订单单价*订单数量+税收)*美元汇率
     */
    if(demandType == '采购'){
        var orderMoney = ((unitPrice * orderNumNew) + taxNew) * dollarRate;
        $("#editId #orderMoneyOkEdit").text(orderMoney.toFixed(2));
        $("#editId #orderMoneyHidOkEdit").val(orderMoney.toFixed(2));

    }else if(demandType == '刷单'){
        //var orderMoney = orderNumNew * unitPrice * dollarRate * cycle * frequency;
        var orderMoney = orderNumNew * unitPrice * dollarRate;
        $("#editId #orderMoneyOkEdit").text(orderMoney.toFixed(2));
        $("#editId #orderMoneyHidOkEdit").val(orderMoney.toFixed(2));
    }
}


//设置运单号
function editPageSN(id) {
    if (Number(id)) {
        //清除原有数据
        $("#orderCodeView").text('');
        $("#taskCodeView").text('');
        $.each($("#editFreightSn td[id$='Edit']"), function (i, area) {
            $(area).text('');
            $(area).val();
        });

        $.ajax({
            url: ctx.path + '/manage/task/viewCustTaskInfo' + ctx.bizSuffix,
            async: true,
            dataType: 'json',
            type: 'POST',
            data: {taskId: id},
            success: function (data) {
                if (data.rs == -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                //对页面属性赋值（要求页面id与map的key值保持一致）
                var cust = dataRet.cust;
                var order = dataRet.order;
                var task = dataRet.task;
                var product = dataRet.product;
                var account = dataRet.account;
                var buyer = dataRet.buyer;

                //如果是刷单，则显示刷单频次和刷单周期；采购显示税收
                if (order.demandType == 2) {
                    $("#demandType_3_EditId").removeAttr("style");
                    $("#taxSnEdit").attr("style", "display:none;");
                } else {
                    $("#demandType_3_EditId").attr("style", "display:none;");
                    $("#taxSnEdit").removeAttr("style");
                }


                for (var key in dataRet) {
                    $("#editFreightSn #" + key + "SnEdit").text(dataRet[key]);
                }
                for (var key in cust) {
                    $("#editFreightSn #" + key + "SnEdit").text(cust[key]);
                }
                for (var key in order) {
                    $("#editFreightSn #" + key + "SnEdit").text(order[key]);
                }
                for (var key in task) {
                    $("#editFreightSn #" + key + "SnEdit").text(task[key]);
                }
                for (var key in product) {
                    $("#editFreightSn #" + key + "SnEdit").text(product[key]);
                }
                for (var key in buyer) {
                    $("#editFreightSn #" + key + "SnEdit").text(buyer[key]);
                }
                for (var key in account) {
                    $("#editFreightSn #" + key + "SnEdit").text(account[key]);
                }

                $("#editFreightSn #cardsCodeSnEdit").val(task.cardsCode);
                $("#editFreightSn #taskIdSnEdit").val(task.taskId);
                $("#editFreightSn #taskStateSnEdit").val("已完成");
                $("#editFreightSn #freightSnSnEdit").val(task.freightSn);
                $("#editFreightSn #custIdSnEdit").val(task.custId);

                parentIndex = layer.open({
                    title: '设置运单号',
                    type: 1,
                    area: ['60%', '98%'], //宽高
                    btn: ['保存', '关闭'],
                    yes: function (index, layero) {
                        $("#editFreightForm").submit();
                    },
                    content: $('#editFreightSn'),
                    cancel: function (index) {
                        FormUtil.resetForm("editFreightForm");
                        editValidate.resetForm();
                    }
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                top.toastr.error("操作失败");
            }
        });
    } else {
        top.toastr.error("数据异常！");
    }
}

function openSettingOkPage() {
    // 页面层
    index = layer.open({
        type: 1,
        title: '更改报告任务状态',
        move: false,
        area: ['420px', '340px'], // 宽高
        content: $('#importSettingOk'),
        btn: ['保存', '关闭'],
        yes: function (index, layero) {
            $("#importSettingOkForm").submit();
            if($("#importSettingOkForm").valid()){
                loading();
            }
        },
        cancel: function (index) {
            FormUtil.resetForm("importSettingOkForm");
            importSettingOkValidate.resetForm();
        }
    });
}


function loading(){
    loadingData = layer.load(0, {
        shade: 0.3
    });
}

function openSettingSnPage() {
    // 页面层
    index = layer.open({
        type: 1,
        title: '更改报告任务状态',
        move: false,
        area: ['420px', '340px'], // 宽高
        content: $('#importSettingSn'),
        btn: ['保存', '关闭'],
        yes: function (index, layero) {
            $("#importSettingSnForm").submit();
            if($("#importSettingSnForm").valid()){
                loading();
            }
        },
        cancel: function (index) {
            FormUtil.resetForm("importSettingOkForm");
            importSettingSnValidate.resetForm();
        }
    });
}

function refundPage(id) {
    if (Number(id)) {
        //清除原有数据
        $.each($("#refundId td[id$='Edit']"), function (i, area) {
            $(area).text('');
            $(area).val();
        });

        $.ajax({
            url: ctx.path + '/manage/task/viewCustTaskInfo' + ctx.bizSuffix,
            async: true,
            dataType: 'json',
            type: 'POST',
            data: {taskId: id},
            success: function (data) {
                if (data.rs == -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                //对页面属性赋值（要求页面id与map的key值保持一致）
                var cust = dataRet.cust;
                var order = dataRet.order;
                var task = dataRet.task;
                var product = dataRet.product;
                var account = dataRet.account;
                var buyer = dataRet.buyer;

                //如果是刷单，则显示刷单频次和刷单周期；采购显示税收
                if (order.demandType == 2) {
                    $("#demandType_4_EditId").removeAttr("style");
                    $("#taxRefundEdit").attr("style", "display:none;");
                } else {
                    $("#demandType_4_EditId").attr("style", "display:none;");
                    $("#taxRefundEdit").removeAttr("style");
                }

                for (var key in dataRet) {
                    $("#refundId #" + key + "RefundEdit").text(dataRet[key]);
                }
                for (var key in cust) {
                    $("#refundId #" + key + "RefundEdit").text(cust[key]);
                }
                for (var key in order) {
                    $("#refundId #" + key + "RefundEdit").text(order[key]);
                }
                for (var key in task) {
                    $("#refundId #" + key + "RefundEdit").text(task[key]);
                }
                for (var key in product) {
                    $("#refundId #" + key + "RefundEdit").text(product[key]);
                }
                for (var key in buyer) {
                    $("#refundId #" + key + "RefundEdit").text(buyer[key]);
                }
                for (var key in account) {
                    $("#refundId #" + key + "RefundEdit").text(account[key]);
                }

                $("#refundId #taskIdRefundEdit").val(task.taskId);
                $("#refundId #taskStateRefundEdit").text("已完成");
                $("#refundId #returnMoneyRefundEdit").val(task.returnMoney);

                parentIndex = layer.open({
                    title: '退款',
                    type: 1,
                    area: ['60%', '98%'], //宽高
                    btn: ['保存', '关闭'],
                    yes: function (index, layero) {
                        $("#refundForm").submit();
                    },
                    content: $('#refundId'),
                    cancel: function (index) {
                        FormUtil.resetForm("refundForm");
                        refundValidate.resetForm();
                    }
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                top.toastr.error("操作失败");
            }
        });
    } else {
        top.toastr.error("数据异常！");
    }
}