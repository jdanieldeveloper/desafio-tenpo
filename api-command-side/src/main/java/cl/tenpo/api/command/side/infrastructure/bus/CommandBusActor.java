package cl.tenpo.api.command.side.infrastructure.bus;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import cl.tenpo.api.command.side.application.command.SumValuesCommand;
import cl.tenpo.api.command.side.application.aggregate.impl.MathAggregateImpl;
import cl.tenpo.api.command.side.infrastructure.handler.CommandHandler;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Representa un Bus de datos que en este caso esta encapsulado en un {@link akka.actor.Actor}. Este {@link akka.actor.Actor}
 * tiene la caracteristica en su {@link akka.dispatch.Mailbox} interno, retiene mensajes y asegura el orden en caso que se
 * quiera entrar con muchas transacciones
 *
 * @author daniel.carvajal
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CommandBusActor extends AbstractActor implements Bus {

    private MathAggregateImpl mathAggregate;

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public CommandBusActor(MathAggregateImpl mathAggregate) {
        this.mathAggregate = mathAggregate;
    }

    /**
     * Metodo que recibe los mensajes desde el {@link CommandHandler} y luego
     * procesa la logica de negocio y en algunos casos devuelve la respuesta segun corresponda
     *
     * @return mensaje de respuesta segun corresponda
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                // addition operation with values
                .match(SumValuesCommand.class, command -> {
                    log.info("[{}] Se ha recibido el siguiente comando [{}] ", CommandBusActor.class.getSimpleName(), command);

                    // handle command and apply business logic
                    mathAggregate.handle(command);

                    // send back message and status
                    sender().tell(command, self());
                })
         .build();
    }
}
