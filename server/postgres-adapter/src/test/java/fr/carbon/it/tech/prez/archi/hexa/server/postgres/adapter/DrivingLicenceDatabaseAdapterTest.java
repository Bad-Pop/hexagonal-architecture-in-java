package fr.carbon.it.tech.prez.archi.hexa.server.postgres.adapter;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;
import fr.carbon.it.tech.prez.archi.hexa.server.postgres.entity.DrivingLicenceEntity;
import fr.carbon.it.tech.prez.archi.hexa.server.postgres.mapper.DrivingLicenceEntityMapper;
import fr.carbon.it.tech.prez.archi.hexa.server.postgres.repository.DrivingLicenceRepository;
import lombok.val;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static io.vavr.API.None;
import static io.vavr.API.Some;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceDatabaseAdapterTest {

  @InjectMocks private DrivingLicenceDatabaseAdapter adapter;

  @Mock private DrivingLicenceRepository repository;

  @Nested
  class Save {

    @Captor private ArgumentCaptor<DrivingLicenceEntity> entityCaptor;

    @Test
    void should_save() {
      val licence = DrivingLicence.builder().build();
      val entity = DrivingLicenceEntityMapper.fromDomain(licence);

      when(repository.save(any(DrivingLicenceEntity.class))).thenReturn(entity);

      val actual = adapter.save(licence);

      verify(repository).save(entityCaptor.capture());
      verifyNoMoreInteractions(repository);

      VavrAssertions.assertThat(actual).isRight().containsRightInstanceOf(DrivingLicence.class);
      assertThat(actual.get()).usingRecursiveComparison().isEqualTo(licence);
      assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
    }

    @Test
    void should_not_save_if_repository_throw_exception() {
      val licence = DrivingLicence.builder().build();
      val entity = DrivingLicenceEntityMapper.fromDomain(licence);
      val throwable = new IllegalArgumentException();

      doThrow(throwable).when(repository).save(any(DrivingLicenceEntity.class));

      val actual = adapter.save(licence);

      verify(repository).save(entityCaptor.capture());
      verifyNoMoreInteractions(repository);

      VavrAssertions.assertThat(actual).isLeft().containsLeftInstanceOf(ApplicationError.class);
      assertThat(actual.getLeft())
          .usingRecursiveComparison()
          .isEqualTo(new ApplicationError("Unable to save licence", null, licence, throwable));
      assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
    }
  }

  @Nested
  class FindById {
    @Test
    void should_find() {
      val id = UUID.randomUUID();
      val entity = DrivingLicenceEntity.builder().offences(new ArrayList<>()).build();
      val domain = DrivingLicenceEntityMapper.toDomain(entity);

      when(repository.findDrivingLicenceEntityById(id)).thenReturn(Some(entity));

      val actual = adapter.findById(id);

      VavrAssertions.assertThat(actual).isDefined();
      assertThat(actual.get()).usingRecursiveComparison().isEqualTo(domain);

      verifyNoMoreInteractions(repository);
    }

    @Test
    void should_not_find() {
      val id = UUID.randomUUID();

      when(repository.findDrivingLicenceEntityById(id)).thenReturn(None());

      val actual = adapter.findById(id);

      VavrAssertions.assertThat(actual).isEmpty();

      verifyNoMoreInteractions(repository);
    }
  }
}
