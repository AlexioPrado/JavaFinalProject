/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 4/29/26
 * 
 */

public class enemyMcCuen extends enemy {
    public enemyMcCuen(){
        this.name = "Russ McCuen";
        this.health = 26;
        this.maxHealth = 26;
        this.attack = 1;
        this.isStun = false;
        this.stunDuration = 0;
    }
    
    public void enemyAttack(agent agent){
        if (stunDuration == 0 && isStun){
            attack = 1;
            isStun = !isStun;
        }
        if (isStun){
            totalAttack = -1;
            stunDuration -= 1;
        } else {
            attack *= 2;
        }
        

        enemyControl.dealDamage(totalAttack, "agent");
    }
}