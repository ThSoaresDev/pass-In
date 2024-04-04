package rockeatseat.com.pass.in.services;

import com.sun.jdi.request.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rockeatseat.com.pass.in.domain.attendee.Attendee;
import rockeatseat.com.pass.in.domain.event.Event;
import rockeatseat.com.pass.in.dto.event.EventDetailDTO;
import rockeatseat.com.pass.in.dto.event.EventIdDTO;
import rockeatseat.com.pass.in.dto.event.EventRequestDTO;
import rockeatseat.com.pass.in.dto.event.EventResponseDTO;
import rockeatseat.com.pass.in.repositories.AttendeeRepository;
import rockeatseat.com.pass.in.repositories.EventRepository;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(()-> new RuntimeException("Event not found with ID:" + eventId));
        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);
        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO){
        Event newEvent = new Event();

        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text,Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]","")
                .replaceAll("[^\\w\\s]","")
                .replaceAll("\\s+","-")
                .toLowerCase();
    }

}
