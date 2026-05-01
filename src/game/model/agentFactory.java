/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 5/1/26
 * 
 * Class to create any type of agent subclass in one class
 */

package game.model;
import game.model.agentSubClass.*;

/**
 * A class to create agent subclasses from input taken by user
 * Returns them to become the characters used in the game
 */
public class agentFactory {
    /**
     * Method to evaluate input to return the specified agent subclass
     * 
     * @param type value to determine what agent is returned
     * @return agent object. Null if not given the right value
     */
    public agent chooseCharacter(int type){
        switch (type) {
            case 1:
                return new agentAria();
            case 2: 
                return new agentNangong();
            case 3: 
                return new agentSunna();
            default:
                return null;
        }
    }
}