/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 5/1/26
 * 
 * Enemy subclass for the enemy Hachiware.
 * Deals 2 dmg, for every 3 turns, deal more dmg
 */
package game.model.enemySubClass;
import game.model.enemy;

/**
 * A subclass of enemy for the character Hachiware.
 * Attack: Deals 2 damage, for every 3 turns, increase dmg by 5
 */
public class enemyHachiware extends enemy {
    //Special resource of Hachiware. Checks when to increase dmg and dmg bonus
    private int bigAttackBonus = 5;
    private int bigAttackCounter = 3;
    
    //Constructor method of Hachiware
    public enemyHachiware(){
        this.name = "Hachiware";
        this.health = 30;
        this.maxHealth = 30;
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
         * - continue
         */
        if (isStun){
            totalAttack = -1;
            stunDuration -= 1;
        } else {
            /**
             * If counter == 0
             * - Increase dmg to deal and reset counter to 3
             * if not,
             * - Reduce counter by 1
             */
            if (bigAttackCounter == 0){
                totalAttack += bigAttackBonus;
                bigAttackCounter = 3;
            } else {
                bigAttackCounter -= 1;
            }
        }
        
        //Deal damage to agent with total attack
        enemyControl.dealDamage(totalAttack, "agent");
    }
}