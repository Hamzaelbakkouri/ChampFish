package champ.fish.Aftas.Controllers;

import champ.fish.Aftas.Models.DTO.Ranking.RankingDTO;
import champ.fish.Aftas.Models.DTO.Ranking.RankingDTOresp;
import champ.fish.Aftas.Services.Interfaces.Ranking_Interface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/ranking")
@AllArgsConstructor
public class RankingController {
    private Ranking_Interface rankingInterface;

    @PostMapping
    public ResponseEntity<?> createRanking(@Valid @RequestBody RankingDTO rankingDTO) {
        Map<String, Object> result = new HashMap<>();
        RankingDTOresp rankingDtoResp = this.rankingInterface.create(rankingDTO);
        if (rankingDtoResp != null) {
            result.put("Ranking", rankingDtoResp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Inserted");
        return ResponseEntity.status(400).body(result);
    }

    @PutMapping(path = "/{member}/{competition}")
    public ResponseEntity<?> updateRanking(@Valid @RequestBody RankingDTO rankingDTO, @PathVariable Integer member, @PathVariable String competition) {

        Map<String, Object> result = new HashMap<>();
        RankingDTOresp rankingDtoResp = this.rankingInterface.update(rankingDTO, member , competition);
        if (rankingDtoResp != null) {
            result.put("Ranking", rankingDtoResp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Updated");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping(path = "/{member}/{competition}")
    public ResponseEntity<?> getRanking(@Valid @PathVariable Integer member, @PathVariable String competition) {

        Map<String, Object> result = new HashMap<>();
        RankingDTOresp rankingDtoResp = this.rankingInterface.get(member , competition);
        if (rankingDtoResp != null) {
            result.put("Ranking", rankingDtoResp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> result = new HashMap<>();
        List<RankingDTOresp> rankingDtoRespList = this.rankingInterface.getAll();
        if (rankingDtoRespList != null) {
            result.put("Rankings", rankingDtoRespList);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Rankings Not Found");
        return ResponseEntity.status(400).body(result);
    }
    

    @DeleteMapping(path = "/{member}/{competition}")
    public ResponseEntity<?> delete(@Valid @PathVariable Integer member, @PathVariable String competition) {
        Map<String, String> result = new HashMap<>();
        Boolean isDeleted = this.rankingInterface.delete(member , competition);
        if (isDeleted) {
            result.put("message", "ranking deleted successfully");
            return ResponseEntity.ok(result);
        }
        result.put("message", "ranking Not Found");
        return ResponseEntity.status(404).body(result);
    }
}
