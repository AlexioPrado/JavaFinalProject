/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 4/29/26
 * 
 */

package game.model.enemySubClass;
import game.model.enemy;

public class enemyKeller extends enemy {
    public enemyKeller(){
        this.name = "Leslie Keller";
        this.health = 20;
        this.maxHealth = 20;
        this.attack = 4;
        this.isStun = false;
        this.stunDuration = 0;
    }
    

    public void enemyAttack(){
        int totalAttack = attack;

        if (stunDuration == 0 && isStun){
            isStun = !isStun;
        }
        if (isStun){
            totalAttack = -1;
            stunDuration -= 1;
        } 
        
        enemyControl.dealDamage(totalAttack, "agent");
    }
}