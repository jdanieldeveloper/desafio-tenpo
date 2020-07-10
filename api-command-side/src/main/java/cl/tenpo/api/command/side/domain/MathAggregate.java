package cl.tenpo.api.command.side.domain;

import cl.tenpo.api.command.side.application.aggregate.Aggregate;
import cl.tenpo.api.command.side.application.command.SumValuesCommand;
import cl.tenpo.api.command.side.application.event.CreatedAdditionEvent;
import cl.tenpo.api.command.side.infraestructure.bus.CommandBusActor;


public abstract class MathAggregate implements Aggregate {

    /**
     * Metodo receptor del comando @{@link EditEquipoCommand} que tiene el proposito de editar
     * un equipo. Este metodo guarda la integridad de las operaciones al ser transaccional, si existe
     * un error el realiza un rollback de las operaciones que se ejecutaron y informara al @{@link CommandBusActor}
     * encansulandolo en un @{@link akka.actor.Status.Failure}. En caso de exito se informa al {@link CommandBusActor}
     * encapsulandolo en un {@link akka.actor.Status.Success}
     *
     * @param command
     */
    public abstract void handle(SumValuesCommand command);

    public Addition makeAddition(int... values){
        return Addition.builder().values(values).build();
    }

    public abstract void on(CreatedAdditionEvent event);
}
