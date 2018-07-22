package ch5;

import io.github.viscent.mtpattern.ch14.hsha.example.AlarmMgr;
import io.github.viscent.mtpattern.ch5.tpt.example.AlarmType;
import io.github.viscent.util.Debug;
import org.junit.*;

public class AlarmMgrTest {

    private static AlarmMgr alarmMgr;

    /**
     * @BeforeClass:这个注解表示这个方法会在所有测试方法执行之前执行
     * 因为是static修饰的静态方法，所有只会执行一次。通常用来进行一些
     * 资源的加载，如通过JUnit测试Spring相关的东西时，可以在这个方法
     * 中加载Spring的配置文件
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        alarmMgr=AlarmMgr.getInstance();
        Debug.info("this is before class");
    }

    /**
     *  @AfterClass:这个注解表示这个方法会在所有方法执行完毕之后执行，通常用来释放资源
     *  */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Debug.info("this is after class");
    }

    /**
     * @Before:这个注解表示这个方法会在每个测试方法执行之前执行一次
     * 有多少个测试方法就会执行多少次
     */
    @Before
    public void setUp() throws Exception {
        alarmMgr.init();
        Debug.info("this is before");
    }

    /**
     * @After:这个注解表示这个方法会在每个测试方法执行之后执行一次
     * 有多少个测试方法就会执行多少次
     */
    @After
    public void tearDown() throws Exception {
        Debug.info("this is Down");
    }

    @Test
    public void testSendAlarm() throws Exception {
        for(int i=1;i<=1000;i++){
            int x=1+(int)(Math.random()*2);
            if(x==1){
                alarmMgr.sendAlarm(AlarmType.RESUME, String.valueOf(i),"You will resume !");
            }else if(x==2){
                alarmMgr.sendAlarm(AlarmType.FAULT, String.valueOf(i),"You are Fault!");
            }else{
                Debug.info("##$$wrong x="+x);
            }
            if(i==100){
                alarmMgr.shutdown();
            }
        }
        Debug.info("this is testSendAlarm");
    }
}
