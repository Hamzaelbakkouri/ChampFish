package champ.fish.Aftas.Controllers;

import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTO;
import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTOresp;
import champ.fish.Aftas.Services.Interfaces.Competition_Interface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path = "/api/competition")
public class CompetitionController {
    private Competition_Interface competitionInterface;

    @PostMapping
    public ResponseEntity<?> createCompetition(@Valid @RequestBody CompetitionDTO competitionDTO) {
        Map<String, Object> result = new HashMap<>();
        if (competitionDTO.getStartTime().isBefore(competitionDTO.getEndTime())) {
            CompetitionDTOresp competitionDTOresp = this.competitionInterface.create(competitionDTO);
            if (competitionDTOresp != null) {
                result.put("Member", competitionDTOresp);
                return ResponseEntity.ok(result);
            }
        } else {
            throw new ResourceNotFoundException("End time must be after start time");
        }
        result.put("Message", "Not Inserted");
        return ResponseEntity.status(400).body(result);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateCompetition(@Valid @RequestBody CompetitionDTO competitionDTO, @PathVariable String id) {
        competitionDTO.setCode(id);
        Map<String, Object> result = new HashMap<>();
        CompetitionDTOresp competitionDTOresp = this.competitionInterface.update(competitionDTO);
        if (competitionDTOresp != null) {
            result.put("Competition", competitionDTOresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Updated");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCompetition(@Valid @PathVariable String id) {
        Map<String, Object> result = new HashMap<>();
        CompetitionDTOresp competitionDTOresp = this.competitionInterface.get(id);
        if (competitionDTOresp != null) {
            result.put("Competition", competitionDTOresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> result = new HashMap<>();
        List<CompetitionDTOresp> competitionDTOrespList = this.competitionInterface.getAll();
        if (competitionDTOrespList != null) {
            result.put("Competitions", competitionDTOrespList);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Competitions Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping(path = "/paginate")
    public ResponseEntity<Page<CompetitionDTOresp>> paginateAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(competitionInterface.getAllWithPagination(pageable));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Map<String, String> result = new HashMap<>();
        Boolean isDeleted = this.competitionInterface.delete(id);
        if (isDeleted) {
            result.put("message", "member with " + id + " deleted successfully");
            return ResponseEntity.ok(result);
        }
        result.put("message", "member with " + id + " Not Found");
        return ResponseEntity.status(404).body(result);
    }
}
