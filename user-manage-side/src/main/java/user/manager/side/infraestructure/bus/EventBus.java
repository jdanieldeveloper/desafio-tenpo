package user.manager.side.infraestructure.bus;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.manager.side.application.aggregate.impl.UserAggregateImpl;
import user.manager.side.application.command.CreateUserCommand;

@Slf4j
@Component
public class EventBus implements Bus {

    @Autowired
    private UserAggregateImpl userAggregate;

    public void handle(CreateUserCommand command){
        userAggregate.handle(command);
    }


}
