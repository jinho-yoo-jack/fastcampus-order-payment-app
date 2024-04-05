package sw.sustainable.springlabs.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AppConfigTest {
    @Autowired
    private AppConfig appConfig;

    @Test
    void configValueTest(){
        Assertions.assertEquals("jinho_test", appConfig.getName());
        Assertions.assertEquals(30, appConfig.getAge());
        System.out.println(appConfig.toString());
        System.out.println(appConfig.toJsonString());
    }

}
