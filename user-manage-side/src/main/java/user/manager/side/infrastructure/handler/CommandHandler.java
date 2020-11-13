package user.manager.side.infrastructure.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.manager.side.application.command.CreateUserCommand;
import user.manager.side.infrastructure.bus.CommandBus;

@Component
public class CommandHandler {

	@Autowired
	private CommandBus commandBus;

	public void handle(CreateUserCommand command) {
		commandBus.handle(command);
	}

}
