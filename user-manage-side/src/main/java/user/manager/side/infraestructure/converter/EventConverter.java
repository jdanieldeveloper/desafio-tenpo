package user.manager.side.infraestructure.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import user.manager.side.application.event.Event;

import java.io.IOException;


/**
 * Created by daniel.carvajal on 25-06-2018.
 */
@Slf4j
@Component
public class EventConverter implements Converter<Event, String> {

    @Override
    public String convert(Event event) {
        String to = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            to = objectMapper.writeValueAsString(event);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return to;
    }
}