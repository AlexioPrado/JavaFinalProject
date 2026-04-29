/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 4/29/26
 * 
 */

package game.model.agentSubClass;
import game.model.agent;

public class agentAria extends agent{
    private boolean naBuffActive = false;
    private int naBuffDuration = 2;

    public agentAria(){
        this.name = "Aria";
        this.maxHealth = 25;
        this.health = 25;
        this.maxEnergy = 4;
        this.energy = 0;
        this.naName = "Perfect Pitch";
        this.skName = "Fall Into Delusion";
        this.ultName = "100% Energy";
        this.naDmg = 3;
        this.skDmg = 4;
        this.ultDmg = 2;
        this.partnerBuff = false;
        this.partnerBuffDmg = -1;
        this.partnerBuffDuration = -1;
        this.partnerBuffMaxDuration = -1;
    }

    @Override
    public String getNormalAttackInfo(){
        String naInfo = "Aria takes a leap and plunges down\n| toward the enemy, dealing 3 dmg.\n| If the enemy is stunned, deal an\n| additonal 3 dmg.";
        return naInfo;
    }

    @Override
    public String getSkillAttackInfo(){
        String skInfo = "Aria kicks the enemy swiftly, dealing\n| 4 dmg. Aria\\'s partner, astonished\n| by her skills, will gain\n| 1 energy.";
        return skInfo;
    }
    
    @Override
    public String getUltimateAttackInfo(){
        String ultInfo = "Aria leaps into the sky, shooting \n| her bow to the enemy, dealing\n| 2 dmg. For the next 2 turns,\n| Perfect Pitch dmg is increased by 3.";
        return ultInfo;
    }

    @Override
    public void normalAttack(){
        int totalAttack = naDmg;

        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }
        if (agentControl.isEnemyStun()){
            totalAttack += 3;
        }
        if (naBuffActive && naBuffDuration >= 1){
            totalAttack += 3;
            naBuffDuration -= 1;
        } else if (naBuffDuration == 0) {
            naBuffActive = !naBuffActive;
            naBuffDuration = 2;
        }

        gainEnergy();
        
        agentControl.dealDamage(totalAttack, "enemy");
    }
    
    @Override
    public void skillAttack(){
        int totalAttack = skDmg;

        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        gainEnergy();
        agentControl.partnerEnergyIncrease();

        agentControl.dealDamage(totalAttack, "enemy");
    }
    
    @Override
    public void ultimateAttack(){
        int totalAttack = ultDmg;

        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        useEnergy();
        
        if (naBuffActive){
            naBuffDuration = 2;
        } else {
            naBuffActive = !naBuffActive;
        }
    
        agentControl.dealDamage(totalAttack, "enemy");
    }
}