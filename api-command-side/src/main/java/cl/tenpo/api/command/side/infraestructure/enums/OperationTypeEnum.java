package cl.tenpo.api.command.side.infraestructure.enums;

/**
 * Representa los tipos de vehiculos en el sistema de sinoptico
 *
 * @author daniel.carvajal
 */
public enum OperationTypeEnum {
    ADDITION("ADD", "Addition", "Addition Operation"),
    SUBTRACTION("SUB", "Subtraction", "Subtraction Operation"),
    MULTIPLICATION("MULT", "Multiplication", "Multiplication Operation"),
    DIVISION("DIV", "Division", "Division Operation");

    private final String type;
    private final String name;
    private final String description;

    private OperationTypeEnum(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
