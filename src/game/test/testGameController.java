package game.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.model.*;
import game.model.agentSubClass.*;
import game.model.enemySubClass.*;
import game.view.*;
import game.controller.*;

class testGameController {
	private agent activeChar;
	private agent offChar;
	private enemy enemy;
	private gameView view;
	private gameController control;
	
	@BeforeEach
	void setUp() throws Exception {
		view = new gameView();
		control = new gameController(view);
		activeChar = new agentNangong();
		offChar = new agentSunna();
		enemy = new enemyUsagi();
		control.activeCharacter = activeChar;
		control.offCharacter = offChar;
		control.enemy = enemy;
	}

	@Test
	@DisplayName("Switch Characters")
	void switchAgents() {
		control.switchCharacter();
		assertEquals(offChar, control.activeCharacter, "failed");
	}
	
	@Test
	@DisplayName("Enemy Death")
	void enemyDeaths() {
		control.dealDamage(55, "enemy");
		assertEquals(false, enemy.isAlive(), "failed");
	}
	
	@Test
	@DisplayName("Player Death")
	void playerDeaths() {
		control.dealDamage(30, "agent");
		assertEquals(false, activeChar.isAlive(), "failed");
	}

	//@Test
	//@DisplayName("Switch Character. OffCharacter dead")
	//void switchAgentDead() {
	//	offChar.setHealth(0);
	//	control.switchCharacter();
	//	assertEquals(activeChar, control.activeCharacter, "failed");
	//}
}
