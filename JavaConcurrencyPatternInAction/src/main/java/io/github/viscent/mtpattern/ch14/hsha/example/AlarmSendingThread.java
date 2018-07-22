package io.github.viscent.mtpattern.ch14.hsha.example;

import io.github.viscent.mtpattern.ch5.tpt.AbstractTerminatableThread;
import io.github.viscent.mtpattern.ch5.tpt.example.AlarmType;
import io.github.viscent.util.Debug;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
/*
 * 告警发送线程。
 * 模式角色：HalfSync/HalfAsync.AsyncTask
 * 模式角色：Two-phaseTermination.ConcreteTerminatableThread
 */
public class AlarmSendingThread extends AbstractTerminatableThread {

    private final AlarmAgent alarmAgent=new AlarmAgent();

    /*
     * 告警队列。
     * 模式角色：HalfSync/HalfAsync.Queue
     */
    private final BlockingQueue<AlarmInfo> alarmQueue;
    private final ConcurrentMap<String,AtomicInteger> submittedAlarmRegistry;

    public AlarmSendingThread() {
        super();
        this.alarmQueue=new ArrayBlockingQueue<AlarmInfo>(100);
        this.submittedAlarmRegistry = new ConcurrentHashMap<String, AtomicInteger>();
        alarmAgent.init();
    }

    protected void doRun() throws Exception {
        AlarmInfo alarm;
        alarm = alarmQueue.take();
        terminationToken.reservations.decrementAndGet();
        try {
            //将告警信息发送至告警服务器
            alarmAgent.sendAlarm(alarm);
            String key = alarm.type.toString() + ':' + alarm.getId() + '@' +alarm.getExtraInfo();
            Debug.info("key:"+key+" currentReservationCount:"+this.getReservationCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
		 * 处理恢复告警：将相应的故障告警从注册表中删除， 使得相应故障恢复后若再次出现相同故障，该故障信息能够上报到服务器
		 */
        if(AlarmType.RESUME==alarm.type){
            String key = AlarmType.FAULT.toString() + ':' + alarm.getId() + '@' +alarm.getExtraInfo();
            submittedAlarmRegistry.remove(key);
            key = AlarmType.RESUME.toString() + ':' + alarm.getId() + '@' +alarm.getExtraInfo();
            submittedAlarmRegistry.remove(key);
        }
    }

    public int sendAlarm(final AlarmInfo alarminfo){
        AlarmType type=alarminfo.type;
        String id=alarminfo.getId();
        String extraInfo=alarminfo.getExtraInfo();
        if(terminationToken.isToShutdown()){
            // 记录告警信息
            System.out.println("rejected alarm:"+id+"," + extraInfo);
            return -1;
        }
        int duplicateSubmissionCount=0;
        try{
            AtomicInteger preSubmittedCounter;
            preSubmittedCounter=submittedAlarmRegistry.putIfAbsent(type.toString()+':'+id+'@'+extraInfo,
                    new AtomicInteger(0));
            if(null==preSubmittedCounter){
                alarmQueue.put(alarminfo);
                this.terminationToken.reservations.incrementAndGet();
            }else{
                // 故障未恢复，不用重复发送告警信息给服务器，故仅增加计数
                duplicateSubmissionCount=preSubmittedCounter.incrementAndGet();
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
        return duplicateSubmissionCount;
    }

    @Override
    protected void doCleanup(Exception exp){
        if(null!=exp && !(exp instanceof InterruptedException)){
            exp.printStackTrace();
        }
        alarmAgent.disconnect();
    }

}
