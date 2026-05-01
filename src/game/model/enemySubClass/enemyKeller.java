/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 5/1/26
 * 
 * Enemy subclass for the enemy Ms.Keller.
 * Deals 4 damage
 */

package game.model.enemySubClass;
import game.model.enemy;

/**
 * A subclass of enemy for the character Ms.Keller.
 * Attack: Deals 4 damage
 */
public class enemyKeller extends enemy {
    //Constructor method of Ms.Keller
    public enemyKeller(){
        this.name = "Leslie Keller";
        this.health = 20;
        this.maxHealth = 20;
        this.attack = 4;
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
         */
        if (isStun){
            totalAttack = -1;
            stunDuration -= 1;
        } 
        
        //Deal damage to agent with total attack
        enemyControl.dealDamage(totalAttack, "agent");
    }
}