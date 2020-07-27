package user.manager.side.domain.aggregate.model.user;

import lombok.Data;
import java.util.Optional;

@Data
public class Geo {
	
    private final Identifier geoId;
    private final Location city;
    private final Location comune;
    private final Location province;
    private final Location region;
    private final Location country;
    private final Optional<Double> latitude;
    private final Optional<Double> longitude;
    
    
	public Geo(Identifier geoId, Location city, Location comune, Location province, Location region, Location country, Optional<Double> latitude, Optional<Double> longitude) {
		this.geoId = geoId;
		this.city =  city;				
		this.comune = comune;				
		this.province = province;				
		this.region = region;				
		this.country = country;
		//
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
     
              
