package CrownAndAnchorGame;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class Bug1_Testing
{

  @Test
  public void winningWithOneMatch_increasesBalanceByWinnings()
  {
    // Game with two dice with the same value, 
    // and a third with a different value
    Dice d1 = new Dice();
    Dice d2 = d1;
    Dice d3;
    do {
      d3 = new Dice();
    }
    while (d3.getValue().toString().equals(d1.getValue().toString()));
    Game game = new Game(d1, d2, d3);
    // starting balance of $100 who bets $5
    Player player = new Player("Fred", 100);
    int bet = 5;
    int startingBalance = player.getBalance(); // = $100

    // Player rolls a match to the third dice
    DiceValue pick = d3.getValue();
    int winnings = game.playRound(player, pick, bet);

    // Player's balance should be increased by the amount of the winnings
    int finalBalance = player.getBalance();
    assertThat(finalBalance).isEqualTo(startingBalance + winnings);
  }
}
