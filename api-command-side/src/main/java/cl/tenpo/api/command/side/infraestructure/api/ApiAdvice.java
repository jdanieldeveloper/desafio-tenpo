package cl.tenpo.api.command.side.infraestructure.api;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * Mantiene las excepciones genericas y su posterior tratamamiento para trasmitir por la api
 *
 */
@ControllerAdvice
public class ApiAdvice extends ResponseEntityExceptionHandler {


}
