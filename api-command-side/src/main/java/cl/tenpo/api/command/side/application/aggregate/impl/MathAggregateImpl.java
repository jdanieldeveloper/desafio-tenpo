package cl.tenpo.api.command.side.application.aggregate.impl;

import akka.actor.Status;
import cl.tenpo.api.command.side.application.command.SumValuesCommand;
import cl.tenpo.api.command.side.application.event.CreatedAdditionEvent;
import cl.tenpo.api.command.side.domain.Addition;
import cl.tenpo.api.command.side.domain.MathAggregate;
import cl.tenpo.api.command.side.domain.MathRepository;
import cl.tenpo.api.command.side.infrastructure.handler.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Slf4j
@Component
public class MathAggregateImpl extends MathAggregate {

    @Autowired
    private EventHandler eventHandler;

    @Autowired
    private MathRepository mathRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void handle(SumValuesCommand command) {
        Object status;
        try {
            // get addition if exists before
            Optional<Addition> additionBefore =
                    mathRepository.getAdditionOpByValues(command.getVal1(), command.getVal2());

             Addition addition = null;
            // validate if doesn't exists some before
            if (additionBefore.isPresent()) {
                // make addition operation
                addition = additionBefore.get();
                //addition.apply();
            } else {
                // make addition operation
                addition = makeAddition(command.getVal1(), command.getVal2());
                addition.apply();
            }
            // success
            status = new Status.Success(addition.getResult());
            //fire event
            on(new CreatedAdditionEvent(
                    mathRepository.getOperationId(), addition.getValues(), addition.getResult(), "dcarvajal", new Date()));

        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
            // fail
            status = new Status.Failure(e);
        }
        // set status to command
        command.setStatus(status);
    }

    @Override
    public void on(CreatedAdditionEvent event) {
        eventHandler.handle(event);
    }
}
