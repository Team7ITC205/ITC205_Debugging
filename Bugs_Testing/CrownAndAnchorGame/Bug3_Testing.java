package CrownAndAnchorGame;

import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Bug 3: Odds in the game do not appear to be correct.
 */
public class Bug3_Testing
{
  @Test
  public void ratioShouldBeConsistent()
  {
    // 100 games
    int games = 100;
    for (int g = 1; g < games; g++) {
      int wins = 0;
      int rounds = 100000;
      double ratio;

      // with 100,000 rounds each, with 3 random dice
      for (int r = 0; r < rounds; r++) {
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();
        Game game = new Game(d1, d2, d3);
        // starting balance of $5
        Player player = new Player("Fred", 5);
        // bets $5
        int bet = 5;
        // A random pick
        DiceValue pick = new Dice().getValue();

        int winnings = game.playRound(player, pick, bet);
        // Count the number of wins
        if (winnings > 0) {
          wins++;
        }
      }
      // ratio calculation
      ratio = (double)wins / (double)rounds;

      // wins should be arround 0.42
      assertThat(wins).isGreaterThan(41000); // ensure all rounds are run
      assertThat(ratio).isGreaterThan(0.41).isLessThan(0.43);
    }
  }



  @Test
  public void ratioDiceValuesHouldBeEqual()
  {
    int runs = 300000;
    int symbols = 6;
    int[] count = new int[symbols];
    int lowerBoundary = (int)(((double)runs / symbols) * 0.95);
    int upperBoundary = (int)(((double)runs / symbols) * 1.05);
    Dice d1 = new Dice();
    DiceValue dv;

    for (int i = 0; i < runs; i++) {
      dv = d1.roll();

      if (dv.equals(DiceValue.CROWN)) {
        count[0]++;
      }
      if (dv.equals(DiceValue.ANCHOR)) {
        count[1]++;
      }
      if (dv.equals(DiceValue.HEART)) {
        count[2]++;
      }
      if (dv.equals(DiceValue.DIAMOND)) {
        count[3]++;
      }
      if (dv.equals(DiceValue.CLUB)) {
        count[4]++;
      }
      if (dv.equals(DiceValue.SPADE)) {
        count[5]++;
      }
    }
    for (int j =0; j < 6; j++) {
      assertThat(count[j]).isNotNull();
    }
    for (int j =0; j < 6; j++) {
      assertThat(count[j]).isGreaterThan(lowerBoundary).isLessThan(upperBoundary);
    }
  }

}