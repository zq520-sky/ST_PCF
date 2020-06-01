<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
    <style type="text/css">
        .call_wrap{
            width: 100%;
            height: 100%;
            padding: 5px;
        }
        .call_wrap .call_right{
            width: 100%;
            height: 100%;
            top: 10px;
            border: 1px solid #dfe5e7;
            background: #fff;
            overflow: auto;
        }
        .call_right_text{
            color: #dbdbdb;
            position: relative;
            top: 35%;
            text-align: center;
            font-size: 30px;
        }
    </style>
</head>
<body>
    <div class="call_wrap">
        <div class="call_right" >
            <div class="call_right_text empty_tips">
                <div>
                    <img src="${ctx.path}/resources/images/callcenter_img.png" alt="img"/>
                </div>
                <div>欢迎使用采购刷单业务管理系统</div>
            </div>
        </div>
    </div>
</body>
</html>

