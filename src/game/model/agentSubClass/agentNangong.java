/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 4/26/26
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
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        agentControl.dealDamage(totalAttack, "enemy");
      
        gainEnergy();
    }

    @Override
    public void skillAttack(){
        int totalAttack = skDmg;
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        //Stun functionality. create controller method for stunning

        agentControl.dealDamage(totalAttack, "enemy");

        gainEnergy();
        
    }
    
    @Override
    public void ultimateAttack(){
        int totalAttack = ultDmg;
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        //Implement stun

        agentControl.dealDamage(totalAttack, "enemy");
        
        useEnergy();
    }
}