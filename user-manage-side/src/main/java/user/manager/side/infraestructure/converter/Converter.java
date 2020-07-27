package user.manager.side.infraestructure.converter;

public interface Converter<From, To> {
	
	To convert(From from);
	
}
