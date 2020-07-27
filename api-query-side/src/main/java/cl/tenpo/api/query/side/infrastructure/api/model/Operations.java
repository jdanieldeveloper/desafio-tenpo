package cl.tenpo.api.query.side.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * Representa un modelo de la api
 *
 * @author daniel.carvajal
 *
 */
@Data

public class Operations {

    @JsonProperty(value = "operationId")
    private long operationId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "description")
    private String descripcion;

    @JsonProperty(value = "values")
    private String value;

    @JsonProperty(value = "result")
    private int result;

    @JsonProperty(value = "createdBy")
    private String createdBy;

    @JsonProperty(value = "createdDate")
    private LocalDateTime createdDate;

    @JsonProperty(value = "details")
    private String details;

}
