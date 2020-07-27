package user.manager.side.infraestructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserModel {

    @JsonProperty(value = "socialIdentifier", required = true)
    private String socialIdentifier;

    @JsonProperty(value = "firstName")
    public String firstName;

    @JsonProperty(value = "lastName")
    public String lastName;

    @JsonProperty(value = "groupName")
    public String groupName;

    @JsonProperty(value = "userType")
    public String userType;
}
