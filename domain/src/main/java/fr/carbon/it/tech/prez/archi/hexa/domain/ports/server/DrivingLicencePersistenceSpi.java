package fr.carbon.it.tech.prez.archi.hexa.domain.ports.server;

import fr.carbon.it.tech.prez.archi.hexa.domain.functional.model.DrivingLicence;

import java.util.UUID;

public interface DrivingLicencePersistenceSpi extends PersistenceSpi<DrivingLicence, UUID> {}
