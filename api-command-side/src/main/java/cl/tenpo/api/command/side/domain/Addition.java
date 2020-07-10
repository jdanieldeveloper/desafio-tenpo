package cl.tenpo.api.command.side.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;

@Getter
@Builder
public class Addition implements Operation {

    private int[] values;

    private int result;

    public void apply() {
        this.result = Arrays.stream(values).sum();
    }

    public boolean isEmpty() {
        return values == null ? true : false;
    }
}
