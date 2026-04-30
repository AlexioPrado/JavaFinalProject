package game.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.controller.gameController;
import game.model.*;
import game.model.enemySubClass.*;
import game.model.agentSubClass.*;
import game.view.gameView;

class testEnemyMcCuen {
	private enemy mcCuen;
	private gameController control;
	private agent agent;
	
	@BeforeEach
	void setUp() throws Exception {
		mcCuen = new enemyMcCuen();
		control = new gameController(new gameView());
		mcCuen.setGameControl(control);
		agent = new agentAria();
		control.activeCharacter = agent;
		control.enemy = mcCuen;
	}
	
	@Test
	@DisplayName("Basic Attack")
	void basicAttack() {
		mcCuen.enemyAttack(); // + 2
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, mcCuen.getAttack(), "failed");
	}
	
	@Test
	@DisplayName("Basic Attack Stunned")
	void basicAttackStunned() {
		control.stunEnemy(1);
		mcCuen.enemyAttack(); // -1
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 0, "failed");
	}
	
	@Test
	@DisplayName("Basic Attack After Stunned")
	void basicAttackAfterStunned() {
		control.stunEnemy(1);
		mcCuen.enemyAttack(); // -1
		mcCuen.enemyAttack(); // +2
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, mcCuen.getAttack(), "failed");
	}
	
}
