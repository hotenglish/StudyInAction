package spittr.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import spittr.domain.Spittle;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.internet.MimeMessage;

@Component
public class SpitterMailServiceImpl implements SpitterMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public SpitterMailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /* (non-Javadoc)
     * @see spittr.email.SpitterMailService#sendSimpleSpittleEmail(java.lang.String, spittr.domain.Spittle)
     */
    @Override
    public void sendSimpleSpittleEmail(String to, Spittle spittle) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String spitterName = spittle.getSpitter().getFullName();
        simpleMailMessage.setFrom("noreply@spitter.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("New spittle from " + spitterName);
        simpleMailMessage.setText(spitterName + " says: " + spittle.getText());
        mailSender.send(simpleMailMessage);
    }

    /* (non-Javadoc)
     * @see spittr.email.SpitterMailService#sendSpittleEmailWithAttachment(java.lang.String, spittr.domain.Spittle)
     */
    @Override
    public void sendSpittleEmailWithAttachment(String to, Spittle spittle) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String spitterName = spittle.getSpitter().getFullName();
        helper.setFrom("noreply@spitter.com");
        helper.setTo(to);
        helper.setSubject("New spittle from " + spitterName);
        helper.setText(spitterName + " says: " + spittle.getText());
        ClassPathResource couponImage = new ClassPathResource("/collateral/coupon.jpg");
        helper.addAttachment("Coupon.png", couponImage);
        mailSender.send(message);
    }

}
