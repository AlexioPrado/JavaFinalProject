/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 5/1/26
 * 
 * Enemy subclass for the enemy Mr.Gardner.
 * Deals 3 damage, increasing every turn
 */

package game.model.enemySubClass;
import game.model.enemy;

/**
 * A subclass of enemy for the character Mr.Gardner.
 * Attack: Deals 3 damage, increasing by 1 every turn.
 */
public class enemyGardner extends enemy {
    //Constructor method of Mr.Gardner
    public enemyGardner(){
        this.name = "Ethan Gardner";
        this.health = 25;
        this.maxHealth = 25;
        this.attack = 3;
        this.isStun = false;
        this.stunDuration = 0;
    }

    /**
     * Method that handles attack damage
     */
    public void enemyAttack(){
        //If stunned and duration is 0, reset attack damage and set isStun to false
        if (stunDuration == 0 && isStun){
            attack = 3;
            isStun = !isStun;
        }
        /**
         * If enemy is stunned:
         * - set total attack to -1 and reduce stun duration
         * When given to controller, it tells it that the enemy is stunned
         * 
         * If not stunned:
         * - Increase dmg by 1
         */
        if (isStun){
            attack = -1;
            stunDuration -= 1;
        } else {
            attack += 1;
        }
        
        //Deal damage to agent with total attack
        enemyControl.dealDamage(attack, "agent");
    }
}