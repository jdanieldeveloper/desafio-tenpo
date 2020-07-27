package user.manager.side.infraestructure.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import user.manager.side.application.event.CreatedUserEvent;
import user.manager.side.infraestructure.converter.EventConverter;

@Slf4j
@Component
public class EventHandler {

	@Autowired
	private EventConverter eventConverter;

	public void handler(CreatedUserEvent event) {
		//TODO send event to event bus
		//eventConverter.convert(event);
	}
}
