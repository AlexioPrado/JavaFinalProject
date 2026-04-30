package game.test;

import static org.junit.jupiter.api.Assertions.*;
import game.model.*;
import game.model.enemySubClass.*;
import org.junit.jupiter.api.*;

class testEnemyFactory {
	private enemyFactory factory;
	
	@BeforeEach
	void setUp() throws Exception {
		factory = new enemyFactory();
	}

	@Test
	@DisplayName("Testing Enemy Creation 1")
	void creatingEnemy1() {
		assertEquals(factory.chooseEnemy(1).getName(), new enemyChiikawa().getName(), "failed");
	}
	
	@Test
	@DisplayName("Testing Enemy Creation 2")
	void creatingEnemy2() {
		assertEquals(factory.chooseEnemy(2).getName(), new enemyHachiware().getName(), "failed");
	}
	
	@Test
	@DisplayName("Testing Enemy Creation 3")
	void creatingEnemy3() {
		assertEquals(factory.chooseEnemy(3).getName(), new enemyUsagi().getName(), "failed");
	}
	
	@Test
	@DisplayName("Testing Enemy Creation 4")
	void creatingEnemy4() {
		assertEquals(factory.chooseEnemy(4).getName(), new enemyMcCuen().getName(), "failed");
	}
	
	@Test
	@DisplayName("Testing Enemy Creation 5")
	void creatingEnemy5() {
		assertEquals(factory.chooseEnemy(5).getName(), new enemyKeller().getName(), "failed");
	}
	
	@Test
	@DisplayName("Testing Enemy Creation 6")
	void creatingEnemy6() {
		assertEquals(factory.chooseEnemy(6).getName(), new enemyGardner().getName(), "failed");
	}
	
	@Test
	@DisplayName("Testing Enemy Creation NUll")
	void creatingEnemy7() {
		assertEquals(factory.chooseEnemy(7), null, "failed");
	}

}
