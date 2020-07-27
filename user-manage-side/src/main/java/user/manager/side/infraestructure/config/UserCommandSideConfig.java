package user.manager.side.infraestructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

/**
 *
 * Mantiene la configuracion de la aplicacion y los beans correspondientes.
 *
 * @author daniel.carvajal
 *
 */
@Configuration
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan({"user.manager.side"})
@MapperScan({"user.manager.side.infraestructure.persistence.mybatis.mapper"})
public class UserCommandSideConfig {

    /**
     * Bean que permite recuperar un datasource
     *
     * @return el datasource
     */
    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        return  new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2) //.H2
                .addScript("db/h2/sql/schema-hsqldb.sql")
                .addScript("db/h2/sql/data-hsqldb.sql")
        .build();
    }
}
