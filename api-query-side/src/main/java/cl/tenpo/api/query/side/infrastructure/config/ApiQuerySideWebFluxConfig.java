package cl.tenpo.api.query.side.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Mantiene la configuracion de la aplicacion y los beans correspondientes al spring reactive
 * Para mas configuraciones mire el archivo application.properties
 *
 * @author daniel.carvajal
 */
@Configuration
@ComponentScan({"cl.tenpo.api.query.side"})
public class ApiQuerySideWebFluxConfig implements WebFluxConfigurer {

    /**
     * Contiene la configuracion global para agregar CORS a las peticiones de los controladores
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "DELETE", "GET", "POST");
    }


    /**
     * Bean que mapea los recursos para que se expongas los recursos web y swagger
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/index.html")) {
            registry.addResourceHandler("/index.html")
                    .addResourceLocations("classpath:/static/");
        }
    }
}
