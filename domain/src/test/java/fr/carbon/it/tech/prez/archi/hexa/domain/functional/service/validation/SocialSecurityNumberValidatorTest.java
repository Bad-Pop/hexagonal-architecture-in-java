package fr.carbon.it.tech.prez.archi.hexa.domain.functional.service.validation;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static fr.carbon.it.tech.prez.archi.hexa.domain.functional.service.validation.SocialSecurityNumberValidator.validate;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class SocialSecurityNumberValidatorTest {

  @ParameterizedTest
  @ValueSource(strings = {"123456789012345", "098765432109876"})
  void should_validate(String validSSNumber) {
    val actual = validate(DrivingLicence.builder().driverSSNumber(validSSNumber).build());
    assertThat(actual).containsValidInstanceOf(DrivingLicence.class);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"lorem ipsum", "azertyuiopmlkjh", "09876543210987654321", "098654"})
  void should_not_validate(String invalidSSNumber) {
    val actual = validate(DrivingLicence.builder().driverSSNumber(invalidSSNumber).build());
    assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
  }
}
