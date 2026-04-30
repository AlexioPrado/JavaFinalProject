package game.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.controller.gameController;
import game.model.*;
import game.model.enemySubClass.*;
import game.model.agentSubClass.*;
import game.view.gameView;

class testEnemyUsagi {
	private enemy usagi;
	private gameController control;
	private agent agent;
	
	@BeforeEach
	void setUp() throws Exception {
		usagi = new enemyUsagi();
		control = new gameController(new gameView());
		usagi.setGameControl(control);
		agent = new agentAria();
		control.activeCharacter = agent;
		control.enemy = usagi;
	}
	
	@Test
	@DisplayName("Basic Attack")
	void basicAttack() {
		usagi.enemyAttack(); // + 2
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, usagi.getAttack(), "failed");
	}
	
	@Test
	@DisplayName("Basic Attack: Check healing")
	void basicAttackHealing() {
		usagi.setHealth(40);
		usagi.enemyAttack(); // + 2 | Heal + 3
		assertEquals(43, usagi.getHealth(), "failed");
	}
	
	@Test
	@DisplayName("Basic Attack Stunned")
	void basicAttackStunned() {
		control.stunEnemy(1);
		usagi.enemyAttack(); // -1
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 0, "failed");
	}

	@Test
	@DisplayName("Basic Attack After Stunned")
	void basicAttackAfterStunned() {
		control.stunEnemy(1);
		usagi.enemyAttack(); // -1
		usagi.enemyAttack(); // +2
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, usagi.getAttack(), "failed");
	}
}
