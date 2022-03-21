package fr.carbon.it.tech.prez.archi.hexa.bootstrap;

import fr.carbon.it.tech.prez.archi.hexa.bootstrap.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ApplicationConfiguration.class})
@SpringBootApplication(scanBasePackages = "fr.carbon.it.tech.prez.archi.hexa")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
