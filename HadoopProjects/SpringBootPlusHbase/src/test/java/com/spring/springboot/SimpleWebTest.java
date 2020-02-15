package com.spring.springboot;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.sprintboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootConfiguration
public class SimpleWebTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/api/city/get")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("zhangsan"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result); //{"username":"tom","password":null}

    }

    @Test
    public void pageNotFound() {
        RestTemplate rest = new RestTemplate();
        User user = rest.getForObject("http://localhost:{port}/api/user/get", null, 8082);
        System.out.println(user);
    }

    @Test
    public void testRestTemplate() throws Exception {
        TestRestTemplate rest = new TestRestTemplate();
        String s = rest.getForObject("http://localhost:{port}/api/user/get", null, 8082);
        System.out.println(s);
    }

}
