package fr.carbon.it.tech.prez.archi.hexa.domain.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class TrafficOffence {
  @Default UUID id = UUID.randomUUID();
  String label;
  String details;
  int pointsToWithdraw;
}
