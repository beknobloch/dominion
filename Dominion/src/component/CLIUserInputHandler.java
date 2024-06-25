package component;

import java.util.ArrayList;
import java.util.Scanner;

public class CLIUserInputHandler implements UserInputHandler {

    Scanner scanner;

    public CLIUserInputHandler() {
        scanner = new Scanner(System.in);
    }


    @Override
    public Kingdom initiateGame() {
        
        System.out.println("""
            \n\n\t\tWelcome to
            \t\t________                 .__       .__               
            \t\t\\______ \\   ____   _____ |__| ____ |__| ____   ____  
            \t\t |    |  \\ /  _ \\ /     \\|  |/    \\|  |/  _ \\ /    \\ 
            \t\t |    `   (  <_> )  Y Y  \\  |   |  \\  (  <_> )   |  \\
            \t\t/_______  /\\____/|__|_|  /__|___|  /__|\\____/|___|  /
            \t\t        \\/             \\/        \\/               \\/  
            \t\t                 """);
        
        while(true) {
            System.out.println("Select a kingdom to play with: \n");
            int i = 0;
            for (KingdomOption ko : KingdomOption.values())
            {
                ++i;
                System.out.println(i + ". " + ko.toString());
            }
            System.out.print("\nType a number: ");
            int choice = scanner.nextInt();
            scanner.skip("\\R");

            try {
                return new Kingdom(this, KingdomOption.get(choice), yes_or_no("Would you like to play with Colony and Platinum?"););
            } catch (Exception e) {
            }
        }
    }

    @Override
    public int getPlayerCount() {
        System.out.print("\nHow many players are there?: ");
        int playCount = scanner.nextInt();
        scanner.skip("\\R");
        return playCount;
    }

    @Override
    public String getPlayerName() {
        System.out.print("\nType player name: ");
        String read = scanner.nextLine();
        System.out.println("\n\n\n");
        return read;
    }

    @Override
    public void display(String message, boolean enterToProceed)
    {
        System.out.println("\n" + message);

        if (enterToProceed)
        {
            System.out.print("\nPress enter to proceed.");
            scanner.nextLine();
        }
        
        System.out.println();
    }

    @Override
    public void display(String message)
    {
        display(message, true);
    }

    @Override
    public boolean yes_or_no(String question)
    {
        System.out.print(question + ": ");
        String response = scanner.nextLine();
        while (!(response.toLowerCase().equals("yes") || response.toLowerCase().equals("no") || response.toLowerCase().equals("y") || response.toLowerCase().equals("n")))
        {
            System.out.print(question + " Please type yes or no: ");
            response = scanner.nextLine();
        }
        return response.toLowerCase().equals("yes") || response.toLowerCase().equals("y");
    }
    

    @Override
    public void markNewPlayerTurn(Player player)
    {
        System.out.println("\n\n----------");
        display(player.name() + "'s turn.\n\n----------\n");
    }

    @Override
    public void displayRemainingBuys(Player player)
    {
        System.out.println("\nYou have $" + player.coins() + " and " + player.buys() + " remaining buys.\n");
    }
    @Override
    public void displayRemainingActions(Player player)
    {
        System.out.println("\nYou have " + player.actions() + " remaining actions.\n");
    }
    @Override
    public void concludePlayerTurn(Player player)
    {
        System.out.println("Your next hand is " + player.hand());
        System.out.println();
    }

    @Override
    public Card chooseCardFromHand(Player player, String message) throws IndexOutOfBoundsException
    {
        if (player.hand().isEmpty()) throw new IndexOutOfBoundsException("The player's hand is empty.");
        int i = 1;
        for (Card c : player.hand())
        {
            System.out.println("\n\n" + message + ":\n");
            System.out.println(i + ": " + c.toString());
            ++i;
        }
        System.out.print("\nPlease select a card by number: ");
        int selection = scanner.nextInt();
        scanner.skip("\\R");
        while (selection < 1 || selection > player.hand().size()) {
            System.out.print("\nThat's not a valid selection. Please choose again: ");
            selection = scanner.nextInt();
            scanner.skip("\\R");
        }

        return player.hand().get(selection - 1);
    }

    ArrayList<Card> chooseCards(Player player, String message, int minCardsToChoose, int maxCardsToChoose, String type, String l)
    {
        ArrayList<Card> location = player.hand();
        switch (l) {
            case "hand" -> location = player.hand();
            case "discard pile" -> location = player.discardPile();
            default -> {
            }
        }

        ArrayList<Card> chosenCards = new ArrayList<>();
        System.out.println(message);
        System.out.println("Your " + l + ": " + location);

        ArrayList<Card> selection = new ArrayList<>();

        for (Card c : location)
        {
            if (type.equals("all") || (type.equals("action") && c.type().contains(Type.ACTION)) || (type.equals("treasure") && c.type().contains(Type.TREASURE)))
            {
                selection.add(c);
            } 
        }

        int cardsSelected = 0;
        while (true) {
            
            int i = 0;
            for (Card c : selection)
            {
                ++i;
                System.out.println(i + ". " + c);
            }

            System.out.print("\nEnter the index of a card (0 for none): ");
            int index = scanner.nextInt();
            scanner.skip("\\R");
            if (index == 0 && cardsSelected >= minCardsToChoose) {
                break;
            }
            if (index > 0 && index <= location.size()) {
                Card selectedCard = selection.get(index - 1);
                chosenCards.add(selectedCard);
                ++cardsSelected;
                selection.remove(selectedCard);
                if (cardsSelected >= maxCardsToChoose && maxCardsToChoose != -1) {
                    break;
                }
            } else {
                System.out.println("Invalid index. Please choose again.");
            }
        }
        return chosenCards;
    }

    @Override
    public ArrayList<Card> chooseCardsFromHand(Player player, String message, int minCardsToChoose, int maxCardsToChoose, String type)
    {
        return chooseCards(player, message, minCardsToChoose, maxCardsToChoose, type, "hand");
    }

    @Override
    public ArrayList<Card> chooseCardsFromDiscardPile(Player player, String message, int minCardsToChoose, int maxCardsToChoose, String type)
    {
        return chooseCards(player, message, minCardsToChoose, maxCardsToChoose, "discard pile", type);
    }

    @Override
    public ArrayList<Card> selectCardsFromSupply(Player player, String message, int minCardsToChoose, int maxCardsToChoose)
    {

        System.out.println("The current supply:\n");

        int i = 0;
        for (Supply<Card> s : player.kingdom().kingdom())
        {
            ++i;
            Card availableCard;
            try {
                availableCard = s.peek_card();
            } catch (Exception e) {
                availableCard = s.displayCard();
            }

            System.out.println(String.format("%d. %s %s  |  ¤%d  |  :  %s", i, availableCard.toString(), availableCard.typesToString(), availableCard.cost(), availableCard.description()));
        }

        int cardsSelected = 0;
        ArrayList<Card> chosenCards = new ArrayList<>();

        while (true) {
            System.out.print("\nEnter the index of a card (0 for none): ");
            int index = scanner.nextInt();
            scanner.skip("\\R");
            if (index == 0 && cardsSelected > minCardsToChoose) {
                break;
            }
            if (index > 0 && index <= player.kingdom.kingdom().size()) {
                
                Card selectedCard = player.kingdom.kingdom().get(index - 1).displayCard();
                chosenCards.add(selectedCard);
                System.out.println("Selected card: " + selectedCard);
                
                ++cardsSelected;
                if (cardsSelected >= maxCardsToChoose && maxCardsToChoose != -1) {
                    break;
                }

            } else {
                System.out.println("Invalid index. Please choose again.");
            }
        }
        
        return chosenCards;
    }

}
