package game.test;
import game.model.*;
import game.model.agentSubClass.*;
import game.controller.*;
import game.view.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class testAgent {
	private agent agent;
	private gameController control;
		
	@BeforeEach
	void setUp() throws Exception {
		agent = new agentAria();
		control = new gameController(new gameView());
	}
	
	@Test
	@DisplayName("Creating agentControl")
	void createAgentControl() {
		agent.setAgentControl(control);
		assertEquals(control, agent.getGameController(), "failed");
	}
	
	@ParameterizedTest
	@CsvSource({"0, false","10, true","-5, false"})
	@DisplayName("agent still alive")
	void agentIsAlive(int setHealth, boolean alive) {
		agent.setHealth(setHealth);
		assertEquals(alive, agent.isAlive(), "failed");
	}
	
	@ParameterizedTest
	@CsvSource({"20, 3, 23","20, 6, 25"})
	@DisplayName("Healing")
	void healing(int health, int heal, int finalHealth) {
		agent.setHealth(health);
		agent.heal(heal);
		assertEquals(finalHealth, agent.getHealth(), "failed");
	}
	
	@Test
	@DisplayName("Gaining Energy")
	void gainEnergy() {
		agent.gainEnergy();
		assertEquals(1, agent.getEnergy(), "failed");
	}
	
	@Test
	@DisplayName("Gain Energy to max")
	void gainAtMaxEnergy() {
		for (int i = 0; i <= 3; i++) {
			agent.gainEnergy();
		}
		assertEquals(4, agent.getEnergy(), "failed");
	}
	
	@Test
	@DisplayName("Using Energy")
	void usingEnergy() {
		agent.gainEnergy();
		agent.useEnergy();
		assertEquals(0, agent.getEnergy(), "failed");
	}
	
	@ParameterizedTest
	@CsvSource({"2, false", "4, true"})
	@DisplayName("ultimateStatus")
	void ultimateStatusCheck(int energy, boolean ultimateUse) {
		for (int i = 0; i <= energy; i++) {
			agent.gainEnergy();
		}
		assertEquals(ultimateUse, agent.ultimateStatus(), "failed");
	}
	
	@ParameterizedTest
	@CsvSource({"5, 20", "10, 15"})
	@DisplayName("Taking damage")
	void takingDamage(int dmg, int finalHealth) {
		agent.takeDamage(dmg);
		assertEquals(finalHealth, agent.getHealth(), "failed");
	}
	
	@Test
	@DisplayName("Setting up partnerBuff dmg")
	void settingBuffDmg() {
		agent.setPartnerBuffDmg(5);
		assertEquals(5, agent.getPartnerBuffDmg(), "failed");
	}
	
	@Test
	@DisplayName("Setting up partnerBuff duration")
	void settingBuffDuration() {
		agent.setPartnerBuffDuration(5);
		assertEquals(5, agent.getPartnerBuffDuration(), "failed");
	}
	
	@Test
	@DisplayName("Setting up partnerBuff max duration")
	void settingBuffMaxDuration() {
		agent.setPartnerBuffMaxDuration(5);
		assertEquals(5, agent.getPartnerBuffMaxDuration(), "failed");
	}
	
	@Test
	@DisplayName("Changing health")
	void changeHealth() {
		agent.setHealth(20);
		assertEquals(20, agent.getHealth(), "failed");
	}
	
	@Test
	@DisplayName("Getting name")
	void getttingName() {
		assertEquals("Aria", agent.getName(), "failed");
	}
	
	@Test
	@DisplayName("Getting health")
	void getttingHealth() {
		assertEquals(25, agent.getHealth(), "failed");
	}
	
	@Test
	@DisplayName("Getting max health")
	void getttingMaxHealth() {
		assertEquals(25, agent.getMaxHealth(), "failed");
	}
	
	@Test
	@DisplayName("Getting energy")
	void getttingEnergy() {
		agent.gainEnergy();
		agent.gainEnergy();
		assertEquals(2, agent.getEnergy(), "failed");
	}
	
	@Test
	@DisplayName("Getting max energy")
	void getttingMaxEnergy() {
		assertEquals(4, agent.getMaxEnergy(), "failed");
	}
	
	@Test
	@DisplayName("Getting normal attack name")
	void getttingNAName() {
		assertEquals("Perfect Pitch", agent.getNormalAttackName(), "failed");
	}
	
	@Test
	@DisplayName("Getting skill attack name")
	void getttingSKName() {
		assertEquals("Fall Into Delusion", agent.getSkillName(), "failed");
	}
	
	@Test
	@DisplayName("Getting ultimate attack name")
	void getttingULTName() {
		assertEquals("100% Energy", agent.getUltName(), "failed");
	}
	
	@Test
	@DisplayName("Getting buff dmg")
	void getttingBuffDmg() {
		agent.setPartnerBuffDmg(3);
		assertEquals(3, agent.getPartnerBuffDmg(), "failed");
	}
	
	@Test
	@DisplayName("Getting buff duration")
	void getttingBuffDuration() {
		agent.setPartnerBuffDuration(4);
		assertEquals(4, agent.getPartnerBuffDuration(), "failed");
	}
	
	@Test
	@DisplayName("Getting buff max duration")
	void getttingBuffMaxDuration() {
		agent.setPartnerBuffMaxDuration(5);
		assertEquals(5, agent.getPartnerBuffMaxDuration(), "failed");
	}
	
	@Test
	@DisplayName("Getting agentControl")
	void getttingAgentControl() {
		agent.setAgentControl(control);
		assertEquals(control, agent.getGameController(), "failed");
	}

}
