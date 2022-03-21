package fr.carbon.it.tech.prez.archi.hexa.client.rest.mapper;

import fr.carbon.it.tech.prez.archi.hexa.client.rest.dto.DrivingLicenceCreationDto;
import fr.carbon.it.tech.prez.archi.hexa.client.rest.dto.DrivingLicenceDto;
import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;

public interface DrivingLicenceDtoMapper {

  static DrivingLicenceDto toDto(DrivingLicence licence) {
    return new DrivingLicenceDto(
        licence.getId(),
        licence.getAvailablePoints(),
        licence.getOffences().map(TrafficOffenceDtoMapper::toDto),
        licence.getDriverSSNumber());
  }

  static DrivingLicence drivingLicenceCreationToDomain(DrivingLicenceCreationDto dto) {
    return DrivingLicence.builder().driverSSNumber(dto.driverSSNumber()).build();
  }
}
