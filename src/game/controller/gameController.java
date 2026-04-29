/**
 * Marcus Alexio Prado
 * Course: JAdv Java
 * Date: 4/23/26
 * Last Modified: 4/28/26
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
    public ArrayList<agent> teamList = new ArrayList<agent>();
    //private boolean running = true;

    public gameController(gameView view){
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
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
                scanner.next();
                start();
        }
    }

    public void combatRules(){
        clearTerminal();
        view.showCombatRules();
        view.showMessage("You read the combat rules of the Mii Hollow.");
        view.showMessage("Enter 'go' to go back:");
        scanner.next();
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
                scanner.next();
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
        partner2 = agentCreator.chooseCharacter(choice);
        teamList.add(partner2);

        activeCharacter = teamList.get(0);

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

        while (enemy.isAlive()){
            view.showEnemyQuickStats(enemy);
            view.showMessage("Active Character: ");
            view.showCharacterQuickStats(activeCharacter);
            view.showPlayerTurn(activeCharacter);
        }
        

        //You encounter ___ enemy.
        //show enemy stats
        //show active character stas
        //show choices
        //player choose choice, give error handling
        //do what player chooses. 
        //enemy turn.
        //repeat
    }

    public void playerTurn(){

    }

    public void enemyTurn(){

    }

    public enemy createEnemy(){
        int getEnemy = randomEnemy.nextInt(6) + 1;
        return enemyCreator.chooseEnemy(getEnemy);
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

   //public agent getPartnerInfo(String name){
   //    switch (name){
   //        case "Aria":
   //            return aria;
   //        case "Nangong":
   //            return nangong;
   //        case "Sunna":
   //            return sunna;
   //    }
   //}

    public void dealDamage(int dmg, String dmgDealtTo){
        if (dmg == -1){
            //USE TO INDICATE ENEMY ATTACK WAS BLOCKED DUE TO STUN
        }

        switch(dmgDealtTo){
            case "agent":
                // DEAL DMG TO THE ACTIVE AGENT
                break;
            case "enemy":
                //DEAL DMG TO THE ENEMY
                break;
        }
    }

    public boolean isEnemyStun(){
        //take enemy object and return isStun method. 
        return true;
    }

    public void stunEnemy(int duration){

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









