/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 4/29/26
 * 
 */

package game.model.agentSubClass;
import game.model.agent;

public class agentSunna extends agent{

    public agentSunna(){
        this.name = "Sunna";
        this.maxHealth = 20;
        this.health = 20;
        this.maxEnergy = 2;
        this.energy = 0;
        this.naName = "Mischief Meteor Hammer";
        this.skName = "Bubblegum Barrage";
        this.ultName = "Smash It All";
        this.naDmg = 1;
        this.skDmg = 3;
        this.ultDmg = 2;
        this.partnerBuffDmg = 3;
        this.partnerBuffDuration = 0;
        this.partnerBuffMaxDuration = 4;
    }

    @Override
    public String getNormalAttackInfo(){
        String naInfo = "Sunna clobbles the enemy with her\n| megaphone, dealing 1 dmg.";
        return naInfo;
    }

    @Override
    public String getSkillAttackInfo(){
        String skInfo = "Sunna calls her bubblegum assistant,\n| dealing 3 dmg. Bubblegum increases\n| the exploration team attack dmg by 3\nfor 4 turns.";
        return skInfo;
    }
    
    @Override
    public String getUltimateAttackInfo(){
        //                                                                                      
        String ultInfo = "Sunna becomes enraged, clammoring \n| the enemy with her mallet,\n| dealing 2 dmg. Heals the exploration\n| team by 5 HP.";
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
        
        partnerBuffDuration = partnerBuffMaxDuration;
        agentControl.applyPartnerBuff();
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

        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }
    
        heal(7);
        agentControl.healOffCharacter(7);

        useEnergy();

        agentControl.dealDamage(totalAttack, "enemy");        
    }
}