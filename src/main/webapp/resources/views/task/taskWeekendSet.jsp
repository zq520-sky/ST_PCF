<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="${ctx.path}/resources/components/bootstrap/bootstrap-theme.min.css?v=2.2" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx.path}/resources/css/task/taskSetManage.css?v=1.2" rel="stylesheet" />
		<script src="${ctx.path}/resources/script/task/taskSetManage.js?v=${ctx.version}"></script>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>

	</head>
	<script type="text/javascript">
        $().ready(function() {
            //获取已选中的用户信息
            var user_ids="${custTaskSet.weekendUsers}";
            if(user_ids!=null&&user_ids!=""){
                //用户id数组
                var ids_arr = user_ids.split(',');
                for(var i in ids_arr){
                    $('input[name="user_id"]').each(function(){
                        var user_id=$(this).val();
                        if(user_id==ids_arr[i]){
                            //选中处理
                            $(this).prop("checked", true);
                        }
                    });
                }
			}

        });

        /**
		 * 将参数设置到父页面
         */
        function setUser(){
        	var reg = /^\d+(\.\d+)?$/ ;
            var user_id="",user_name="",user_rate="",rate="",falg=false ;
            $('input[name="user_id"]:checked').each(function(){

                user_id=user_id+$(this).val()+",";
                user_name=user_name+$(this).next().html()+",";
            });
            if(user_id.length<=0){
                //alert("您没有选择！");
                top.toastr.error("请勾选责任人！");
                return;
            }else{
                parent.setTaskList(user_id,user_name,null,2);
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.close(index);
            }
        }
	</script>
	<body>
		<div id="taskRateSet" style="overflow-y: scroll;height: 100%;">
			<table class="table table-hover" >
				<thead>
				<tr >
					<th class="taskweek" style="text-align: left;padding-left: 35px;font-size: 14px;">负责人</th>
				</tr>
				</thead>

				<tbody class="taskweekend" style="text-align: left;">
				<c:if test="${empty requestScope.userList}">
					<tr>
						<td align="center" colspan="2">对不起，没有找到相关数据！</td>
					</tr>
				</c:if>
				<c:forEach items="${requestScope.userList}" var="user" varStatus="staus">
				<tr>
					<td style="padding: 8px 20px;display: flex;" class="task_setweek">
						<input type="checkbox" id="user_id_${user.userId}" name="user_id" value="${user.userId}" style="">
						<label title="${user.userName}" for="user_id_${user.userId}" style="width: 100%;padding-left: 5px;">${user.userName}</label>
					</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>

