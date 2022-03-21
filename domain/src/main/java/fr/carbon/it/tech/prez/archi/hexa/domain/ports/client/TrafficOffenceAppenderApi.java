package fr.carbon.it.tech.prez.archi.hexa.domain.ports.client;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.TrafficOffence;
import io.vavr.control.Either;

import java.util.UUID;

public interface TrafficOffenceAppenderApi {
  Either<ApplicationError, DrivingLicence> appendOffence(
      UUID drivingLicenceId, TrafficOffence offence);
}
