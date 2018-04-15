package katas.sortable_poker_hands;

import java.util.List;

public class Rank {
    private int baseRank;
    private List<Card> scoringCards;
    private List<Card> nonScoringCards;

    public Rank(int baseRank, List<Card> scoringCards, List<Card> nonScoringCards) {
        this.baseRank = baseRank;
        this.scoringCards = scoringCards;
        this.nonScoringCards = nonScoringCards;
    }

    public int getRank() {
        int scoringValue = 1;
        int nonScoringValue = 0;
        for (Card scoringCard : scoringCards) {
//            scoringValue += scoringCard.rankValue;
        }

        for (Card nonScoringCard : nonScoringCards) {
//            nonScoringValue += nonScoringCard.rankValue;
        }

        return (baseRank * scoringValue) + nonScoringValue;
    }
}
