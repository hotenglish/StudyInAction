package io.github.viscent.mtpattern.ch4.gs.example;

import io.github.viscent.mtpattern.ch4.gs.Blocker;
import io.github.viscent.mtpattern.ch4.gs.ConditionVarBlocker;
import io.github.viscent.mtpattern.ch4.gs.GuardedAction;
import io.github.viscent.mtpattern.ch4.gs.Predicate;
import io.github.viscent.util.Debug;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class NestedMonitorLockoutExample {

    public static void main(String args[]){
        final Helper helper=new Helper();
        Debug.info("Before calling guaredMethod.");
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                String result;
                result=helper.xGuardedMethod("test");
                Debug.info(result);
            }
        });
        t.start();

        final Timer timer=new Timer();
        // 延迟50ms调用helper.stateChanged方法
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                helper.xStateChanged();
                timer.cancel();
            }
        },50,10);

    }

    private static class Helper {
        private volatile boolean isStateOk=false;
        private final Predicate stateBeOk=new Predicate() {
            @Override
            public boolean evaluate() {
                return isStateOk;
            }
        };

        private final Blocker blocker=new ConditionVarBlocker();

        public synchronized String xGuardedMethod(final String message){
            GuardedAction<String> guardedAction=new GuardedAction<String>(stateBeOk) {
                @Override
                public String call() throws Exception {
                    return message+"->received.";
                }
            };
            String result=null;
            try {
                result=blocker.callWithGuard(guardedAction);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        public synchronized void xStateChanged(){
            try{
                blocker.signalAfter(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        isStateOk=true;
                        Debug.info("state ok.");
                        return Boolean.TRUE;
                    }
                });
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
