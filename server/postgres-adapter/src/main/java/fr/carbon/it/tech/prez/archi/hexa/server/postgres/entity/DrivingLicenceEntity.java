package fr.carbon.it.tech.prez.archi.hexa.server.postgres.entity;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "driving_licence")
public class DrivingLicenceEntity {

  @Id @Include private UUID id;

  private int availablePoints;

  @Column(unique = true)
  private String driverSSNumber;

  @OneToMany(cascade = ALL)
  private List<TrafficOffenceEntity> offences;
}
