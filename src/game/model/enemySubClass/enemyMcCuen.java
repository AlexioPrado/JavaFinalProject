/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 5/1/26
 * 
 * Enemy subclass for the enemy Mr.McCuen.
 * Deals 1 damage, multiplying every turn
 */

package game.model.enemySubClass;
import game.model.enemy;

/**
 * A subclass of enemy for the character Mr.McCuen.
 * Attack: Deals 1 damage, multiplying two fold every turn
 */
public class enemyMcCuen extends enemy {
    //Constructor method of Mr.McCuen
    public enemyMcCuen(){
        this.name = "Russ McCuen";
        this.health = 26;
        this.maxHealth = 26;
        this.attack = 1;
        this.isStun = false;
        this.stunDuration = 0;
    }
    
    /**
     * Method that handles attack damage
     */
    public void enemyAttack(){
        //If stunned and duration is 0, reset attack and set isStun to false
        if (stunDuration == 0 && isStun){
            attack = 1;
            isStun = !isStun;
        }

        /**
         * If enemy is stunned:
         * - set total attack to -1 and reduce stun duration
         * When given to controller, it tells it that the enemy is stunned
         * 
         * If not stunned:
         * - continue
         */
        if (isStun){
            attack = -1;
            stunDuration -= 1;
        } else {
            //Multiply dmg by 2
            attack *= 2;
        }
        
        //Deal damage to agent with total attack
        enemyControl.dealDamage(attack, "agent");
    }
}