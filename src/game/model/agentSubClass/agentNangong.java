/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 4/29/26
 * 
 */

package game.model.agentSubClass;
import game.model.agent;

public class agentNangong extends agent{

    public agentNangong(){
        this.name = "Nangong Yu";
        this.maxHealth = 22;
        this.health = 22;
        this.maxEnergy = 4;
        this.energy = 0;
        this.naName = "Shooting Star Step";
        this.skName = "The Unbearable Weight of Love";
        this.ultName = "Meteor Shower";
        this.naDmg = 2;
        this.skDmg = 3;
        this.ultDmg = 3;
        this.partnerBuffDmg = -1;
        this.partnerBuffDuration = -1;
        this.partnerBuffMaxDuration = -1;
    }

     @Override
    public String getNormalAttackInfo(){       
        String naInfo = "Nangong slams her spiked mace \n| toward the enemy, dealing 2 dmg.";
        return naInfo;
    }

    @Override
    public String getSkillAttackInfo(){
        String skInfo = "Nangong charges toward the enemy,\n| dealing 3 dmg. The enemy becomes \n| stunned for 2 turns. If enemy\n| already stunned, stun duration\n| does not increase.";
        return skInfo;
    }
    
    @Override
    public String getUltimateAttackInfo(){
        String ultInfo = "Nangong spins her spiked maces in \n| the sky, dealing 3 dmg. The enemy\n| becomes stunned for 3 turns. If\n| enemy already stunned, stun duration\n| does not increase.";
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
            totalAttack += 1;
        }
        gainEnergy();

        agentControl.dealDamage(totalAttack, "enemy");
    }

    @Override
    public void skillAttack(){
        int totalAttack = skDmg;

        agentControl.stunEnemy(3);

        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        gainEnergy();

        agentControl.dealDamage(totalAttack, "enemy");     
    }
    
    @Override
    public void ultimateAttack(){
        int totalAttack = ultDmg;

        agentControl.stunEnemy(4);

        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        useEnergy();

        agentControl.dealDamage(totalAttack, "enemy");
    }
}