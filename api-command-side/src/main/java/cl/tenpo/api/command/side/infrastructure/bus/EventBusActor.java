package cl.tenpo.api.command.side.infrastructure.bus;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import cl.tenpo.api.command.side.application.event.CreatedAdditionEvent;
import cl.tenpo.api.command.side.infrastructure.handler.CommandHandler;
import cl.tenpo.api.command.side.infrastructure.persistence.dao.MathDao;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static cl.tenpo.api.command.side.infrastructure.converter.Converters.toAdditionDto;

/**
 * Representa un Bus de datos que en este caso esta encapsulado en un {@link akka.actor.Actor}. Este {@link akka.actor.Actor}
 * tiene la caracteristica en su {@link akka.dispatch.Mailbox} interno, retiene mensajes y asegura el orden en caso que se
 * quiera entrar con muchas transacciones
 *
 * @author daniel.carvajal
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventBusActor extends AbstractActor implements Bus {

    private MathDao mathDao;

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public EventBusActor(MathDao mathDao) {
        this.mathDao = mathDao;
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
                // created addition operation with values
                .match(CreatedAdditionEvent.class, event -> {
                    log.info("[{}] Se ha recibido el siguiente evento [{}] ", EventBusActor.class.getSimpleName(), event);

                    // add operation to database
                    mathDao.saveOperation(toAdditionDto.apply(event));

                    // send back message and status
                    sender().tell(event, self());
                })
         .build();
    }
}