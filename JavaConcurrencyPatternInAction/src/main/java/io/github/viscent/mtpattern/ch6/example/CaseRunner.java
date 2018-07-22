package io.github.viscent.mtpattern.ch6.example;

import java.util.HashMap;
import java.util.Map;

public class CaseRunner {

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>();

        // FTP服务器IP地址，运行代码时请根据实际情况修改！
        params.put("server", "147.128.105.86");

        // FTP账户名，运行代码时请根据实际情况修改！
        params.put("userName", "ftpUser");

        // FTP账户密码，运行代码时请根据实际情况修改！
        params.put("password", "sin90=1x");

        DataSyncTask dst = new DataSyncTask(params);
        Thread t = new Thread(dst);
        t.start();

    }

}
