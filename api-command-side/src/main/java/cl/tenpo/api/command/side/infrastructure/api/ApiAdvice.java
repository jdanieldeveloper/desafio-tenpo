package cl.tenpo.api.command.side.infrastructure.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * Mantiene las excepciones genericas y su posterior tratamamiento para trasmitir por la api
 *
 */
@ControllerAdvice
public class ApiAdvice extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, "404 Pagina no encontrada :-(", headers, status, request);
    }
}
