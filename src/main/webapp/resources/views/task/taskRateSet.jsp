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
            var user_ids="${custTaskSet.workdayUsers}";
            var user_rates="${custTaskSet.workdayUsersRate}";
            if(user_ids!=null&&user_ids!=""){
                //用户id数组
                var ids_arr = user_ids.split(',');
                //用户比列数组
                var rates_arr = user_rates.split(',');
                for(var i in ids_arr){
                    $('input[name="user_id"]').each(function(){
                        var user_id=$(this).val();
                        if(user_id==ids_arr[i]){
                            //选中处理
                            $(this).prop("checked", true);
                            //对分配比列进行设置
                            $(this).parent("td").next().find("input").val(rates_arr[i]);
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
            var user_id="",user_name="",user_rate="",rate="",falg=false,total_rate=0;
            $('input[name="user_id"]:checked').each(function(){
                rate = $(this).parent("td").next().find("input").val();
                if(rate!=null && rate!="" && !reg.test(rate)){
                    falg = true ;
                    return;
				}
                //勾选责任人，必须要有比例
                if($(this).val() > 0 && (rate == null || rate == "")){
					falg = true ;
					return;
				}
                user_id=user_id+$(this).val()+",";
                user_name=user_name+$(this).next().html()+",";
                user_rate=user_rate + rate + ",";
                total_rate = total_rate + Number(rate);
            });
            if(falg){
                //alert("请输入正确的分配比例！");
                top.toastr.error("请输入正确的分配比例！");
                return ;
			}
			if(total_rate != 100){
                top.toastr.error("分配比例总和不等于100！");
                return ;
            }
            if(user_id.length<=0){
                //alert("您没有选择！");
                top.toastr.error("请勾选责任人！");
                return;
            }else{
                parent.setTaskList(user_id,user_name,user_rate,1);
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
					<th class="taskweekend" style="font-size: 14px;">负责人</th>
					<th class="taskweekend" style="font-size: 14px;">分配比例(%)</th>
				</tr>
				</thead>

				<tbody class="taskweekend">
				<c:if test="${empty requestScope.userList}">
					<tr>
						<td align="center" colspan="2">对不起，没有找到相关数据！</td>
					</tr>
				</c:if>
				<c:forEach items="${requestScope.userList}" var="user" varStatus="staus">
				<tr>
					<td class="task_set">
						<input type="checkbox" id="user_id_${user.userId}" name="user_id" value="${user.userId}">
						<label title="${user.userName}" for="user_id_${user.userId}">${user.userName}</label>
					</td>
					<td>
						<input style="font-size: 13px;" type="text" id="user_rate_${user.userId}" name="user_rate" value="" class="taskalloction" />
					</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>

