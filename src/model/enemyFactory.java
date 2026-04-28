/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/27/26
 * Last Modified: 4/27/26
 * 
*/

public class enemyFactory {

    public static enemy chooseCharacter(int type){
        switch (type) {
            case 1:
                return new enemyChiikawa();
                break;
            case 2: 
                return new enemyHachiware();
                break;
            case 3: 
                return new enemyUsagi();
                break;
            case 4:
                return new enemyMcCuen();
                break;
            case 5:
                return new enemyKeller();
                break;
            case 6:
                return new enemyGardner();
                break;
        }
    }
}