package user.manager.side.infraestructure.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import user.manager.side.application.event.CreatedUserEvent;
import user.manager.side.infraestructure.config.UserCommandSideConfig;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserCommandSideConfig.class})
public class EventHandlerITest {

    @Autowired
    private EventHandler eventHandler;

    @Test
    public void testSendCreatedUserEvent()  {
        //
        CreatedUserEvent createdUserEvent =
                new CreatedUserEvent("11111111-1",
                                     "Bart",
                                      "Simpsom");
        eventHandler.handler(createdUserEvent);
    }
}
