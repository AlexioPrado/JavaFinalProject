/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 4/26/26
 * 
*/

public class agentFactory {

    public static agent chooseCharacter(int type){
        switch (type) {
            case 1:
                return new agentAria();
            case 2: 
                return new agentNangong();
            case 3: 
                return new agentSunna();
        }
    }
}