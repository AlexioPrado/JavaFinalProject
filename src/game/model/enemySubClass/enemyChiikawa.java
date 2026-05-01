/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/28/26
 * Last Modified: 5/1/26
 * 
 * Enemy subclass for the enemy Chiikawa.
 * Deals 6 or 7 damage to agents
 */

package game.model.enemySubClass;
import game.model.enemy;

/**
 * A subclass of enemy for the character Chiikawa.
 * Attack: In a back and forth, deals 6 or 7 damage
 */
public class enemyChiikawa extends enemy {
    //Special switch for Chiikawa. Allows it to switch dmg
    private boolean attackSwitch = false;

    //Constructor method of Chiikawa
    public enemyChiikawa(){
        this.name = "Chiikawa";
        this.health = 21;
        this.maxHealth = 21;
        this.attack = 6;
        this.isStun = false;
        this.stunDuration = 0;
    }

    /**
     * Method that handles attack damage
     */
    public void enemyAttack(){
        //Set total attack to attack dmg
        int totalAttack = attack;

        //If enemy is stunned and duration is 0, set isStun to false
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
             * if attackSwitch true
             * - Set attackSwitch false and increase dmg
             * if attackSwitch false
             * - Set attackSwitch true
             */
            if (attackSwitch){
                attackSwitch = !attackSwitch;
                totalAttack += 1;
            } else {
                attackSwitch = !attackSwitch;
            }
        }
        
        //Deal damage to agent with total attack
        enemyControl.dealDamage(totalAttack, "agent");
    }
}