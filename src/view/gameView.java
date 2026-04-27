/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/23/26
 * Last Modified: 4/26/26
 * 
 */

import java.util.ArrayList;

public class gameView {

    public void showMessage(String message){
            System.out.println(message);
        }

    public void showChoices(String message, ArrayList<E> options){
        System.out.println(message);
        for (i = 1; i < options.size(); i++){
            System.out.println(i + ". " + options.get(i));
        }
    }

    public void showCharacterKit(agent character){
        System.out.println("+--------------------------------------");
        System.out.println("| %-36s |%n", "Name: " + character.name);
        System.out.println("|--------------------------------------");
        System.out.println("| Normal Attack:                       ");
        System.out.println("|",character.normalAttack);
        System.out.println("| Skill:                               ");
        System.out.println("|",character.skill);
        System.out.println("| Ultimate:                            ");
        System.out.println("|",character.ultimate);
        System.out.println("+--------------------------------------");
    }

    public void showCharacterQuickStats(agent character){
        System.out.println("Name:", character.name);
        System.out.println("HP:", character.health, "/", character.maxHealth);
        System.out.println("Energy:", character.energy, "/", character.maxEnergy);
        System.out.println("Buffs:")
        for (String buffs : character.getBuffs()){
            System.out.println(buffs);
        }
    }

    public void showEnemyQuickStats(enemy enemy){
        System.out.println("Name:", enemy.name);
        System.out.println("HP:", enemy.health, "/", enemy.maxHealth);
        System.out.println("Energy:", enemy.energy, "/", enemy.maxEnergy);
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

        showChoices("You have started your mission.\nSelect your next Action: ", new ArrayList<E>(List.of("1. Select your agent exploration team", "2. Examine agent combat abilities (How agent's work)", "3. Hollow Exploration Combat Rules")));
    }

    public void showPlayerTurn(agent character){
        System.out.println("Active Character:", character.name);
        System.out.println("Select your next Action:");
        System.out.println("1. Normal Attack:", character.naName);
        System.out.println("2. Skill:", character.skName);
        System.out.println("3. Ultimate:", character.ultName);
        System.out.println("4. Switch Character");
        System.out.println("5. Examine Agent Capabilities");
        System.out.println("6. Examine Enemy Capabilities");
        System.out.println("7. End Game");
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
}