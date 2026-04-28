/**
 * Marcus Alexio Prado
 * Course: JAdv Java
 * Date: 4/23/26
 * Last Modified: 4/28/26
 * 
 * 
 */
import java.util.*;
import enemyFactory;

public class gameController {
    private enemyFactory enemyCreator;
    private agentFactory agentCreator;
    private gameView view;
    private Scanner scanner;
    protected agent partner1;
    protected agent partner2;
    protected ArrayList<agent> teamList;
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
        }
        
        view.showChoices("You read thoroughly about the agent.", new ArrayList<>(Arrays.asList("Examine another agent.", "Go back to begin exploration.")));

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
        ArrayList<String> agentList = new ArrayList<>(List.of("Aria","Nangong","Sunna"));

        clearTerminal();
        view.showChoices("It\'s time to recruit your agents.\nSelect your 1st agent below.", agentList);
        switch(scanner.next()){
            case "1":
                partner1 = agentCreator.chooseCharacter(1);
                System.out.println("got here");
                agentList.remove("Aria");
                System.out.println("got here 2");
                break;
            case "2":
                partner1 = agentCreator.chooseCharacter(2);
                agentList.remove("Nangong");
                break;
            case "3":
                partner1 = agentCreator.chooseCharacter(3);
                agentList.remove("Sunna");
                break;
        }

        teamList.add(partner1);
        System.out.println("got here 3");

        clearTerminal();
        view.showChoices("Select your 2nd agent below.", agentList);
        switch(scanner.next()){
            case "1":
                switch(agentList.get(0)){
                    case "Aria":
                        partner2 = new agentAria();
                        break;
                    case "Nangong":
                        partner2 = new agentNangong();
                        break;
                    case "Sunna":
                        partner2 = new agentSunna();
                        break;
                }
            case "2":
                switch(agentList.get(1)){
                    case "Aria":
                        partner2 = new agentAria();
                        break;
                    case "Nangong":
                        partner2 = new agentNangong();
                        break;
                    case "Sunna":
                        partner2 = new agentSunna();
                        break;
                }
        }
        teamList.add(partner2);

        clearTerminal();


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
            break;
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









