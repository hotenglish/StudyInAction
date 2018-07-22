package io.github.viscent.mtpattern.ch14.hsha.example;

import io.github.viscent.mtpattern.ch5.tpt.example.AlarmType;
import io.github.viscent.util.Debug;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class SampleAlarmClient {
    private final static Logger logger = Logger
            .getLogger(SampleAlarmClient.class);

    // 告警日志抑制阈值
    private static final int ALARM_MSG_SUPRESS_THRESHOLD = 10;

    static {

        // 初始化告警模块
        AlarmMgr.getInstance().init();
    }

    public static void main(String[] args) throws Exception{
        SampleAlarmClient alarmClient = new SampleAlarmClient();
        Connection dbConn = null;
        try{
            dbConn=alarmClient.retrieveDBConnection();

        }catch (Exception e){
            final AlarmMgr alarmMgr = AlarmMgr.getInstance();

            // 告警被重复发送至告警模块的次数
            int duplicateSubmissionCount;
            String alarmId = "00000010000020";
            final String alarmExtraInfo = "operation=GetDBConnection;detail=Failed to get DB connection:"
                    + e.getMessage();

            duplicateSubmissionCount = alarmMgr.sendAlarm(AlarmType.FAULT,alarmId,
                alarmExtraInfo);
            if (duplicateSubmissionCount < ALARM_MSG_SUPRESS_THRESHOLD) {
                Debug.info("Alarm[" + alarmId + "] raised,extraInfo:"
                    + alarmExtraInfo);
            } else {
                if (duplicateSubmissionCount == ALARM_MSG_SUPRESS_THRESHOLD){
                    Debug.info("Alarm[" + alarmId + "] was raised more than "
                        + ALARM_MSG_SUPRESS_THRESHOLD
                        + " times, it will no longer be logged.");
                }
            }
            return;
        }

        alarmClient.doSomething(dbConn);

    }

    // 获取数据库连接
    private Connection retrieveDBConnection() throws Exception{
        Connection cnn=null;
        throw new Exception("Failed to get DB connection");
        // 省略其它代码

        //return cnn;
    }

    private void doSomething(Connection cnn){
        // 省略其它代码
    }
}
