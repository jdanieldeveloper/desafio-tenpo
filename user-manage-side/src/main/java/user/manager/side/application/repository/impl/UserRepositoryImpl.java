package user.manager.side.application.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import user.manager.side.domain.aggregate.model.user.Identifier;
import user.manager.side.domain.aggregate.model.user.User;
import user.manager.side.domain.aggregate.model.user.UserRepository;
import user.manager.side.infrastructure.converter.IdentifierConverter;
import user.manager.side.infrastructure.converter.FromUserConverter;
import user.manager.side.infrastructure.dto.PartyDto;
import user.manager.side.infrastructure.dto.RoleDto;
import user.manager.side.infrastructure.persistence.mybatis.dao.PartyDao;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private PartyDao partyDao;

    @Autowired
    IdentifierConverter identifierConverter;

    @Autowired
    private FromUserConverter fromUserConverter;


    @Override
    public Optional<Identifier> getUserId() {
        return identifierConverter.convert(partyDao.nexValueForIdentifier());
    }


    @Override
    public Optional<User> addUser(User user) {
        Optional<User> newUser = Optional.empty();
        //
        boolean isAdd;
        PartyDto partyDto = fromUserConverter.convert(user);

            isAdd = partyDao.saveUserLogin(partyDto);
            if (isAdd) {
                partyDao.saveParty(partyDto);
                //
                if(Objects.nonNull(partyDto.getRoleDtos()) && !partyDto.getRoleDtos().isEmpty()){
                    for(RoleDto role : partyDto.getRoleDtos()){
                        partyDao.saveUserRole(role);
                    }
                }
                newUser = Optional.of(user);
            }
        return newUser;
    }
}
