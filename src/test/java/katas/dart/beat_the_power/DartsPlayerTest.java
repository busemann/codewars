package katas.dart.beat_the_power;

import katas.dart.PlayerStatistics;
import katas.dart.Playground;
import org.junit.Assert;
import org.junit.Test;

public class DartsPlayerTest {
    @Test
    public void ICanBeatThePower()
    {
        Playground playground = new Playground(new DartsPlayer());

        for (int i=1; i<=19; i++)
        {
            playground.nextLeg();
        }

        PlayerStatistics p = playground.getPlayerStatistics();
        PlayerStatistics o = playground.getOpponentStatistics();

        System.out.println("You: ");
        writeStatistics(p);
        System.out.println("     ");
        System.out.println("The Power: ");
        writeStatistics(o);
        System.out.println("     ");

        if (p.getWon() <= o.getWon())
        {
            Assert.fail("You have lost to The Power!");
        }
        if (p.getThreeDartAverage() <= 96.14)
        {
            Assert.fail("You'r 3-Dart Average is wnot high enough!");
        }
    }

    private void writeStatistics(PlayerStatistics stats)
    {
        System.out.println("=================================");
        System.out.println("Legs played: " + stats.getLegs());
        System.out.println("Legs won: " + stats.getWon());
        System.out.println("3-Dart average: " + stats.getThreeDartAverage());
        System.out.println("Check out percentage: " + stats.getCheckOutPercentage() + "%");
        System.out.println("Highest finish: " + stats.getHighestFinish());
        System.out.println("180 Scores: " +  stats.getCountOf180());
        System.out.println("140+ Scores: " + stats.getCountOf140Plus());
        System.out.println("100+ Scores: " + stats.getCountOf100Plus());
        System.out.println("9-Darters: " +   stats.getCountOfNineDarters());
    }
}
