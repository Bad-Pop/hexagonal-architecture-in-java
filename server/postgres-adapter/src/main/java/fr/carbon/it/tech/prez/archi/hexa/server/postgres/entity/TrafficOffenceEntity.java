package fr.carbon.it.tech.prez.archi.hexa.server.postgres.entity;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "traffic_offence")
public class TrafficOffenceEntity {
  @Id @Include private UUID id;
  private String label;
  private String details;
  private int pointsToWithdraw;
}
