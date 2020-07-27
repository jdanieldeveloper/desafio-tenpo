package user.manager.side.domain.aggregate.model.user;

import lombok.Value;

import java.util.Objects;

@Value
public class UserTypeIdentifier extends Identifier {

	private final String id;

	public UserTypeIdentifier(String id) {
		assertIfValidIdentifier(id);
		//
		this.id = new String(id);
	}

	@Override
	public void assertIfValidIdentifier(String id) {
		if(Objects.isNull(id)) {
			throw new IllegalArgumentException("El identificador del tipo de usuario no puede ser nulo");
		}
	}

	public String getIdentifier() {
		return id;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new UserTypeIdentifier(id);
	}
}
