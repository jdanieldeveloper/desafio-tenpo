package cl.tenpo.api.command.side.infrastructure.persistence.mybatis.mapper;

import cl.tenpo.api.command.side.infrastructure.dto.PartyDto;
import cl.tenpo.api.command.side.infrastructure.dto.RoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PartyMapper {

	PartyDto findPartyByUserLoginId(@Param("userLoginId") String userLoginId);

	List<RoleDto> findRoleByUserLoginId(@Param("userLoginId") String userLoginId);
	
}
