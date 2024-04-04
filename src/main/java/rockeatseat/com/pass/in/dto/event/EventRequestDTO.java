package rockeatseat.com.pass.in.dto.event;

public record EventRequestDTO(String title,
                              String details,
                              String slug,
                              Integer maximumAttendees
) {
}
