package champ.fish.Aftas.Service;

import champ.fish.Aftas.Models.DTO.Ranking.RankingDTOresp;
import champ.fish.Aftas.Models.Entities.Ranking;
import champ.fish.Aftas.Repositories.RankingRepository;
import champ.fish.Aftas.Services.Implementations.RankingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CompetitionServiceTest {
    @Autowired
    private RankingService rankingService;

    @MockBean
    private RankingRepository rankingRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testCalculateAndFetchRankings() {
        String competitionCode = "COMP_CODE";
        List<Ranking> rankings = new ArrayList<>();
        rankings.add(new Ranking());

        Mockito.when(rankingRepository.calculateRankingsForCompetition(competitionCode)).thenReturn(rankings);
        Mockito.when(rankingRepository.save(Mockito.any(Ranking.class))).thenReturn(new Ranking());
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.eq(RankingDTOresp.class))).thenReturn(new RankingDTOresp());

        List<RankingDTOresp> result = rankingService.CountRankings(competitionCode);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void testCalculateAndFetchRankings_NoRankingsFound() {
        String competitionCode = "COMP_CODE";
        List<Ranking> rankings = new ArrayList<>();

        Mockito.when(rankingRepository.calculateRankingsForCompetition(competitionCode)).thenReturn(rankings);

        List<RankingDTOresp> result = rankingService.CountRankings(competitionCode);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void testCalculateAndFetchRankings_MultipleRankings() {
        String competitionCode = "COMP_CODE";
        List<Ranking> rankings = new ArrayList<>();
        rankings.add(new Ranking());
        rankings.add(new Ranking());
        rankings.add(new Ranking());

        Mockito.when(rankingRepository.calculateRankingsForCompetition(competitionCode)).thenReturn(rankings);
        Mockito.when(rankingRepository.save(Mockito.any(Ranking.class))).thenReturn(new Ranking());
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.eq(RankingDTOresp.class))).thenReturn(new RankingDTOresp());

        List<RankingDTOresp> result = rankingService.CountRankings(competitionCode);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
    }

}
