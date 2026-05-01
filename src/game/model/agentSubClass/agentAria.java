/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 5/1/26
 * 
 * Agent subclass for the character Aria.
 * What makes her unique:
 * 1. Normal, skill, ultimate
 * 2. Normal attack buff increase
 * 3. offcharacter energy gain
 * 4. Increase dmg when enemy stunned
 */

package game.model.agentSubClass;
import game.model.agent;

/**
 * A subclass of Agent for the character Aria.
 * Unique aspects in the use of her normal, skill, and ultimate attacks
 */
public class agentAria extends agent{
    //Create special resource for Aria. Boolean to check if active and its duration
    private boolean naBuffActive;
    private int naBuffDuration;

    //Constructor method of Aria
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
        this.partnerBuffDmg = -1;
        this.partnerBuffDuration = -1;
        this.partnerBuffMaxDuration = -1;
        this.naBuffActive = false;
        this.naBuffDuration = 2;
    }

    /**
     * Method to retreive normal attack info
     */
    @Override
    public String getNormalAttackInfo(){
        String naInfo = "Aria takes a leap and plunges down\n| toward the enemy, dealing 3 dmg.\n| If the enemy is stunned, deal an\n| additonal 3 dmg.";
        return naInfo;
    }

    /**
     * Method to retreive skill attack info
     */
    @Override
    public String getSkillAttackInfo(){
        String skInfo = "Aria kicks the enemy swiftly, dealing\n| 4 dmg. Aria\\'s partner, astonished\n| by her skills, will gain\n| 1 energy.";
        return skInfo;
    }
    
    /**
     * Method to retreive ultimate attack info
     */
    @Override
    public String getUltimateAttackInfo(){
        String ultInfo = "Aria leaps into the sky, shooting \n| her bow to the enemy, dealing\n| 2 dmg. For the next 2 turns,\n| Perfect Pitch dmg is increased by 3.";
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
            totalAttack += 3;
        }

        //If personal normal attack buff active and duration greater than or equal to 1
        if (naBuffActive && naBuffDuration >= 1){
            //Increase dmg, reduce duration by 1
            totalAttack += 3;
            naBuffDuration -= 1;
        
        /**
         * Else if duration is 0:
         * This only occurs while buff is active
         * and normal attack has been used twice
         * during duration
         */
        } else if (naBuffDuration == 0) {
            //Set normal attack buff to false and reset duration
            naBuffActive = !naBuffActive;
            naBuffDuration = 2;
        }

        //Aria gains energy
        gainEnergy();
        
        //Deal damage to enemy with total attack
        agentControl.dealDamage(totalAttack, "enemy");
    }
    
    /**
     * Method that handles damage of skill attack.
     */
    @Override
    public void skillAttack(){
        //Set total attack to skill attack dmg
        int totalAttack = skDmg;

        //If partner buff duration greater then 0, increase dmg and reduce duration
        if (partnerBuffDuration > 0){
            totalAttack += partnerBuffDmg;
            partnerBuffDuration -= 1;
        }
        
        //If enemy is stunned, increase dmg
        if (agentControl.isEnemyStun()){
            totalAttack += 1;
        }

        //Aria and off character gains energy
        gainEnergy();
        agentControl.partnerEnergyIncrease();

        //Deal damage to enemy with total attack
        agentControl.dealDamage(totalAttack, "enemy");
    }
    
    /**
     * Method that handles damage of ultimate attack.
     */
    @Override
    public void ultimateAttack(){
        //Set total attack to ultimate attack dmg
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

        //Reset Energy
        useEnergy();
        
        /** 
         * Activate normal attack buff. 
         * 1. Reset duration if already active. 
         * 2. Set normal attack buff as true if not active.
         */
        if (naBuffActive){
            naBuffDuration = 2;
        } else {
            naBuffActive = !naBuffActive;
        }
    
        //Deal damage to enemy with total attack
        agentControl.dealDamage(totalAttack, "enemy");
    }
}