package user.manager.side.domain.aggregate.model.user;

import java.util.Optional;

public interface UserRepository {

    Optional<Identifier> getUserId();

    Optional<User> addUser(User user);

}