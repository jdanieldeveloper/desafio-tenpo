package cl.tenpo.api.command.side.infrastructure.persistence.mybatis.mapper;

import cl.tenpo.api.command.side.infrastructure.dto.OperationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz entre el mapeo de base datos y los dto's de la aplicacion relacionados con los equipos
 *
 * @author daniel.carvajal
 */
@Mapper
public interface OperationMapper {

    Long selectOperationIdFromSeq();

    /**
     * Mapea los parametros necesarios para actualizar un equipo
     *
     * @param operationDto
     * @return la cantiadad de filas actualiadas
     */
    int insertOperation(OperationDto operationDto);


    List<OperationDto> selectOpByTypeAndValues(@Param("type") String type, @Param("values") String values);

}
