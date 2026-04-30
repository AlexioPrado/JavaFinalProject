/**
 * Marcus Alexio Prado 
 * Course: Adv Java
 * Date: 4/24/26
 * Last Modified: 4/29/26
 * 
 */

package game.model;
import game.controller.gameController;
import game.view.gameView;

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

    public gameController agentControl;

    public void setAgentControl(gameController control){
        agentControl = control;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void heal(int heal){
        if (health > 0){
            health += heal;
            if (health > maxHealth){
                health = maxHealth;
            }
        }
    }

    public void gainEnergy(){
        energy += 1;
        if (energy > maxEnergy){
            energy = maxEnergy;
        }
    }

    public void useEnergy(){
        energy = 0;
    }

    public boolean ultimateStatus(){
        return energy == maxEnergy;
    }

    public void takeDamage(int dmg){
        health -= dmg;
    }

    public void setPartnerBuffDmg(int buff){
        partnerBuffDmg = buff;
    }

    public void setPartnerBuffDuration(int duration){
        partnerBuffDuration = duration;
    }

    public void setPartnerBuffMaxDuration(int maxDuration){
        partnerBuffMaxDuration = maxDuration;
    }

    public void setHealth(int healthChange) {
        health = healthChange;
    }

    public String getName(){
        return name;
    }

    public int getHealth(){
        return health;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public int getEnergy(){
        return energy;
    }

    public int getMaxEnergy(){
        return maxEnergy;
    }

    public String getNormalAttackName(){
        return naName;
    }

    public String getSkillkName(){
        return skName;
    }

    public String getUltName(){
        return ultName;
    }

    public int getPartnerBuffDmg(){
        return partnerBuffDmg;
    }

    public int getPartnerBuffDuration(){
        return partnerBuffDuration;
    }

    public int getPartnerBuffMaxDuration(){
        return partnerBuffMaxDuration;
    }

    public gameController getGameController(){
        return agentControl;
    }

    public abstract String getNormalAttackInfo();
    public abstract String getSkillAttackInfo();
    public abstract String getUltimateAttackInfo();

    public abstract void normalAttack();
    public abstract void skillAttack();
    public abstract void ultimateAttack();
    

    //CREATE DMG STUN INCREASE METHOD
    //DIFFERENT FOR EVERYONE

    //CREATE STUN DURATION METHOD
    //different for nangong skill and ultimate

    //stun does NOT STACK.

    //CREATE HEAL METHOD

    //Create atk buff method
    // does not stack

    // take damg method, same as for enemies

    // check is alive, health < 0, same as for enemies.

    // CREATE A BUFF METHOD TO RETURN AN ARRAYLIST OF BUFFS. an array list of strings. 
    // However you do it, maybe buffs are an object and you get its string name or whatever
}