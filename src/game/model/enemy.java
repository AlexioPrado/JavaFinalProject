/**
 * Marcus Alexio Prado
 * Course: Adv Java 
 * Date: 4/27/26
 * Last Modified: 4/27/26
 * 
 */

package game.model;
import game.controller.gameController;

public abstract class enemy {
    public String name;
    public int health;
    public int maxHealth;
    public int attack;
    public boolean isStun;
    public int stunDuration;

    public gameController enemyControl;

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

    public abstract void enemyAttack();
}