package user.manager.side.domain.aggregate.model.user;

import java.util.List;
import java.util.Optional;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;


@Data
public class User {

	private final Identifier userId;
	private final Type userType;
	private final String userLogin;
	private final String currentPassword;
	private final Optional<List<Role>> roles;
	private final Optional<Geo> geo;

	public User(Identifier userId, Type userType, String userLogin, String currentPassword, Optional<List<Role>> roles, Optional<Geo> geo) {
		this.userId = userId;
		this.userType = userType;
		this.userLogin = userLogin;
		this.currentPassword = currentPassword;
		//
		this.geo = geo;
		this.roles = roles;
	}

	public User(Identifier userId, Type userType, String userLogin, String currentPassword, Optional<List<Role>> roles) {
		this.userId = userId;
		this.userType = userType;
		this.userLogin = userLogin;
		this.currentPassword = currentPassword;
		//
		this.geo = Optional.empty();
		this.roles = roles;
	}
}
