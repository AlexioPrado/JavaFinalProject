import java.util.ArrayList;

/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/23/26
 * 
 */

public class gameView{

    public void showChoices(String message, ArrayList<E> options){
        System.out.println(message);
        for (i = 1; i < options.size(); i++){
            System.out.println(i + ". " + options.get(i));
        }
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public void showCharacterStats(agent character){
        System.out.println("+--------------------------------------+");
        System.out.println("| %-18s %18s |%n", "Name: " + character.name, "HP: " + character.health + "/" + character.maxHealth);
        System.out.println("|--------------------------------------|");
        System.out.println("| Buffs:                               |");

        for (String buffs : character.getBuffs()){
            System.out.println("|  - %-33s |%n", buffs);
        }

        System.out.println("+--------------------------------------+");
    }

    public void showCharacterKit(agent Character){
        System.out.println("+--------------------------------------+");
        System.out.println("| %-36s |%n", "Name: " + character.name);
        System.out.println("|--------------------------------------|");
        System.out.println("| Normal Attack:                       |");
        System.out.println()
        System.out.println("| Skill:                               |");

        System.out.println("| Ultimate:                            |");

        System.out.println("+--------------------------------------+");
    }
}