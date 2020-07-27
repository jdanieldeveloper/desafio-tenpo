package cl.tenpo.api.query.side.infrastructure.persistence.repository;

import cl.tenpo.api.query.side.infrastructure.api.model.Operations;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Created by daniel.carvajal on 23-01-2019.
 */

@Repository
public interface OperationRepository extends ReactiveCrudRepository<Operations, Long> {

    @Query("SELECT * FROM OPERATIONS WHERE TYPE = 'ADD' AND CREATED_BY = :user")
    Flux<Operations> findAllOpByUser(String user);

    @Query("SELECT * FROM OPERATIONS WHERE TYPE = 'ADD' AND CREATED_BY = :user LIMIT :limit OFFSET :offset")
    Flux<Operations> findAllOpByUser(String user, int limit, long offset);



}
