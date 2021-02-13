package ramzet89.dictionary.integration;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = AbstractDbTest.DockerPostgreDataSourceInitializer.class)
@Testcontainers
public class AbstractDbTest {

    public static PostgreSQLContainer<?> postgreDBConteiner = new PostgreSQLContainer<>("postgres:9.4");

    static {
        postgreDBConteiner.start();
    }

    public static class DockerPostgreDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + postgreDBConteiner.getJdbcUrl(),
                    "spring.datasource.username=" + postgreDBConteiner.getUsername(),
                    "spring.datasource.password=" + postgreDBConteiner.getPassword()
            );
        }
    }
}
