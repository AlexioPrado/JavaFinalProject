/**
 * Marcus Alexio Prado
 * Course: Adv Java 
 * Date: 4/27/26
 * Last Modified: 4/27/26
 * 
 */

public abstract class enemy {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attack;
    protected boolean isStun;
    protected int stunDuration;

    protected gameController enemyControl;

    public boolean isAlive(){
        return health < 0;
    }

    public void takeDamage(int dmg){
        health -= dmg;
    }

    public void heal(int heal){
        health += heal;
    }

    public boolean isStun(){
        return isStun;
    }

    public void setStun(int duration){
        isStun = !isStun;
        stunDuration = duration;
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

    public abstract void enemyAttack(agent agent);
}