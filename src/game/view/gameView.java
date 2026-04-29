/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/23/26
 * Last Modified: 4/29/26
 * 
 */

package game.view;
import game.model.agent;
import game.model.enemy;
import java.util.ArrayList;
import java.util.Arrays;

public class gameView {

    public void showMessage(String message){
            System.out.println(message);
        }

    public void showChoices(String message, ArrayList<String> options){
        System.out.println(message);
        for (int i = 0; i < options.size(); i++){
            System.out.println((i+1) + ". " + options.get(i));
        }
    }

    public void showCharacterKit(agent character){
        System.out.println("+--------------------------------------");
        System.out.println("| Name: " + character.name);
        System.out.println("|--------------------------------------");
        System.out.println("| Normal Attack:                       ");
        System.out.println("| " + character.getNormalAttackInfo());
        System.out.println("|--------------------------------------");
        System.out.println("| Skill:                               ");
        System.out.println("| " + character.getSkillAttackInfo());
        System.out.println("|--------------------------------------");
        System.out.println("| Ultimate:                            ");
        System.out.println("| " + character.getUltimateAttackInfo());
        System.out.println("+--------------------------------------");
    }

    public void showEnemyQuickStats(enemy enemy){
        System.out.println(enemy.getName() + "  HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        System.out.println("isStun: " + enemy.isStun() + "     Stun Duration: " + enemy.getStunDuration());
    }

    public void showCharacterQuickStats(agent character){
        System.out.println("Active Character: " + character.getName());
        System.out.println("HP: " + character.getHealth() + "/" + character.getMaxHealth() + "    " + "Energy: " + character.getEnergy() + "/" + character.getMaxEnergy());
        //System.out.println("Buffs:");
        //for (String buffs : character.getBuffs()){
        //    System.out.println(buffs);
        //}
    }

    public void showMenu(){
        System.out.println("+--------------------------------------+");
        System.out.println("| Hollow Exploration                   |");
        System.out.println("|--------------------------------------|");
        System.out.println("| You have been tasked to explore the  |");
        System.out.println("| newly expanding Mii Hollow. Ethereals|");
        System.out.println("| appear to resemble New Eridu citizens|");
        System.out.println("| that were consumed by the Hollow.    |");
        System.out.println("| Enter the Mii Hollow and kill all    |");
        System.out.println("| ethereals in your path.              |");
        System.out.println("+--------------------------------------+");
        System.out.println();
        showChoices("You have started your mission.", new ArrayList<>(Arrays.asList("Select your agent exploration team", "Examine agent combat abilities (How agent's work)", "Hollow Exploration Combat Rules")));
        System.out.println();
        showMessage("Select your next Action: ");
    }

    public void showPlayerTurn(agent character){
        showMessage("Select your next Action:");
        System.out.println("1. Normal Attack: " + character.getNormalAttackName());
        System.out.println("2. Skill: " + character.getSkillkName());
        System.out.println("3. Ultimate: " + character.getUltName());
        System.out.println("4. Switch Character");
        System.out.println("5. Examine active agent capability");
        System.out.println("6. End Game");
    }

    public void showCombatRules(){
        System.out.println("+--------------------------------------+");
        System.out.println("| Hollow Exploration Rules             |");
        System.out.println("|--------------------------------------|");
        System.out.println("| 1. Turn Based Combat                 |");
        System.out.println("|    You have the 2 selected agents of |");
        System.out.println("|    choice to come with you to fight. |");
        System.out.println("|    Each action is one turn and you   |");
        System.out.println("|    take turns with the enemy.        |");
        System.out.println("| 2. Agent Combat Capabilities         |");
        System.out.println("|    Has three actions. Normal Attack, |");
        System.out.println("|    skill, and ultimate. Each agent   |");
        System.out.println("|    has different abilities for all   |");
        System.out.println("|    three.                            |");
        System.out.println("| 3. Win/Lose Conditions               |");
        System.out.println("|    Win: Survive each battle          |");
        System.out.println("|    Lose: Until your agents die from  |");
        System.out.println("|    battle.                           |");
        System.out.println("+--------------------------------------+");
    }

    public void endGame(){
        System.out.println("+--------------------------------------+");
        System.out.println("| The world fell in despair.           |");
        System.out.println("|--------------------------------------|");
        System.out.println("| Your efforts to contain the Mii      |");
        System.out.println("| hollow was fruitless. Your           |");
        System.out.println("| exploration team was no match to the |");
        System.out.println("| growing ethereal rampage. You did    |");
        System.out.println("| your very best. Which isn\'t much.    |");
        System.out.println("+--------------------------------------+");
    }
}