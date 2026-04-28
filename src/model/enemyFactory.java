/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/27/26
 * Last Modified: 4/27/26
 * 
*/

public class enemyFactory {
    private static enemy placeholder;
    public static enemy chooseCharacter(int type){
        switch(type) {
            case 1:
                return placeholder = new enemyChiikawa();
            case 2: 
                return placeholder = new enemyHachiware();
            case 3: 
                return placeholder = new enemyUsagi();
            case 4:
                return placeholder = new enemyMcCuen();
            case 5:
                return placeholder = new enemyKeller();
            case 6:
                return placeholder = new enemyGardner();
            default:
                return placeholder;
        }
    }
}