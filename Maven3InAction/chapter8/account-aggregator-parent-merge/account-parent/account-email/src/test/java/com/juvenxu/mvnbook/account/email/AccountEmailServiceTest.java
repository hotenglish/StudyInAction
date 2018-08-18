package com.juvenxu.mvnbook.account.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;

import static junit.framework.Assert.assertEquals;

public class AccountEmailServiceTest {
    private GreenMail greenMail;

    @Before
    public void startMailServer() throws Exception {
        final ServerSetup serverSetup = new ServerSetup(3868, (String)null, "smtp");
        greenMail = new GreenMail(serverSetup);
        greenMail.setUser("test@juvenxu.com", "123456");
        greenMail.start();
    }

    @Test
    public void testSendMail() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean("accountEmailService");
        String subject = "Test Subject";
        String htmlText = "<h3>Test</h3>";
        accountEmailService.sendMail("test@juvenxu.com", subject, htmlText);
        greenMail.waitForIncomingEmail(2000, 1);
        Message[] msg = greenMail.getReceivedMessages();
        assertEquals(1, msg.length);
        assertEquals(subject, msg[0].getSubject());
        assertEquals(htmlText, GreenMailUtil.getBody(msg[0]).trim());
    }

    @After
    public void stopMailServer() throws Exception {
        greenMail.stop();
    }
}
