package daggerok.app.domain.election;

import com.fasterxml.jackson.annotation.JsonIgnore;
import daggerok.app.domain.election.candidate.Candidate;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
public class Election {

    @Id
    private final UUID id;
    private final String name;

    @JsonIgnore
    private final Collection<CandidateRef> candidates;

    public Election(UUID id, String name) {
        this(id, name, new CopyOnWriteArrayList<>());
    }

    public Election registerCandidates(Candidate... candidates) {
        Objects.requireNonNull(candidates);
        for (Candidate c : candidates) {
            CandidateRef ref = CandidateRef.of(c.getId());
            this.candidates.add(ref);
        }
        return this;
    }
}
