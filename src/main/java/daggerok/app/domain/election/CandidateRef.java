package daggerok.app.domain.election;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("candidate_ref")
@Value(staticConstructor = "of")
public class CandidateRef {
    /*
     * should have SQL definition, like this:
     * candidate UUID references candidate(id)
     */
    private final UUID candidate;

    /*
     * as far this ref is used in Election as Array,
     * then should also have in SQL definition:
     * election UUID references election(id)
     * election_key UUID
     */
}
