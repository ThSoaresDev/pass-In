package rockeatseat.com.pass.in.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rockeatseat.com.pass.in.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, String>{

}
