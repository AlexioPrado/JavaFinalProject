/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/29/26
 * Last Modified: 4/29/26
 * 
 */

public class enemyUsagi extends enemy {
    public enemyUsagi(){
        this.name = "Usagi";
        this.health = 50;
        this.maxHealth = 50;
        this.attack = 2;
        this.isStun = false;
        this.stunDuration = 0;
    }
    
    public void enemyAttack(agent agent){
        int totalAttack = attack;

        if (stunDuration == 0 && isStun){
            isStun = !isStun;
        }
        if (isStun){
            totalAttack = -1;
            stunDuration -= 1;
        } else {
            heal(totalAttack + 1);
        }
        
        enemyControl.dealDamage(totalAttack, "agent");
    }
}