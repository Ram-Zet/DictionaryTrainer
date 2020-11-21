package ramzet89.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DictionaryApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DictionaryApplication.class, args);
    }

}
