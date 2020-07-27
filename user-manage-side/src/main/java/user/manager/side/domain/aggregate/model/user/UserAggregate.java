package user.manager.side.domain.aggregate.model.user;

import user.manager.side.application.aggregate.Aggregate;
import user.manager.side.application.command.CreateUserCommand;
import user.manager.side.application.event.CreatedUserEvent;

import java.util.List;
import java.util.Optional;

public abstract class UserAggregate implements Aggregate {

    public void handle(CreateUserCommand command) {
    }

    public User makePerson(Identifier userId, Type userType, String userLogin, String currentPassword, Optional<List<Role>> roles) {
        return new User(userId, userType, userLogin, currentPassword, roles);
    }

    public Role makeRole(String roleId, String description)  {
        return new Role(roleId, description);
    }

    public Geo makeGeo(Identifier geoId, Location city, Location comune, Location province, Location region, Location country) {
        return new Geo(geoId, city, comune, province, region, country, Optional.empty(), Optional.empty());
    }

    public void onEvent(CreatedUserEvent event){
    }
}