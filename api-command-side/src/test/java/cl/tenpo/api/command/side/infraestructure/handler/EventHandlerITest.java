package cl.tenpo.api.command.side.infraestructure.handler;

import cl.tenpo.api.command.side.application.event.CreatedAdditionEvent;
import cl.tenpo.api.command.side.infraestructure.config.ApiCmdSideTestConfig;
import cl.tenpo.api.command.side.infraestructure.dto.OperationDto;
import cl.tenpo.api.command.side.infraestructure.enums.OperationTypeEnum;
import cl.tenpo.api.command.side.infraestructure.persistence.dao.MathDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel.carvajal on 10-12-2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiCmdSideTestConfig.class})
public class EventHandlerITest {

    @Autowired
    private EventHandler eventHandler;

    @Autowired
    private MathDao mathDao;

    @Test
    public void sendCreatedAdditionEventOk() throws InterruptedException {
        CreatedAdditionEvent event = new CreatedAdditionEvent(5, new int[]{1, 2}, 1, "dcarvajal", new Date());
        eventHandler.handle(event);
        // asynchronous message wait for the return
        Thread.sleep(1000);
        //
        List<OperationDto> operationsDtos =
                mathDao.getOpByTypeAndValues(OperationTypeEnum.ADDITION.getType(), Arrays.toString(event.getValues()));
        Assert.assertTrue(!operationsDtos.isEmpty());
    }
}
