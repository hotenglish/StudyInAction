package chapter10;

import org.apache.log4j.Logger;
import java.util.concurrent.*;

public class ConcurrentTask {

    private static Logger logger = Logger.getLogger(ConcurrentTask.class);

    private final ConcurrentHashMap<Object, Future<String>> taskCache = new ConcurrentHashMap<Object, Future<String>>();

    private String executionTask(final String taskName) throws ExecutionException, InterruptedException {
        while (true) {
            Future<String> future = taskCache.get(taskName);//1.1,2.1
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    public String call() throws InterruptedException {
                        //......
                        return taskName;
                    }
                };
                //1.2创建任务
                FutureTask<String> futureTask = new FutureTask<String>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);//1.3
                if (future == null) {
                    future = futureTask;
                    futureTask.run();//1.4执行任务
                }
            }

            try {
                return future.get(); //1.5,2.2线程在此等待任务执行完成
            } catch (CancellationException e) {
                taskCache.remove(taskName, future);
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConcurrentTask task=new ConcurrentTask();
        logger.info(task.executionTask("you are my test!"));
    }

}
