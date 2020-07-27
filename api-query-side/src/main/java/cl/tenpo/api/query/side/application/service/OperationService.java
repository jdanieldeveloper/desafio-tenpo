package cl.tenpo.api.query.side.application.service;


import cl.tenpo.api.query.side.infrastructure.api.model.Operations;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

/**
 *
 * Mantiene todas las operaciones realacionadas con los equipos
 *
 * @author daniel.carvajal
 *
 */
public interface OperationService extends Service {

    Flux<Operations> findAllOpByUser(String user, Pageable page);

}
