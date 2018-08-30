package com.juvenxu.mvnbook.account.captcha;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;

public class RandomGeneratorTest {

    @Test(groups={"unit"})
    public void testGetRandomString() throws Exception {
        Set<String> randoms = new HashSet<String>(100);
        for (int i = 0; i < 100; i++) {
            String random = RandomGenerator.getRandomString();
            assertFalse(randoms.contains(random));
            randoms.add(random);
        }
        System.out.println("-------------testGetRandomString---------------");
    }
}
