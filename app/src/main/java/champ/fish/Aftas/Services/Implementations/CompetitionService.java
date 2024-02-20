package champ.fish.Aftas.Services.Implementations;


import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTO;
import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTOresp;
import champ.fish.Aftas.Models.Entities.Competition;
import champ.fish.Aftas.Repositories.CompetitionRepository;
import champ.fish.Aftas.Services.Interfaces.Competition_Interface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CompetitionService implements Competition_Interface {
    private CompetitionRepository competitionRepository;

    private ModelMapper modelMapper;

    @Override
    public CompetitionDTOresp create(CompetitionDTO competitionReq) {
        Competition competition = modelMapper.map(competitionReq, Competition.class);
        competition = competitionRepository.save(competition);
        return modelMapper.map(competition, CompetitionDTOresp.class);
    }

    @Override
    public CompetitionDTOresp update(CompetitionDTO competitionReq) {
        Optional<Competition> existCompetition = competitionRepository.findById(competitionReq.getCode());
        if (existCompetition.isPresent()) {
            competitionReq.setCode(existCompetition.get().getCode());
            return modelMapper.map(competitionRepository.save(modelMapper.map(competitionReq, Competition.class)), CompetitionDTOresp.class);
        }
        return null;
    }

    @Override
    public Boolean delete(String code) {
        Optional<Competition> competition = competitionRepository.findById(code);
        if (competition.isPresent()) {
            competitionRepository.delete(competition.get());
            return true;
        }
        return false;
    }

    @Override
    public List<CompetitionDTOresp> getAll() {
        return competitionRepository.findAll().stream().map(competition -> modelMapper.map(competition, CompetitionDTOresp.class)).collect(Collectors.toList());
    }

    @Override
    public Page<CompetitionDTOresp> getAllWithPagination(Pageable pageable) {
        Page<Competition> competitions = competitionRepository.findAll(pageable);
        return competitions.map(competition -> modelMapper.map(competition, CompetitionDTOresp.class));
    }

    @Override
    public Page<CompetitionDTOresp> filterDoneStatus(LocalDate date, Pageable pageable) {
        Page<Competition> competitions = competitionRepository.getCompetitionsByDateBefore(date, pageable);
        return competitions.map(competition -> modelMapper.map(competition, CompetitionDTOresp.class));
    }


    @Override
    public Page<CompetitionDTOresp> filterPendingStatus(LocalDate date, Pageable pageable) {
        Page<Competition> competitions = competitionRepository.getCompetitionsByDate(date, pageable);
        return competitions.map(competition -> modelMapper.map(competition, CompetitionDTOresp.class));
    }

    @Override
    public Page<CompetitionDTOresp> filterInProgressStatus(LocalDate date, Pageable pageable) {
        Page<Competition> competitions = competitionRepository.getCompetitionsByDateAfter(date, pageable);
        return competitions.map(competition -> modelMapper.map(competition, CompetitionDTOresp.class));
    }

    @Override
    public CompetitionDTOresp get(String code) {
        Optional<Competition> competition = competitionRepository.findById(code);
        return competition.map(value -> modelMapper.map(value, CompetitionDTOresp.class)).orElse(null);
    }
}
