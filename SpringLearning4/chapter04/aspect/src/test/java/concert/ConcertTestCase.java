package concert;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=ConcertConfig.class)
@ContextConfiguration(locations = "classpath:AspectConfig.xml")
public class ConcertTestCase {

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Autowired
    private Performance actor;

    @Autowired
    private Performance actress;

    @Test
    public void testAspect(){
        actor.perform();
        actress.perform();
        assertEquals("Silencing cell phones\n" +
                        "Taking seats\n" +
                        "Actor is performing...\n" +
                        "CLAP CLAP CLAP!!!\n" +
                        "Silencing cell phones\n" +
                        "Taking seats\n" +
                        "Actress is performing...\n" +
                        "CLAP CLAP CLAP!!!\n",
                log.getLog());
    }

    @Test
    public void testAddNewFunctionToInterface(){
        testAspect();
        log.clear();
        ((Encoreable)actor).performEncore();
        ((Encoreable)actress).performEncore();
        assertEquals("execute performEncore() in DefaultEncoreable class...\n" +
                        "execute performEncore() in DefaultEncoreable class...\n",
                log.getLog());

    }

}
