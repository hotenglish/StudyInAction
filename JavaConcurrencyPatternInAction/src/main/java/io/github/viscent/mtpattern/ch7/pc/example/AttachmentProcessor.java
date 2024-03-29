package io.github.viscent.mtpattern.ch7.pc.example;

import io.github.viscent.mtpattern.ch5.tpt.AbstractTerminatableThread;
import io.github.viscent.mtpattern.ch7.pc.BlockingQueueChannel;
import io.github.viscent.mtpattern.ch7.pc.Channel;
//import io.github.viscent.mtpattern.ch7.pc.SemaphoreBasedChannel;
import io.github.viscent.util.Debug;
import java.io.*;
import java.text.Normalizer;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

//模式角色：Producer-Consumer.Producer
public class AttachmentProcessor {
    private final String ATTACHMENT_STORE_BASE_DIR="E:\\test";

    // 模式角色：Producer-Consumer.Channel
    private final Channel<File> channel=new BlockingQueueChannel<File>(new ArrayBlockingQueue<File>(200));
    //private final Channel<File> channel=new SemaphoreBasedChannel<File>(new ArrayBlockingQueue<File>(200),20);
    // 模式角色：Producer-Consumer.Consumer
    private final AbstractTerminatableThread indexingThread=new AbstractTerminatableThread(){
        @Override
        public void doRun() throws Exception{
            File file=null;
            file=channel.take();
            try{
                indexFile(file);
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                terminationToken.reservations.decrementAndGet();
            }
        }

        // 根据指定文件生成全文搜索所需的索引文件
        private void indexFile(File file) throws Exception {
            // 省略其它代码

            // 模拟生成索引文件的时间消耗
            Random rnd = new Random();
            try {
                Thread.sleep(rnd.nextInt(100));
            } catch (InterruptedException e) {
                ;
            }
            Debug.info("Executed indexFile function!");
        }
    };

    public void init(){
        indexingThread.start();
    }

    public void shutdown(){
        indexingThread.terminate(true);
    }

    public void saveAttachment(InputStream in, String documentId, String originalFileName) throws Exception{
        File file=this.saveAsFile(in,documentId,originalFileName);
        try{
            channel.put(file);
            indexingThread.terminationToken.reservations.incrementAndGet();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private File saveAsFile(InputStream in, String documentId, String originalFileName) throws Exception {
        String dirName = ATTACHMENT_STORE_BASE_DIR + documentId;
        File dir=new File(dirName);
        dir.mkdirs();
        File file=new File(dirName+"/"+ Normalizer.normalize(originalFileName,Normalizer.Form.NFC));

        // 防止目录跨越攻击
        if(!dirName.equals(file.getCanonicalFile().getParent())){
            throw new SecurityException("Invalid originalFileName:"
                    + originalFileName);
        }
        BufferedOutputStream bos=null;
        BufferedInputStream bis=new BufferedInputStream(in);

        byte buf[]=new byte[2048];
        int len=-1;
        try{
            bos=new BufferedOutputStream(new FileOutputStream(file));
            while((len=bis.read(buf))>0){
                bos.write(buf,0,len);
            }
            bos.flush();
        }finally {
            try{
                bis.close();
            }catch (IOException e){
                ;
            }
            try{
                if(null!=bos){
                    bos.close();
                }
            }catch (IOException e){
                ;
            }
        }
        return file;
    }

}
