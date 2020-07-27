package cl.tenpo.api.command.side.infrastructure.persistence.dao.impl;

import cl.tenpo.api.command.side.infrastructure.dto.OperationDto;
import cl.tenpo.api.command.side.infrastructure.persistence.mybatis.mapper.OperationMapper;
import cl.tenpo.api.command.side.infrastructure.persistence.dao.MathDao;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Contiene las implementaciones para el Dao de los equipos, manteninedo todas las operaciones que tienen relacion con los equipos
 *
 * @author daniel.carvajal
 */
@Slf4j
@Component
public class MathDaoImpl implements MathDao {

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public Long nextOperationId() {
        long operationId = 0;
        try {
            operationId = operationMapper.selectOperationIdFromSeq();
            if (operationId > 0) {
                log.info("[{}] It was generated [operationId : {}] successful!!!", MathDaoImpl.class.getSimpleName(), operationId);
            } else {
                if (log.isWarnEnabled()) {
                    log.warn("[{}] Error of data consistency. It wasn't generated [operationId] successful!!!", MathDaoImpl.class.getSimpleName());
                }
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[{}] Exception to generate [operationId] from the database!!!", MathDaoImpl.class.getSimpleName());
                log.error(e.getMessage(), e);
            }
        }
        return operationId;
    }

    @Override
    public boolean saveOperation(OperationDto operationDto) {
        int rowAffected;
        boolean isSaved = false;
        try {
            // save party
            rowAffected = operationMapper.insertOperation(operationDto);
            if (rowAffected == 1) {
                isSaved = true;
                //
                log.info("[{}] Insert operation correctly [{}]!!!", MathDaoImpl.class.getSimpleName(), operationDto);
            } else {
                if (log.isWarnEnabled()) {
                    log.warn("[{}] Error of data consistency. It wasn't inserted [{}]!!!", MathDaoImpl.class.getSimpleName(), operationDto);
                }
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[{}] Exception to insert operation from the database [{}]!!!", MathDaoImpl.class.getSimpleName(), operationDto);
                log.error(e.getMessage(), e);
            }
        }
        return isSaved;
    }

    @Override
    public List<OperationDto> getOpByTypeAndValues(String type, String values) {
        List<OperationDto> operationDtos = Lists.newArrayList();
        try {
            operationDtos = operationMapper.selectOpByTypeAndValues(type, values);
            //
            log.info("[{}] Found [{}] operations by type[{}] and values[{}]!!! ",
                    OperationMapper.class.getSimpleName(), operationDtos.size(), type, values );

        }catch (Exception e){
            if(log.isErrorEnabled()) {
                log.error("[{}] Error when find operations by type[{}] and values[{}] !!!",
                        OperationMapper.class.getSimpleName(), type, values);
                log.error(e.getMessage(), e);
            }
        }
        return operationDtos;
    }
}
