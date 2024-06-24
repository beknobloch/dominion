
import component.CLIUserInputHandler;
import component.Game;
import component.UserInputHandler;


public class App {
    public static void main(String[] args) throws Exception {
        
        UserInputHandler myHandler = new CLIUserInputHandler();
        Game myGame = new Game(myHandler);
        myGame.mainGameFlow();

    }
}
