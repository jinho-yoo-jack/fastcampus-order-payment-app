package sw.sustainable.springlabs.core.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import sw.sustainable.springlabs.core.common.BaseUtils;

@Configuration
@ConfigurationProperties(prefix = "my")
@Slf4j
public class AppConfig implements BaseUtils {
    private String name;
    private int age;

    @PostConstruct
    public void init() {
        log.info("AppConfig name ::: {}", name);
        log.info("AppConfig age ::: {}", age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setAge(int a) {
        this.age = a;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("\tname: ");
        builder.append(this.name);
        builder.append(",\n");
        builder.append("\tage: ");
        builder.append(this.age);
        builder.append(",\n");
        builder.append("}\n");
        return builder.toString();
    }
}
