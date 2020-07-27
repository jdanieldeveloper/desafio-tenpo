package cl.tenpo.api.gateway.side.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 *
 * Mantiene el inicio de la aplicacion
 *
 * @author daniel.carvajal
 *
 */

@EnableConfigurationProperties
@SpringBootApplication(exclude = ReactiveUserDetailsServiceAutoConfiguration.class)
public class ApiGatewaySideApp {

    public static void main(String args[]) {
        SpringApplication.run(ApiGatewaySideApp.class, args);

    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("api.command.side",r ->
                        r.path("/api/math/operation/sum")
                                .filters(f ->
                                        f.rewritePath("/api/math/operation/sum",
                                                "/api-command-side/api/math/operation/sum")
                                ).uri("http://localhost:8081")
                )
                .route("api.query.side",r ->
                        r.path("/api/math/operations/**/sum/**")
                                .filters(f ->
                                        f.rewritePath("/api/math/operations/<user>/sum/<page>",
                                                "/api-query-side/api/math/operations/${user}/sum/${page}"))
                                .uri("http://localhost:8082")
                )
        .build();
    }
}
