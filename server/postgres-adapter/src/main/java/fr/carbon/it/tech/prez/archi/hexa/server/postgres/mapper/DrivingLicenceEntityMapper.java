package fr.carbon.it.tech.prez.archi.hexa.server.postgres.mapper;

import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;
import fr.carbon.it.tech.prez.archi.hexa.server.postgres.entity.DrivingLicenceEntity;
import io.vavr.collection.List;

public interface DrivingLicenceEntityMapper {

  static DrivingLicence toDomain(DrivingLicenceEntity entity) {
    return DrivingLicence.builder()
        .id(entity.getId())
        .availablePoints(entity.getAvailablePoints())
        .driverSSNumber(entity.getDriverSSNumber())
        .offences(List.ofAll(entity.getOffences()).map(TrafficOffenceEntityMapper::toDomain))
        .build();
  }

  static DrivingLicenceEntity fromDomain(DrivingLicence domain) {
    return DrivingLicenceEntity.builder()
        .id(domain.getId())
        .availablePoints(domain.getAvailablePoints())
        .driverSSNumber(domain.getDriverSSNumber())
        .offences(domain.getOffences().map(TrafficOffenceEntityMapper::fromDomain).asJavaMutable())
        .build();
  }
}
