package fr.carbon.it.tech.prez.archi.hexa.bootstrap.config.domain;

import fr.carbon.it.tech.prez.archi.hexa.domain.functional.service.DrivingLicenceCreatorService;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.service.TrafficOffenceAppenderService;
import fr.carbon.it.tech.prez.archi.hexa.domain.ports.client.DrivingLicenceCreatorApi;
import fr.carbon.it.tech.prez.archi.hexa.domain.ports.client.TrafficOffenceAppenderApi;
import fr.carbon.it.tech.prez.archi.hexa.domain.ports.server.DrivingLicencePersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

  @Bean
  public DrivingLicenceCreatorApi drivingLicenceCreatorService(DrivingLicencePersistenceSpi spi) {
    return new DrivingLicenceCreatorService(spi);
  }

  @Bean
  public TrafficOffenceAppenderApi trafficOffenceAppenderService(DrivingLicencePersistenceSpi spi) {
    return new TrafficOffenceAppenderService(spi);
  }
}
