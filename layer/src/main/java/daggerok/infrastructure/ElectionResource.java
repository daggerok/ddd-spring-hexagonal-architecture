package daggerok.infrastructure;

import daggerok.application.ElectionService;
import daggerok.application.dto.CandidateDTO;
import daggerok.application.dto.ElectionDTO;
import daggerok.application.dto.VoterDTO;
import daggerok.domain.Election;
import daggerok.domain.candidate.Candidate;
import daggerok.domain.candidate.voter.Voter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.*;
import java.util.function.Function;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Infrastructure (Framework) layer.
 * In this case we relay on Spring ecosystem (Spring MVC).
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class ElectionResource {

    private final ElectionService electionService;

    @GetMapping("/elections")
    Iterable<Election> getElections() {
        return electionService.getElections();
    }

    @PostMapping(path = "/elections", consumes = APPLICATION_JSON_VALUE)
    ElectionDTO beginElection(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        return electionService.beginElection(name);
    }

    @PostMapping(path = "/elections/{electionId}/candidates", consumes = APPLICATION_JSON_VALUE)
    CandidateDTO registerCandidate(@PathVariable("electionId") UUID electionId,
                                @RequestBody Map<String, String> request) {
        String name = request.get("name");
        return electionService.registerCandidate(electionId, name);
    }

    @GetMapping("/candidates")
    Iterable<Candidate> getCandidates() {
        return electionService.getCandidates();
    }

    @PostMapping(path = "/elections/{electionId}/voters", consumes = APPLICATION_JSON_VALUE)
    VoterDTO registerVoter(@PathVariable("electionId") UUID electionId,
                        @RequestBody Map<String, String> request) {
        String name = request.get("name");
        return electionService.registerVoter(electionId, name);
    }

    @GetMapping("/voters")
    Iterable<Voter> getVoters() {
        return electionService.getVoters();
    }

    @PutMapping("/elections/{electionId}/candidates/{candidateId}/voters/{voterId}")
    void vote(@PathVariable("electionId") UUID electionId,
              @PathVariable("candidateId") UUID candidateId,
              @PathVariable("voterId") UUID voterId) {
        electionService.vote(electionId, candidateId, voterId);
    }

    @GetMapping("/elections/{electionId}")
    List<String> getStatistics(@PathVariable("electionId") UUID electionId) {
        return electionService.getStatistics(electionId);
    }

    @RequestMapping("/**")
    Map<String, String> fallbackApiInfo(HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURL().toString());
        String baseUrl = String.format("%s://%s", uri.getScheme(), uri.getAuthority());
        Function<String, String> url = path -> String.format("%s%s", baseUrl, path);
        Map<String, String> response = new HashMap<>();

        response.put("     show elections GET", url.apply("/elections"));
        response.put(" open new election POST", url.apply("/elections"));
        response.put("    show candidates GET", url.apply("/candidates"));
        response.put("register candidate POST", url.apply("/elections/{electionId}/candidates"));
        response.put("        show voters GET", url.apply("/voters"));
        response.put("    register voter POST", url.apply("/elections/{electionId}/voters"));
        response.put("               vote PUT", url.apply("/elections/{electionId}/candidates/{candidateId}/voters/{voterId}"));
        response.put("   election results GET", url.apply("/elections/{electionId}"));
        response.put("                  _self", uri.toString());
        return response;
    }
}
