package user.manager.side.infrastructure.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import user.manager.side.infrastructure.util.UtilProperties;

/**
 *
 * Mantiene la configuracion de la aplicacion y los beans correspondientes al Spring MVC
 *
 * @author daniel.carvajal
 *
 */
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class UserManageSideMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UtilProperties utilProperties;

    /**
     * Contiene la configuracion global para agregar CORS a las peticiones de los controladores
     *
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "DELETE", "GET", "POST");
    }


    /**
     * Bean que mapea los recursos para que se exponga los recursos web y swagger
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("swagger-ui.html")) {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
        }

        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }

        if (!registry.hasMappingForPattern("/index.html")) {
            registry.addResourceHandler("/index.html")
                    .addResourceLocations("classpath:/static/");
        }
    }

    /**
     * Bean que selecciona todos los endpoint expuestos en la ruta señalada para swagger
     *
     * @return los endpoint
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .apiInfo(apiEndPointsInfo());
    }

    /**
     * Bean que configura y documenta la api expuesta para swagger
     *
     * @return la informacion para la api
     */
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("Api Tenpo User Manager Side REST API")
                .description("REST API contains creation and auth for user")
                .version(UtilProperties.USER_COMMAND_SIDE_FINAL_VERSION_PROPERTY_VALUE)
                .build();
    }

    // Only select apis that matches the given Predicates.
    private Predicate<String> paths() {
        // Match all paths except /error
        return Predicates.and(
                PathSelectors.regex("/.*"),
                Predicates.not(PathSelectors.regex("/error.*"))
        );
    }

}
