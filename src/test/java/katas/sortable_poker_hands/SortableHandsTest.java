package katas.sortable_poker_hands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SortableHandsTest {
    @Test
    public void pokerHandSortTest() {
        // Arrange
        ArrayList<PokerHand> expected = new ArrayList<PokerHand>();
        expected.add(new PokerHand("KS AS TS QS JS"));
        expected.add(new PokerHand("2H 3H 4H 5H 6H"));
        expected.add(new PokerHand("AS 2S 3S 4S 5S"));
        expected.add(new PokerHand("AS AD AC AH JD"));
        expected.add(new PokerHand("JS JD JC JH 5D"));
        expected.add(new PokerHand("JS JD JC JH 3D"));
        expected.add(new PokerHand("2S AH 2H AS AC"));
        expected.add(new PokerHand("AS 3S 4S 8S 2S"));
        expected.add(new PokerHand("KS 9S TS QS JS"));
        expected.add(new PokerHand("2H 3H 5H 6H 7H"));
        expected.add(new PokerHand("3S 4H 5H 6S 7C"));
        expected.add(new PokerHand("2S 3H 4H 5S 6C"));
        expected.add(new PokerHand("2D AC 3H 4H 5S"));
        expected.add(new PokerHand("AH AC 5H 6H AS"));
        expected.add(new PokerHand("2S 2H 4H 5S 4C"));
        expected.add(new PokerHand("AH AC 5H 6H 7S"));
        expected.add(new PokerHand("5H 6H 7S 4H 4C"));
        expected.add(new PokerHand("AH AC 4H 6H 7S"));
        expected.add(new PokerHand("2S AH 4H 5S KC"));
        expected.add(new PokerHand("2S 3H 6H 7S 9C"));
        expected.add(new PokerHand("2S 3H 6H 7S 4C"));

        Random random = new Random();
        ArrayList<PokerHand> actual = createRandomOrderedList(random, expected);

        // Act
        Collections.sort(actual);

        // Assert
        Iterator a = actual.iterator();
        for (PokerHand e : expected) {
            assertEquals(e, a.next());
        }
    }

//    @Test
//    public void handShouldBeOnePair() {
//        PokerHand pokerHand = new PokerHand("2C 5S 3C 5C 6C");
//
//        assertEquals("Should be one pair", HandRankValue.ONE_PAIR, pokerHand.rank);
//        assertEquals(121, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeTwoPairs() {
//        PokerHand pokerHand = new PokerHand("2C 5S 6D 5C 6C");
//
//        assertEquals("Should be two pair", HandRankValue.TWO_PAIR, pokerHand.rank);
//        assertEquals(224, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeThreeOfAKind() {
//        PokerHand pokerHand = new PokerHand("5D 5S 2D 5C 6C");
//
//        assertEquals("Should be three of a kind", HandRankValue.THREE_OF_A_KIND, pokerHand.rank);
//        assertEquals(323, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeStraight() {
//        PokerHand pokerHand = new PokerHand("2C 4S 3C 5C 6C");
//
//        assertEquals("Should be a straight", HandRankValue.STRAIGHT, pokerHand.rank);
//        assertEquals(420, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeAceLowStraight() {
//        PokerHand pokerHand = new PokerHand("2C 4D AC 5C 3C");
//
//        assertEquals("Should be a straight", HandRankValue.STRAIGHT, pokerHand.rank);
//        assertEquals(415, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeAceHighStraight() {
//        PokerHand pokerHand = new PokerHand("AC JD KC QC TC");
//
//        assertEquals("Should be a straight", HandRankValue.STRAIGHT, pokerHand.rank);
//        assertEquals(460, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeFlush() {
//        PokerHand pokerHand = new PokerHand("2C 3C TC KC AC");
//
//        assertEquals("Should be a flush", HandRankValue.FLUSH, pokerHand.rank);
//        assertEquals(542, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeFullHouse() {
//        PokerHand pokerHand = new PokerHand("2C 3C 3D 2D 3S");
//
//        assertEquals("Should be a full house", HandRankValue.FULL_HOUSE, pokerHand.rank);
//        assertEquals(613, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeFourOfAKind() {
//        PokerHand pokerHand = new PokerHand("5D 5S 2D 5C 5H");
//
//        assertEquals("Should be four of a kind", HandRankValue.FOUR_OF_A_KIND, pokerHand.rank);
//        assertEquals(722, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeStraightFlush() {
//        PokerHand pokerHand = new PokerHand("2C 3C 5C 4C 6C");
//
//        assertEquals("Should be a straight flush", HandRankValue.STRAIGHT_FLUSH, pokerHand.rank);
//        assertEquals(820, pokerHand.getTotalRank());
//    }
//
//    @Test
//    public void handShouldBeRoyalStraightFlush() {
//        PokerHand pokerHand = new PokerHand("QC KC AC JC TC");
//
//        assertEquals("Should be a royal straight flush", HandRankValue.ROYAL_STRAIGHT_FLUSH, pokerHand.rank);
//        assertEquals(960, pokerHand.getTotalRank());
//    }

    @Test
    public void randomizedTest() {
        List<String> hands = new ArrayList<String>();
        hands.add("JH AH TH KH QH"); // royal flush
        hands.add("JH 9H TH KH QH"); // straight flush
        hands.add("5C 6C 3C 7C 4C"); // straight flush
        hands.add("2D 6D 3D 4D 5D"); // straight flush
        hands.add("2C 3C AC 4C 5C"); // straight flush
        hands.add("JC KH JS JD JH"); // 4 of a kind
        hands.add("JC 7H JS JD JH"); // 4 of a kind
        hands.add("JC 6H JS JD JH"); // 4 of a kind
        hands.add("KH KC 3S 3H 3D"); // full house
        hands.add("2H 2C 3S 3H 3D"); // full house
        hands.add("3D 2H 3H 2C 2D"); // full house
        hands.add("JH 8H AH KH QH"); // flush
        hands.add("4C 5C 9C 8C KC"); // flush
        hands.add("3S 8S 9S 5S KS"); // flush
        hands.add("8C 9C 5C 3C TC"); // flush
        hands.add("QC KH TS JS AH"); // straight
        hands.add("JS QS 9H TS KH"); // straight
        hands.add("6S 8S 7S 5H 9H"); // straight
        hands.add("3C 5C 4C 2C 6H"); // straight
        hands.add("2C 3H AS 4S 5H"); // straight
        hands.add("AC KH QH AH AS"); // 3 of a kind
        hands.add("7C 7S KH 2H 7H"); // 3 of a kind
        hands.add("7C 7S 3S 7H 5S"); // 3 of a kind
        hands.add("AS 3C KH AD KC"); // 2 pairs
        hands.add("3C KH 5D 5S KC"); // 2 pairs
        hands.add("5S 5D 2C KH KC"); // 2 pairs
        hands.add("3H 4C 4H 3S 2H"); // 2 pairs
        hands.add("AH 8S AH KC JH"); // pair
        hands.add("KD 4S KD 3H 8S"); // pair
        hands.add("KC 4H KS 2H 8D"); // pair
        hands.add("QH 8H KD JH 8S"); // pair
        hands.add("8C 4S KH JS 4D"); // pair
        hands.add("KS 8D 4D 9S 4S"); // pair
        hands.add("KD 6S 9D TH AD");
        hands.add("TS KS 5S 9S AC");
        hands.add("JH 8S TH AH QH");
        hands.add("TC 8C 2S JH 6C");
        hands.add("2D 6D 9D TH 7D");
        hands.add("9D 8H 2C 6S 7H");
        hands.add("4S 3H 2C 7S 5H");
        Random random = new Random();
        ArrayList<PokerHand> expected = new ArrayList<PokerHand>();
        int i=0;
        for (String hand : hands) {
            if (i==5) expected.add(new PokerHand("AS AC AH KS AS"));
            else if (i==23) expected.add(new PokerHand("6C 6S 3S 6H 5S"));
            else expected.add(new PokerHand(hand));
            i++;
        }
        for (int j = 0; i < 25000; i++) {
            ArrayList<PokerHand> actual = createRandomOrderedList(random, expected);

            assertTrue(differences(expected, actual) > 30);
            Collections.sort(actual);
//            printHands(expected, actual);
            assertEquals("Expect no differences in the sort order between the expected and actual list of " + expected.size() + " items.", 0, differences(expected, actual));
        }
    }

    private int differences(ArrayList<PokerHand> expected, ArrayList<PokerHand> actual){
        Iterator a = actual.iterator();
        int count = 0;
        for (PokerHand e : expected) {
            count += e.equals(a.next()) ? 0 : 1;
        }
        return count;
    }

    private void printHands(ArrayList<PokerHand> expected, ArrayList<PokerHand> actual) {
        for(int i = 0; i < expected.size(); i++) {
            PokerHand e = expected.get(i);
            PokerHand a = actual.get(i);
            System.out.println(e.toString() + " ::::: " + a.toString());
        }

    }

    private ArrayList<PokerHand> createRandomOrderedList(Random random, ArrayList<PokerHand> expected){
        ArrayList<PokerHand> actual = new ArrayList<>();
        for (PokerHand pokerHand : expected) {
            int j = random.nextInt(actual.size()+1);
            actual.add(j, pokerHand);
        }
        return actual;
    }
}
