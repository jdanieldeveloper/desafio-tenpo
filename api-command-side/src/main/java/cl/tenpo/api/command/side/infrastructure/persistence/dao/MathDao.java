package cl.tenpo.api.command.side.infrastructure.persistence.dao;

import cl.tenpo.api.command.side.infrastructure.dto.OperationDto;

import java.util.List;

/**
 * Contiene las implementaciones para el Dao de los equipos, manteninedo todas las operaciones que tienen relacion con los equipos
 *
 * @author daniel.carvajal
 */
public interface MathDao extends Dao {


    public Long nextOperationId();

    /**
     * Actualiza en base datos un equipo
     *
     * @param operationDto
     * @return si el equipo se actualizo
     */
    boolean saveOperation(OperationDto operationDto);


    List<OperationDto> getOpByTypeAndValues(String type, String values);
}
