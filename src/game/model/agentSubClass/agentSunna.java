/***
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 5/1/26
 * 
 * Agent subclass for the character Sunna.
 * What makes her unique:
 * 1. Normal, skill, ultimate
 * 2. Skill attack increases party damage by 3.
 * 3. Ultimate attack heals the party by 5 HP.
 */

package game.model.agentSubClass;
import game.model.agent;

/**
 * A subclass of Agent for the character Sunna.
 * Unique aspects in the use of her normal, skill, and ultimate attacks
 */
public class agentSunna extends agent{
    //Constructor method for Nangong
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

    /**
     * Method to retreive normal attack info
     */
    @Override
    public String getNormalAttackInfo(){
        String naInfo = "Sunna clobbles the enemy with her\n| megaphone, dealing 1 dmg.";
        return naInfo;
    }

    /**
     * Method to retreive skill attack info
     */
    @Override
    public String getSkillAttackInfo(){
        String skInfo = "Sunna calls her bubblegum assistant,\n| dealing 3 dmg. Bubblegum increases\n| the exploration team attack dmg by 3\nfor 4 turns.";
        return skInfo;
    }
    
    /**
     * Method to retreive ultimate attack info
     */
    @Override
    public String getUltimateAttackInfo(){
        //                                                                                      
        String ultInfo = "Sunna becomes enraged, clammoring \n| the enemy with her mallet,\n| dealing 2 dmg. Heals the exploration\n| team by 7 HP.";
        return ultInfo;
    }

    /**
     * Method that handles damage of normal attack.
     */
    @Override
    public void normalAttack(){
        //Set total attack to normal attack dmg
        int totalAttack = naDmg;

        //If partner buff duration greater then 0, increase dmg and reduce duration
        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }

        //If enemy is stunned, increase dmg
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        //Sunna gains energy
        gainEnergy();

        //Deal damage to enemy with total attack
        agentControl.dealDamage(totalAttack, "enemy");
    }

    /**
     * Method that handles damage of skill attack.
     */
    @Override
    public void skillAttack(){
        //Set total attack to normal attack dmg
        int totalAttack = skDmg;
        
        //Sets duration of buff to maxduration on herself. Essentialy activating the buff
        partnerBuffDuration = partnerBuffMaxDuration;
        //Applies the buff to her other party member
        agentControl.applyPartnerBuff();

        //If partner buff duration greater then 0, increase dmg and reduce duration
        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }

        //If enemy is stunned, increase dmg
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        //Sunna gains energy
        gainEnergy();

        //Deal damage to enemy with total attack
        agentControl.dealDamage(totalAttack, "enemy");        
    }
    
    /**
     * Method that handles damage of ultimate attack.
     */
    @Override
    public void ultimateAttack(){
        //Set total attack to normal attack dmg
        int totalAttack = ultDmg;

        //If partner buff duration greater then 0, increase dmg and reduce duration
        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }

        //If enemy is stunned, increase dmg
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }
        
        //Heals the party by 7HP
        heal(7);
        agentControl.healOffCharacter(7);

        //Sunna gains energy
        useEnergy();

        //Deal damage to enemy with total attack
        agentControl.dealDamage(totalAttack, "enemy");        
    }
}