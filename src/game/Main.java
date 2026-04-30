/**
 * Marcus Alexio Prado 
 * Course: Adv Java 
 * Date: 4/23/26 
 * Last Modified: 4/30/26 
 * 
 * Main.java begins the game application.
 */

package game;
import game.view.*;
import game.controller.*;

/**
 * Responsible for starting application.
 */
public class Main {
    /**
     * Creates a view and controller to 
     * start the application's game flow.
     * 
     * @param args command-line arguements
     */
    public static void main(String[] args){
        gameView view = new gameView();
        gameController controller = new gameController(view);
        controller.start();
    }
}