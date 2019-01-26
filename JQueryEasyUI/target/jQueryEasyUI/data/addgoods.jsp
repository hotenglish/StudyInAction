<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>增加商品</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">

    <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../js/JQuery-formui.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>

    <style type='text/css'>
        body {
            margin: 0px;
            padding: 0px;
        }
    </style>
</head>
<body>
<!-- 查询条件 -->
<div id="formdata" class="demo-info">
    <a id="btnAdd" class="easyui-linkbutton" data-options="iconCls:'icon-add'">查询</a>
    <a id="btnAddGoods" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
</div>
<div>
    <form id="addgoods" method="post">
        <label for="CITY">城市:</label>
        <input type="text" id="CITY" name="CITY"/>
        <label for="GOODS">产品:</label>
        <input type="text" id="GOODS" name="GOODS"/>
        <label for="AMOUNT">数量:</label>
        <input type="text" id="AMOUNT" name="AMOUNT"/>
        <label for="RECEIVER">接收人:</label>
        <input type="text" id="RECEIVER" name="RECEIVER"/>
        <label for="SENDDATE">发送时间:</label>
        <input type="text" id="SENDDATE" name="SENDDATE" onclick="WdatePicker();"/>
        <label for="TAKEDATE">接收时间:</label>
        <input type="text" id="TAKEDATE" name="TAKEDATE" onclick="WdatePicker();"/>
        <label for="REMARK">备注:</label>
        <input type="text" id="REMARK" name="REMARK"/>
        <input type="hidden" id="flag" name="flag" value="add"/>
    </form>
</div>

<!-- 显示结果 -->
<table id="datagrid"></table>
<script type="text/javascript">
    $(function () {
        //查询按钮
        $("#btnQuery").click(function () {
            binddatagrid();
        });

        //随窗体缩放
        $(window).resize(function () {
            $("#datagrib").datagrid("resize");
        });

        //绑定数据列表
        function binddatagrid(condition) {
            //获取查询条件
            var condition = $("formdata").toJsonString();
            condition = escape(encodeURIComponent(condition));

            //ajax查询数据
            var url = "SendAmount.action";
            if (condition && condition.length > 0) {
                url += "?condition=" + condition;
                $("#datagrid").datagrid({
                    nowrap: false,
                    fitColumns: true,
                    pageList: [20, 50, 100],
                    singleSelect: true,
                    collapsible: false,
                    url: url,
                    frozenColumns: [[{
                        field: 'ck',
                        checkedbox: true
                    }]],
                    columns: [[
                        {
                            field: 'CITY',
                            title: '城市',
                            width: 100,
                            sortable: true
                        }, {
                            field: 'GOODS',
                            title: '产品',
                            width: 100,
                            sortable: true
                        }, {
                            field: 'AMOUNT',
                            title: '数量',
                            width: 100,
                            rowspan: 2
                        }, {
                            field: 'RECEIVER',
                            field: 'RECEIVER',
                            title: '接收人',
                            width: 100,
                            sortable: true,
                            rowspan: 2
                        },
                        {
                            field: 'SENDDATE',
                            title: '发送时间',
                            width: 150,
                            sortable: true,
                            rowspan: 2
                        },
                        {
                            field: 'TAKEDATE',
                            title: '接收时间',
                            width: 150,
                            sortable: true,
                            rowspan: 2
                        },
                        {
                            field: 'REMARK',
                            title: '备注',
                            width: 100,
                            sortable: true,
                            rowspan: 2
                        }
                    ]],
                    pagination: true,
                    rownumbers: true
                });

                $("#datagrid").datagrid("getPager").pagination({
                    beforePageText: '第',
                    afterPageText: '页    共 {pages} 页',
                    displayMsg: '当前显示从{from}到{to}共{total}记录',
                    onBeforeRefresh: function (pageNumber, pageSize) {
                        $("#datagrid").datagrid("clearSelections");
                    }
                });
            }
        }
    });

    $("#btnSaveFile").click(function () {
        $.messager.progress({
            title: "请等待",
            msg: '数据处理中...'
        });
        var condition = $("#formdata").toJsonString();
        var exportFlag = "yes";
        condition = escape(encodeURIComponent(condition));
        var url = 'SendAmount.action?condition=' + condition + '&exportflag=' + exportflag;
        $.ajax({
            type: "post",
            url: url,
            error: function (event, request, settings) {
                $.messager.alert("提示消息", "请求失败", "info");
            },
            success: function (data) {
                $.messager.progress('close');
                var name = data.rows;
                window.location.href = "FileDownload.action?number=1&fileName=" + name;
            }
        });
    });

</script>
</body>
</html>
