package CrownAndAnchorGame;

import org.junit.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Bug 2: Player cannot reach betting limit.
 */
public class Bug2_Testing
{
  @Test
  public void balanceFiveAndLimitOfZero_betFiveLoseFive_balanceIsZero()
  {
    // Game with 3 dice with the same value
    Dice d1 = new Dice();
    Game game = new Game(d1, d1, d1);
    // starting balance of $5
    Player player = new Player("Fred", 5);
    // bets $5
    int bet = 5;
    // limit of 0
    int limit = 0;
    player.setLimit(limit);

    DiceValue pick;
    do {
      pick = new Dice().getValue();
    }
    while (pick.toString().equals(d1.getValue().toString()));

    // player loses round
    game.playRound(player, pick, bet);

    // balance should be equal to limit zero
    int finalBalance = player.getBalance();
    assertThat(finalBalance).isEqualTo(limit).isEqualTo(0);
  }
}