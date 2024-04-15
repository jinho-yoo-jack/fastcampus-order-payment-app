package sw.sustainable.springlabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringLabsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLabsApplication.class, args);
    }

}
