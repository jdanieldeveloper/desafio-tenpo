package cl.tenpo.api.query.side.infrastructure.config;

import cl.tenpo.api.query.side.infrastructure.util.UtilProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.sql.DataSource;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

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
public class ApiQuerySideConfig {

    /**
     * Bean que permite recuperar un datasource vinculdao con la base datos posgres del registro global JDNI del tomcat
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

    @Bean
    public RouterFunction<ServerResponse> notFound() {
        return RouterFunctions
                .route(GET("/statuses/not-found"),
                        request -> ServerResponse.notFound().build());
    }

}
