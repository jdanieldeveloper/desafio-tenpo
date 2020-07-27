package user.manager.side.domain.aggregate.model.user;

import lombok.Value;

@Value
public class Role {

    private final String roleTypeId;
    private final String roleDescription;
    
    public Role(String roleTypeId, String roleDescription) {
        this.roleTypeId = roleTypeId;
    	this.roleDescription = roleDescription;
    }
}
    
