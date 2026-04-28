/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/28/26
 * Last Modified: 4/29/26
 * 
 */

public class enemyChiikawa extends enemy {
    private boolean attackSwitch = false;

    public enemyChiikawa(){
        this.name = "Chiikawa";
        this.health = 18;
        this.maxHealth = 18;
        this.attack = 4;
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
            if (attackSwitch){
                attackSwitch = !attackSwitch;
                totalAttack += 1;
            } else {
                attackSwitch = !attackSwitch;
            }
        }
        
        enemyControl.dealDamage(totalAttack, "agent");
    }
}