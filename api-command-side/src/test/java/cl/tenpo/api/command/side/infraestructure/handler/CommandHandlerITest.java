package cl.tenpo.api.command.side.infraestructure.handler;

import akka.actor.Status;
import cl.tenpo.api.command.side.application.command.SumValuesCommand;
import cl.tenpo.api.command.side.infraestructure.config.ApiCmdSideTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by daniel.carvajal on 10-12-2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiCmdSideTestConfig.class})
public class CommandHandlerITest {

    @Autowired
    private CommandHandler commandHandler;

    @Test
    public void sendSumValuesCommandAndReceiveOk() throws ExecutionException, InterruptedException {
        SumValuesCommand command = (SumValuesCommand)
                ((CompletableFuture<Object>) commandHandler.handle(
                        new SumValuesCommand(   1, // value1
                                                2  // value2
                        )
                )).get();
        //
        Assert.assertTrue(command.getStatus() instanceof Status.Success);
        Status.Success success = (Status.Success) command.getStatus();
        Assert.assertEquals(((Integer) success.status()).intValue(), 3);
    }
}
