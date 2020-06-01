var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var verifyValidate;


$(function() {
    //校验新增表单
    verifyValidate = $("#verifyForm").validate(
        {
            rules: {

            },
            messages: {

            },
            submitHandler: function (form) {
                $(form).ajaxSubmit(
                    {
                        //表单提交成功后的回调
                        success: function (responseText, statusText,
                                           xhr, $form) {
                            if (responseText.rs > 0) {
                                top.toastr.success("审核成功！");
                                FormUtil.resetForm("verifyForm");
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
            url : ctx.path + '/manage/finance/viewOrderInfoList' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {orderId : id},
            success : function(data) {
                if (data.rs == -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                var imagePath = ctx.path + dataRet['payImage'];
                //初始化，显示图片
                var cuploadUpdate = new Cupload ({
                    ele	 : '#payImageView',
                    num  : 1,
                    name : 'file',
                    data : [imagePath],
                    download : true
                });
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    if(key === 'payImage'){
                        continue;
                    }else{
                        $("#viewForm #"+ key+"View").html(dataRet[key]);
                    }
                }
                parentIndex = layer.open({
                    title : '查看账户充值审核记录',
                    type : 1,
                    area : [ '550px', '400px' ], //宽高
                    content : $('#viewId'),
                    btn : [ '关闭' ],
                    yes: function(index){
                        $('#payImageView').empty();
                        layer.close(index);
                    },
                    cancel : function(index) {
                        $('#payImageView').empty();
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

function verifyPage(id) {
    if (Number(id)) {
        $.ajax({
            url : ctx.path + '/manage/finance/viewOrderInfoList' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {orderId : id},
            success : function(data) {
                if (data.rs == -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                var imagePath = ctx.path + dataRet['payImage'];
                //初始化，显示图片
                var cuploadUpdate = new Cupload ({
                    ele	 : '#payImageEdit',
                    num  : 1,
                    name : 'file',
                    data : [imagePath],
                    download : true
                });
                $("#orderCodeRev").val(dataRet['orderCode']);
                $("#orderIdEdit").val(dataRet['orderId']);
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    if(key === 'payImage'){
                        continue;
                    }else{
                        $("#verifyForm #"+ key+"Edit").html(dataRet[key]);
                    }
                }
                parentIndex = layer.open({
                    title : '客户充值审核',
                    type : 1,
                    area : [ '550px', '400px' ], //宽高
                    content : $('#verifyInfo'),
                    btn : [ '保存' , '关闭' ],
                    yes : function(index, layero) {
                        $("#verifyForm").submit();
                    },
                    close: function(index, layero){
                        $('#payImageEdit').empty();
                        layer.close(index);
                    },
                    cancel : function(index) {
                        $('#payImageEdit').empty();
                        FormUtil.resetForm("verifyForm");
                        verifyValidate.resetForm();
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