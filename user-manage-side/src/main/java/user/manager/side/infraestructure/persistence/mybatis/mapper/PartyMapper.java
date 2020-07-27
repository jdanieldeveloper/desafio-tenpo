package user.manager.side.infraestructure.persistence.mybatis.mapper;

import user.manager.side.infraestructure.dto.PartyDto;
import org.apache.ibatis.annotations.Mapper;
import user.manager.side.infraestructure.dto.RoleDto;

@Mapper
public interface PartyMapper {

	Long nexValueForIdentifier();	
	
	int saveParty(PartyDto partyDto);
	
	int saveUserLogin(PartyDto partyDto);

	int saveRole(RoleDto roleDto);
	
}
