/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 4/29/26
 * 
 */

public class enemyGardner extends enemy {
    public enemyGardner(){
        this.name = "Ethan Gardner";
        this.health = 25;
        this.maxHealth = 25;
        this.attack = 4;
        this.isStun = false;
        this.stunDuration = 0;
    }

    public void enemyAttack(agent agent){
        if (stunDuration == 0 && isStun){
            attack = 3;
            isStun = !isStun;
        }
        if (isStun){
            attack = -1;
            stunDuration -= 1;
        } else {
            attack += 1;
        }
        
        enemyControl.dealDamage(attack, "agent");
    }
}