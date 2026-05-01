/**
 * Marcus Alexio Prado 
 * Course: Adv Java
 * Date: 4/24/26
 * Last Modified: 5/1/26
 * 
 * The parent class agent. Holds methods to get and set information
 * of a character and creates abstract methods for each subclass to 
 * use differently.
 */

package game.model;
import game.controller.gameController;

/**
 * An abstract class to create an agent with methods to retrieve information
 */
public abstract class agent {
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int maxEnergy;
    protected int energy;
    protected String naName;
    protected String skName;
    protected String ultName;
    protected int naDmg;
    protected int skDmg;
    protected int ultDmg;
    protected int partnerBuffDmg;
    protected int partnerBuffDuration;
    protected int partnerBuffMaxDuration;
    protected gameController agentControl;

    /**
     * Set agentcontrol, the gameController variable name
     * 
     * @param control gameController object
     */
    public void setAgentControl(gameController control){
        agentControl = control;
    }

    /**
     * Ask if the agent is alive
     * 
     * @return true if agent is alive; false otherwise
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Heal the agent. Set health to max health if healing overflows
     * 
     * @param heal amount of healing
     */
    public void heal(int heal){
        if (health > 0){
            health += heal;
            if (health > maxHealth){
                health = maxHealth;
            }
        }
    }

    //Agent gains energy. Set to max Energy if energy overcaps
    public void gainEnergy(){
        energy += 1;
        if (energy > maxEnergy){
            energy = maxEnergy;
        }
    }

    //Set energy to 0, essentialy "using" it
    public void useEnergy(){
        energy = 0;
    }

    /**
     * Ask if the ultimate can be used. 
     * Has to have max energy inorder to use
     * 
     * @return true if energy required is available; false if otherwise
     */
    public boolean ultimateStatus(){
        return energy == maxEnergy;
    }

    /**
     * Reduce health to simulate taking damage
     * 
     * @param dmg amount of damage taken
     */
    public void takeDamage(int dmg){
        health -= dmg;
    }

    /**
     * Set the partnerbuff damage
     * 
     * @param buff damage increase
     */
    public void setPartnerBuffDmg(int buff){
        partnerBuffDmg = buff;
    }

    /**
     * Set the partnerbuff duration
     * 
     * @param duration how many turns buff is used
     */
    public void setPartnerBuffDuration(int duration){
        partnerBuffDuration = duration;
    }

    /**
     * Set the partnerbuff max duration
     * 
     * @param maxDuration how many turns it can only be used 
     */
    public void setPartnerBuffMaxDuration(int maxDuration){
        partnerBuffMaxDuration = maxDuration;
    }

    /**
     * Set health of agent
     * 
     * @param healthChange the now updated health
     */
    public void setHealth(int healthChange) {
        health = healthChange;
    }

    /**
     * Gets the agent's name
     * 
     * @return name of agent
     */
    public String getName(){
        return name;
    }

    /**
     * Gets health of agent
     * 
     * @return health of agent
     */
    public int getHealth(){
        return health;
    }

    /**
     * Gets max health of agent
     * 
     * @return max health of agent
     */
    public int getMaxHealth(){
        return maxHealth;
    }

    /**
     * Gets energy of agent
     * 
     * @return energy of agent
     */
    public int getEnergy(){
        return energy;
    }

    /**
     * Gets max energy of agent
     * 
     * @return max energy of agent
     */
    public int getMaxEnergy(){
        return maxEnergy;
    }

    /**
     * Gets normal attack name
     * 
     * @return normal attack name
     */
    public String getNormalAttackName(){
        return naName;
    }

    /**
     * Gets skill attack name
     * 
     * @return skill attack name
     */
    public String getSkillName(){
        return skName;
    }

    /**
     * Gets ultimate attack name
     * 
     * @return ultimate attack name
     */
    public String getUltName(){
        return ultName;
    }

    /**
     * Gets partner buff dmg
     * 
     * @return partner buff dmg
     */
    public int getPartnerBuffDmg(){
        return partnerBuffDmg;
    }

    /**
     * Gets partner buff duration
     * 
     * @return partner buff duration
     */
    public int getPartnerBuffDuration(){
        return partnerBuffDuration;
    }

    /**
     * Gets partner buff max duration
     * 
     * @return partner buff max duration
     */
    public int getPartnerBuffMaxDuration(){
        return partnerBuffMaxDuration;
    }

    /**
     * Gets gameController object of agent
     * 
     * @return agentController 
     */
    public gameController getGameController(){
        return agentControl;
    }

    /**
     * Get normal attack dmg
     * 
     * @return normal attack dmg
     */
    public int getNADmg() {
    	return naDmg;
    }
    
    /**
     * Get skil attack dmg
     * 
     * @return skill attack dmg
     */
    public int getSKDmg() {
    	return skDmg;
    }
    
    /**
     * Get ultimate attack dmg
     * 
     * @return ultimate attack dmg
     */
    public int getUltDmg() {
    	return ultDmg;
    }

    //Abstract methods to get information of normal, skill, and ultimate attack
    public abstract String getNormalAttackInfo();
    public abstract String getSkillAttackInfo();
    public abstract String getUltimateAttackInfo();

    //Abstract method to evaluate dmg of normal, skill, and ultimate then deal damage to enemy
    public abstract void normalAttack();
    public abstract void skillAttack();
    public abstract void ultimateAttack();
}