package daggerok.domain.voter;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.UUID;

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
public class Voter {

    @Id
    private final UUID id;
    private final String name;
}
