package cl.tenpo.api.command.side.infraestructure.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * Mantiene los valores de las propiedades definidas en el application.properties
 *
 * @author daniel.carvajal
 *
 */
@Component
public class UtilProperties {

	public static String API_COMMAND_SIDE_FINAL_NAME_PROPERTY_VALUE;
	public static String API_COMMAND_SIDE_FINAL_VERSION_PROPERTY_VALUE;

	@Value("${api.command.side.final.version}")
	public void setApiCommandSideFinalVersionPropertyValue(String apiCommandSideFinalVersionPropertyValue) {
		API_COMMAND_SIDE_FINAL_VERSION_PROPERTY_VALUE = apiCommandSideFinalVersionPropertyValue;
	}

	@Value("${api.command.side.final.name}")
	public void setApiCommandSideFinalNamePropertyValue(String apiCommandSideFinalNamePropertyValue) {
		API_COMMAND_SIDE_FINAL_NAME_PROPERTY_VALUE = apiCommandSideFinalNamePropertyValue;
	}
}
