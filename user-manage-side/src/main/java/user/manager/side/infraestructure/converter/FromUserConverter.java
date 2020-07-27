package user.manager.side.infraestructure.converter;

import user.manager.side.domain.aggregate.model.user.UserIdentifier;
import user.manager.side.domain.aggregate.model.user.User;
import user.manager.side.infraestructure.dto.PartyDto;
import org.springframework.stereotype.Component;
import user.manager.side.infraestructure.dto.RoleDto;

import java.util.stream.Collectors;


@Component
public class FromUserConverter implements Converter<User, PartyDto>{

	public PartyDto convert(User user) {
		PartyDto partyDto = new PartyDto();
		partyDto.setPartyId(((UserIdentifier)user.getUserId()).getIdentifier());
		partyDto.setUserLoginId(user.getUserLogin());
		partyDto.setCurrentPassword(user.getCurrentPassword());
		if(user.getRoles().isPresent()) {
            partyDto.setRoleDtos(
                    user
                        .getRoles().get()
                            .stream()
                                .map(role -> {
                                    RoleDto roleDto = new RoleDto();
                                    roleDto.setPartyId(partyDto.getPartyId());
                                    roleDto.setRoleTypeId(role.getRoleTypeId());
                                    roleDto.setDescription(role.getRoleDescription());
                                    return roleDto;

                                })
                     .collect(Collectors.toList()));
        }
		return partyDto;
	}
}
