package com.erayozer.kicktipp.service;

import com.erayozer.kicktipp.model.Bet;
import com.erayozer.kicktipp.model.MatchType;
import com.erayozer.kicktipp.model.Result;
import com.erayozer.kicktipp.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PointCalculatorTest {

    @InjectMocks
    PointCalculator pointCalculator;

    @Test
    public void test_exact_result(){
        Bet bet = new Bet(
                false,
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                6,
                4,
                MatchType.GROUP
        );

        Result result = new Result(
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                6,
                4,
                MatchType.GROUP
        );
        int point = pointCalculator.calculateGroupPhase(bet, result);
        assert point == 7;
        bet.setJoker(true);
        int pointJoker = pointCalculator.calculateGroupPhase(bet, result);
        assert pointJoker == 14;
    }

    @Test
    public void test_wrong_results_compared(){
        Bet bet = new Bet(
                false,
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                6,
                4,
                MatchType.GROUP
        );

        Result result = new Result(
                new Team("Turkey", "TUR"),
                new Team("Italy", "ITA"),
                6,
                4,
                MatchType.GROUP
        );
        int point = pointCalculator.calculateGroupPhase(bet, result);
        assert point == -1;
        bet.setHomeTeam(new Team("Turkey", "TUR"));
        int point2 = pointCalculator.calculateGroupPhase(bet, result);
        assert point2 == -1;
        bet.setAwayTeam(new Team("Italy", "ITA"));
        int point3 = pointCalculator.calculateGroupPhase(bet, result);
        assert point3 == 7;
    }

    @Test
    public void test_no_point_gained(){
        Bet bet = new Bet(
                false,
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                6,
                4,
                MatchType.GROUP
        );

        Result result = new Result(
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                4,
                6,
                MatchType.GROUP
        );
        int point = pointCalculator.calculateGroupPhase(bet, result);
        assert point == 0;
    }

    @Test
    public void test_tendency_points_gained(){
        Bet bet = new Bet(
                false,
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                6,
                4,
                MatchType.GROUP
        );

        Result result = new Result(
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                2,
                1,
                MatchType.GROUP
        );
        int point = pointCalculator.calculateGroupPhase(bet, result);
        assert point == 2;
    }

    @Test
    public void test_tendency_and_difference_points_gained(){
        Bet bet = new Bet(
                false,
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                6,
                4,
                MatchType.GROUP
        );

        Result result = new Result(
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                2,
                0,
                MatchType.GROUP
        );
        int point = pointCalculator.calculateGroupPhase(bet, result);
        assert point == 4;
        bet.setJoker(true);
        int point2 = pointCalculator.calculateGroupPhase(bet, result);
        assert point2 == 8;
    }

    @Test
    public void test_win_by_equal_but_same_score(){
        Bet bet = new Bet(
                false,
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                3,
                3,
                MatchType.GROUP
        );

        Result result = new Result(
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                3,
                3,
                MatchType.GROUP
        );
        int point = pointCalculator.calculateGroupPhase(bet, result);
        assert point == 2;
        bet.setJoker(true);
        int point2 = pointCalculator.calculateGroupPhase(bet, result);
        assert point2 == 4;
    }

    @Test
    public void test_win_by_only_equal(){
        Bet bet = new Bet(
                false,
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                3,
                3,
                MatchType.GROUP
        );

        Result result = new Result(
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                2,
                2,
                MatchType.GROUP
        );
        int point = pointCalculator.calculateGroupPhase(bet, result);
        assert point == 2;
        bet.setJoker(true);
        int point2 = pointCalculator.calculateGroupPhase(bet, result);
        assert point2 == 4;
    }

    @Test
    public void test_different_match_types(){
        Bet bet = new Bet(
                false,
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                3,
                3,
                MatchType.GROUP
        );

        Result result = new Result(
                new Team("Germany", "GER"),
                new Team("France", "FRA"),
                2,
                2,
                MatchType.ACHTEL
        );
        int point = pointCalculator.calculateGroupPhase(bet, result);
        assert point == -1;
    }
}
