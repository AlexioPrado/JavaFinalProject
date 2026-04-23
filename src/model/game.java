/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/23/26
 * Last Modified:
 * 
 * Runs the game loop and flow 
 */

public class game{

    public enum gameState{
        MENU,
        SELECTCHAR,
        FIGHT,
        END
    }

    private gameState currentState = GameState.MENU;

    public void start(){
        currentState = gameState.SELECTCHAR;
    }
}