package user.manager.side.domain.aggregate.model.user;

import lombok.Value;

@Value
public class UserIdentifier extends Identifier {

    private long value;

    public UserIdentifier(long value) {
        assertIfValidIdentifier(value);
        //
        this.value = value;
    }

    @Override
    public void assertIfValidIdentifier(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El identificador debe ser un correlativo");
        }
    }

    public long getIdentifier() {
        return value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new UserIdentifier(value);
    }
}
