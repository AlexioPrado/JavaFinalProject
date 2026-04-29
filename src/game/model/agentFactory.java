/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 4/29/26
 * 
*/

package game.model;
import game.model.agentSubClass.*;

public class agentFactory {
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