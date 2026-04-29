
 
/**
 * Marcus Alexio Prado 
 * Course: Adv Java 
 * Date: 4/23/26 
 * Last Modified: 4/28/26 
 * 
 * Main begins the game and application 
 */

package game;

import game.view.*;
import game.controller.*;

public class Main {
    public static void main(String[] args){
        gameView view = new gameView();
        gameController controller = new gameController(view);
        controller.start();
    }
}