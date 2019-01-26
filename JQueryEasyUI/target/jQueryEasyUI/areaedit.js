(function ($) {
    $(function () {
        //获取参数
        var icode = JUDGE.getURLParameter("icode");
        //alert(icode);
        //icode
        if (!JUDGE.isNull(icode)) {
            var url = "AREAFindByUUID.action?maindatauuid=" + icode;
            $.ajax({
                type: "post",
                url: url,
                contentType: "text/html",
                error: function (event, request, settings) {
                    $.messager.alert("提示消息", "请求失败!", "info");
                },
                success: function (data) {
                    $("#maindata").fromJsonString(JSON.stringify(data));
                }
            });
        }

        $("#btnCancel").click(function () {
            window.parent.$("#wedit").window("close");
        });

        $("#btnSave").click(function () {

            if (!$("#maindata").form("validate")) {

                //表单验证
                $.messager.alert("提示消息", "信息填写不完整!", "info")
                return;
            }

            //主表
            var maindata = $("#maindata").toJsonString();

            $.ajax({
                type: "post",
                url: "AREASave.action",
                dataType: "json",
                data: maindata,
                contentType: "text/html",
                error: function (event, request, settings) {
                    //请求失败时调用函数。
                    $.messager.alert("提示消息", "请求失败!", "info");
                },
                success: function (data) {
                    if (data.returncount > 0) {
                        //自身主键刷新，不要出现重复保存的情况
                        if (data.savetype == "insert") {
                            $.messager.alert("提示消息", "新增保存成功!", "info", function () {
                                window.parent.$("#dataview").datagrid("reload");
                                window.parent.$("#wedit").window("close");
                            });
                        } else {
                            $.messager.alert("提示消息", "编辑保存成功!", "info", function () {
                                window.parent.$("#dataview").datagrid("reload");
                                window.parent.$("#wedit").window("close");
                            });
                        }
                    } else {
                        $.messager.alert("提示消息", "保存失败!", "info");
                    }
                }
            });
        });
    });
})(window.jQuery);