package cl.tenpo.api.command.side.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"cl.tenpo.api.command.side"})
@MapperScan({"cl.tenpo.api.command.side.infrastructure.persistence.mybatis.mapper"})
public class ApiCmdSideTestConfig {

    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        return  new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2) //.H2
                .addScript("db/sql/schema-hsqldb.sql")
                .addScript("db/sql/data-hsqldb.sql")
        .build();
    }
}
