package daggerok.application.dto;

import daggerok.domain.voter.Voter;
import lombok.Value;

import java.util.UUID;

@Value
public class VoterDTO {

    private final UUID id;
    private final String name;

    public static VoterDTO of(Voter voter) {
        return new VoterDTO(voter.getId(), voter.getName());
    }
}
