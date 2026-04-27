/**
 * Marcus Alexio Prado
 * Course: JAdv Java
 * Date: 4/23/26
 * Last Modified: 
 * 
 * 
 */
import java.util.*;

public class gameController {
    private gameView view;
    private Scanner scanner;

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
        view.showMessage("Enter to go back:");
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
        clearTerminal();
        System.out.println("explore");
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









