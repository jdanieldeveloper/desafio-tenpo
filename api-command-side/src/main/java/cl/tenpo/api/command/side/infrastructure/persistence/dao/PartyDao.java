package cl.tenpo.api.command.side.infrastructure.persistence.dao;

import cl.tenpo.api.command.side.infrastructure.dto.PartyDto;
import cl.tenpo.api.command.side.infrastructure.dto.RoleDto;

import java.util.List;

/**
 *
 *
 * @author daniel.carvajal
 */
public interface PartyDao extends Dao {

    PartyDto findPartyByUserLoginId(String userLoginId);

    List<RoleDto> findRoleByUserLoginId(String userLoginId);

}
