package component;

import java.util.ArrayList;

public class CouncilRoom extends Action {

  @Override
  public int cost() { return 5; }

  @Override
  public int cards() { return 4; }
  @Override
  public int buys() { return 1; }

  @Override
  public void effect(Player player, ArrayList<Player> otherPlayers)
  {

    for (Player p : otherPlayers)
      p.draw(1);
    
  }

  @Override
  public String toString() { return "Council Room"; }
  @Override
  public String description() { return "Each other player draws a card."; }
  
}
