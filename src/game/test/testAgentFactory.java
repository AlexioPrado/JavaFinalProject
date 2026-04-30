package game.test;

import static org.junit.jupiter.api.Assertions.*;
import game.model.*;
import game.model.agentSubClass.*;

import org.junit.jupiter.api.*;

class testAgentFactory {
	private agentFactory factory;

	@BeforeEach
	void setUp() throws Exception {
		factory = new agentFactory();
	}

	@Test
	@DisplayName("Testing Agent Creation 1")
	void creatingAgent1() {
		assertEquals(factory.chooseCharacter(1).getName(), new agentAria().getName(), "failed");
	}

	@Test
	@DisplayName("Testing Agent Creation 2")
	void creatingAgent2() {
		assertEquals(factory.chooseCharacter(2).getName(), new agentNangong().getName(), "failed");
	}

	@Test
	@DisplayName("Testing Agent Creation 3")
	void creatingAgent3() {
		assertEquals(factory.chooseCharacter(3).getName(), new agentSunna().getName(), "failed");
	}

	@Test
	@DisplayName("Testing Agent Creation NUll")
	void creatingAgent4() {
		assertEquals(factory.chooseCharacter(4), null, "failed");
	}

}
