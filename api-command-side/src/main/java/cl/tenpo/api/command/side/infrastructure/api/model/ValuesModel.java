package cl.tenpo.api.command.side.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 *
 * Representa un device model de la api
 *
 * @author daniel.carvajal
 *
 */
@Data
public class ValuesModel {

    @JsonProperty(value = "value1")
    public int value1;

    @JsonProperty(value = "value2")
    public int value2;

}
