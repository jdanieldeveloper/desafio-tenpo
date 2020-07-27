package user.manager.side.application.command;

import lombok.Value;

@Value
public class CreateUserCommand {
	public String userLogin;
	public String currentPassword;
}
