package user.manager.side.infrastructure.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * Mantiene los valores de las propiedades definidas en el application.properties que sirven para app
 *
 * @author daniel.carvajal
 *
 */
@Component
public class UtilProperties {

	public static String USER_COMMAND_SIDE_FINAL_NAME_PROPERTY_VALUE;
	public static String USER_COMMAND_SIDE_FINAL_VERSION_PROPERTY_VALUE;

	@Value("${user.manager.command.side.final.version}")
	public void setUserCommandSideFinalVersionPropertyValue(String userCommandSideFinalVersionPropertyValue) {
		USER_COMMAND_SIDE_FINAL_VERSION_PROPERTY_VALUE = userCommandSideFinalVersionPropertyValue;
	}

	@Value("${user.manager.command.side.final.name}")
	public void setUserCommandSideFinalNamePropertyValue(String userCommandSideFinalNamePropertyValue) {
		USER_COMMAND_SIDE_FINAL_NAME_PROPERTY_VALUE = userCommandSideFinalNamePropertyValue;
	}
}
