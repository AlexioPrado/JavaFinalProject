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
        gameView view = new gameView();
        gameController controller = new gameController(view);
        controller.start();
    }
}