package fr.carbon.it.tech.prez.archi.hexa.domain.functional.service.validation;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;
import io.vavr.control.Validation;
import lombok.val;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;

public interface SocialSecurityNumberValidator {

  static Validation<ApplicationError, DrivingLicence> validate(DrivingLicence drivingLicence) {
    val ssNumber = drivingLicence.getDriverSSNumber();
    return ssNumber != null && ssNumber.length() == 15 && ssNumber.matches("[0-9]+")
        ? Valid(drivingLicence)
        : Invalid(new ApplicationError("Invalid Social Security Number", null, ssNumber, null));
  }
}
