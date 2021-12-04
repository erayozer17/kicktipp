package com.erayozer.kicktipp.service;

import com.erayozer.kicktipp.model.Bet;
import com.erayozer.kicktipp.model.MatchType;
import com.erayozer.kicktipp.model.Result;

public class PointCalculator {

    private static final int JOKER_FACTOR = 2;
    private static final int ACHTEL_POINTS = 10;
    private static final int QUARTER_POINTS = 15;
    private static final int HALF_POINTS = 20;
    private static final int FINAL_POINTS = 30;
    private static final int CHAMPION_POINTS = 50;
    private static final int GOAL_KING_POINTS = 25;

    public int calculateGroupPhase(Bet bet, Result result) {
        int totalScore = 0;
        if (!isComparisonAvailableForGroupPhase(bet, result)) {
            return -1;
        }
        if (isWinByEven(bet, result)) {
            totalScore = 2;
            if (isBetJoker(bet)) {
                totalScore *= JOKER_FACTOR;
            }
            return totalScore;
        }
        if (isWinByResult(bet, result)) {
            totalScore = 7;
            if (isBetJoker(bet)) {
                totalScore *= JOKER_FACTOR;
            }
            return totalScore;
        }
        if (isWinByTendency(bet, result)) {
            totalScore = 2;
            if (isDifferenceEqual(bet, result)) {
                totalScore += 2;
            }
            if (isBetJoker(bet)) {
                totalScore *= JOKER_FACTOR;
            }
        }

        return totalScore;
    }

    private boolean isComparisonAvailableForGroupPhase(Bet bet, Result result) {
        if ((!bet.getHomeTeam().getName().equals(result.getHomeTeam().getName())) ||
                (!bet.getAwayTeam().getName().equals(result.getAwayTeam().getName())) ||
                (bet.getMatchType() != result.getMatchType()) ||
                (bet.getMatchType() != MatchType.GROUP || result.getMatchType() != MatchType.GROUP)) {
            return false;
        }
        return true;
    }

    private boolean isBetHomeWin(Bet bet) {
        return bet.getHomeScore() > bet.getAwayScore();
    }

    private boolean isBetAwayWin(Bet bet) {
        return bet.getHomeScore() < bet.getAwayScore();
    }

    private boolean isResultHomeWin(Result result) {
        return result.getHomeScore() > result.getAwayScore();
    }

    private boolean isResultAwayWin(Result result) {
        return result.getHomeScore() < result.getAwayScore();
    }

    private boolean isResultEven(Result result) {
        return result.getAwayScore() == result.getHomeScore();
    }

    private boolean isBetEven(Bet bet) {
        return bet.getAwayScore() == bet.getHomeScore();
    }

    private int differenceBetweenHomeAwayResult(Result result) {
        return result.getHomeScore() - result.getAwayScore();
    }

    private int differenceBetweenHomeAwayBet(Bet bet) {
        return bet.getHomeScore() - bet.getAwayScore();
    }

    private boolean isWinByResult(Bet bet, Result result) {
        return bet.getHomeScore() == result.getHomeScore() && bet.getAwayScore() == result.getAwayScore();
    }

    private boolean isWinByEven(Bet bet, Result result) {
        return isBetEven(bet) && isResultEven(result);
    }

    private boolean isWinByTendency(Bet bet, Result result) {
        return (isBetAwayWin(bet) && isResultAwayWin(result)) || (isBetHomeWin(bet) && isResultHomeWin(result));
    }

    private boolean isDifferenceEqual(Bet bet, Result result) {
        return differenceBetweenHomeAwayBet(bet) == differenceBetweenHomeAwayResult(result);
    }

    private boolean isBetJoker(Bet bet) {
        return bet.isJoker();
    }
}
