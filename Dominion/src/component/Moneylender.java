package component;

import java.util.ArrayList;

public class Moneylender extends Action {

  @Override
  public int cost() { return 4; }

  @Override
  public void effect(Player player, ArrayList<Player> otherPlayers)
  {
    boolean foundCopper = false;
    for (Card c : player.hand())
    {
      if (c instanceof Copper)
      {
        foundCopper = true;
        break;
      }
    }
    if ( foundCopper && userInputHandler.yes_or_no("Trash a copper from your hand for ¤3?") )
    {
      for (Card c : player.hand())
      {
        if (c instanceof Copper)
        {
          player.trash(c);
          player.remove_from_hand(c);
          player.earn_coins(3);
          break;
        }
      }
    }
  }

  @Override
  public String toString() { return "Moneylender"; }
  @Override
  public String description() { return "You may trash a copper from your hand for ¤3."; }

}
