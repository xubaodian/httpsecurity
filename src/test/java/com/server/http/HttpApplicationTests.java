package com.server.http;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@DataRedisTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class HttpApplicationTests {

    private static final String DOCKER_IMAGE = "redis:5.0.1";

    @Autowired
    private MockMvc mockMvc;

   /* private WebDriver driver;

    @Before
    public void setup() {
        this.driver = MockMvcHtmlUnitDriverBuilder.mockMvcSetup(this.mockMvc).build();
    }

    @After
    public void tearDown() {
        this.driver.quit();
    }*/
    @Test
    public void exampleTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/shop/list")).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        Cookie[] cookies = mvcResult.getResponse().getCookies();
        for (Cookie item : cookies) {
            System.out.println(item.getName() + ":" + item.getValue());
        }
        System.out.println(res);
    }
    @Test
    public void contextLoads() {
    }

}
