package user.manager.side.domain.aggregate.model.user;

import lombok.Value;

@Value
public class Type {
	
    private final Identifier typeId;
            
    public Type(Identifier typeId){
    	this.typeId = typeId;
    }	
    
    
    @Override
    protected Object clone() throws CloneNotSupportedException {    	
    	return new Type(typeId);
    }
}
    
               
