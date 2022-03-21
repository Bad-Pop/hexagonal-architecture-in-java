package fr.carbon.it.tech.prez.archi.hexa.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DrivingLicenceCreationDto(@JsonProperty("driverSSNumber") String driverSSNumber) {}
