/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 5/1/26
 * 
 * Enemy subclass for the enemy Usagi.
 * Deals 2 damage, heals themselves
 */

package game.model.enemySubClass;
import game.model.enemy;

/**
 * A subclass of enemy for the character Usagi.
 * Attack: 2 damage, heal based on dmg + 1
 */
public class enemyUsagi extends enemy {
    //Constructor method of Usagi
    public enemyUsagi(){
        this.name = "Usagi";
        this.health = 50;
        this.maxHealth = 50;
        this.attack = 2;
        this.isStun = false;
        this.stunDuration = 0;
    }
    
    /**
     * Method that handles attack damage
     */
    public void enemyAttack(){
        //Set total attack to attack dmg
        int totalAttack = attack;

        //If stunned and duration is 0, set isStun to false
        if (stunDuration == 0 && isStun){
            isStun = !isStun;
        }

        /**
         * If enemy is stunned:
         * - set total attack to -1 and reduce stun duration
         * When given to controller, it tells it that the enemy is stunned
         * 
         * If not stunned:
         * - heal self based on attack + 1
         */
        if (isStun){
            totalAttack = -1;
            stunDuration -= 1;
        } else {
            heal(totalAttack + 1);
        }
        
        //Deal damage to agent with total attack
        enemyControl.dealDamage(totalAttack, "agent");
    }
}