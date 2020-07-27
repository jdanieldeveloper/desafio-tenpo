package user.manager.side.application.repository.impl;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import user.manager.side.domain.aggregate.model.user.Identifier;
import user.manager.side.domain.aggregate.model.user.Role;
import user.manager.side.domain.aggregate.model.user.Type;
import user.manager.side.domain.aggregate.model.user.User;
import user.manager.side.domain.aggregate.model.user.UserIdentifier;
import user.manager.side.domain.aggregate.model.user.UserRepository;
import user.manager.side.domain.aggregate.model.user.UserTypeIdentifier;
import user.manager.side.infraestructure.config.UserCommandSideConfig;
import user.manager.side.infraestructure.enums.RoleEnum;
import user.manager.side.infraestructure.enums.UserTypeEnum;

import java.util.Optional;


/**
 * Created by daniel.carvajal
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserCommandSideConfig.class})
public class UserRepositoryITest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUseId() {
        Optional<Identifier> identifier = userRepository.getUserId();
        Assert.assertTrue(identifier.isPresent());
    }

    @Test
    public void addUser() {
        // identifier
        Optional<Identifier> identifier = userRepository.getUserId();
        Assert.assertTrue(identifier.isPresent());
        UserIdentifier userIdentifier = (UserIdentifier) identifier.get();

        // userType
        Type userType = new Type(new UserTypeIdentifier(UserTypeEnum.VISITOR.getTypeId()));

        User user = new User(userIdentifier, // identifier
                             userType, // userType
                            "dcarvajal", // userLogin
                            "123456789", // currentPassword
                             Optional.empty()); // roles

        // add the user person
        Optional<User> addUser = userRepository.addUser(user);
        Assert.assertTrue(addUser.isPresent());
    }

    @Test
    public void addUserWithRoles() {
        // identifier
        Optional<Identifier> identifier = userRepository.getUserId();
        Assert.assertTrue(identifier.isPresent());
        UserIdentifier userIdentifier = (UserIdentifier) identifier.get();

        // userType
        Type userType = new Type(new UserTypeIdentifier(UserTypeEnum.VISITOR.getTypeId()));

        // roles for default
        Role readRole  = new Role(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
        Role createRole = new Role(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());

        User user = new User(userIdentifier, // identifier
                userType, // userType
                "dcarvajal", // userLogin
                "123456789", // currentPassword
                Optional.of(Lists.newArrayList(createRole, readRole))); // roles

        // add the user person
        Optional<User> addUser = userRepository.addUser(user);
        Assert.assertTrue(addUser.isPresent());
    }
}
