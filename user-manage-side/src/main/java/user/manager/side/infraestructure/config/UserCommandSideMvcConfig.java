package user.manager.side.infraestructure.config;

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
import user.manager.side.infraestructure.util.UtilProperties;

/**
 *
 * Mantiene la configuracion de la aplicacion y los beans correspondientes al Spring MVC
 *
 * @author daniel.carvajal
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@EnableSwagger2
@ComponentScan({"user.manager.side"})
public class UserCommandSideMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UtilProperties utilProperties;


    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/" };

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
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**")
                    .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
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
                .apis(RequestHandlerSelectors
                        .basePackage("user.manager.command.side.infraestructure.api"))
                .paths(PathSelectors.regex("/.*"))
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
                .title("User Manager Command Side REST API")
                    .description("REST API que mantiene las operaciones relacionados con el dominio de los usuarios")
                    .version(UtilProperties.USER_COMMAND_SIDE_FINAL_VERSION_PROPERTY_VALUE)
                .build();
    }

}
