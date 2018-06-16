package chapter8;

import org.apache.log4j.Logger;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 银行流水处理服务类
 *
 * @author laims
 */
public class BankWaterService implements Runnable {

    private static Logger logger = Logger.getLogger(BankWaterService.class);

    /**
     * 创建4个屏障，处理完之后执行当前类的run方法
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);

    /**
     * 假设只有4个sheet,所以只启动4个线程
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    /**
     * 保存每个sheet 计算出出的银行流水结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<String, Integer>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //计算当前的银行流水数据，计算代码省略
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    logger.info(Thread.currentThread().getName()+" has done the action!");
                    //银行流水计算完成，插入一个屏障
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }finally {
                        logger.info(Thread.currentThread().getName()+" leave the barrier!");
                    }
                }
            });
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        int result = 0;
        //汇总每个sheet 计算出的结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        //结果输出
        sheetBankWaterCount.put("result", result);
        logger.info(result);
   }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
