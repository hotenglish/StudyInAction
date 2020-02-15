package com.elson.hadoop.microblog.main;

import com.elson.hadoop.microblog.constant.Constant;
import com.elson.hadoop.microblog.utils.WeiBoUtil;

import java.io.IOException;

public class Weibo {


    public static void init() throws IOException {

        //创建命名空间
        WeiBoUtil.createNamespace(Constant.NAMESPACE);

        //创建内容表
        WeiBoUtil.createTable(Constant.CONTENT, 1, "info");


        //创建用户关系表
        WeiBoUtil.createTable(Constant.RELATIONS, 1, "attends", "fans");


        //创建收件箱表（多版本）
        WeiBoUtil.createTable(Constant.INBOX, 2, "info");

    }

    public static void main(String[] args) throws IOException {

//        //测试
//        init();


        //1001，1002发布微博
//        WeiBoUtil.createDate("1001", "Today is a fine day!!!");
//        WeiBoUtil.createDate("1002", "Today is raining!!!");

        //1001关注1002和1003
//        WeiBoUtil.addAttend("1001", "1002", "1003");


        //获取1001初始化页面信息
//        WeiBoUtil.getInit("1001");


        //1003发布微博
        WeiBoUtil.createDate("1003", "How are you?");
        WeiBoUtil.createDate("1003", "Outstanding！");
        WeiBoUtil.createDate("1003", "Study hard！");

        System.out.println("*****************************************");

        //获取1001初始化页面信息
        WeiBoUtil.getInit("1001");

        //取关
        WeiBoUtil.delAttend("1001", "1002");

        System.out.println("*****************************************");

        WeiBoUtil.getInit("1001");


    }

}
