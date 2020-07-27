package cl.tenpo.api.command.side.application.aggregate.impl;

import akka.actor.Status;
import cl.tenpo.api.command.side.application.command.SumValuesCommand;
import cl.tenpo.api.command.side.domain.Addition;
import cl.tenpo.api.command.side.domain.MathRepository;
import cl.tenpo.api.command.side.infrastructure.handler.EventHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;


/**
 * Created by daniel.carvajal handle 10-12-2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class MathAggregateTest {

    @Mock
    private MathRepository mathRepository;

    @Mock
    private EventHandler eventHandler;

    @InjectMocks
    private MathAggregateImpl mathAggregate;

    @Test
    public void handleSumValuesCommandOk(){
        Mockito.when(
                mathRepository.getAdditionOpByValues(Mockito.any()))
                        .thenReturn(Optional.empty());

        SumValuesCommand command = new SumValuesCommand(1, 2);
        // command is a mutable
        mathAggregate.handle(command);

        Assert.assertTrue(command.getStatus() instanceof Status.Success);
        Status.Success success = (Status.Success) command.getStatus();
        Assert.assertEquals(((Integer) success.status()).intValue(), 3);
    }

    @Test
    public void handleSumValuesCommandWithBeforeValues(){
        Addition addition = mathAggregate.makeAddition(2, 2);
        addition.apply();
        Mockito.when(
                mathRepository.getAdditionOpByValues(Mockito.any()))
                    .thenReturn(Optional.of(addition));

        SumValuesCommand command = new SumValuesCommand(2, 2);
        // command is a mutable
        mathAggregate.handle(command);

        Assert.assertTrue(command.getStatus() instanceof Status.Success);
        Status.Success success = (Status.Success) command.getStatus();
        Assert.assertEquals(((Integer) success.status()).intValue(), 4);
    }

    @Test
    public void handleSumValuesCommandWithException(){
        Mockito.when(
                mathRepository.getAdditionOpByValues(Mockito.any()))
                .thenThrow(new NullPointerException());

        SumValuesCommand command = new SumValuesCommand(2, 2);
        // command is a mutable
        mathAggregate.handle(command);

        Assert.assertTrue(command.getStatus() instanceof Status.Failure);
        Status.Failure failure = (Status.Failure) command.getStatus();
        Assert.assertTrue(failure.cause() instanceof NullPointerException);
    }
}
