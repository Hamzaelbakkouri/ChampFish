package champ.fish.Aftas.Controllers;

import champ.fish.Aftas.Models.DTO.Hunting.HuntingDTO;
import champ.fish.Aftas.Models.DTO.Hunting.HuntingDTOresp;
import champ.fish.Aftas.Services.Interfaces.Hunting_Interface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path = "/api/hunting")
public class HuntingController {
    private Hunting_Interface huntingInterface;

    @PostMapping
    public ResponseEntity<?> createHunting(@Valid @RequestBody HuntingDTO huntingDTO) {
        Map<String, Object> result = new HashMap<>();
        HuntingDTOresp HuntingDto = this.huntingInterface.create(huntingDTO);
        if (HuntingDto != null) {
            result.put("Hunting", HuntingDto);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Inserted");
        return ResponseEntity.status(400).body(result);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateHunting(@Valid @RequestBody HuntingDTO huntingDTO, @PathVariable Integer id) {
        huntingDTO.setId(id);
        Map<String, Object> result = new HashMap<>();
        HuntingDTOresp HuntingDto = this.huntingInterface.update(huntingDTO);
        if (HuntingDto != null) {
            result.put("Hunting", HuntingDto);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Updated");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getHunting(@Valid @PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        HuntingDTOresp HuntingDto = this.huntingInterface.get(id);
        if (HuntingDto != null) {
            result.put("Hunting", HuntingDto);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> result = new HashMap<>();
        List<HuntingDTOresp> HuntingDtoList = this.huntingInterface.getAll();
        if (HuntingDtoList != null) {
            result.put("Huntings", HuntingDtoList);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Huntings Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, String> result = new HashMap<>();
        Boolean isDeleted = this.huntingInterface.delete(id);
        if (isDeleted) {
            result.put("message", "Hunting with id : " + id + " deleted successfully");
            return ResponseEntity.ok(result);
        }
        result.put("message", "Hunting with id : " + id + " Not Found");
        return ResponseEntity.status(404).body(result);
    }
}
