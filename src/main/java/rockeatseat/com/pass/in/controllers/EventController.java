package rockeatseat.com.pass.in.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rockeatseat.com.pass.in.dto.event.EventIdDTO;
import rockeatseat.com.pass.in.dto.event.EventRequestDTO;
import rockeatseat.com.pass.in.dto.event.EventResponseDTO;
import rockeatseat.com.pass.in.services.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        EventResponseDTO event = this.service.getEventDetail(id);

        return ResponseEntity.ok(event);
    }

   @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body , UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDTO = this.service.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return  ResponseEntity.created(uri).body(eventIdDTO);
    }

}
