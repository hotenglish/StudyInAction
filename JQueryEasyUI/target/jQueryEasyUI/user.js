(function ($) {

    $(function () {
        $(function () {
            $("dataview").treegrid({
                nowrap: false,
                rownumbers: true,
                animate: true,
                collapsible: true,
                fitcolumns: true,
                url: 'USERFindGrid.action',
                idField: 'ICODE',
                treeField: 'NAME',
                columns: [[
                    {
                        field: 'NAME',
                        title: '姓名',
                        width: 50
                    }, {
                        field: 'LOGINNAME',
                        title: '登陆账号',
                        width: 50
                    }, {
                        field: 'PHONE',
                        title: '联系电话',
                        width: 50
                    }, {
                        field: 'EMAIL',
                        title: '电子邮箱',
                        width: 50
                    }, {
                        field: '操作',
                        title: '操作',
                        width: 50,
                        // 添加超级链，并将来文号作为参数传入
                        formatter: function (value, row, index) {
                            if (row.ICONCLS && row.ICONCLS.lenght > 0) {
                            } else {
                                var s = '<span  onclick="dataEdit(\'' + row.ICODE + '\');" style="color:blue;">编辑</span> '
                                    + '| <span  onclick="dataDelete(\'' + row.ICODE + '\');" style="color:blue;">删除</span>'
                                    + '| <span  onclick="dataSetRole(\'' + row.ICODE + '\');" style="color:blue;">设置角色</span>';
                                return s;
                            }
                        }
                    }
                ]],
                toolbars: [
                    {
                        id: 'btnadd',
                        text: '新增',
                        iconCls: 'icon-add',
                        handler: function () {
                            $("#iframemain").attr("src", "useredit.jsp");
                            $("#wedit").window("close");
                        }
                    }, {
                        id: 'btncut',
                        text: '刷新',
                        iconCls: 'icon-reload',
                        iconCls: function () {
                            //$('#btnsave').linkbutton('enable');
                            //alert('cut')
                            //getSelected();
                            $("#dataview").treegrid("reload");
                        }
                    }
                ]
            });
        });
    });

})(window.jQuery);

function dataSetRole(icode) {
    $("#iframemain").attr("src", "userroleedit.jsp?icode=" + icode);
    $("#wedit").window("open", {title: "用户角色"});
}

function dataEdit(icode) {
    $("#iframemain").attr("src", "useredit.jsp?icode=" + icode);
    $("#wedit").window("open");
}

function dataDelete(icode) {
    $.messager.confirm('询问', '确定要删除吗?', function (r) {
        if (r) {
            $.ajax({
                type: "post",
                url: "USERDelete.action?maindatauuid=" + icode,
                contentType: "text/html",
                error: function (event, request, settings) {
                    //请求失败时调用函数。
                    $.messager.alert("提示消息", "请求失败!", "info");
                },
                success: function (data) {
                    //请求成功后回调函数。
                    //$.messager.alert('操作提示', '数据删除成功!');
                    //clearSelect('datagrid');
                    //flashTable("datagrid");
                    $("#dataview").treegrid('reload');
                }
            });
        }
    });
}