package spittr.client;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.Spittle;
import spittr.client.config.ClientConfig;
import spittr.client.exception.SpitterException;
import spittr.client.exception.SpittlesNotFoundException;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfig.class)
public class ClientTest {

    @Autowired
    private RestSpittleClient restSpittleClient;

    @Test
    public void testRestSpittleClient() throws SpitterException {
        Spittle spittle = new Spittle(null, "This is a test spittle message", new Date(), 0.0d, 0.0d);
        Spittle postSpittle = restSpittleClient.postSpittle(spittle);
        System.out.println(postSpittle);

        Spittle anotherSpittle = new Spittle(null, "tomcat", new Date(), 0.1d, 0.1d);
        String locationString = restSpittleClient.postSpittleLocation(anotherSpittle);
        System.out.println(anotherSpittle);
        System.out.println(locationString);

        Spittle fetchSpittle = restSpittleClient.fetchSpittle(String.valueOf(postSpittle.getId()));
        assertEquals(spittle, fetchSpittle);

        fetchSpittle = restSpittleClient.getSpittleByExchange(postSpittle);
        assertEquals(spittle, fetchSpittle);

        spittle.setId(postSpittle.getId());
        spittle.setMessage("you are the first good message");
        spittle.setLatitude(0.2d);
        spittle.setLongitude(0.2d);
        restSpittleClient.putSpittle(spittle);
        Spittle putSpittle = restSpittleClient.fetchSpittle(String.valueOf(spittle.getId()));
        System.out.println(putSpittle);

        spittle.setMessage("you are the second good messages");
        spittle.setLatitude(0.3d);
        spittle.setLongitude(0.3d);
        restSpittleClient.putSpittleV(spittle);
        putSpittle = restSpittleClient.fetchSpittle(String.valueOf(spittle.getId()));
        System.out.println(putSpittle);

        spittle.setMessage("you are the third good messages");
        spittle.setLatitude(0.4d);
        spittle.setLongitude(0.4d);
        restSpittleClient.putSpittleM(spittle);
        putSpittle = restSpittleClient.fetchSpittle(String.valueOf(spittle.getId()));
        System.out.println(putSpittle);

        //restSpittleClient.deleteSpittle(spittle.);
        restSpittleClient.deleteSpittleV(postSpittle.getId());
        Spittle deletedSpittle = restSpittleClient.fetchSpittle(String.valueOf(postSpittle.getId()));
        assertNull(deletedSpittle);

    }
}
