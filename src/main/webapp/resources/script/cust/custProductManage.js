var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var editValidate;
$(function() {
    //编辑校验
    editValidate = $("#editForm").validate({
        rules : {
            platformType : {
                required : true
            },
            accountsName : {
                required : true
            },
            itemId : {
                required : true
            }
        },
        messages : {
            platformType : {
                required : "请选择电商平台！"
            },
            accountsName : {
                required : "请输入卖家账号(店铺名称)！"
            },
            itemId : {
                required : "请输入产品ID！"
            }
        },
        submitHandler : function(form) {
            $(form).ajaxSubmit({
                //表单提交成功后的回调
                success : function(responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        top.toastr.success("编辑卖家产品信息【" + $("#editForm").find("#custCode").text() + "】成功！");
                        FormUtil.resetForm("editForm");
                        doSearch();
                        layer.close(index); //再执行关闭
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
            url : ctx.path + '/manage/custProduct/viewCustProduct' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {productId : id},
            success : function(data) {
                if (data.rs== -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#viewForm #"+ key).html(dataRet[key]);
                }
                parentIndex = layer.open({
                    title : '查看卖家产品信息',
                    type : 1,
                    area : [ '500px', '450px' ], //宽高
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
            url : ctx.path + '/manage/custProduct/viewCustProduct' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {
                productId : id
            },
            success : function(data) {
                if (data.rs == -1) {
                    top.toastr.error("操作失败");
                    return false;
                }
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    if($("#editForm #"+ key).is("span")){
                        $("#editForm #"+ key).html(dataRet[key]);
                    }
                    else{
                        $("#editForm").find("#"+key).val(dataRet[key]);
                    }
                }
                //$("#editForm").fill(dataRet);//表单数据填充
                //$("#editForm").find("#custId").val(id);

                parentIndex = layer.open({
                    title : '编辑卖家产品信息',
                    type : 1,
                    area : [ '500px', '450px' ], //宽高
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

function viewCustPage(id) {
    if (Number(id)) {
        $.ajax({
            url : ctx.path + '/manage/custProduct/viewCustProductByCustId' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {productId : id},
            success : function(data) {
                if (data.rs== -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#viewForm #"+ key).html(dataRet[key]);
                }
                parentIndex = layer.open({
                    title : '查看卖家产品信息2',
                    type : 1,
                    area : [ '40%', '50%' ], //宽高
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

