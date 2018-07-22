package io.github.viscent.mtpattern.ch12.ms.example.testdatagen;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractRequestFactory implements RequestFactory {

    protected final Map<String, Integer> respDelayConf;
    protected final AtomicInteger seq = new AtomicInteger(0);
    protected final Random reqTimeRnd = new Random();
    protected final Random respDelayRnd = new Random();
    /**
     * 部件内部操作延时随机生成器
     */
    protected final Random internalDelayRnd = new Random();
    protected final long startTimestamp = new Date().getTime();
    private long nowTimestamp = startTimestamp;
    protected final DecimalFormat traceIdFormatter = new DecimalFormat("0000000");

    private final int maxRequestPerTimeSlice;
    /**
     * 单位时间（10s）内产生的请求数
     */
    private int requestGeneratedInTimeSlice = 0;

    public AbstractRequestFactory(Map<String, Integer> respDelayConf,
        int maxRequestPerTimeslice) {
        super();
        this.respDelayConf = respDelayConf;
        this.maxRequestPerTimeSlice = maxRequestPerTimeslice;
    }

    public long nextRequestTimestamp() {
        long requestTimestamp;

        requestTimestamp = nowTimestamp + reqTimeRnd.nextInt((10 - 1) * 1000) / 1000;
        if ((++requestGeneratedInTimeSlice) >= maxRequestPerTimeSlice) {
            nowTimestamp = nowTimestamp + (10 * 1000);
            requestGeneratedInTimeSlice = 0;

        }

        return requestTimestamp;
    }

    public int genResponseDelay(String externalDevice){
        return respDelayRnd.nextInt(respDelayConf.get(externalDevice));
    }

    public int genInternalDelay(){
        return internalDelayRnd.nextInt(respDelayConf.get("ESB"));
    }

}
