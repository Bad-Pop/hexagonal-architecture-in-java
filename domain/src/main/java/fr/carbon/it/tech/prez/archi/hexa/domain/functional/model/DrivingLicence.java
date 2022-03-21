package fr.carbon.it.tech.prez.archi.hexa.domain.functional.model;

import io.vavr.collection.Seq;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.util.UUID;

import static io.vavr.API.Seq;

@Value
@Builder
public class DrivingLicence {
  @Default UUID id = UUID.randomUUID();
  @With @Default int availablePoints = 12;
  @With @Default Seq<TrafficOffence> offences = Seq();
  String driverSSNumber;
}
