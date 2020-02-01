package daggerok;

import daggerok.domain.candidate.Candidate;
import daggerok.domain.candidate.CandidateRepository;
import daggerok.domain.voter.Voter;
import daggerok.domain.voter.VoterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.UUID;

@Log4j2
@SpringBootTest(classes = App.class)
@AllArgsConstructor(onConstructor_ = @Autowired)
class AppTest {

    TransactionTemplate tx;
    VoterRepository voterRepository;
    CandidateRepository candidateRepository;

    @Test
    void should_save_all_voters() {
        Iterable<Voter> voters = voterRepository.saveAll(Arrays.asList(
                new Voter(UUID.randomUUID(), "Voter 1"),
                new Voter(UUID.randomUUID(), "Voter 2"),
                new Voter(UUID.randomUUID(), "Voter 3")
        ));
        log.info(voters);
    }

    @Test
    void should_save_all_candidates() {
        Iterable<Candidate> candidates = candidateRepository.saveAll(Arrays.asList(
                new Candidate(UUID.randomUUID(), "Candidate 1"),
                new Candidate(UUID.randomUUID(), "Candidate 2")
        ));
        log.info(candidates);
    }

    @Test
    void should_save_candidate_with_voters() {
        Candidate candidate = new Candidate(UUID.randomUUID(), "Candidate 1");
        candidateRepository.save(candidate);
        log.info("candidate: {}", candidate);

        Iterable<Voter> voters = voterRepository.saveAll(Arrays.asList(
                new Voter(UUID.randomUUID(), "Voter 1"),
                new Voter(UUID.randomUUID(), "Voter 2"),
                new Voter(UUID.randomUUID(), "Voter 3")
        ));
        log.info("voters: {}", voters);

        candidateRepository.save(candidate.receiveVotes(voters));
        for (Candidate c : candidateRepository.findAll()) {
            log.info("after voted: {}", c);
        }
    }
}
