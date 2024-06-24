package component;

@FunctionalInterface
public interface CardCreator<E extends Card> {
    E createCard();
}
