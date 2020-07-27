package cl.tenpo.api.query.side.infrastructure.api;

import cl.tenpo.api.query.side.application.service.OperationService;
import cl.tenpo.api.query.side.infrastructure.api.model.Operations;
import cl.tenpo.api.query.side.infrastructure.util.UtilProperties;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * Mantiene todas las operaciones relacionadas con los dispositivos expuestas como api restfull
 *
 * @author daniel.carvajal
 */
@Slf4j
@RestController
public class ApiController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private UtilProperties properties;

    /**
     * EndPoint que obtiene todas las operaciones de suma a traves de un usuario determinado
     *
     * @param user
     * @return lista de operaciones suma
     */
    @GetMapping(value = "/api/math/operations/{user}/sum")
    @Operation(summary = "Find all addition operations by user")
    public Flux<Operations> getAllSumOpByUser(
            @PathVariable String user, @RequestParam int page) {
        return operationService.findAllOpByUser(user,
                PageRequest.of(page, Integer.parseInt(properties.API_QUERY_SIDE_PAGEABLE_DEFAULT_PAGE)));
    }
}
