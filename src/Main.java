/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/23/26
 * Last Modified: 
 * 
 * Main begins the game and application
 */

public class Main {
    public static void main(String[] args){
        game game = new game();
        gameView view = new gameView();
        gameController controller = new gameController(model, view);
        controller.start();
    }
}