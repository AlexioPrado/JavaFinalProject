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
     * Main method for getting user input to decide the player's action in a fight.
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

    /**
     * Creates an enemy using a random integer and through the enemyFactory,
     * create an enemy with that corresponding integer
     */
    public enemy createEnemy(){
        int getEnemy = randomEnemy.nextInt(6) + 1;
        return enemyCreator.chooseEnemy(getEnemy);
    }

    /**
     * Endgame method to output the story ending and give
     * options to the user on their next steps
     */
    public void endGame(){
        int choice;
        clearTerminal();

        //Outtput inormation of the ending
        view.endGame();
        view.showMessage(" ");

        //Give users an option to play again or stop
        view.showChoices("What should you do now?", new ArrayList<String>(Arrays.asList("Restart time. Save humanity.","I don\'t gaf.")));
        
        //Get user input, run the neccesary methods or end the game
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

    /**
     * Get choice methods makes obtaining user information easier
     * and evaluate input validation at one place. It re
     * 
     * @param min The lowest value of options. Basically always 1
     * @param max The highest value of options. If there is 3 options, 3 is the max
     */
    private int getChoice(int min, int max) {
        int choice = -1;

        /**
         * While the choice is greater than the max or less than max,
         * continue asking for user input
         */
        while (choice < min || choice > max) {
            //Take user input that is an integer
            if (scanner.hasNextInt()) {
                //set it as the choice
                choice = scanner.nextInt();
            } else {
                //Take user input that is not an integer
                scanner.next();

                /**
                 * This handles user input for instances with multiple choices 
                 * and user input that only needs one. In the cases of one input,
                 * its instructions to continue.
                 * 
                 * If statements asks if there is more than one option, greater than 0
                 */
                if (max - min > 0){
                    view.showMessage("Invalid input. Please enter a number between " + min + " and " + max + ".");
                } else {
                    view.showMessage("Invalid input. Please enter the number " + min);
                }
            }
        }

        //Return correctly inputed chocie by user
        return choice;
    }

    /**
     * Used by the active character to heal their other exploration member
     * 
     * @param heal The amount of healing given to the offcharacter
    */
    public void healOffCharacter(int heal){
        offCharacter.heal(heal);
    }

    /**
     * Used by the active character to buff their other exploration member
     */
    public void applyPartnerBuff(){
        /**
         * Creates variables based on the active character stats
         *
         * Buff = the amount of dmg increase
         * duration = counter for how long buff lasts
         * maxDuration = how long it should last. To not overcap the duration of buff
         */
        int buff = activeCharacter.getPartnerBuffDmg();
        int duration = activeCharacter.getPartnerBuffDuration();
        int maxDuration = activeCharacter.getPartnerBuffMaxDuration();

        //Apply it to the offCharater
        offCharacter.setPartnerBuffDmg(buff);
        offCharacter.setPartnerBuffDuration(duration-1);
        offCharacter.setPartnerBuffMaxDuration(maxDuration-1);
    }

    /**
     * Used by active character to increase
     * energy of the other exploration member
     */
    public void partnerEnergyIncrease(){
        offCharacter.gainEnergy();
    }

    /**
     * Method for both agents and enemies to deal dmg to the other
     * 
     * @param dmg amount of damage
     * @param dmgDealtTo who is it being damaged to
     */
    public void dealDamage(int dmg, String dmgDealtTo){
        view.showMessage(" ");

        // Determine who is getting damaged
        switch(dmgDealtTo){
            case "agent":
                //Output enemy's action
                view.showMessage("Enemy action: ");

                //Inside the enemy object, dmg is set to -1 if they are stunned
                if (dmg == -1){
                    //Output stunned message
                    view.showMessage(enemy.getName() + " tried to deal dmg but is stunned!");
                } else {
                    //Output the damage dealt to what agent
                    view.showMessage(enemy.getName() + " dealt " + dmg + " dmg to " + activeCharacter.getName() + "!");
                    //Update activeCharacter's health
                    activeCharacter.takeDamage(dmg);

                    //Check if the activeCharacter is still alive from the dmg
                    if (!activeCharacter.isAlive()){
                        agentDeath();
                    }
                }
                break;
            case "enemy":
                //Output player's action
                view.showMessage("Player action:");
                //Output the damage dealt to the enemy
                view.showMessage(activeCharacter.getName() + " dealt " + dmg + " dmg to " + enemy.getName() + "!");
                //Update enemy's health
                enemy.takeDamage(dmg);

                //Check if the enemy is still alive from the dmg
                if (!enemy.isAlive()){
                    enemyDeath();
                }
                break; 
        }
    }

    /**
     * Method for switching out the active and off character
     * Also checks if the offcharacter is dead.
     */
    public void switchCharacter(){
        //Ouptut player's action
        view.showMessage("Player action: ");

        //Checks if offcharacter is alive
        if (offCharacter.isAlive()){
            //Create placeholder for activeCharacter
            agent placeholder = activeCharacter;
            //Switch the agents
            activeCharacter = offCharacter;
            offCharacter = placeholder;
            //Output message of the switch
            view.showMessage(offCharacter.getName() + " switched with " + activeCharacter.getName());
        } else {
            //Output message that the offCharacter is dead
            view.showMessage(offCharacter.getName() + "is dead. " + activeCharacter.getName() + "must fight alone.");
            //Call playerTurn because the switch action became invalid, asks for player's action again
            playerTurn();
        }
    }

    /**
     * Method to output message of the death of an enemy
     */
    public void enemyDeath(){
        //Ouput message of dead enemy by what character
        view.showMessage(enemy.getName() + " died by " + activeCharacter.getName() + "\'s attack!");
    }

    /**
     * Method to output message of the death of the active character
     * and evaluate next steps in the case of both agents dead
     */
    public void agentDeath(){
        //Output message of dead enemy
        view.showMessage(activeCharacter.getName() + " died by " + enemy.getName() + "\'s attack!");
        //Output message of active character and off character switching places
        view.showMessage(offCharacter.getName() + " switched with " + activeCharacter.getName());
        //Check if both characters are still alive
        if (!activeCharacter.isAlive() && !offCharacter.isAlive()){
            endGame();
            return;
        }

        //Handling of switching character. Create a placeholder then switch
        agent placeholder = activeCharacter;
        activeCharacter = offCharacter;
        offCharacter = placeholder;
    }

    /**
     * Used by agents to check the enemy if they are stunned
     * If stunned, it increases dmg of agents
     * 
     * @return true/false if the enemy is stunned
     */
    public boolean isEnemyStun(){
        return enemy.isStun();
    }

    /**
     * Used by agents to stun the enemy. 
     * 
     * @param duration how long the stun lasts for
     */
    public void stunEnemy(int duration){
        enemy.setStun(duration);
    }

    /**
     * Method to reset the game once the game was lost. 
     * Each variable will be reset to its former value
     */
    public void resetGame(){
        running = true;
        enemy = null;
        partner1 = null;
        partner2 = null;
        activeCharacter = null;
        offCharacter = null;
        teamList.clear();
    }   

    /**
     * Method to clear the terminal. Aesthetic purpose
     * 
     * NOTE: 
     */
    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        //try {
        //    if (System.getProperty("os.name").contains("Windows")) {
        //        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        //    } else {
        //        new ProcessBuilder("clear").inheritIO().start().waitFor();
        //    }
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    }
}
