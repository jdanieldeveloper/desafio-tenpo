package cl.tenpo.api.command.side.domain;

import java.util.Optional;

/**
 * Repositorio que contiene las operaciones relacionadas con los equipos y se comunica al exterior del dominio
 *
 * @author daniel.carvajal
 *
 */
public interface MathRepository {

    long getOperationId();

    Optional<Addition> getAdditionOpByValues(int... values);

}