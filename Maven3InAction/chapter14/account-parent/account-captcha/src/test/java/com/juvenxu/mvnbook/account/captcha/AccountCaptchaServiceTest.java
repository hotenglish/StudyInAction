package com.juvenxu.mvnbook.account.captcha;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class AccountCaptchaServiceTest {

    private AccountCaptchaService service;

    @BeforeMethod(groups={"unit"})
    public void prepare() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-captcha.xml");
        service = (AccountCaptchaService) ctx.getBean("accountCaptchaService");
    }

    @Test(groups={"unit,integration"})
    public void testGenerateCaptcha() throws Exception {
        String captchaKey = service.generateCaptchaKey();
        assertNotNull(captchaKey);

        byte[] captchaImage = service.generateCaptchaImage(captchaKey);
        assertTrue(captchaImage.length > 0);

        File image = new File("target/" + captchaKey + ".jpg");
        OutputStream output = null;
        try {
            output = new FileOutputStream(image);
            output.write(captchaImage);
        } finally {
            if (output != null) {
                output.close();
            }
        }
        assertTrue(image.exists() && image.length() > 0);
        System.out.println("-------------testGenerateCaptcha---------------");
    }

    @Test(groups={"unit,integration"})
    public void testValidateCaptchaCorrect() throws Exception {
        List<String> preDefineTexts=new ArrayList<>();
        preDefineTexts.add("12345");
        preDefineTexts.add("abcde");
        service.setPreDefinedTexts(preDefineTexts);

        String captchaKey=service.generateCaptchaKey();
        service.generateCaptchaImage(captchaKey);
        assertTrue(service.validateCaptcha(captchaKey,"12345"));

        captchaKey=service.generateCaptchaKey();
        service.generateCaptchaImage(captchaKey);
        assertTrue(service.validateCaptcha(captchaKey,"abcde"));
        System.out.println("-------------testValidateCaptchaCorrect---------------");
    }

    @Test(groups={"unit"})
    public void testValidateCaptchaIncorrect() throws Exception {
        List<String> preDefineTexts=new ArrayList<>();
        preDefineTexts.add("12345");
        service.setPreDefinedTexts(preDefineTexts);

        String captchaKey=service.generateCaptchaKey();
        service.generateCaptchaImage(captchaKey);
        assertFalse(service.validateCaptcha(captchaKey,"67890"));
        System.out.println("-------------testValidateCaptchaIncorrect---------------");
    }

}
