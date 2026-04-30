package game.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.controller.gameController;
import game.model.*;
import game.model.enemySubClass.*;
import game.model.agentSubClass.*;
import game.view.gameView;

class testEnemyGardner {
	private enemy gardner;
	private gameController control;
	private agent agent;
	
	@BeforeEach
	void setUp() throws Exception {
		gardner = new enemyGardner();
		control = new gameController(new gameView());
		gardner.setGameControl(control);
		agent = new agentSunna();
		control.activeCharacter = agent;
		control.enemy = gardner;
	}
	
	@Test
	@DisplayName("Basic Attack")
	void basicAttack() {
		gardner.enemyAttack(); // + 4
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, gardner.getAttack(), "failed");
	}
	
	@Test
	@DisplayName("Basic Attack + 2")
	void basicAttackContinuous() {
		gardner.enemyAttack(); // + 4
		gardner.enemyAttack(); // + 5
		gardner.enemyAttack(); // + 6
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 15, "failed");
	}
	
	@Test
	@DisplayName("Basic Attack Stunned")
	void basicAttackStunned() {
		control.stunEnemy(1);
		gardner.enemyAttack(); // -1
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 0, "failed");
	}
	
	@Test
	@DisplayName("Basic Attack After Stunned")
	void basicAttackAfterStunned() {
		control.stunEnemy(1);
		gardner.enemyAttack(); // -1
		gardner.enemyAttack(); // +3
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 4, "failed");
	}
}
