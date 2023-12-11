package champ.fish.Aftas.Services.Implementations;

import champ.fish.Aftas.Models.DTO.Hunting.HuntingDTO;
import champ.fish.Aftas.Models.DTO.Hunting.HuntingDTOresp;
import champ.fish.Aftas.Models.Entities.Competition;
import champ.fish.Aftas.Models.Entities.Fish;
import champ.fish.Aftas.Models.Entities.Hunting;
import champ.fish.Aftas.Models.Entities.Member;
import champ.fish.Aftas.Repositories.CompetitionRepository;
import champ.fish.Aftas.Repositories.FishRepository;
import champ.fish.Aftas.Repositories.HuntingRepository;
import champ.fish.Aftas.Repositories.MemberRepository;
import champ.fish.Aftas.Services.Interfaces.Hunting_Interface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HuntingService implements Hunting_Interface {
    private HuntingRepository huntingRepository;
    private ModelMapper modelMapper;
    private FishRepository fishRepository;
    private CompetitionRepository competitionRepository;
    private MemberRepository memberRepository;
    Optional<Fish> fish;
    Optional<Competition> competition;
    Optional<Member> member;

    @Override
    public HuntingDTOresp create(HuntingDTO huntingReq) {
        Hunting huntingE = modelMapper.map(huntingReq, Hunting.class);
        fish = fishRepository.findById(huntingReq.getFish_id());
        member = memberRepository.findById(huntingReq.getMember_id());
        competition = competitionRepository.findById(huntingReq.getCompetition_id());

        if (fish.isPresent() && member.isPresent() && competition.isPresent()) {
            huntingE.setFish(fish.get());
            huntingE.setMember(member.get());
            huntingE.setCompetition(competition.get());
        }

        huntingE = huntingRepository.save(huntingE);
        return modelMapper.map(huntingE, HuntingDTOresp.class);
    }

    @Override
    public HuntingDTOresp update(HuntingDTO huntingReq) {

        Optional<Hunting> huntingE = huntingRepository.findById(huntingReq.getId());

        if (huntingE.isPresent()) {
            fish = fishRepository.findById(huntingReq.getFish_id());
            member = memberRepository.findById(huntingReq.getMember_id());
            competition = competitionRepository.findById(huntingReq.getCompetition_id());

            if (fish.isPresent() && member.isPresent() && competition.isPresent()) {
                huntingE.get().setFish(fish.get());
                huntingE.get().setMember(member.get());
                huntingE.get().setCompetition(competition.get());
            }

            huntingE = Optional.of(huntingRepository.save(huntingE.get()));
            return modelMapper.map(huntingE.get(), HuntingDTOresp.class);
        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<Hunting> hunting = huntingRepository.findById(id);
        if (hunting.isPresent()) {
            huntingRepository.delete(hunting.get());
            return true;
        } else return false;
    }

    @Override
    public List<HuntingDTOresp> getAll() {
        return huntingRepository.findAll().stream().map(hunting -> modelMapper.map(hunting, HuntingDTOresp.class)).collect(Collectors.toList());
    }

    @Override
    public HuntingDTOresp get(Integer id) {
        Optional<Hunting> hunting = huntingRepository.findById(id);
        return hunting.map(value -> modelMapper.map(value, HuntingDTOresp.class)).orElse(null);
    }
}
