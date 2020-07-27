package cl.tenpo.api.command.side.infrastructure.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import cl.tenpo.api.command.side.infrastructure.bus.CommandBusActor;
import cl.tenpo.api.command.side.infrastructure.config.akka.SpringExtension;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 *
 * Mantiene la configuracion de la aplicacion y los beans correspondientes.
 * Para mas configuraciones mire el archivo application.properties
 *
 * @author daniel.carvajal
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({"cl.tenpo.api.command.side"})
@MapperScan({"cl.tenpo.api.command.side.infrastructure.persistence.mybatis.mapper"})
public class ApiCmdSideConfig {

    @Autowired
    private ActorSystem actorSystem;

    /**
     * Bean que permite recuperar un datasource vinculado con la base datos posgres del registro global JDNI del tomcat
     *
     * @return el datasource
     */
    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        return  new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2) //.H2
                .addScript("db/sql/schema-hsqldb.sql")
                .addScript("db/sql/data-hsqldb.sql")
                .build();
    }

    /**
     * Bean que mantiene el {@link ActorRef} del {@link CommandBusActor}
     *
     * @return la referencia para el commandBusActor
     */
    @Bean(name = "commandBus")
    public ActorRef commandBus() {
        return actorSystem.actorOf(
                SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("commandBusActor"), "commandBus");
    }
    @Bean(name = "eventBus")
    public ActorRef eventBus() {
        return actorSystem.actorOf(
                SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("eventBusActor"), "eventBus");
    }
}
