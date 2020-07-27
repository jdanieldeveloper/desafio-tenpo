package user.manager.side.application.command;

import lombok.Value;

@Value
public class CreateGroupCommand {
	public String tributarySingleRole;
	public String groupName;

	// todo add 1 ...n fields
}
