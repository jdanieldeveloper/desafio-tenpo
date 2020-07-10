package cl.tenpo.api.command.side.application.command;

import lombok.Data;

/**
 *  Representa un comando que se envia a la aplicacion con el proposito de registrar un nuevo grupo de equipo
 *
 *  @author daniel.carvajal
 */
@Data
public class SumValuesCommand implements Command {

    private int val1;

    private int val2;

    private Object status;

    public SumValuesCommand(int val1, int val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
}
