package user.manager.side.domain.aggregate.model.user;

import lombok.Value;

@Value
public class Location {
	
    private final Identifier locationId;
    private final Type locationType;
    private final Code locationCode;
    private final String abbreviation;   
    
        
	public Location(Identifier locationId, Type locationType, Code locationCode, String abbreviation) {
		this.locationId = locationId;
		this.locationType = locationType;
		this.locationCode = locationCode;
		this.abbreviation = abbreviation;
	}
		
	
	@Override
	protected Object clone() throws CloneNotSupportedException {		
		return new Location(locationId, locationType, locationCode, abbreviation);
	}
}  
              
