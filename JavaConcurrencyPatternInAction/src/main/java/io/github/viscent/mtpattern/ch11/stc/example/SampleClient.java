package io.github.viscent.mtpattern.ch11.stc.example;

public class SampleClient {
    private static final MessageFileDownloader DOWNLOADER;

    static{

        //请根据实际情况修改构造器MessageFileDownloader的参数
        DOWNLOADER=new MessageFileDownloader("E:\\test000887282",
  "147.128.105.190", "ftpUser", "sin90=1x");
        DOWNLOADER.init();
    }

    public static void main(String args[]) throws InterruptedException {
        DOWNLOADER.downloadFile("test_document.txt");

        // 执行其它操作
        DOWNLOADER.shutdown();
    }
}
