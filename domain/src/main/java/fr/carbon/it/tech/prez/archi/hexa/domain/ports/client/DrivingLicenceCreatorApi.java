package fr.carbon.it.tech.prez.archi.hexa.domain.ports.client;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;
import io.vavr.control.Either;

public interface DrivingLicenceCreatorApi {
  Either<ApplicationError, DrivingLicence> create(DrivingLicence drivingLicence);
}
