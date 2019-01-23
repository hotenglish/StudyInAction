(function ($) {
    $(function () {
        //获取参数
        var icode = COMM.getURLParameter("icode");
        //alert(icode);
        //icode
        if (!COMM.isNull(icode)) {
            var url = "MODELFindByUUID.action?maindatauuid=" + icode;
            $.ajax({
                type: "post",
                url: url,
                contentType: "text/html",
                error: function (event, reuest, settings) {
                    $.messager.alert("提示消息", "请求失败!", "info");
                },
                success: function (data) {
                    $("#maindata").fromJsonString(JSON.stringify(data.maindata));
                }
            });
        }

        $("#btnCancel").click(function () {
            window.parent.$("#wedit").window('close');
        });

        $("#btnSave").click(function () {
            //表单验证
            if (!$("#maindata").form("validate")) {
                $.messager.alert("提示消息", "信息填写不完整!", "info");
                return;
            }

            //主表
            var mainData = $("#maindata").toJsonString();
            //alert(maindata);

            $.ajax({
                type: "post",
                url: "MODELSave.action",
                dataType: "json",
                data: mainData,
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
                                window.parent.$("#dataview").treegrid("reload");
                                window.parent.$("#wedit").window("close");
                            });
                        } else {
                            $.messager.alert("提示消息", "编辑保存成功!", "info", function () {
                                window.parent.$("#dataview").treegrid("reload");
                                window.parent.$("#wedit").treegrid("close");
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