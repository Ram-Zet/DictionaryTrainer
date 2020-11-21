package ramzet89.dictionary.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "learn.getwordstolearn")
@Getter
@Setter
public class GetWordsToLearnConfig {
    private int newWords = 10;
    private int repeatWords = 7;
    private int difficultToLearnWords = 3;
}
