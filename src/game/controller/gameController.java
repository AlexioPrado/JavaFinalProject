/**
 * Marcus Alexio Prado
 * Course: JAdv Java
 * Date: 4/23/26
 * Last Modified: 4/30/26
 * 
 * The game flow of my application. Manages interactions
 * with model and viwer, and between classes.
 */
package game.controller;
import game.view.gameView;
import game.model.*;
import game.model.agentSubClass.agentAria;
import game.model.agentSubClass.agentNangong;
import game.model.agentSubClass.agentSunna;
import game.model.enemySubClass.*;
import java.util.*;

/**
 * Handles the game flow and interactions between enemy and 
 * agent objects, and between agents. Also handles what is being
 * outputted throughout game flow.
 */
public class gameController {
    public gameView view;
    public Scanner scanner;
    public enemyFactory enemyCreator;
    public agentFactory agentCreator;
    public Random randomEnemy;
    public enemy enemy;
    public agent partner1;
    public agent partner2;
    public agent activeCharacter;
    public agent offCharacter;
    public ArrayList<agent> teamList;
    public int battleCounter;
    public int turnCounter;
    public boolean running;

    /**
     * Constructur method of gameController.
     * @param view object for outputting
     */    
    public gameController(gameView view){
        this.view = view;
        this.scanner = new Scanner(System.in);
        enemyCreator = new enemyFactory();
        agentCreator = new agentFactory(); 
        randomEnemy = new Random();
        teamList = new ArrayList<agent>();
        battleCounter = 1;
        turnCounter = 1;
        running = true;
    }

    /**
     * Starts the game flow by outputting 
     * game backstory and menu options.
     */
    public void start(){
        int choice = -1;
        resetGame();
        clearTerminal();

        //Output menu and choices to continue game
        view.showMenu();

        //Get user input, call specified method
        choice = getChoice(1, 3);
        switch (choice){
            case 1:
                selectExploration();
                break;
            case 2:
                showAgentCombat();
                break;
            case 3:
                combatRules();
                break;
        }
    }

    /**
     * Output the combat rules of the game.
     * Gives instruction to go back to menu.
     */
    public void combatRules(){
        int choice = -1;
        clearTerminal();

        //Output combat rules and instruction to go back to menu
        view.showCombatRules();
        view.showMessage("You read the combat rules of the Mii Hollow.");
        view.showMessage("Enter \"1\" to go back:");
        choice = getChoice(1, 1);

        //Call start method
        start();
    }

    /**
     * Output an agents combat abilities, using user input to select agent.
     * Gives option to look through more agents or go back to menu.
     */
    public void showAgentCombat(){
        int choice = -1;

        clearTerminal();

        //Output options to select agent of interest
        view.showChoices("Select agent to examine:", new ArrayList<>(Arrays.asList("Aria", "Nangong", "Sunna")));
        view.showMessage("Select your next action:");

        //Get user input. Clear Terminal then output agent abilities
        choice = getChoice(1, 3);
        switch (choice) {
            case 1:
                clearTerminal();
                view.showCharacterKit(new agentAria());
                break;
            case 2:
                clearTerminal();
                view.showCharacterKit(new agentNangong());
                break;
            case 3:
                clearTerminal();
                view.showCharacterKit(new agentSunna());
                break;
        }
        
        //Output options to continue examining abilities or go back to menu
        view.showChoices("You read thoroughly about the agent.", new ArrayList<>(Arrays.asList("Examine another agent.", "Go back to begin exploration.")));
        view.showMessage("Select your next action:");

        //Get user input. Calls specified method
        choice = getChoice(1, 2);
        switch (choice) {
            case 1:
                showAgentCombat();
                break;
            case 2:
                start();
                break;
        }
    }

    /**
     * Next step into game flow: Selecting exploration team
     * Asks user to select two agents out of the given three
     */
    public void selectExploration(){
        //Method variables
        int choice;
        ArrayList<String> agentList = new ArrayList<>(List.of("Aria","Nangong","Sunna"));

        clearTerminal();

        //Show options to select first agent
        view.showChoices("It\'s time to recruit your agents.\nSelect your 1st agent below.", agentList);
        view.showMessage("Select your next action:");

        /**
         * 1. Get user input
         * 2. create agent object
         * 3. remove name from list of agents to choose
         * 4. add agent object to the teamList
         */
        choice = getChoice(1, 3);
        partner1 = agentCreator.chooseCharacter(choice);
        agentList.remove(agentList.get(choice - 1));
        teamList.add(partner1);

        clearTerminal();

        //Show chosen 1st agent and options to select second agent
        view.showMessage("Selected Agents: " + teamList.get(0).getName());
        view.showMessage("");
        view.showChoices("Select your 2nd agent below.", agentList);
        view.showMessage("Select your next action:");

        /**
         * 1. Get User input
         * 2. switch case, based on the change of the agentList, create 2nd agent object
         * 3. add agent object to teamList
         */
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

        //Set 1st agent as the active character and 2nd agent as the off character
        activeCharacter = teamList.get(0);
        offCharacter = teamList.get(1);

        //Call confirmation method
        teamConfirmation();
    }

    /**
     * Asks user for confirmation on the team they selected
     */
    public void teamConfirmation(){
        int choice;
        clearTerminal();

        //Output selected team and the active character
        view.showMessage("Selected team: " + teamList.get(0).getName() + " | " + teamList.get(1).getName());
        view.showMessage("Active Character: " + activeCharacter.getName());
        view.showMessage(" ");
        
        //Show choices to continue with the team or create a new one
        view.showChoices("Is this your final descision?", new ArrayList<String>(Arrays.asList("Yes. Start exploration", "No. Create a new exploration team")));;
        
        //Get user input. Call specified method
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

    /**
     * Main game flow method.
     * Creates layout for fighting enemies
     */
    public void game(){
        int choice = -1;
        clearTerminal();

        //Set turn to 1
        turnCounter = 1;

        //Create enemy and output the enemy encountered
        enemy = createEnemy();
        view.showMessage("Your team encountered " + enemy.getName() + "!");

        // Set the gameController for both agents and enemy objects
        activeCharacter.setAgentControl(this);
        offCharacter.setAgentControl(this);
        enemy.setGameControl(this);

        // Main game loop, depending on if the enemy is alive
        while (enemy.isAlive()){
            view.showMessage("\n--------------------------------------------");
            
            //Output Battle and turn counter
            System.out.println("Battle: " + battleCounter + " | Turn: " + turnCounter);

            //Call playerTurn method
            playerTurn();

            //If user decides to endgame, running is set to false, returning
            if (!running) return;

            //If enemy still alive, call enemyTurn method
            if (enemy.isAlive()){
                enemyTurn();
            }
        }
        
        //Output format for when enemy is defeated
        view.showMessage("\n--------------------------------------------");
        view.showMessage("Exploration team defeated " + enemy.getName());

        //Each won battle, agents get healed by 5. Also shows output
        activeCharacter.heal(5);
        offCharacter.heal(5);
        view.showMessage("+ Team is healed by 5.\n");
        
        //Instruction to continue to next battle
        view.showMessage("Continue exploring the Mii hollow. \nEnter \"1\" to continue:");
        choice = getChoice(1, 1);

        //Increase battle counter
        battleCounter++;

        //call itself to start next battle
        game();
    }

    /**
     * Main method for getting user input to decide the player's action.
     */
    public void playerTurn(){
        int choice;

        //Increase turn counter
        turnCounter++;

        //Output brief stats of the active character and enemy
        view.showMessage(" ");
        view.showEnemyQuickStats(enemy);
        view.showMessage(" ");
        view.showCharacterQuickStats(activeCharacter);
        view.showMessage(" ");

        //Output options of the active character
        view.showPlayerTurn(activeCharacter);

        //Get user input
        view.showMessage("\nChoice: ");
        choice = getChoice(1, 6);

        clearTerminal();

        /**
         * Use user input to decide action
         * 1. normal attack
         * 2. skill attack
         * 3. ultimate attack
         * 4. switch character
         * 5. show active character abilities
         * 6. end game
         */
        switch (choice){
            case 1:
                activeCharacter.normalAttack();
                break;
            case 2:
                activeCharacter.skillAttack();
                break;
            case 3:
                /**
                 * If character has enough energy, do ultimate attack
                 * Else, output message of ultimate not being ready, call itself to restart user action
                 */
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

                //Output agent abilties
                view.showCharacterKit(activeCharacter);
                view.showMessage(" ");

                //Output battle and turn counter
                System.out.println("Battle: " + battleCounter + " | Turn: " + turnCounter);
                
                //Offset turnCounter to make sure looking at agent abilities is not considered an action
                turnCounter -= 1;

                //Call itself to let user choose an action
                playerTurn();
                break;
            case 6:
                endGame();
                break;
        }
    }

    /**
     * Enemy turn is just to call the enemeis method in attacking
     */
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
        view.showChoices("What should you do now?", new ArrayList<String>(Arrays.asList("Restart time. Save humanity.","I don\'t gaf.")));
        choice = getChoice(1, 2);
        switch (choice){
            case 1:
                start();
                break;
            case 2:
                running = false;
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
                if (max - min > 0){
                    view.showMessage("Invalid input. Please enter a number between " + min + " and " + max + ".");
                } else {
                    view.showMessage("Invalid input. Please enter the number " + min);
                }
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
        running = true;
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
