package user.manager.side.domain.aggregate.model.user;


public abstract class Identifier implements Id {

	@Override
	public void assertIfValidIdentifier(Long id) {}

	@Override
	public void assertIfValidIdentifier(String id) {}

}
