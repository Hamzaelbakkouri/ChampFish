package champ.fish.Aftas.Services.Implementations;

import champ.fish.Aftas.Exeptions.NotFoundExeption;
import champ.fish.Aftas.Models.DTO.Ranking.RankingDTO;
import champ.fish.Aftas.Models.DTO.Ranking.RankingDTOresp;
import champ.fish.Aftas.Models.Entities.Competition;
import champ.fish.Aftas.Models.Entities.EmbeddedRanking;
import champ.fish.Aftas.Models.Entities.Member;
import champ.fish.Aftas.Models.Entities.Ranking;
import champ.fish.Aftas.Repositories.CompetitionRepository;
import champ.fish.Aftas.Repositories.MemberRepository;
import champ.fish.Aftas.Repositories.RankingRepository;
import champ.fish.Aftas.Services.Interfaces.Ranking_Interface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class RankingService implements Ranking_Interface {
    private RankingRepository rankingRepository;
    private ModelMapper modelMapper;
    private MemberRepository memberRepository;
    private CompetitionRepository competitionRepository;

    @Override
    public RankingDTOresp create(RankingDTO rankingDTO) {
        Optional<Member> member = this.memberRepository.findById(rankingDTO.getId().getMember_num());
        Optional<Competition> competition = this.competitionRepository.findById(rankingDTO.getId().getCompetition_code());
        if (member.isPresent() && competition.isPresent()) {
            Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
            return modelMapper.map(this.rankingRepository.save(ranking), RankingDTOresp.class);
        }
        throw new NotFoundExeption("Member or Competition Not Found");
    }

    @Override
    public RankingDTOresp update(RankingDTO rankingDTO, Integer mbr, String cmp) {
        Optional<Member> member = this.memberRepository.findById(mbr);
        Optional<Competition> competition = this.competitionRepository.findById(cmp);
        EmbeddedRanking embeddedRankingID = new EmbeddedRanking();
        if (member.isPresent() && competition.isPresent()) {
            embeddedRankingID.setCompetition(competition.get());
            embeddedRankingID.setMember(member.get());
            Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
            ranking.setId(embeddedRankingID);
            return modelMapper.map(this.rankingRepository.save(ranking), RankingDTOresp.class);
        }
        throw new NotFoundExeption("Member or Competition Not Found");
    }

    @Override
    public Boolean delete(Integer mbr, String cmp) {
        Optional<Member> member = this.memberRepository.findById(mbr);
        Optional<Competition> competition = this.competitionRepository.findById(cmp);
        EmbeddedRanking embeddedRankingID = new EmbeddedRanking();
        if (member.isPresent() && competition.isPresent()) {
            embeddedRankingID.setCompetition(competition.get());
            embeddedRankingID.setMember(member.get());
            this.rankingRepository.deleteById(embeddedRankingID);
            return true;
        }
        throw new NotFoundExeption("Member or Competition Not Found");
    }

    @Override
    public RankingDTOresp get(Integer mbr, String cmp) {
        Optional<Member> member = this.memberRepository.findById(mbr);
        Optional<Competition> competition = this.competitionRepository.findById(cmp);
        EmbeddedRanking embeddedRankingID = new EmbeddedRanking();
        if (member.isPresent() && competition.isPresent()) {
            embeddedRankingID.setCompetition(competition.get());
            embeddedRankingID.setMember(member.get());
            return modelMapper.map(this.rankingRepository.findById(embeddedRankingID).get(), RankingDTOresp.class);
        }
        throw new NotFoundExeption("Member or Competition Not Found");
    }

    @Override
    public List<RankingDTOresp> getAll() {
        List<Ranking> ranking = this.rankingRepository.findAll();
        if (ranking != null) {
            return ranking.stream().map(ranking1 -> modelMapper.map(ranking1, RankingDTOresp.class)).collect(Collectors.toList());
        }
        throw new NotFoundExeption("No Ranking Found");
    }

    public List<RankingDTOresp> getRankings(String competitionCode){
        return rankingRepository.getAllByCompetition(competitionCode)
                .stream().map(ranking -> modelMapper.map(ranking,RankingDTOresp.class))
                .collect(Collectors.toList());
    }

    public List<RankingDTOresp> CountRankings(String competitionCode) {
        List<Ranking> rankings = rankingRepository.calculateRankingsForCompetition(competitionCode);
        return IntStream.range(0,rankings.size()).mapToObj(i -> {
            rankings.get(i).setRank(i+1);
            return modelMapper.map(rankingRepository.save(rankings.get(i)),RankingDTOresp.class);
        }).collect(Collectors.toList());
    }
}
