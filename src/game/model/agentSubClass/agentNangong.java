/***
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 5/1/26
 * 
 * Agent subclass for the character Nangong.
 * What makes her unique:
 * 1. Normal, skill, ultimate
 * 2. Normal/skill attack stuns the enemy for a duration of 3/4 respectively
 * 3. Stunning increases damage to enemies
 */

package game.model.agentSubClass;
import game.model.agent;

/**
 * A subclass of Agent for the character Nangong.
 * Unique aspects in the use of her normal, skill, and ultimate attacks
 */
public class agentNangong extends agent{
    //Constructor method for Nangong
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

    /**
     * Method to retreive normal attack info
     */
    @Override
    public String getNormalAttackInfo(){       
        String naInfo = "Nangong slams her spiked mace \n| toward the enemy, dealing 2 dmg.";
        return naInfo;
    }

    /**
     * Method to retreive skill attack info
     */
    @Override
    public String getSkillAttackInfo(){
        String skInfo = "Nangong charges toward the enemy,\n| dealing 3 dmg. The enemy becomes \n| stunned for 3 turns. If enemy\n| already stunned, stun duration\n| does not increase.";
        return skInfo;
    }
    
    /**
     * Method to retreive ultimate attack info
     */
    @Override
    public String getUltimateAttackInfo(){
        String ultInfo = "Nangong spins her spiked maces in \n| the sky, dealing 3 dmg. The enemy\n| becomes stunned for 4 turns. If\n| enemy already stunned, stun duration\n| does not increase.";
        return ultInfo;
    }
    
    /**
     * Method that handles damage of skill attack.
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

        //Nangong gains energy
        gainEnergy();

        //Deal damage to enemy with total attack
        agentControl.dealDamage(totalAttack, "enemy");
    }

    /**
     * Method that handles damage of ultimate attack.
     */
    @Override
    public void skillAttack(){
        //Set total attack to skill attack dmg
        int totalAttack = skDmg;

        //Stun enemy for 3 turns.
        agentControl.stunEnemy(3);

        //If partner buff duration greater then 0, increase dmg and reduce duration
        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }

        //If enemy is stunned, increase dmg
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        //Nangong gains energy
        gainEnergy();

        //Deal damage to enemy with total attack
        agentControl.dealDamage(totalAttack, "enemy");     
    }
    
    @Override
    public void ultimateAttack(){
        //Set total attack to ultimate attack dmg
        int totalAttack = ultDmg;

        //Stun enemy for 4 turns.
        agentControl.stunEnemy(4);

        //If partner buff duration greater then 0, increase dmg and reduce duration
        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }

        //If enemy is stunned, increase dmg
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        //Reset Energy
        useEnergy();

        //Deal damage to enemy with total attack
        agentControl.dealDamage(totalAttack, "enemy");
    }
}