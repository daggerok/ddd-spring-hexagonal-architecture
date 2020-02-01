package daggerok.application.dto;

import daggerok.domain.election.Election;
import lombok.Value;

import java.util.UUID;

@Value
public class ElectionDTO {

    private final UUID id;
    private final String name;

    public static ElectionDTO of(Election election) {
        return new ElectionDTO(election.getId(), election.getName());
    }
}
