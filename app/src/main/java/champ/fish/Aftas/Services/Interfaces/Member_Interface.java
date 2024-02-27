package champ.fish.Aftas.Services.Interfaces;

import champ.fish.Aftas.Models.DTO.Member.MemberDTO;
import champ.fish.Aftas.Models.DTO.Member.MemberDTOresp;
import champ.fish.Aftas.Models.Enums.Role;

public interface Member_Interface extends G_Types<MemberDTO, MemberDTOresp, Integer> {
    MemberDTOresp updateRole(Integer id, String role);
}
