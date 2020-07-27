package cl.tenpo.api.command.side.infrastructure.dto;

import lombok.Data;

import java.util.Date;

/**
 *
 * Representa un EquipoDto
 *
 * @author daniel.carvajal
 *
 */
@Data
public class OperationDto implements Dto {

    private long operationId;

    private String name;

    private String type;

    private String description;

    private String createdBy;

    private Date createdDate;

    private String values;

    private int result;

    private String details;

}
