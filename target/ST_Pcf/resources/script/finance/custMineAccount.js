var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var editValidate;
$(function() {
    //校验充值申请
    rechargeAddValidate = $("#addCustRechargeForm").validate(
        {
            rules: {
                payTime : {
                    required : true
                },
                // file : {
                //     required : true
                // },
                rechargeMoney : {
                    required : true,
                    money: true
                },
                payType : {
                    required : true
                }
            },
            messages: {
                payTime : {
                    required : "请选择充值日期！"
                },
                // file : {
                //     required : "请上传支付凭证！"
                // },
                rechargeMoney : {
                    required : "请输入金额！",
                    money: "请输入正确的金额！"
                },
                payType : {
                    required : "请选择充值方式！"
                }
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit(
                    {
                        //表单提交成功后的回调
                        success: function (responseText, statusText,
                                           xhr, $form) {
                            if (responseText.rs > 0) {
                                top.toastr.success("新增充值申请成功！");
                                FormUtil.resetForm("addBannerForm");
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
            url : ctx.path + '/manage/finance/viewAccount' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            /*data : {accountId : id},*/
            success : function(data) {
                if (data.rs== -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#viewForm #"+ key +"View").html(dataRet[key]);
                }
                parentIndex = layer.open({
                    title : '查看账户信息',
                    type : 1,
                    area : [ '450px', '400px' ], //宽高
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


//新增充值
function openAddPage() {
    var cuploadCreate = new Cupload({
        ele: '#payImageAdd',
        num: 1,
        name: 'file'
    });
    index = layer.open({
        type : 1,
        title : '充值申请',
        move : false,
        area : [ '520px', '400px' ], // 宽高
        content : $('#addCustRecharge'),
        btn : [ '保存', '关闭' ],
        yes : function(index, layero) {
            $("#addCustRechargeForm").submit();
        },
        close: function(index, layero){
            $('#payImageAdd').empty();
            layer.close(index);
        },
        cancel : function(index) {
            $('#payImageAdd').empty();
            FormUtil.resetForm("addCustRechargeForm");
            rechargeAddValidate.resetForm();
        }
    });
}