/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/27/26
 * Last Modified: 5/1/26
 * 
 * Class to create any type of enemy subclass in one class 
 */

package game.model;
import game.model.enemySubClass.*;

/**
 * A class to create enemy subclasses from input randomly generated
 * Returns and becomes the enemy the player will have to fight
 */
public class enemyFactory {
    /**
     * Method to evaluate input to return the specified enemy subclass
     * 
     * @param type value to determine what enemy is returned
     * @return enemy object. Null if not given the right value
     */
    public enemy chooseEnemy(int type){
        switch(type) {
            case 1:
                return new enemyChiikawa();
            case 2: 
                return new enemyHachiware();
            case 3: 
                return new enemyUsagi();
            case 4:
                return new enemyMcCuen();
            case 5:
                return new enemyKeller();
            case 6:
                return new enemyGardner();
            default:
                return null;
        }
    }
}