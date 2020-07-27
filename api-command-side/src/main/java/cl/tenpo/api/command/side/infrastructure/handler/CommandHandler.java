package cl.tenpo.api.command.side.infrastructure.handler;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import cl.tenpo.api.command.side.application.command.SumValuesCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import scala.compat.java8.FutureConverters;

import java.util.concurrent.CompletionStage;


/**
 * Representa un commandHandler que recepciona distintos tipos de comandos
 *
 * @author daniel.carvajal
 */
@Component
public class CommandHandler implements Handler {

    @Autowired
    @Qualifier("commandBus")
    private ActorRef commandBus;

    /**
     * Recepciona el comando @{@link SumValuesCommand}
     *
     * @param command
     * @return la posible respuesta representada en un futuro
     */
    public CompletionStage<Object> handle(SumValuesCommand command) {
        return FutureConverters.toJava(Patterns.ask(commandBus, command, 5000));
    }
}
