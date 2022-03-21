package fr.carbon.it.tech.prez.archi.hexa.server.postgres.mapper;

import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.TrafficOffence;
import fr.carbon.it.tech.prez.archi.hexa.server.postgres.entity.TrafficOffenceEntity;

public interface TrafficOffenceEntityMapper {

  static TrafficOffence toDomain(TrafficOffenceEntity entity) {
    return TrafficOffence.builder()
        .id(entity.getId())
        .label(entity.getLabel())
        .details(entity.getDetails())
        .pointsToWithdraw(entity.getPointsToWithdraw())
        .build();
  }

  static TrafficOffenceEntity fromDomain(TrafficOffence domain) {
    return TrafficOffenceEntity.builder()
        .id(domain.getId())
        .label(domain.getLabel())
        .details(domain.getDetails())
        .pointsToWithdraw(domain.getPointsToWithdraw())
        .build();
  }
}
