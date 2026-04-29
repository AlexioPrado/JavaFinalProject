/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 4/26/26
 * 
*/

package game.model;
import game.model.agentSubClass.*;

public class agentFactory {
    private static agent placeholder;
    public agent chooseCharacter(int type){
        switch (type) {
            case 1:
                return placeholder = new agentAria();
            case 2: 
                return placeholder = new agentNangong();
            case 3: 
                return placeholder = new agentSunna();
            default:
                return placeholder;
        }
    }
}