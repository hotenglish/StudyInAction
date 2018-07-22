package io.github.viscent.mtpattern.ch14.hsha.example;

import io.github.viscent.mtpattern.ch5.tpt.example.AlarmType;

public class CaseRunner {

    public static void main(String args[]) throws Exception{
        AlarmMgr alarmMgr = AlarmMgr.getInstance();
        alarmMgr.init();

        String alarmId="0000000010";

        alarmMgr.sendAlarm(AlarmType.FAULT,alarmId,"key1=value1;key2=value2");

        Thread.sleep(80);

        alarmMgr.sendAlarm(AlarmType.RESUME,alarmId,"key1=value1;key2=value2");
        Thread.sleep(600);
    }

}
