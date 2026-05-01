package game.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.controller.gameController;
import game.model.*;
import game.model.enemySubClass.*;
import game.model.agentSubClass.*;
import game.view.gameView;

class testEnemyChiikawa {
	private enemy chiikawa;
	private gameController control;
	private agent agent;
	
	
	@BeforeEach
	void setUp() throws Exception {
		chiikawa = new enemyChiikawa();
		control = new gameController(new gameView());
		chiikawa.setGameControl(control);
		agent = new agentSunna();
		control.activeCharacter = agent;
		control.enemy = chiikawa;
	}
	
	@Test
	@DisplayName("Basic Attack: False attackSwitch")
	void basicAttackFalse() {
		chiikawa.enemyAttack(); // + 6
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, chiikawa.getAttack(), "failed");
	}
	
	@Test
	@DisplayName("Basic Attack: True attackSwitch")
	void basicAttackTrue() {
		chiikawa.enemyAttack(); // + 6
		chiikawa.enemyAttack(); // + 7
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 13, "failed");
	}
	
	@Test
	@DisplayName("Basic Attack: Stunned")
	void basicAttackStunned() {
		control.stunEnemy(2);
		chiikawa.enemyAttack(); // -1
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, 0, "failed");
	}
	
	@Test
	@DisplayName("Basic Attack: After Stunned")
	void basicAttackAfterStunned() {
		control.stunEnemy(1);
		chiikawa.enemyAttack(); // +6
		chiikawa.enemyAttack(); // +7
		int dmg = control.activeCharacter.getMaxHealth() - control.activeCharacter.getHealth();
		assertEquals(dmg, chiikawa.getAttack(), "failed");
	}

}
