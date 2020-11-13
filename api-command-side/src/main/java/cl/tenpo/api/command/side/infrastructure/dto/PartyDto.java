package cl.tenpo.api.command.side.infrastructure.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PartyDto {
	// party fields
	private Long partyId;
	private String partyType;
	private String description;
	private String statusId;

	private Date createdDate;
	private String createByUserLogin;

	private Date lastModifiedDate;
	private String lastModifiedByUserLogin;

	// user login fields
	private String userLoginId;
	private String currentPassword;

	private List<RoleDto> roleDtos;

}
