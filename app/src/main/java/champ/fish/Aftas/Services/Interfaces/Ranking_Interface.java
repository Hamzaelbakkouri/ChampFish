package champ.fish.Aftas.Services.Interfaces;

import champ.fish.Aftas.Models.DTO.Ranking.RankingDTO;
import champ.fish.Aftas.Models.DTO.Ranking.RankingDTOresp;

import java.util.List;


public interface Ranking_Interface {
    RankingDTOresp create(RankingDTO rankingDTO);

    RankingDTOresp update(RankingDTO rankingDTO,Integer member, String competition);

    Boolean delete(Integer member, String competition);

    RankingDTOresp get(Integer member, String competition);

    List<RankingDTOresp> getAll();

    List<RankingDTOresp> CountRankings(String competitionCode);

    List<RankingDTOresp> getRankings(String competitionCode);
}
