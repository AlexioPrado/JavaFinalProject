/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 4/29/26
 * 
 */
package game.model.enemySubClass;
import game.model.enemy;

public class enemyHachiware extends enemy {
    private int bigAttackBonus = 5;
    private int bigAttackCounter = 3;
    
    public enemyHachiware(){
        this.name = "Hachiware";
        this.health = 30;
        this.maxHealth = 30;
        this.attack = 2;
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
        } else {
            if (bigAttackCounter == 0){
                totalAttack += bigAttackBonus;
                bigAttackCounter = 3;
            } else {
                bigAttackCounter -= 1;
            }
        }
        
        enemyControl.dealDamage(totalAttack, "agent");
    }
}