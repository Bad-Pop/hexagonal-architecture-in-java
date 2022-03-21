package fr.carbon.it.tech.prez.archi.hexa.domain.functional.service.validation;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.TrafficOffence;
import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static fr.carbon.it.tech.prez.archi.hexa.domain.functional.service.validation.TrafficOffenceValidator.validate;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class TrafficOffenceValidatorTest {

  @Test
  void should_validate() {
    val given = TrafficOffence.builder().label("label").build();
    val actual = validate(given);
    assertThat(actual).containsValidSame(given);
  }

  @ParameterizedTest
  @MethodSource("provideInvalidOffences")
  void should_not_validate(TrafficOffence invalidOffence) {
    val actual = validate(invalidOffence);
    assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
  }

  private static Stream<TrafficOffence> provideInvalidOffences() {
    return Stream.of(
        null,
        TrafficOffence.builder().build(),
        TrafficOffence.builder().label(null).build(),
        TrafficOffence.builder().label("").build());
  }
}
