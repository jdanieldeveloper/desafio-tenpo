package user.manager.side.application.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import user.manager.side.infrastructure.dto.PartyDto;
import user.manager.side.infrastructure.dto.RoleDto;
import user.manager.side.infrastructure.persistence.mybatis.dao.PartyDao;

import java.util.List;
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
