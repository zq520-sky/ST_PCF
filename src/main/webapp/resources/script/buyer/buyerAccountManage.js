var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var editValidate;
var importAccountValidate;
$(function() {
    //编辑校验
    editValidate = $("#editForm").validate({
        rules : {
           /* address : {
                required : true
            },
            city : {
                required : true
            },
            province : {
                required : true
            },
            postcode : {
                required : true
            },*/
            accountMoneyHis : {
                required : true,
                money:true
            }
        },
        messages : {
            /*address : {
                required : "请输入地址！"
            },
            city : {
                required : "请输入城市！"
            },
            province : {
                required : "请输入州！"
            },
            postcode : {
                required : "请输入邮编！"
            },*/
            accountMoneyHis : {
                required : "请输入历史余额！",
                money:"请输入正确的历史余额！"
            }
        },
        submitHandler : function(form) {
            $(form).ajaxSubmit({
                //表单提交成功后的回调
                success : function(responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        top.toastr.success("编辑买家账号信息【" + $("#editForm").find("#buyerCodeEdit").text() + "】成功！");
                        FormUtil.resetForm("editForm");
                        doSearch();
                        layer.close(index); //再执行关闭
                    }
                }
            });
        }
    });


    //导入回调
    importAccountValidate = $("#importBuyerAccountForm").validate({
        rules : {
            file : {
                required : true
            }
        },
        messages : {
            file : {
                required : "请选择导入的文件！"
            }
        },
        submitHandler : function(form) {
            $(form).ajaxSubmit({
                //表单提交成功后的回调
                success : function(responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        if(responseText.data.status > 0){
                            top.toastr.success(responseText.data.msg);
                            FormUtil.resetForm("importBuyerAccountForm");
                            doSearch();
                            layer.close(index); //再执行关闭
                        }
                        else{
                            top.toastr.error(responseText.data.msg);
                        }
                        layer.close(loadingData);
                    }
                }
            });
        }
    });

});

//执行查询
function doSearch() {
	$("#pageForm").submit();
	top.progressbar(frameId);
}


//打开查看页面
function viewPage(id) {
    if (Number(id)) {
        $.ajax({
            url : ctx.path + '/manage/buyerAccount/viewAccount' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {recordId : id},
            success : function(data) {
                if (data.rs== -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#viewId #"+ key+"View").html(dataRet[key]);
                }
                parentIndex = layer.open({
                    title : '查看买家账号',
                    type : 1,
                    area : [ '60%', '85%' ], //宽高
                    content : $('#viewId'),
                    btn : [ '关闭' ],
                    cancel : function(index) {
                    }
                });
            },
            error : function(jqXHR, textStatus, errorThrown) {
                top.toastr.error("操作失败");
            }
        });
    } else {
        top.toastr.error("数据异常！");
    }
}

//打开编辑页面
function editPage(id) {
    if (Number(id)) {
        $.ajax({
            url : ctx.path + '/manage/buyerAccount/viewAccount' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {
                recordId : id
            },
            success : function(data) {
                if (data.rs == -1) {
                    top.toastr.error("操作失败");
                    return false;
                }
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#editId #"+ key+"Edit").html(dataRet[key]);
                }
                $("#editForm #recordIdHidEdit").val(dataRet.recordId);
                $("#editForm #buyerIdHidEdit").val(dataRet.buyerId);
                $("#editForm #buyerCodeHidEdit").val(dataRet.buyerCode);
                $("#editForm #addressEdit").val(dataRet.address);
                $("#editForm #cityEdit").val(dataRet.city);
                $("#editForm #provinceEdit").val(dataRet.province);
                $("#editForm #postcodeEdit").val(dataRet.postcode);
                $("#editForm #accountMoneyHisEdit").val(dataRet.accountMoneyHis);
                $("#editForm #creditCardEdit").val(dataRet.creditCard);
                $("#editForm #payPalEdit").val(dataRet.payPal);

                parentIndex = layer.open({
                    title : '编辑买家账号信息',
                    type : 1,
                    area : [ '60%', '85%' ], //宽高
                    btn : [ '保存', '关闭' ],
                    yes : function(index, layero) {
                        $("#editForm").submit();
                    },
                    content : $('#editId'),
                    cancel : function(index) {
                        FormUtil.resetForm("editForm");
                        editValidate.resetForm();
                    }
                });
            },
            error : function(jqXHR, textStatus, errorThrown) {
                top.toastr.error("操作失败");
            }
        });

    } else {
        top.toastr.error("数据异常！");

    }

}

//禁用/启用
function setPage(id, flag, name) {
    //操作禁用和启用的时候正好相反
    var oprStr = flag == 0 ? "禁用" : "启用";
    var opr = flag == 0 ? 1 : 0;
    if (Number(id)) {
        var msg = "确定"+oprStr+"买家账号代码【" + name + "】吗？";
        layer.confirm(msg, {
            icon : 3,
            title : "提示信息"
        }, function(index) {
            $.get(ctx.path + "/manage/buyerAccount/setAccount"+ ctx.bizSuffix + "?buyerId=" + id + "&isForbid=" + opr + "&buyerCode=" + name, function(result) {
                if (result.rs == 1) {
                    top.toastr.success(oprStr+"买家账号代码【" + name + "】成功！");
                    doSearch();
                    //刷新表单
                }
            }, "json");
            layer.close(index);
        });
    } else {
        top.toastr.error("数据异常！");
    }
}


//打开导入页面
function openImportPage() {
    index = layer.open({
        type : 1,
        title : '导入买家账号',
        move : false,
        area : [ '520px', '400px' ], // 宽高
        content : $('#importBuyerAccount'),
        btn : [ '保存', '关闭' ],
        yes : function(index, layero) {
            $("#importBuyerAccountForm").submit();
            if($("#importBuyerAccountForm").valid()){
                loading();
            }
        },
        close: function(index, layero){
            layer.close(index);
        }
    });
}
var loadingData;

function loading(){
    loadingData = layer.load(0, {
        shade: 0.3
    });
}