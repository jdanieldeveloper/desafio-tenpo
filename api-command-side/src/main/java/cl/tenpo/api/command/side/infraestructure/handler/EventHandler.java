package cl.tenpo.api.command.side.infraestructure.handler;

import akka.actor.ActorRef;
import cl.tenpo.api.command.side.application.command.SumValuesCommand;
import cl.tenpo.api.command.side.application.event.CreatedAdditionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Representa un commandHandler que recepciona distintos tipos de comandos
 *
 * @author daniel.carvajal
 */
@Component
public class EventHandler implements Handler {

    @Autowired
    @Qualifier("eventBus")
    private ActorRef eventBus;

    /**
     * Recepciona el comando @{@link SumValuesCommand}
     *
     * @param event
     * @return la posible respuesta representada en un futuro
     */
    public void handle(CreatedAdditionEvent event) {
        eventBus.tell(event, ActorRef.noSender());
    }
}
