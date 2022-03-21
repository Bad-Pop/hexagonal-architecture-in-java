package fr.carbon.it.tech.prez.archi.hexa.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record TrafficOffenceCreationDto(
        @JsonProperty("label") String label,
        @JsonProperty("details") String details,
        @JsonProperty("pointsToWithdraw") int pointsToWithdraw
) {}
