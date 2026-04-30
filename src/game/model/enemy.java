/**
 * Marcus Alexio Prado
 * Course: Adv Java 
 * Date: 4/27/26
 * Last Modified: 4/29/26
 * 
 */

package game.model;
import game.controller.gameController;
import game.view.gameView;

public abstract class enemy {
    public String name;
    public int health;
    public int maxHealth;
    public int attack;
    public boolean isStun;
    public int stunDuration;

    public gameController enemyControl;

    public void setGameControl(gameController control){
        enemyControl = control;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void takeDamage(int dmg){
        health -= dmg;
    }

    public void heal(int heal){
        health += heal;
        if (health > maxHealth){
            health = maxHealth;
        }
    }

    public boolean isStun(){
        return isStun;
    }

    public void setStun(int duration){
        if (!isStun){
            isStun = !isStun;
            stunDuration = duration;
        }
    }

    public void setHealth(int healthSet){
        health = healthSet;
    }

    public void setMaxHealth(int healthSet){
        maxHealth = healthSet;
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

    public int getStunDuration(){
        return stunDuration;
    }

    public int getAttack(){
        return attack;
    }

    public gameController getGameController() {
    	return enemyControl;
    }

    public abstract void enemyAttack();
}