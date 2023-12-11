package champ.fish.Aftas.Controllers;

import champ.fish.Aftas.Models.DTO.Member.MemberDTO;
import champ.fish.Aftas.Models.DTO.Member.MemberDTOresp;
import champ.fish.Aftas.Services.Interfaces.Member_Interface;
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
@RequestMapping(path = "/api/member")
public class MemberController {
    private Member_Interface memberService;

    @PostMapping
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberDTO memberDto) {
        Map<String, Object> result = new HashMap<>();
        MemberDTOresp memberDtoresp = this.memberService.create(memberDto);
        if (memberDtoresp != null) {
            result.put("Member", memberDtoresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Inserted");
        return ResponseEntity.status(400).body(result);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateMember(@Valid @RequestBody MemberDTO memberDto, @PathVariable Integer id) {
        memberDto.setNum(id);
        Map<String, Object> result = new HashMap<>();
        MemberDTOresp memberDtoresp = this.memberService.update(memberDto);
        if (memberDtoresp != null) {
            result.put("Member", memberDtoresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Updated");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getMember(@Valid @PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        MemberDTOresp memberDtoresp = this.memberService.get(id);
        if (memberDtoresp != null) {
            result.put("Member", memberDtoresp);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> result = new HashMap<>();
        List<MemberDTOresp> memberDtorespList = this.memberService.getAll();
        if (memberDtorespList != null) {
            result.put("Members", memberDtorespList);
            return ResponseEntity.ok(result);
        }
        result.put("Message", "Members Not Found");
        return ResponseEntity.status(400).body(result);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, String> result = new HashMap<>();
        Boolean isDeleted = this.memberService.delete(id);
        if (isDeleted) {
            result.put("message", "member with " + id + " deleted successfully");
            return ResponseEntity.ok(result);
        }
        result.put("message", "member with " + id + " Not Found");
        return ResponseEntity.status(404).body(result);
    }
}
