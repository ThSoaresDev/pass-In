package rockeatseat.com.pass.in.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rockeatseat.com.pass.in.domain.attendee.Attendee;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee,String> {
    public List<Attendee> findByEventId(String eventId);
}
