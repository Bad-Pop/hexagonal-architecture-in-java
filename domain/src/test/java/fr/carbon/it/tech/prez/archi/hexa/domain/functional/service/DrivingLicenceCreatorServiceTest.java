package fr.carbon.it.tech.prez.archi.hexa.domain.functional.service;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;
import fr.carbon.it.tech.prez.archi.hexa.domain.ports.server.DrivingLicencePersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.vavr.API.Left;
import static io.vavr.API.Right;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceCreatorServiceTest {

  @InjectMocks private DrivingLicenceCreatorService service;
  @Mock private DrivingLicencePersistenceSpi spi;

  @Test
  void should_create_licence() {
    val given = DrivingLicence.builder().driverSSNumber("123456789098765").build();
    when(spi.save(given)).thenReturn(Right(given));

    val actual = service.create(given);
    assertThat(actual).containsRightSame(given);
  }

  @Test
  void should_not_create_licence_if_ssNumber_is_invalid() {
    val given = DrivingLicence.builder().driverSSNumber("invalid").build();
    val actual = service.create(given);
    assertThat(actual).containsLeftInstanceOf(ApplicationError.class);
    verifyNoInteractions(spi);
  }

  @Test
  void should_not_create_licence_if_technical_error_occurred_in_adapter() {
    val given = DrivingLicence.builder().driverSSNumber("123456789098765").build();
    val error = new ApplicationError("An error occurred", null, null, null);
    when(spi.save(given)).thenReturn(Left(error));

    val actual = service.create(given);
    assertThat(actual).containsLeftSame(error);
  }
}
