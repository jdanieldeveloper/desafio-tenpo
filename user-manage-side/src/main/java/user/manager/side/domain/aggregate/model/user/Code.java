package user.manager.side.domain.aggregate.model.user;

import lombok.Value;

@Value
public class Code {
	
	private final String code;
	private final String description;
		
	public Code(String code, String description) {		
		this.code = code;
		this.description = description; 
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {		
		return new Code(code, description);
	}
	
}
