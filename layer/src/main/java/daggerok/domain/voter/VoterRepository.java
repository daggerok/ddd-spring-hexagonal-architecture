package daggerok.domain.voter;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VoterRepository extends CrudRepository<Voter, UUID> { }
