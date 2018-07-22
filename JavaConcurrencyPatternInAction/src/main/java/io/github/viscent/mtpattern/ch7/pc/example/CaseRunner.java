package io.github.viscent.mtpattern.ch7.pc.example;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CaseRunner {

    public static void main(String args[])throws Exception{
        AttachmentProcessor ap=new AttachmentProcessor();
        ap.init();
        InputStream in=new ByteArrayInputStream("Hello".getBytes());
        try{
            ap.saveAttachment(in,"000887282","测试文档");
        }catch (Exception e){
            e.printStackTrace();
        }
        ap.shutdown();
    }

}
