package cl.tenpo.api.command.side.application.service;

import cl.tenpo.api.command.side.infrastructure.dto.PartyDto;
import cl.tenpo.api.command.side.infrastructure.dto.RoleDto;
import cl.tenpo.api.command.side.infrastructure.persistence.dao.PartyDao;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PartyDao partyDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PartyDto partyDto = partyDao.findPartyByUserLoginId(username);
        // Roles
        Set<SimpleGrantedAuthority> authorities = Sets.newHashSet();
        if(Objects.nonNull(partyDto) && Objects.nonNull(partyDto.getRoleDtos())){
            for(RoleDto roleDto : partyDto.getRoleDtos()){
             SimpleGrantedAuthority authority = new SimpleGrantedAuthority(String.format("ROLE_%s", roleDto.getRoleTypeId()));
             authorities.add(authority);
            }
        }

        if (partyDto == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(partyDto.getUserLoginId(), partyDto.getCurrentPassword(), authorities);
    }
}
