package cl.tenpo.api.command.side.application.repository.impl;

import cl.tenpo.api.command.side.domain.Addition;
import cl.tenpo.api.command.side.infrastructure.enums.OperationTypeEnum;
import cl.tenpo.api.command.side.infrastructure.persistence.dao.MathDao;
import cl.tenpo.api.command.side.domain.MathRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

import static cl.tenpo.api.command.side.infrastructure.converter.Converters.toAddition;

/**
 * Repositorio que contiene las operaciones relacionadas con los equipos y se comunica al exterior del dominio
 *
 * @author daniel.carvajal
 */
@Slf4j
@Repository
public class MathRepositoryImpl implements MathRepository {

    @Autowired
    private MathDao mathDao;


    @Override
    public long getOperationId() {
        return mathDao.nextOperationId();
    }

    @Override
    public Optional<Addition> getAdditionOpByValues(int... values) {
        return mathDao.getOpByTypeAndValues(OperationTypeEnum.ADDITION.getType(), Arrays.toString(values))
                .stream()
                .map(toAddition)
                .filter(add -> !add.isEmpty())
         .findFirst();
    }
}
