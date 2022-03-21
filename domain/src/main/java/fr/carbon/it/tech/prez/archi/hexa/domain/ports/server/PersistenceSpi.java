package fr.carbon.it.tech.prez.archi.hexa.domain.ports.server;

import fr.carbon.it.tech.prez.archi.hexa.domain.ApplicationError;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface PersistenceSpi<T, ID> {
  Either<ApplicationError, T> save(T o);

  Option<T> findById(ID id);
}
