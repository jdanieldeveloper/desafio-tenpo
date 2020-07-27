package cl.tenpo.api.query.side.infrastructure.util;


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

	public static String SERVER_SERVLET_CONTEXT_PATH;
	public static String API_QUERY_SIDE_FINAL_NAME_PROPERTY_VALUE;
	public static String API_QUERY_SIDE_FINAL_VERSION_PROPERTY_VALUE;
	public static String API_QUERY_SIDE_PAGEABLE_DEFAULT_PAGE;


	@Value("${server.servlet.context-path}")
	public void setServerServletContextPath(String serverServletContextPath) {
		SERVER_SERVLET_CONTEXT_PATH = serverServletContextPath;
	}

	@Value("${api.query.side.final.name}")
	public void setApiQuerySideFinalNamePropertyValue(String apiQuerySideFinalNamePropertyValue) {
		API_QUERY_SIDE_FINAL_NAME_PROPERTY_VALUE = apiQuerySideFinalNamePropertyValue;
	}

	@Value("${api.query.side.final.version}")
	public void setApiQuerySideFinalVersionPropertyValue(String apiQuerySideFinalVersionPropertyValue) {
		API_QUERY_SIDE_FINAL_VERSION_PROPERTY_VALUE = apiQuerySideFinalVersionPropertyValue;
	}

	@Value("${api.query.side.pageable.default-page-size}")
	public void setApiQuerySidePageableDefaultPage(String apiQuerySidePageableDefaultPage) {
		API_QUERY_SIDE_PAGEABLE_DEFAULT_PAGE = apiQuerySidePageableDefaultPage;
	}
}
