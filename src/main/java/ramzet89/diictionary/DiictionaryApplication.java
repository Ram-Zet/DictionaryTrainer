package ramzet89.diictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import ramzet89.diictionary.service.MainMenuService;

@SpringBootApplication
public class DiictionaryApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DiictionaryApplication.class, args);
        run.getBean(MainMenuService.class).mainMenu();
    }

}
