package champ.fish.Aftas.Controllers;

import champ.fish.Aftas.Models.DTO.Level.LevelDTO;
import champ.fish.Aftas.Models.DTO.Level.LevelDTOresp;
import champ.fish.Aftas.Services.Interfaces.Level_Interface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/level")
@AllArgsConstructor
@Validated
public class LevelController {
    private Level_Interface levelService;

    @PostMapping
    public ResponseEntity<?> createLevel(@Valid @RequestBody LevelDTO levelDTO) {
        Map<String, Object> result = new HashMap<>();
        LevelDTOresp levelDTOresp = this.levelService.create(levelDTO);
        if (levelDTOresp != null) {
            result.put("Level", levelDTOresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Inserted");
        return ResponseEntity.status(400).body(result);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateLevel(@Valid @RequestBody LevelDTO levelDTO, @PathVariable Integer id) {
        levelDTO.setCode(id);
        Map<String, Object> result = new HashMap<>();
        LevelDTOresp levelDTOresp = this.levelService.update(levelDTO);
        if (levelDTOresp != null) {
            result.put("Level", levelDTOresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Updated");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getLevel(@Valid @PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        LevelDTOresp levelDTOresp = this.levelService.get(id);
        if (levelDTOresp != null) {
            result.put("Level", levelDTOresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> result = new HashMap<>();
        List<LevelDTOresp> levelDTOrespList = this.levelService.getAll();
        if (levelDTOrespList != null) {
            result.put("Levels", levelDTOrespList);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Levels Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, String> result = new HashMap<>();
        Boolean isDeleted = this.levelService.delete(id);
        if (isDeleted) {
            result.put("message", "level with " + id + " deleted successfully");
            return ResponseEntity.ok(result);
        }
        result.put("message", "level with " + id + " Not Found");
        return ResponseEntity.status(404).body(result);
    }
}
