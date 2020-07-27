package user.manager.side.infraestructure.persistence.mybatis.dao;

import user.manager.side.infraestructure.dto.PartyDto;
import user.manager.side.infraestructure.dto.RoleDto;

/**
 *
 *
 * @author daniel.carvajal
 */
public interface PartyDao extends Dao {

    Long nexValueForIdentifier();

    boolean saveParty(PartyDto partyDto);

    boolean saveUserLogin(PartyDto partyDto);

    boolean saveUserRole(RoleDto roleDto);

}
