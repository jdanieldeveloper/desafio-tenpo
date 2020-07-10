package cl.tenpo.api.command.side.infraestructure.config.akka;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mantiene las configuraciones akka - spring boot
 *
 * @author daniel.carvajal
 */
@Configuration
@ComponentScan({"cl.tenpo.api.command.side"})
public class AkkaConfig {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Bean que mantiene el {@link ActorSystem} de akka
     *
     * @return @{@link ActorSystem}
     */
    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("api-command-side");
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).initialize(applicationContext);
        //
        return actorSystem;
    }
}
