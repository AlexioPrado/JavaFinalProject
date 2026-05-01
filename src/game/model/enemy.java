/**
 * Marcus Alexio Prado
 * Course: Adv Java 
 * Date: 4/27/26
 * Last Modified: 5/1/26
 * 
 * The parent class of enemy. Hols methods to set and get information
 * of the enemy object. Creates an abstract method for how enemies 
 * interact with their damage to the players
 */

package game.model;
import game.controller.gameController;

/**
 * Abstract class enemy to create information of all agent objects
 */
public abstract class enemy {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attack;
    protected boolean isStun;
    protected int stunDuration;
    protected gameController enemyControl;

    /**
     * Set enemyControl, the gameController object of enemy
     * 
     * @param control gameController object
     */
    public void setGameControl(gameController control){
        enemyControl = control;
    }

    /**
     * Ask if the enemy is alive
     * 
     * @return true if enemy is alive; false otherwise
     */
    public boolean isAlive(){
        return health > 0;
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
     * Heal the enemy. Set health to max health if healing overflows
     * 
     * @param heal amount of healing
     */
    public void heal(int heal){
        health += heal;
        if (health > maxHealth){
            health = maxHealth;
        }
    }

    /**
     * Asks if the enemy is stunned
     * 
     * @return true of stunned; false if otherwise
     */
    public boolean isStun(){
        return isStun;
    }

    /**
     * Sets the duration of the stun
     * 
     * @param duration how long stun lasts for
     */
    public void setStun(int duration){
        if (!isStun){
            isStun = !isStun;
            stunDuration = duration;
        }
    }

    /**
     * Set health of agent
     * 
     * @param healthSet the now updated health
     */
    public void setHealth(int healthSet){
        health = healthSet;
    }

    /**
     * Set max health of agent
     * 
     * @param healthSet the now updated max health
     */
    public void setMaxHealth(int healthSet){
        maxHealth = healthSet;
    }

    /**
     * Gets the enemy's name
     * 
     * @return name of enemy
     */
    public String getName(){
        return name;
    }

    /**
     * Gets health of enemy
     * 
     * @return health of enemy
     */
    public int getHealth(){
        return health;
    }

    /**
     * Gets max health of enemy
     * 
     * @return max health of enemy
     */
    public int getMaxHealth(){
        return maxHealth;
    }

    /**
     * Gets stun duration of enemy
     * 
     * @return duration of stun
     */
    public int getStunDuration(){
        return stunDuration;
    }

    /**
     * Gets attack dmg
     * 
     * @return attack dmg
     */
    public int getAttack(){
        return attack;
    }

    /**
     * Get the gameController object of the enemy
     * 
     * @return enemyControl
     */
    public gameController getGameController() {
    	return enemyControl;
    }

    //Abstract method to evaluate damage of an enemy and deal damage to agent
    public abstract void enemyAttack();
}