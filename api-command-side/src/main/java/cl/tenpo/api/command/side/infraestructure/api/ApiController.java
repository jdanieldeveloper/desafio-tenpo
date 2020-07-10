package cl.tenpo.api.command.side.infraestructure.api;

import akka.actor.Status;
import cl.tenpo.api.command.side.application.command.SumValuesCommand;
import cl.tenpo.api.command.side.infraestructure.api.model.SummedValuesModel;
import cl.tenpo.api.command.side.infraestructure.api.model.ValuesModel;
import cl.tenpo.api.command.side.infraestructure.handler.CommandHandler;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;

import static cl.tenpo.api.command.side.infraestructure.converter.Converters.toSumedValuesResponse;

/**
 * Mantiene todas las operaciones relacionadas con los equipos expuestas como api restfull
 *
 * @author daniel.carvajal
 */
@Slf4j
@RestController
public class ApiController {

    @Autowired
    private CommandHandler commandHandler;

    /**
     * EndPoint que edita un equipo
     *
     * @return el equipo editado
     */
    @PutMapping(value = "/api/math/operation/sum")
    @ApiOperation(value = "sum", notes = "Makes the addition of two values and return it result")
    public ResponseEntity<SummedValuesModel> sum(@RequestBody ValuesModel valuesModel) {
        ResponseEntity<SummedValuesModel> responseEntity;
        //
        // TODO validar parametros de entrada minimos

        SummedValuesModel summedValuesModel = new SummedValuesModel();
        try {
            // edit device
            SumValuesCommand command = (SumValuesCommand)
                    ((CompletableFuture<Object>) commandHandler.handle(
                            new SumValuesCommand(   valuesModel.getValue1(), // value1
                                                    valuesModel.getValue2()  // value2
                            )
                    )).get();
            //
            if (command.getStatus() instanceof Status.Success) {
                Status.Success success = (Status.Success) command.getStatus();
                //set result of summed values
                summedValuesModel.setResult((Integer) success.status());

                String messageInfo = MessageFormat.format(
                        "Se ha aplicado la operacion de suma ([{0}] + [{1}] = [{2}]) correctamente!!!",
                        valuesModel.getValue1(), valuesModel.getValue2(), summedValuesModel.getResult());
                log.info(messageInfo);
                // it's Ok
                responseEntity =
                        toSumedValuesResponse.apply(Triple.of(summedValuesModel, HttpStatus.OK, messageInfo));

            } else {
                Status.Failure failure = (Status.Failure) command.getStatus();
                //
                responseEntity =
                        toSumedValuesResponse.apply(Triple.of(summedValuesModel, HttpStatus.ACCEPTED, failure.cause().getMessage()));
                //
                if (log.isErrorEnabled()) {
                    log.error(failure.cause().getMessage(), failure.cause());
                }
            }
        } catch (Exception e) {
            responseEntity =
                    toSumedValuesResponse.apply((Triple.of(summedValuesModel, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
            //
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return responseEntity;
    }
}
