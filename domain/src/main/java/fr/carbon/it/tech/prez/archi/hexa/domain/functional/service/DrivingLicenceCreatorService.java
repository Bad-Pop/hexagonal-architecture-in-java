package fr.carbon.it.tech.prez.archi.hexa.domain.functional.service;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.service.validation.SocialSecurityNumberValidator;
import fr.carbon.it.tech.prez.archi.hexa.domain.ports.client.DrivingLicenceCreatorApi;
import fr.carbon.it.tech.prez.archi.hexa.domain.ports.server.DrivingLicencePersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DrivingLicenceCreatorService implements DrivingLicenceCreatorApi {

  private final DrivingLicencePersistenceSpi spi;

  @Override
  public Either<ApplicationError, DrivingLicence> create(DrivingLicence drivingLicence) {
    return SocialSecurityNumberValidator.validate(drivingLicence)
        .toEither()
        .peekLeft(
            error -> log.error("An error occurred while validating driving licence : {}", error))
        .flatMap(spi::save);
  }
}
