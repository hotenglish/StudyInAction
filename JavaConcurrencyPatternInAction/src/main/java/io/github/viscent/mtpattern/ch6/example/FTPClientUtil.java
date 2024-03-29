package io.github.viscent.mtpattern.ch6.example;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FTPClientUtil {

    private final FTPClient ftp=new FTPClient();
    private final Map<String,Boolean> dirCreateMap=new HashMap<String, Boolean>();

    // 私有构造器
    private FTPClientUtil(){

    }

    public static Future<FTPClientUtil> newInstance(final String ftpServer,
        final String userName,final String password) {
        Callable<FTPClientUtil> callable=new Callable<FTPClientUtil>(){
            @Override
            public FTPClientUtil call() throws Exception{
                FTPClientUtil self=new FTPClientUtil();
                self.init(ftpServer,userName,password);
                return self;
            }
        };
        // 下面这行代码与本案例的实际代码并不一致，这是为了讨论方便。
        FutureTask<FTPClientUtil> task=new FutureTask<FTPClientUtil>(callable);
        new Thread(task).start();
        return task;
    }

    private void init(String ftpServer,String userName,String password) throws Exception{
        FTPClientConfig config=new FTPClientConfig();
        ftp.configure(config);
        int reply;
        ftp.connect(ftpServer);
        System.out.println(ftp.getReplyString());
        reply=ftp.getReplyCode();
        if(!FTPReply.isPositiveCompletion(reply)){
             ftp.disconnect();
             throw new RuntimeException("FTP server refused connection.");
        }
        boolean isOK=ftp.login(userName,password);
        if(isOK){
            System.out.println(ftp.getReplyString());
        }else{
            throw new RuntimeException("Failed to login." + ftp.getReplyString());
        }
        ftp.cwd("/");
        if(!FTPReply.isPositiveCompletion(reply)){
            ftp.disconnect();
            throw new RuntimeException("Failed to change working directory.reply:"
                    + reply);
        }else{
            System.out.println(ftp.getReplyString());
        }
        ftp.setFileType(FTP.ASCII_FILE_TYPE);
    }

    public void upload(File file)throws Exception{
        InputStream datain=new BufferedInputStream(new FileInputStream(file),1024*8);
        boolean isOK;
        String dirName=file.getParentFile().getName();
        String fileName=dirName+'/'+file.getName();
        ByteArrayInputStream checkFileInputStream=new ByteArrayInputStream("".getBytes());
        try{
            if(!dirCreateMap.containsKey(dirName)){
                ftp.makeDirectory(dirName);
                dirCreateMap.put(dirName,null);
            }
            try{
                isOK=ftp.storeFile(fileName,datain);
            }catch(IOException ex){
                throw new RuntimeException("Failed to upload " + file, ex);
            }
            if(isOK){
                ftp.storeFile(fileName+".c",checkFileInputStream);
            }else{
                throw new RuntimeException("Failed to upload " + file + ",reply:" + "," + ftp.getReplyString());
            }
        }finally{
            datain.close();
        }
    }

    public void disconnect(){
        if(ftp.isConnected()){
            try{
                ftp.disconnect();
            }catch (IOException e){
                // 什么也不做
            }
            //省略与清单6-2中相同的代码
        }
    }
}
