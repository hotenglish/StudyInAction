package spittr.email;

import com.icegreen.greenmail.spring.GreenMailBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.config.MailConfig;
import spittr.domain.Spitter;
import spittr.domain.Spittle;
import spittr.mail.SpitterMailService;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MailConfig.class)
public class SpitterMailServiceImplTest {

    @Autowired
    private SpitterMailService mailService;

    @Autowired
    private GreenMailBean mailServer;

    @Test
    public void testSendSimpleSpittleEmail() throws Exception {
        Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "c@habuma.com", true);
        Spittle spittle = new Spittle(1L, spitter, "Hiya!", new Date());
        mailService.sendSimpleSpittleEmail("craig@habuma.com", spittle);

        MimeMessage[] receivedMessages = mailServer.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        assertEquals("New spittle from Craig Walls", receivedMessages[0].getSubject());
        assertEquals("Craig Walls says: Hiya!", ((String) receivedMessages[0].getContent()).trim());
        Address[] from = receivedMessages[0].getFrom();
        assertEquals(1, from.length);
        assertEquals("noreply@spitter.com", ((InternetAddress) from[0]).getAddress());
        assertEquals("craig@habuma.com", ((InternetAddress) receivedMessages[0].getRecipients(Message.RecipientType.TO)[0]).getAddress());
    }

    @Test
    public void testSendSpittleEmailWithAttachment() throws Exception {
        Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "c@habuma.com", true);
        Spittle spittle = new Spittle(1L, spitter, "Hiya!", new Date());
        mailService.sendSpittleEmailWithAttachment("craig@habuma.com", spittle);

        MimeMessage[] receivedMessages = mailServer.getReceivedMessages();
        assertEquals(2, receivedMessages.length);
        assertEquals("New spittle from Craig Walls", receivedMessages[1].getSubject());

        Address[] from = receivedMessages[1].getFrom();
        assertEquals(1, from.length);
        assertEquals("noreply@spitter.com", ((InternetAddress) from[0]).getAddress());
        assertEquals("craig@habuma.com", ((InternetAddress) receivedMessages[1].getRecipients(Message.RecipientType.TO)[0]).getAddress());
    }

}
