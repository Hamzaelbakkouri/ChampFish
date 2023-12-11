package champ.fish.Aftas.Controllers;

import champ.fish.Aftas.Models.DTO.Fish.FishDTO;
import champ.fish.Aftas.Models.DTO.Fish.FishDTOresp;
import champ.fish.Aftas.Services.Interfaces.Fish_Interface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fish")
@AllArgsConstructor
@Validated
public class FishController {
    private Fish_Interface fishInterface;

    @PostMapping
    public ResponseEntity<?> createFish(@Valid @RequestBody FishDTO levelDTO) {
        Map<String, Object> result = new HashMap<>();
        FishDTOresp fishDTOresp = this.fishInterface.create(levelDTO);
        if (fishDTOresp != null) {
            result.put("Fish", fishDTOresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Inserted");
        return ResponseEntity.status(400).body(result);
    }

    @PutMapping(path = "/{name}")
    public ResponseEntity<?> updateFish(@Valid @RequestBody FishDTO levelDTO, @PathVariable String name) {
        levelDTO.setName(name);
        Map<String, Object> result = new HashMap<>();
        FishDTOresp fishDTOresp = this.fishInterface.update(levelDTO);
        if (fishDTOresp != null) {
            result.put("Fish", fishDTOresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Updated");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getFish(@Valid @PathVariable String name) {
        Map<String, Object> result = new HashMap<>();
        FishDTOresp fishDTOresp = this.fishInterface.get(name);
        if (fishDTOresp != null) {
            result.put("Fish", fishDTOresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> result = new HashMap<>();
        List<FishDTOresp> fishDTOrespList = this.fishInterface.getAll();
        if (fishDTOrespList != null) {
            result.put("Fishs", fishDTOrespList);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Fishs Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        Map<String, String> result = new HashMap<>();
        Boolean isDeleted = this.fishInterface.delete(name);
        if (isDeleted) {
            result.put("message", "fish with name : " + name + " deleted successfully");
            return ResponseEntity.ok(result);
        }
        result.put("message", "fish with name : " + name + " Not Found");
        return ResponseEntity.status(404).body(result);
    }
}
