package user.manager.side.infrastructure.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import user.manager.side.domain.aggregate.model.user.UserIdentifier;
import user.manager.side.domain.aggregate.model.user.User;
import user.manager.side.domain.aggregate.model.user.UserTypeIdentifier;
import user.manager.side.infrastructure.dto.PartyDto;
import org.springframework.stereotype.Component;
import user.manager.side.infrastructure.dto.RoleDto;

import java.util.stream.Collectors;


@Component
public class FromUserConverter implements Converter<User, PartyDto>{

	public PartyDto convert(User user) {
		PartyDto partyDto = new PartyDto();
		partyDto.setPartyId(((UserIdentifier)user.getUserId()).getIdentifier());
		partyDto.setPartyType(((UserTypeIdentifier) user.getUserType().getTypeId()).getIdentifier());
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
