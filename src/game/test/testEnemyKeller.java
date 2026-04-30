package game.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.controller.gameController;
import game.model.*;
import game.model.enemySubClass.*;
import game.model.agentSubClass.*;
import game.view.gameView;

class testEnemyKeller {
	private enemy keller;
	private gameController control;
	private agent agent;
	
	@BeforeEach
	void setUp() throws Exception {
		keller = new enemyKeller();
		control = new gameController(new gameView());
		keller.setGameControl(control);
		agent = new agentAria();
		control.activeCharacter = agent;
		control.enemy = keller;
	}
	
	@Test
	@DisplayName("Basic Attack")
	void basicAttack() {
		keller.enemyAttack(); // + 4
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, keller.getAttack(), "failed");
	}
	
	@Test
	@DisplayName("Basic Attack Stunned")
	void basicAttackStunned() {
		control.stunEnemy(1);
		keller.enemyAttack(); // -1
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 0, "failed");
	}

	@Test
	@DisplayName("Basic Attack After Stunned")
	void basicAttackAfterStunned() {
		control.stunEnemy(1);
		keller.enemyAttack(); // -1
		keller.enemyAttack(); // +2
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, keller.getAttack(), "failed");
	}
}
