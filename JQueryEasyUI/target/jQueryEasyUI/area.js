(function ($) {
    $(function () {
        $(function () {
            $("dataview").treegrid({
                nowrap: false,
                rownumber: true,
                animate: true,
                collapsible: true,
                url: 'AREAFindGrid.action',
                idField: 'ICODE',
                treeField: 'NAME',
                columns: [[{
                    field: 'NAME',
                    title: '名称',
                    width: 200
                }, {
                    field: 'NO',
                    title: '编码',
                    width: 50
                }, {
                    field: 'SPELLNO',
                    title: '拼音码',
                    width: 50
                }, {
                    field: 'CUSTOMNO',
                    title: '自定义码',
                    width: 50
                }, {
                    field: 'TEL',
                    title: '电话',
                    width: 50
                }, {
                    field: 'ADDRESS',
                    title: '地址',
                    width: 250
                }, {
                    field: 'ZIP',
                    title: '邮编',
                    width: 50
                }, {
                    field: '操作',
                    title: '操作',
                    width: 50,
                    // 添加超级链，并将来文号作为参数传入
                    formatter: function (value, row, index) {
                        var s = '<span  onclick="dataEdit(\'' + row.ICODE + '\');" style="color:blue;">编辑</span> '
                            + '| <span  onclick="dataDelete(\'' + row.ICODE + '\');" style="color:blue;">删除</span>';
                        return s;
                    }
                }]],
                toolbar: [
                    {
                        id: 'btnadd',
                        text: '新增',
                        iconCls: 'icon-add',
                        handler: function () {
                            $("#iframemain").attr("src", "areaedit.jsp");
                            $("#wedit").window("open");
                        }
                    }, {
                        id: 'btncut',
                        text: '刷新',
                        iconCls: 'icon-reload',
                        handler: function () {
                            //$('#btnsave').linkbutton('enable');
                            //alert('cut')
                            //getSelected();
                            $("#dataview").treegrid("reload");
                        }
                    }]
            });
        });
    });
})(window.jQuery);


function dataEdit(icode) {
    $("#iframemain").attr("src", "areaedit.jsp?icode=" + icode);
    $("wedit").window("open");
}

function dataDelete(icode) {
    $.messager.confirm('询问', '确定要删除吗?', function (r) {
        if (r) {
            $.ajax({
                type: "post",
                url: "AREADelete.action?maindatauuid=" + icode,
                contentType: "text/html",
                error: function () {
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