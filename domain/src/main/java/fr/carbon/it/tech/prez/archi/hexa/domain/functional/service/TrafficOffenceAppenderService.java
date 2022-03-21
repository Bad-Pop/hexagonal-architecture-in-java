package fr.carbon.it.tech.prez.archi.hexa.domain.functional.service;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.TrafficOffence;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.service.validation.TrafficOffenceValidator;
import fr.carbon.it.tech.prez.archi.hexa.domain.ports.client.TrafficOffenceAppenderApi;
import fr.carbon.it.tech.prez.archi.hexa.domain.ports.server.DrivingLicencePersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static io.vavr.API.Left;

@Slf4j
@RequiredArgsConstructor
public class TrafficOffenceAppenderService implements TrafficOffenceAppenderApi {

  private final DrivingLicencePersistenceSpi spi;

  @Override
  public Either<ApplicationError, DrivingLicence> appendOffence(
      UUID drivingLicenceId, TrafficOffence offence) {
    return spi.findById(drivingLicenceId)
        .onEmpty(() -> log.error("Unable to find driving licence with id {}", drivingLicenceId))
        .fold(
            () -> Left(new ApplicationError("No driving licence", null, drivingLicenceId, null)),
            drivingLicence -> verifyAppendCalculateAndSave(drivingLicence, offence));
  }

  private Either<ApplicationError, DrivingLicence> verifyAppendCalculateAndSave(
      DrivingLicence drivingLicence, TrafficOffence offence) {
    return TrafficOffenceValidator.validate(offence)
        .toEither()
        .peekLeft(
            error ->
                log.error("Unable to validate traffic offence : {}, with error {}", offence, error))
        .map(o -> drivingLicence.withOffences(drivingLicence.getOffences().append(offence)))
        .map(licence -> licence.withAvailablePoints(calculateNewPointsBalance(licence, offence)))
        .flatMap(spi::save);
  }

  private int calculateNewPointsBalance(DrivingLicence drivingLicence, TrafficOffence offence) {
    return Math.max(drivingLicence.getAvailablePoints() - offence.getPointsToWithdraw(), 0);
  }
}
