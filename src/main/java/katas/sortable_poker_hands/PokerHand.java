package katas.sortable_poker_hands;

import java.util.*;
import java.util.stream.Collectors;

class PokerHand implements Comparable<PokerHand> {
    private List<Card> cards = new ArrayList<>();
    int rank;
    int[] highCards;

    PokerHand(String handAsString) {
        String[] cs = handAsString.split(" ");
        for (String c : cs) {
            cards.add(new Card(c));
        }
        this.sort();

        PokerHandRankerChain chain = new PokerHandRankerChain(this);
        this.rank = chain.getRank();
        this.highCards = chain.getHighCards();

    }

    List<Card> getCards() {
        return cards;
    }

    private void sort() {
        cards.sort(Comparator.comparingInt(Card::getRank));
    }

    @Override
    public int compareTo(PokerHand o) {
        if (this.rank > o.rank) {
            return -1;
        } else if (this.rank < o.rank) {
            return 1;
        } else {
//            for (int i = this.highCards.length; i > 0; i--) {
//                if (this.highCards()[i] > o.highCards()[i]) {
//                    return -1;
//                } else if (this.highCards()[i] < o.highCards()[i]) {
//                    return 1;
//                }
//                return 0;
//            }
//            System.out.println(this.toString() + " ::: " + this.cards.size() + " ::: " + o.cards.size());
            for (int i = this.cards.size() - 1; i >= 0; i--) {
                Card c1 = this.cards.get(i);
                Card c2 = o.cards.get(i);
//                int r1 = this.cards.get(i).getRank();
//                int r2 = o.cards.get(i).getRank();
//                if (this.rank == 800 && (c1.faceValue.equals("A") || c2.faceValue.equals("A"))) {
//                    System.out.println(c1.toString() + " :: " + c2.toString());
//                }
                if (c1.getRank() > c2.getRank()) {
                    return -1;
                } else if (c1.getRank() < c2.getRank()) {
                    return 1;
                }
            }
            return 0;
        }
    }

    int getTotalRank() {
        int totalRank = rank;
//        for (Card card : cards) {
//            totalRank += card.getRank();
//        }
        return totalRank;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : this.cards) {
            sb.append(card.faceValue).append(card.suite).append(" ");
        }
        sb.append(" - ").append(this.rank).append(" - ").append(this.getTotalRank());
        return sb.toString();
    }

    public int[] highCards() {
        return this.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getFaceValue))
                .values()
                .stream()
                .filter(values -> values.size() == 1)
                .flatMap(Collection::stream)
                .mapToInt(Card::getRank)
                .toArray();
    }
}

class HandDecider {
    private PokerHand pokerHand;

    HandDecider(PokerHand pokerHand) {
        this.pokerHand = pokerHand;
    }

    boolean isOnePair() {
        resetAceHigh();
        return this.pokerHand.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getFaceValue))
                .values()
                .stream()
                .filter(cards -> cards.size() == 2)
                .count() == 1;
    }

    boolean isTwoPairs() {
        resetAceHigh();
        return this.pokerHand.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getFaceValue))
                .values()
                .stream()
                .filter(cards -> cards.size() == 2)
                .count() == 2;
    }

    boolean isThreeOfaKind() {
        resetAceHigh();
        return this.pokerHand.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getFaceValue))
                .values()
                .stream()
                .filter(cards -> cards.size() == 3)
                .count() == 1;
    }

    boolean isStraight() {
        resetAceHigh();
        boolean isLow = this.pokerHand.getCards()
                .stream()
                .map(Card::getFaceValue)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList("A", "2"));
        boolean isStraight = true;
        int previousRankValue = 0;
        int cardsToIterate = isLow ? 4 : 5;
        this.pokerHand.getCards().get(this.pokerHand.getCards().size() - 1).isAceLow = isLow;
        for (int i = 0; i < cardsToIterate; i++) {
            Card card = this.pokerHand.getCards().get(i);
            if (previousRankValue == 0) {
                previousRankValue = card.getRank();
            } else {
                if (previousRankValue + 1 != card.getRank()) {
                    isStraight = false;
                } else {
                    previousRankValue = card.getRank();
                }
            }
        }

        return isStraight;
    }

    boolean isFlush() {
//        resetAceHigh();
        return this.pokerHand.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getSuite))
                .values()
                .stream()
                .filter(cards -> cards.size() == 5)
                .count() == 1;
    }

    boolean isFullHouse() {
        resetAceHigh();
        return isThreeOfaKind() && isOnePair();
    }

    boolean isFourOfaKind() {
        resetAceHigh();
        return this.pokerHand.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getFaceValue))
                .values()
                .stream()
                .filter(cards -> cards.size() == 4)
                .count() == 1;
    }

    boolean isStraightFlush() {
//        resetAceHigh();
        return isStraight() && isFlush();
    }

    boolean isRoyalStraightFlush() {
        resetAceHigh();
        boolean isHigh = this.pokerHand.getCards()
                .stream()
                .map(Card::getFaceValue)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList("A", "K"));
        return isHigh && isStraightFlush();
    }

    void resetAceHigh() {
        for (Card card : this.pokerHand.getCards()) {
            if (card.faceValue.equals("A")) {
                card.isAceLow = false;
            }
        }
    }
}

class HandRankValue {
    static int HIGH_CARD = 0;
    static int ONE_PAIR = 100;
    static int TWO_PAIR = 200;
    static int THREE_OF_A_KIND = 300;
    static int STRAIGHT = 400;
    static int FLUSH = 500;
    static int FULL_HOUSE = 600;
    static int FOUR_OF_A_KIND = 700;
    static int STRAIGHT_FLUSH = 800;
    static int ROYAL_STRAIGHT_FLUSH = 900;
}

class PokerHandRankerChain {
    private PokerHandRanker pokerHandRanker;

    PokerHandRankerChain(PokerHand pokerHand) {
        List<PokerHandRanker> rankers = new ArrayList<>();
        rankers.add(new RoyalStraightFlushRanker(pokerHand));
        rankers.add(new StraightFlushRanker(pokerHand));
        rankers.add(new FourOfAKindRanker(pokerHand));
        rankers.add(new FullHouseRanker(pokerHand));
        rankers.add(new FlushRanker(pokerHand));
        rankers.add(new StraightRanker(pokerHand));
        rankers.add(new ThreeOfAKindRanker(pokerHand));
        rankers.add(new TwoPairRanker(pokerHand));
        rankers.add(new OnePairRanker(pokerHand));

        for (int i = 0; i < rankers.size() - 1; i++) {
            rankers.get(i).setNextRanker(rankers.get(i + 1));
        }

        pokerHandRanker = rankers.get(0);
    }

    int getRank() {
        return this.pokerHandRanker.getRank();
    }

    int[] getHighCards() {
        return this.pokerHandRanker.getHighCards();
    }
}

interface PokerHandRanker {
    void setNextRanker(PokerHandRanker nextRanker);
    int getRank();
    int[] getHighCards();
}

class RoyalStraightFlushRanker implements PokerHandRanker {
    private PokerHandRanker pokerHandRanker;
    private HandDecider handDecider;

    RoyalStraightFlushRanker(PokerHand pokerHand) {
        this.handDecider = new HandDecider(pokerHand);
    }

    @Override
    public void setNextRanker(PokerHandRanker nextRanker) {
        this.pokerHandRanker = nextRanker;
    }

    @Override
    public int getRank() {
        return handDecider.isRoyalStraightFlush() ? HandRankValue.ROYAL_STRAIGHT_FLUSH : pokerHandRanker.getRank();
    }

    @Override
    public int[] getHighCards() {
        return new int[0];
    }
}

class StraightFlushRanker implements PokerHandRanker {
    private PokerHandRanker pokerHandRanker;
    private HandDecider handDecider;

    StraightFlushRanker(PokerHand pokerHand) {
        this.handDecider = new HandDecider(pokerHand);
    }

    @Override
    public void setNextRanker(PokerHandRanker nextRanker) {
        this.pokerHandRanker = nextRanker;
    }

    @Override
    public int getRank() {
        return handDecider.isStraightFlush() ? HandRankValue.STRAIGHT_FLUSH : pokerHandRanker.getRank();
    }

    @Override
    public int[] getHighCards() {
        return new int[0];
    }
}

class FourOfAKindRanker implements PokerHandRanker {
    private PokerHandRanker pokerHandRanker;
    private HandDecider handDecider;

    FourOfAKindRanker(PokerHand pokerHand) {
        this.handDecider = new HandDecider(pokerHand);
    }

    @Override
    public void setNextRanker(PokerHandRanker nextRanker) {
        this.pokerHandRanker = nextRanker;
    }

    @Override
    public int getRank() {
        return handDecider.isFourOfaKind() ? HandRankValue.FOUR_OF_A_KIND : pokerHandRanker.getRank();
    }

    @Override
    public int[] getHighCards() {
        return new int[0];
    }
}

class FullHouseRanker implements PokerHandRanker {
    private PokerHandRanker pokerHandRanker;
    private HandDecider handDecider;

    FullHouseRanker(PokerHand pokerHand) {
        this.handDecider = new HandDecider(pokerHand);
    }

    @Override
    public void setNextRanker(PokerHandRanker nextRanker) {
        this.pokerHandRanker = nextRanker;
    }

    @Override
    public int getRank() {
        return handDecider.isFullHouse() ? HandRankValue.FULL_HOUSE : pokerHandRanker.getRank();
    }

    @Override
    public int[] getHighCards() {
        return new int[0];
    }
}

class FlushRanker implements PokerHandRanker {
    private PokerHandRanker pokerHandRanker;
    private HandDecider handDecider;

    FlushRanker(PokerHand pokerHand) {
        this.handDecider = new HandDecider(pokerHand);
    }

    @Override
    public void setNextRanker(PokerHandRanker nextRanker) {
        this.pokerHandRanker = nextRanker;
    }

    @Override
    public int getRank() {
        return handDecider.isFlush() ? HandRankValue.FLUSH : pokerHandRanker.getRank();
    }

    @Override
    public int[] getHighCards() {
        return new int[0];
    }
}

class StraightRanker implements PokerHandRanker {
    private PokerHandRanker pokerHandRanker;
    private HandDecider handDecider;

    StraightRanker(PokerHand pokerHand) {
        this.handDecider = new HandDecider(pokerHand);
    }

    @Override
    public void setNextRanker(PokerHandRanker nextRanker) {
        this.pokerHandRanker = nextRanker;
    }

    @Override
    public int getRank() {
        return handDecider.isStraight() ? HandRankValue.STRAIGHT : pokerHandRanker.getRank();
    }

    @Override
    public int[] getHighCards() {
        return new int[0];
    }
}

class ThreeOfAKindRanker implements PokerHandRanker {
    private PokerHandRanker pokerHandRanker;
    private HandDecider handDecider;

    ThreeOfAKindRanker(PokerHand pokerHand) {
        this.handDecider = new HandDecider(pokerHand);
    }

    @Override
    public void setNextRanker(PokerHandRanker nextRanker) {
        this.pokerHandRanker = nextRanker;
    }

    @Override
    public int getRank() {
        return handDecider.isThreeOfaKind() ? HandRankValue.THREE_OF_A_KIND : pokerHandRanker.getRank();
    }

    @Override
    public int[] getHighCards() {
        return new int[0];
    }
}

class TwoPairRanker implements PokerHandRanker {
    private PokerHandRanker pokerHandRanker;
    private HandDecider handDecider;

    TwoPairRanker(PokerHand pokerHand) {
        this.handDecider = new HandDecider(pokerHand);
    }

    @Override
    public void setNextRanker(PokerHandRanker nextRanker) {
        this.pokerHandRanker = nextRanker;
    }


    @Override
    public int getRank() {
        return handDecider.isTwoPairs() ? HandRankValue.TWO_PAIR : pokerHandRanker.getRank();
    }

    @Override
    public int[] getHighCards() {
        return new int[0];
    }
}

class OnePairRanker implements PokerHandRanker {
    private HandDecider handDecider;
    private PokerHand pokerHand;

    OnePairRanker(PokerHand pokerHand) {
        this.pokerHand = pokerHand;
        this.handDecider = new HandDecider(this.pokerHand);
    }

    @Override
    public void setNextRanker(PokerHandRanker nextRanker) {
    }

    @Override
    public int getRank() {
        return handDecider.isOnePair() ? HandRankValue.ONE_PAIR : HandRankValue.HIGH_CARD;
    }

    @Override
    public int[] getHighCards() {
        return this.pokerHand.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getFaceValue))
                .values()
                .stream()
                .filter(values -> values.size() == 1)
                .flatMap(Collection::stream)
                .mapToInt(Card::getRank)
                .toArray();
    }
}