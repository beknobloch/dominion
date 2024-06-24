package component;

import java.util.ArrayList;

public class Supply <E extends Card> {

    ArrayList<E> cards;
    E displayCard;

    public Supply(int count, CardCreator<E> cardCreator) {
        
        displayCard = cardCreator.createCard();

        cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            E newCard = cardCreator.createCard();
            cards.add(newCard);
        }
    }

    public int size() {
        return cards.size();
    }

    public E displayCard() {
        return displayCard;
    }
    public String name() {
        return displayCard.toString();
    }
    public String description() {
        return displayCard.description();
    }

    public boolean empty() {
        return size() <= 0;
    }

    public E pull_card() throws Exception {
        if (size() > 0) {
            return cards.remove(0);
        }
        throw new Exception("Whoops! Tried to pull a card from an empty pile!");
    }

    public E peek_card() throws Exception {
        if (size() > 0) {
            return cards.get(0);
        }
        throw new Exception("This supply pile is empty.");
    }

    public void place_card(E card) {
        cards.add(0, card);
    }

}
