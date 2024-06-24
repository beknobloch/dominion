package component;

import java.util.ArrayList;

public class Moneylender extends Action {

  public int cost() { return 4; }

  public void effect(Player player, ArrayList<Player> otherPlayers)
  {
    boolean foundCopper = false;
    Card copper;
    for (Card c : player.hand())
    {
      if (c instanceof Copper)
      {
        foundCopper = true;
        copper = c;
        break;
      }
    }
    if ( foundCopper && userInputHandler.yes_or_no("Trash a copper from your hand for ¤3?") )
    {
      player.trash(copper);
      player.remove_from_hand(copper);
      player.earn_coin(3);
    }
    
  }

  @Override
  public String toString() { return "Moneylender"; }
  @Override
  public String description() { return "You may trash a copper from your hand for ¤3."; }

}
