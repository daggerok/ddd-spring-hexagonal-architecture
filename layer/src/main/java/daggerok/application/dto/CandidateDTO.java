package daggerok.application.dto;

import daggerok.domain.candidate.Candidate;
import lombok.Value;

import java.util.UUID;

@Value
public class CandidateDTO {

    private final UUID id;
    private final String name;

    public static CandidateDTO of(Candidate candidate) {
        return new CandidateDTO(candidate.getId(), candidate.getName());
    }
}
