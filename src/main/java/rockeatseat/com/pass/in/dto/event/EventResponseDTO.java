package rockeatseat.com.pass.in.dto.event;

import lombok.Getter;
import rockeatseat.com.pass.in.domain.event.Event;
@Getter
public class EventResponseDTO {

    EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAttendees){
        this.event = new EventDetailDTO(event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), numberOfAttendees);
    }
}
