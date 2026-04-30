package game.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.controller.gameController;
import game.model.*;
import game.model.enemySubClass.*;
import game.model.agentSubClass.*;
import game.view.gameView;

class testEnemyHachiware {
	private enemy hachiware;
	private gameController control;
	private agent agent;
	
	@BeforeEach
	void setUp() throws Exception {
		hachiware = new enemyHachiware();
		control = new gameController(new gameView());
		hachiware.setGameControl(control);
		agent = new agentAria();
		control.activeCharacter = agent;
		control.enemy = hachiware;
	}
	
	@Test
	@DisplayName("Basic Attack")
	void basicAttack() {
		hachiware.enemyAttack(); // + 2
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, hachiware.getAttack(), "failed");
	}
	
	@Test
	@DisplayName("Basic Attack: Big Bonus")
	void basicAttackBonus() {
		hachiware.enemyAttack(); // + 2
		hachiware.enemyAttack(); // + 2
		hachiware.enemyAttack(); // + 2
		hachiware.enemyAttack(); // + 2 + 5
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 13, "failed");
	}

	@Test
	@DisplayName("Basic Attack Stunned")
	void basicAttackStunned() {
		control.stunEnemy(1);
		hachiware.enemyAttack(); // -1
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 0, "failed");
	}
	
	@Test
	@DisplayName("Basic Attack After Stunned")
	void basicAttackAfterStunned() {
		control.stunEnemy(1);
		hachiware.enemyAttack(); // -1
		hachiware.enemyAttack(); // +2
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, hachiware.getAttack(), "failed");
	}
}
