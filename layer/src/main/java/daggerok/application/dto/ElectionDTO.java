package daggerok.application.dto;

import daggerok.domain.Election;
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
