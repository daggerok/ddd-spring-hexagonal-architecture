package daggerok.app.domain;

import daggerok.app.domain.election.Election;
import daggerok.app.domain.election.ElectionRepository;
import daggerok.app.domain.election.candidate.Candidate;
import daggerok.app.domain.election.candidate.CandidateRepository;
import daggerok.app.domain.election.candidate.voter.Voter;
import daggerok.app.domain.election.candidate.voter.VoterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Application layer.
 * It should not be specific to the your Infrastructure / Framework
 * as well as to your Domain.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class ElectionService {

    private final VoterRepository voterRepository;
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    public Iterable<Election> getElections() {
        return electionRepository.findAll();
    }

    public Iterable<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public Iterable<Voter> getVoters() {
        return voterRepository.findAll();
    }

    public Election beginElection(String name) {
        Objects.requireNonNull(name);
        Election election = new Election(null, name);
        return electionRepository.save(election);
    }

    public Candidate registerCandidate(UUID electionId, String name) {
        Objects.requireNonNull(name);
        Election election = getElection(electionId);
        Candidate candidate = candidateRepository.save(new Candidate(null, name));
        Election updated = electionRepository.save(election.registerCandidates(candidate));
        log.info("registered candidate {} for election {}", candidate, updated);
        return candidate;
    }

    public Voter registerVoter(UUID electionId, String name) {
        Objects.requireNonNull(name);
        getElection(electionId);
        return voterRepository.save(new Voter(null, name));
    }

    public void vote(UUID electionId, UUID candidateId, UUID voterId) {
        Objects.requireNonNull(voterId);
        Objects.requireNonNull(candidateId);
        Election election = getElection(electionId);
        Candidate candidate = candidateRepository.findById(candidateId)
                                                 .orElseThrow(() -> new IllegalArgumentException(
                                                         String.format("Candidate %s not found.", candidateId)));
        boolean candidateNotRegister = election.getCandidates()
                                               .stream()
                                               .noneMatch(ref -> ref.getCandidate().equals(candidateId));
        if (candidateNotRegister) throw new IllegalArgumentException(
                String.format("Candidate %s is not register for election %s", candidateId, election));
        Voter voter = voterRepository.findById(voterId)
                                     .orElseThrow(() -> new IllegalArgumentException(
                                             String.format("Voter %s not found.", voterId)));
        candidateRepository.save(candidate.receiveVotes(voter));
    }

    public List<String> getStatistics(UUID electionId) {
        getElection(electionId);
        return candidateRepository.findStatistics(electionId)
                                  .stream()
                                  .map(s -> String.format("%s: %d", s.getName(), s.getTotalVotes()))
                                  .collect(Collectors.toList());
    }

    private Election getElection(UUID electionId) {
        return electionRepository.findById(Objects.requireNonNull(electionId))
                                 .orElseThrow(() -> new IllegalArgumentException(
                                         String.format("Election %s not found.", electionId)));
    }
}
