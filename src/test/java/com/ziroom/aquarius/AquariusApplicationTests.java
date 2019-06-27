package com.ziroom.aquarius;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AquariusApplicationTests {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void contextLoads() {
        try {
            int i=3/0;
        }catch (Exception e){
            logger.error("测试", e);
        }
    }

}
