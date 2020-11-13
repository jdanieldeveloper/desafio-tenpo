package user.manager.side.infrastructure.converter;

public interface Converter<From, To> {
	
	To convert(From from);
	
}
