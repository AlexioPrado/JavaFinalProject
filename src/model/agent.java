/**
 * Marcus Alexio Prado 
 * Course: Adv Java
 * Date: 4/24/26
 * Last Modified: 4/25/26
 * 
 */

public abstract class agent {
    protected String name;
    protected Int maxHealth;
    protected Int health;
    protected Int maxEnergy;
    protected Int energy;
    protected String naName;
    protected String skName;
    protected String ultName;
    protected Int naDmg;
    protected Int skDmg;
    protected Int ultDmg;

    public boolean isAlive() {
        return hp > 0;
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

    public void takeDamage(Int dmg){
        health -= dmg;
    }

    public string getNormalAttackName(){
        return naName;
    }

    public string getSkillkName(){
        return skName;
    }

    public string getUltName(){
        return ultName;
    }

    public abstract string getNormalAttackInfo();
    public abstract string getSkillAttackInfo();
    public abstract string getUltimateAttackInfo();

    public abstract void normalAttack(Enemy enemy);
    public abstract void skillAttack(Enemy enemy);
    public abstract void ultimateAttack(Enemy enemy);
    

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