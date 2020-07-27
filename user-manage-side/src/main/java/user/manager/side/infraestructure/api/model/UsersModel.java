package user.manager.side.infraestructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsersModel {

    @JsonProperty(value = "users")
    private List<UserModel> users;

    @JsonProperty(value = "status")
    private StatusModel status;

    public UsersModel(){
        users = new ArrayList<>();
    }

}
