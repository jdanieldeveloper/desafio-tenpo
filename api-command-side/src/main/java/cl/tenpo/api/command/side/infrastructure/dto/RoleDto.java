package cl.tenpo.api.command.side.infrastructure.dto;

import lombok.Data;

@Data
public class RoleDto {	
	Long partyId;
	String roleTypeId;
	String description;
	
}
