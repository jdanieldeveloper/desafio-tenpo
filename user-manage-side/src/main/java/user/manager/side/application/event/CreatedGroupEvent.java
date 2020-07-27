package user.manager.side.application.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class CreatedGroupEvent implements Event {

	@JsonProperty("nationalSingleRole")
	public String nationalSingleRole;

	@JsonProperty("firstName")
	public String firstName;

	@JsonProperty("lastName")
	public String lastName;
}
