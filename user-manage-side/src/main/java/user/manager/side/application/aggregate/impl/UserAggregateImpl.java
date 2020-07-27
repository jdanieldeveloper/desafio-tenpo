package user.manager.side.application.aggregate.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import user.manager.side.application.command.CreateUserCommand;
import user.manager.side.application.event.CreatedUserEvent;
import user.manager.side.domain.aggregate.model.user.Identifier;
import user.manager.side.domain.aggregate.model.user.Role;
import user.manager.side.domain.aggregate.model.user.Type;
import user.manager.side.domain.aggregate.model.user.User;
import user.manager.side.domain.aggregate.model.user.UserAggregate;
import user.manager.side.domain.aggregate.model.user.UserIdentifier;
import user.manager.side.domain.aggregate.model.user.UserRepository;
import user.manager.side.domain.aggregate.model.user.UserTypeIdentifier;
import user.manager.side.infraestructure.enums.RoleEnum;
import user.manager.side.infraestructure.enums.UserTypeEnum;
import user.manager.side.infraestructure.handler.EventHandler;

import java.util.Optional;

@Slf4j
@Component
public class UserAggregateImpl extends UserAggregate {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventHandler eventHandler;


    @Override
    @Transactional
    public void handle(CreateUserCommand command) {
        Optional<Identifier> identifier = userRepository.getUserId();
        if(identifier.isPresent()){
            // identifier
            UserIdentifier userIdentifier = (UserIdentifier) identifier.get();
            // userType
            Type userType = new Type(new UserTypeIdentifier(UserTypeEnum.VISITOR.getTypeId()));
            // roles
            Role read = makeRole(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
            Role create = makeRole(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
            // user
            User user =
                    makePerson(userIdentifier, // identifier
                               userType, // userType
                               command.getUserLogin(), // user login
                               command.getCurrentPassword(), // password
                               Optional.of(Lists.newArrayList(read,create))); // roles
            // add the user
            Optional<User> addUser = userRepository.addUser(user);

            //
            if(addUser.isPresent()){
                onEvent(new CreatedUserEvent(addUser.get().getUserId().toString(), addUser.get().getUserLogin()));

                //TODO return success message
            }else{
                //TODO returns fail message
            }
        }else{
            //TODO returns fail message
        }
    }


    @Override
    public void onEvent(CreatedUserEvent event){
        eventHandler.handler(event);
    }
}
