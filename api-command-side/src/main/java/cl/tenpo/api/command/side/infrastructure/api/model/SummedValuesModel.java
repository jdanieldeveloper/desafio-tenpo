package cl.tenpo.api.command.side.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by daniel.carvajal on 19-02-2019.
 */
@Data
public class SummedValuesModel {

    @JsonProperty(value = "result")
    private int result;

    @JsonProperty(value = "estatus")
    private StatusModel estatus;

}
