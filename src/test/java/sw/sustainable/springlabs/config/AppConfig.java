package sw.sustainable.springlabs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import sw.sustainable.springlabs.common.BaseUtils;

@Configuration
@ConfigurationProperties(prefix = "my")
public class AppConfig implements BaseUtils {
    private String name;
    private int age;

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setName(String n){
        this.name = n;
    }

    public void setAge(int a){
        this.age = a;
    }
}
