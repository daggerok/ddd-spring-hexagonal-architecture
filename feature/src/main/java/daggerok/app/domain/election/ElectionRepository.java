package daggerok.app.domain.election;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ElectionRepository extends CrudRepository<Election, UUID> { }
