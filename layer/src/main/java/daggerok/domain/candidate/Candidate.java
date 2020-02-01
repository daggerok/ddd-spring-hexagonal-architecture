package daggerok.domain.candidate;

import daggerok.domain.voter.Voter;
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
public class Candidate implements Comparable<Candidate> {

    @Id
    private final UUID id;
    private final String name;
    private final Collection<VoterRef> voters;

    public Candidate(UUID id, String name) {
        this(id, name, new CopyOnWriteArrayList<>());
    }

    public Candidate receiveVotes(Voter... voters) {
        return receiveVotes(Arrays.stream(voters).collect(Collectors.toList()));
    }

    public Candidate receiveVotes(Iterable<Voter> voters) {
        Objects.requireNonNull(voters);
        for (Voter voter : voters) {
            VoterRef ref = VoterRef.of(voter.getId());
            this.voters.add(ref);
        }
        return this;
    }

    @Override
    public int compareTo(Candidate other) {
        return Integer.compare(voters.size(), other.voters.size());
    }
}
