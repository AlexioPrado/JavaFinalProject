/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/27/26
 * Last Modified: 4/29/26
 * 
*/

package game.model;
import game.model.enemySubClass.*;

public class enemyFactory {
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