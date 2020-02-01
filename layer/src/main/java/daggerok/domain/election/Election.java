package daggerok.domain.election;

import daggerok.domain.candidate.Candidate;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
public class Election {

    @Id
    private final UUID id;
    private final String name;
    private final Collection<CandidateRef> candidates;

    public Election(String name) {
        this(null, name, new CopyOnWriteArrayList<>());
    }

    public Election registerCandidates(Candidate... candidates) {
        this.candidates.addAll(Arrays.stream(Objects.requireNonNull(candidates))
                                     .map(Candidate::getId)
                                     .map(CandidateRef::of)
                                     .collect(Collectors.toList()));
        return this;
    }
}
