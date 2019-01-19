(function ($) {
    $(function () {
        //获取参数 角色id
        var icode = JUDGE.getURLParameter("icode");

        //加载这个角色已有权限到树
        $("#tt2").tree({
            url: 'PERMISSIONFindByUUID.action?maindatauuid=' + icode
        });

        //获取选中的节点
        function getChecked() {
            var nodes = $("tt2").tree("getChecked");
            var s = '';
            for (var i = 0; i < nodes.length; i++) {
                if (s != '') {
                    s += ',';
                }
                s += nodes[i].text;
            }
            alert(s);
        }

        //保存
        $("#btnSave").click(function () {

            //获取选中的菜单（多个中间用逗号隔开）
            var nodes = $("#tt2").tree('getChecked');
            var models = '';
            for (var i = 0; i < nodes.length; i++) {
                if (models != '') {
                    models += ',';
                }
                models += nodes[i].text;
            }

            //获取参数中的角色icode
            var roleicode = JUDGE.getURLParameter("icode");

            //拼装数据
            var maindata = "{'ROLEICODE':'" + roleicode + "','MODELS':'" + models + "'}";

            //保存
            $.ajax({
                type: "post",
                url: "PERMISSIONSave.action",
                dataType: "json",
                data: maindata,
                contentType: "text/html",
                error: function (event, request, settings) {
                    //请求失败时调用函数。
                    $.messager.alert("提示消息", "请求失败!", "info");
                },
                success: function (data) {
                    $.messager.alert("提示消息", "保存成功!", "info", function () {
                        window.parent.$("#dataview").datagrid("reload");
                        window.parent.$("#wedit").window("close");
                    });
                }
            });
        });

        //取消
        $("#btnCancel").click(function () {
            window.parent.$("#wedit").window("close");
        });
    });
})(window.jQuery);