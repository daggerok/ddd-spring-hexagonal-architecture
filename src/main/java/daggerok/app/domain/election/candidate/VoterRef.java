package daggerok.app.domain.election.candidate;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("voter_ref")
@Value(staticConstructor = "of")
public class VoterRef {
    private final UUID voter;
}
