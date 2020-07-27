package cl.tenpo.api.query.side.application.service.impl;

import cl.tenpo.api.query.side.application.service.OperationService;
import cl.tenpo.api.query.side.infrastructure.api.model.Operations;
import cl.tenpo.api.query.side.infrastructure.persistence.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Contiene todas las implementaciones para las operaciones realacionadas con los equipos
 *
 * @author daniel.carvajal
 */
@Slf4j
@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository repository;


    @Override
    public Flux<Operations> findAllOpByUser(String user, Pageable page) {
        return repository.findAllOpByUser(user, page.getPageSize(), page.getOffset());
    }
}