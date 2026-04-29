/**
 * Marcus Alexio Prado
 * Course: JAdv Java
 * Date: 4/23/26
 * Last Modified: 4/29/26
 * 
 * 
 */
package game.controller;
import game.view.gameView;
import game.model.*;
import game.model.agentSubClass.agentAria;
import game.model.agentSubClass.agentNangong;
import game.model.agentSubClass.agentSunna;
import game.model.enemySubClass.*;
import java.util.*;


public class gameController {
    public enemyFactory enemyCreator = new enemyFactory();
    public agentFactory agentCreator = new agentFactory();
    public gameView view;
    public Scanner scanner;
    public Random randomEnemy = new Random();
    public enemy enemy;
    public agent partner1;
    public agent partner2;
    public agent activeCharacter;
    public agent offCharacter;
    public ArrayList<agent> teamList = new ArrayList<agent>();
    public int battleCounter = 0;
    //private boolean running = true;

    public gameController(gameView view){
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        resetGame();
        clearTerminal();
        view.showMenu();
        switch (scanner.next()){
            case "1":
                selectExploration();
                break;
            case "2":
                showAgentCombat();
                break;
            case "3":
                combatRules();
                break;
            default:
                clearTerminal();
                view.showMessage("Input Incorrect. The world relies on your decisions. Act quickly and stop delaying.\nEnter to restart:");
                scanner.nextLine();
                start();
        }
    }

    public void combatRules(){
        clearTerminal();
        view.showCombatRules();
        view.showMessage("You read the combat rules of the Mii Hollow.");
        view.showMessage("Enter to go back:");
        scanner.nextLine();
        start();
    }

    public void showAgentCombat(){
        clearTerminal();
        view.showChoices("Select agent to examine:", new ArrayList<>(Arrays.asList("Aria", "Nangong", "Sunna")));
        view.showMessage("Select your next action:");

        switch(scanner.next()){
            case "1":
                clearTerminal();
                view.showCharacterKit(new agentAria());
                break;
            case "2":
                clearTerminal();
                view.showCharacterKit(new agentNangong());
                break;
            case "3":
                clearTerminal();
                view.showCharacterKit(new agentSunna());
                break;
            default:
                clearTerminal();
                view.showMessage("Input Incorrect. Restarting agent combat examine. Take this serious.\nEnter to restart:");
                scanner.nextLine();
                showAgentCombat();
        }
        
        view.showChoices("You read thoroughly about the agent.", new ArrayList<>(Arrays.asList("Examine another agent.", "Go back to begin exploration.")));
        view.showMessage("Select your next action:");

        switch(scanner.next()){
            case "1":
                showAgentCombat();
                break;
            case "2":
                start();
                break;
        }
    }

    public void selectExploration(){
        int choice;
        ArrayList<String> agentList = new ArrayList<>(List.of("Aria","Nangong","Sunna"));

        clearTerminal();
        view.showChoices("It\'s time to recruit your agents.\nSelect your 1st agent below.", agentList);
        view.showMessage("Select your next action:");

        choice = getChoice(1, 3);
        partner1 = agentCreator.chooseCharacter(choice);
        agentList.remove(agentList.get(choice - 1));
        teamList.add(partner1);

        clearTerminal();
        view.showMessage("Selected Agents: " + teamList.get(0).name);
        view.showMessage("");
        view.showChoices("Select your 2nd agent below.", agentList);
        view.showMessage("Select your next action:");

        choice = getChoice(1, agentList.size());
        switch(choice){
            case 1:
                switch(agentList.get(0)){
                    case "Aria":
                        partner2 = agentCreator.chooseCharacter(1);
                        break;
                    case "Nangong":
                        partner2 = agentCreator.chooseCharacter(2);
                        break;
                }
                break;
            case 2:
                switch(agentList.get(1)){
                    case "Nangong":
                        partner2 = agentCreator.chooseCharacter(2);
                        break;
                    case "Sunna":
                        partner2 = agentCreator.chooseCharacter(3);
                        break;
                }
                break;
        }
        teamList.add(partner2);

        activeCharacter = teamList.get(0);
        offCharacter = teamList.get(1);

        teamConfirmation();
    }

    public void teamConfirmation(){
        int choice;
        clearTerminal();
        view.showMessage("Selected team: " + teamList.get(0).name + " | " + teamList.get(1).name);
        view.showMessage("Active Character: " + activeCharacter.name);
        view.showMessage(" ");
        view.showChoices("Is this your final descision?", new ArrayList<String>(Arrays.asList("Yes. Start exploration", "No. Create a new exploration team")));;
        
        choice = getChoice(1, 2);
        switch(choice){
            case 1:
                game();
                break;
            case 2:
                selectExploration();
                break;
        }
    }

    public void game(){
        clearTerminal();
        enemy = createEnemy();
        view.showMessage("Your team encountered " + enemy.getName() + "!");

        activeCharacter.setAgentControl(this);
        offCharacter.setAgentControl(this);
        enemy.setGameControl(this);

        while (enemy.isAlive()){
            view.showMessage("\n--------------------------------------------");
            playerTurn();
            if (enemy.isAlive()){
                enemyTurn();
            }
        }
        
        view.showMessage("\n--------------------------------------------");
        view.showMessage("Exploration team defeated " + enemy.getName());

        activeCharacter.heal(5);
        offCharacter.heal(5);
        view.showMessage("+ Team is healed by 5.\n");

        view.showMessage("Continue exploring the Mii hollow. \nEnter to continue:");
        scanner.next();
        game();
    }

    public void playerTurn(){
        int choice;
        view.showMessage(" ");
        view.showEnemyQuickStats(enemy);
        view.showMessage(" ");
        view.showCharacterQuickStats(activeCharacter);
        view.showMessage(" ");

        view.showPlayerTurn(activeCharacter);
        view.showMessage("\nChoice: ");
        choice = getChoice(1, 6);

        clearTerminal();

        switch (choice){
            case 1:
                activeCharacter.normalAttack();
                break;
            case 2:
                activeCharacter.skillAttack();
                break;
            case 3:
                if (activeCharacter.ultimateStatus()){
                    activeCharacter.ultimateAttack();
                } else {
                    view.showMessage("\nUltimate: " + activeCharacter.getUltName() + " is not ready.");
                    scanner.nextLine();
                    playerTurn();
                }
                break;
            case 4:
                switchCharacter();
                break;
            case 5:
                clearTerminal();
                view.showCharacterKit(activeCharacter);
                playerTurn();
                break;
            case 6:
                endGame();
                break;
        }
    }

    public void enemyTurn(){
        enemy.enemyAttack();
    }

    public enemy createEnemy(){
        int getEnemy = randomEnemy.nextInt(6) + 1;
        return enemyCreator.chooseEnemy(getEnemy);
    }


    public void endGame(){
        int choice;
        clearTerminal();
        view.endGame();
        view.showMessage(" ");
        view.showChoices("What should you do now?", new ArrayList<String>(Arrays.asList("Restart time. Save humanity.","I don\'t give a f*ck.")));
        choice = getChoice(1, 2);
        switch (choice){
            case 1:
                start();
                break;
            case 2:
                break;
        }
    }

    private int getChoice(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
                view.showMessage("Invalid input. Please enter a number between " + min + " and " + max + ".");
            }
        }
        return choice;
    }

    public void healOffCharacter(int heal){
        offCharacter.heal(heal);
    }

    public void applyPartnerBuff(){
        int buff = activeCharacter.getPartnerBuffDmg();
        int duration = activeCharacter.getPartnerBuffDuration();
        int maxDuration = activeCharacter.getPartnerBuffMaxDuration();

        offCharacter.setPartnerBuffDmg(buff);
        offCharacter.setPartnerBuffDuration(duration-1);
        offCharacter.setPartnerBuffMaxDuration(maxDuration-1);
    }

    public void partnerEnergyIncrease(){
        offCharacter.gainEnergy();
    }

    public void dealDamage(int dmg, String dmgDealtTo){
        view.showMessage(" ");

        switch(dmgDealtTo){
            case "agent":
                view.showMessage("Enemy action: ");
                if (dmg == -1){
                    view.showMessage(enemy.getName() + " tried to deal dmg but is stunned!");
                } else {
                    view.showMessage(enemy.getName() + " dealt " + dmg + " dmg to " + activeCharacter.getName() + "!");
                    activeCharacter.takeDamage(dmg);
                    if (!activeCharacter.isAlive()){
                        agentDeath();
                    }
                }
                break;
            case "enemy":
                view.showMessage("Player action:");
                view.showMessage(activeCharacter.getName() + " dealt " + dmg + " dmg to " + enemy.getName() + "!");
                enemy.takeDamage(dmg);
                if (!enemy.isAlive()){
                    enemyDeath();
                }
                break; 
        }
    }

    public void switchCharacter(){
        view.showMessage("Player action: ");
        if (offCharacter.isAlive()){
            agent placeholder = activeCharacter;
            activeCharacter = offCharacter;
            offCharacter = placeholder;
            view.showMessage(offCharacter.getName() + " switched with " + activeCharacter.getName());
        } else {
            view.showMessage(offCharacter.getName() + "is dead. " + activeCharacter.getName() + "must fight alone.");
            scanner.nextLine();
            playerTurn();
        }
    }

    public void enemyDeath(){
        view.showMessage(enemy.getName() + " died by " + activeCharacter.getName() + "\'s attack!");
    }

    public void agentDeath(){
        view.showMessage(activeCharacter.getName() + " died by " + enemy.getName() + "\'s attack!");
        view.showMessage(offCharacter.getName() + " switched with " + activeCharacter.getName());
        if (!activeCharacter.isAlive() && !offCharacter.isAlive()){
            endGame();
            return;
        }
        agent placeholder = activeCharacter;
        activeCharacter = offCharacter;
        offCharacter = placeholder;
    }

    public boolean isEnemyStun(){
        return enemy.isStun();
    }

    public void stunEnemy(int duration){
        enemy.setStun(duration);
    }

    public void resetGame(){
        enemy = null;
        partner1 = null;
        partner2 = null;
        activeCharacter = null;
        offCharacter = null;
        teamList.clear();
}   

    public static void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
