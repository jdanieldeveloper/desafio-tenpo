package user.manager.side.infraestructure.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.manager.side.application.command.CreateGroupCommand;
import user.manager.side.application.command.CreateUserCommand;
import user.manager.side.infraestructure.bus.CommandBus;

@Component
public class CommandHandler {

	@Autowired
	private CommandBus commandBus;

	public void handle(CreateUserCommand command) {
		commandBus.handle(command);
	}

	public void handle(CreateGroupCommand command) {
		//commandBus.handle(command);
	}
}
