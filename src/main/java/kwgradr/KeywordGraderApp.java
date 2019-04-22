package kwgradr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KeywordGraderApp {

    public static void main(String[] args) {
        SpringApplication.run(KeywordGraderApp.class, args);
    }

}
