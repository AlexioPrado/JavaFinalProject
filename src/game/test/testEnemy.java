package game.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import game.controller.gameController;
import game.model.enemySubClass.*;
import game.model.*;
import game.view.gameView;

class testEnemy {
	private enemy enemy;
	private gameController control;
	
	@BeforeEach
	void setUp() throws Exception {
		enemy = new enemyChiikawa();
		control = new gameController(new gameView());
	}
	
	@Test
	@DisplayName("Creating enemyControl")
	void createEnemyControl() {
		enemy.setGameControl(control);
		assertEquals(control, enemy.getGameController(), "failed");
	}
	
	@ParameterizedTest
	@CsvSource({"0, false","10, true","-5, false"})
	@DisplayName("enemy still alive")
	void enemyIsAlive(int setHealth, boolean alive) {
		enemy.setHealth(setHealth);
		assertEquals(alive, enemy.isAlive(), "failed");
	}
	
	@ParameterizedTest
	@CsvSource({"5, 13", "10, 8"})
	@DisplayName("Taking damage")
	void takingDamage(int dmg, int finalHealth) {
		enemy.takeDamage(dmg);
		assertEquals(finalHealth, enemy.getHealth(), "failed");
	}
	
	@ParameterizedTest
	@CsvSource({"15, 2, 17","15, 7, 18"})
	@DisplayName("Healing")
	void healing(int health, int heal, int finalHealth) {
		enemy.setHealth(health);
		enemy.heal(heal);
		assertEquals(finalHealth, enemy.getHealth(), "failed");
	}
	
	@Test
	@DisplayName("Enemy not stunned")
	void notStunned() {
		assertEquals(false, enemy.isStun(), "failed");
	}
	
	@Test
	@DisplayName("Enemy is stunned")
	void isStunned() {
		control.enemy = enemy;
		control.stunEnemy(5);
		assertEquals(true, enemy.isStun(), "failed");
	}
	
	@Test
	@DisplayName("Setting stun")
	void setStun() {
		control.enemy = enemy;
		control.stunEnemy(5);
		assertEquals(5, enemy.getStunDuration(), "failed");
	}
	
	@Test
	@DisplayName("setting health")
	void setHealthEnemy() {
		enemy.setHealth(15);
		assertEquals(15, enemy.getHealth(), "failed");
	}
	
	@Test
	@DisplayName("setting max health")
	void setMaxHealthEnemy() {
		enemy.setMaxHealth(20);
		assertEquals(20, enemy.getMaxHealth(), "failed");
	}
	
	@Test
	@DisplayName("Getting name")
	void gettingName() {
		assertEquals("Chiikawa", enemy.getName(), "failed");
	}
	
	@Test
	@DisplayName("Getting health")
	void gettingHealth() {
		assertEquals(18, enemy.getHealth(), "failed");
	}
	
	@Test
	@DisplayName("Getting max health")
	void gettingMaxHealth() {
		assertEquals(18, enemy.getMaxHealth(), "failed");
	}
	
	@Test
	@DisplayName("Getting stun duration")
	void gettingStunDuration() {
		control.enemy = enemy;
		control.stunEnemy(5);
		assertEquals(5, enemy.getStunDuration(), "failed");
	}
	
	@Test
	@DisplayName("Getting agentControl")
	void getttingAgentControl() {
		enemy.setGameControl(control);
		assertEquals(control, enemy.getGameController(), "failed");
	}
	
}
