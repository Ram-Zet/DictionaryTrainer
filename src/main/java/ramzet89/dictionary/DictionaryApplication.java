package ramzet89.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ramzet89.dictionary.service.MainMenuService;

@SpringBootApplication
public class DictionaryApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DictionaryApplication.class, args);
        run.getBean(MainMenuService.class).mainMenu();

    }

}
