package champ.fish.Aftas.Services.Implementations;

import champ.fish.Aftas.Models.DTO.Member.MemberDTO;
import champ.fish.Aftas.Models.DTO.Member.MemberDTOresp;
import champ.fish.Aftas.Models.Entities.Member;
import champ.fish.Aftas.Models.Enums.Role;
import champ.fish.Aftas.Repositories.MemberRepository;
import champ.fish.Aftas.Services.Interfaces.Member_Interface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MemberService implements Member_Interface {
    private MemberRepository memberRepository;
    private ModelMapper modelMapper;

    @Override
    public MemberDTOresp create(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        member.setAccessionDate(LocalDate.now());
        member = memberRepository.save(member);
        return modelMapper.map(member, MemberDTOresp.class);
    }

    @Override
    public MemberDTOresp update(MemberDTO memberDTO) {
        Optional<Member> existMember = memberRepository.findById(memberDTO.getNum());
        if (existMember.isPresent()) {
            memberDTO.setNum(existMember.get().getNum());
            return modelMapper.map(memberRepository.save(modelMapper.map(memberDTO, Member.class)), MemberDTOresp.class);
        }
        return null;
    }

    @Override
    public MemberDTOresp updateRole(Integer id, String role) {
        Optional<Member> existMember = memberRepository.findById(id);
        Member member = new Member();
        if (existMember.isPresent()) {
            member.setNum(existMember.get().getNum());
            member.setIdentityDocumentType(existMember.get().getIdentityDocumentType());
            member.setName(existMember.get().getName());
            member.setFamilyName(existMember.get().getFamilyName());
            member.setEmail(existMember.get().getEmail());
            member.setNationality(existMember.get().getNationality());
            member.setPassword(existMember.get().getPassword());
            member.setAccessionDate(existMember.get().getAccessionDate());
            member.setIdentityNumber(existMember.get().getIdentityNumber());
            System.out.println(role);
            if (role != null) {
                System.out.println("\n\nrooooooooole" + role + "\n\n\n");
                member.setRole(Role.valueOf(role));
            }

            return modelMapper.map(memberRepository.save(member), MemberDTOresp.class);
        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
            return true;
        }
        return false;
    }

    @Override
    public MemberDTOresp get(Integer id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.map(value -> modelMapper.map(value, MemberDTOresp.class)).orElse(null);
    }

    @Override
    public List<MemberDTOresp> getAll() {
        return memberRepository.findAll()
                .stream()
                .map(member -> modelMapper.map(member, MemberDTOresp.class))
                .collect(Collectors.toList());
    }
}