package daggerok.app.domain.election.candidate;

import lombok.Value;
import lombok.With;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CandidateRepository extends CrudRepository<Candidate, UUID> {

    @With
    @Value
    class Statistic {
        private final String name;
        private final Long totalVotes;
    }

    @Query("  select   c.name          name,                     " +
            "          count(vr.voter) total_votes               " +
            " from     candidate       c                         " +
            " join     voter_ref       vr on c.id = vr.candidate " +
            " join     candidate_ref   cr on c.id = cr.candidate " +
            " join     election        e  on e.id = cr.election  " +
            " where                          e.id = :electionId  " +
            " group by c.name                                    " +
            " order by total_votes desc                          ")
    List<Statistic> findStatistics(@Param("electionId") UUID electionId);
}
