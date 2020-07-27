package cl.tenpo.api.command.side.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * Representa un estado de la respuesta en la api
 *
 * @author daniel.carvajal
 *
 */
@Data
public class StatusModel implements Serializable {

    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "date")
    private Date date;

    @JsonProperty(value = "details")
    private String details;

}
