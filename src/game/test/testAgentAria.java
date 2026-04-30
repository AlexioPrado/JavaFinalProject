package game.test;

import game.model.*;
import game.model.agentSubClass.*;
import game.model.enemySubClass.*;
import game.controller.*;
import game.view.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;



class testAgentAria {
	private agent aria;
	private agent partner;
	private gameController control;
	private String naInfo;
	private String skInfo;
	private String ultInfo;
	private enemy usagi;
	
	@BeforeEach
	void setUp() throws Exception {
		aria = new agentAria();
		partner = new agentNangong();
		control = new gameController(new gameView());
		aria.setAgentControl(control);
		naInfo = aria.getNormalAttackInfo();
		skInfo = aria.getSkillAttackInfo();
		ultInfo = aria.getUltimateAttackInfo();
		usagi = new enemyUsagi();
		control.enemy = usagi;
		control.activeCharacter = aria;
		control.offCharacter = partner;
	}

	@Test
	@DisplayName("getting na Info")
	void getNAInfo() {
		assertEquals(naInfo, aria.getNormalAttackInfo(), "failed");
	}
	
	@Test
	@DisplayName("getting sk Info")
	void getSKInfo() {
		assertEquals(skInfo, aria.getSkillAttackInfo(), "failed");
	}
	
	@Test
	@DisplayName("getting ult Info")
	void getUltInfo() {
		assertEquals(ultInfo, aria.getUltimateAttackInfo(), "failed");
	}
	
	@Test
	@DisplayName("normal attack basic")
	void naBasic() {
		aria.normalAttack();
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, aria.getNADmg(), "failed");
	}
	
	@Test
	@DisplayName("skill attack basic")
	void skBasic() {
		aria.skillAttack();
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, aria.getSKDmg(), "failed");
	}
	
	@Test
	@DisplayName("ultimate attack basic")
	void ultBasic() {
		for (int i = 0; i <= 4; i++) {
			aria.gainEnergy();
		}
		aria.ultimateAttack();
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, aria.getUltDmg(), "failed");
	}
	
	@Test
	@DisplayName("normal attack Stun")
	void naBasicStun() {
		control.stunEnemy(5);
		aria.normalAttack();
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 6, "failed");
	}
	
	@Test
	@DisplayName("skill attack Stun")
	void skBasicStun() {
		control.stunEnemy(5);
		aria.skillAttack();
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 5, "failed");
	}
	
	@Test
	@DisplayName("ultimate attack Stun")
	void ultBasicStun() {
		control.stunEnemy(5);
		for (int i = 0; i <= 4; i++) {
			aria.gainEnergy();
		}
		aria.ultimateAttack();
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 3, "failed");
	}
	
	@Test
	@DisplayName("normal attack buff")
	void naBasicBuff() {
		for (int i = 0; i <= 4; i++) {
			aria.gainEnergy();
		}
		aria.ultimateAttack(); // + 2
		aria.normalAttack(); // + 6
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 8, "failed");
	}
	
	@Test
	@DisplayName("normal attack buff cumulative Dmg")
	void naBasicBuffCumulative() {
		for (int i = 0; i <= 4; i++) {
			aria.gainEnergy();
		}
		aria.ultimateAttack(); // +2
		aria.normalAttack(); // + 6
		aria.normalAttack(); // +6
		aria.normalAttack(); // +3
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 17, "failed");
	}
	
	@Test
	@DisplayName("normal attack buff already active")
	void naBasicBuffAlreadyActive() {
		for (int i = 0; i <= 4; i++) {
			aria.gainEnergy();
		}
		aria.ultimateAttack(); // +2
		for (int i = 0; i <= 4; i++) {
			aria.gainEnergy();
		}
		aria.ultimateAttack(); // +2
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 4, "failed");
	}
	
	@Test
	@DisplayName("Partner Buff Active: normal attack")
	void naBasicPartnerBugg() {
		aria.setPartnerBuffDmg(3); // +3
		aria.setPartnerBuffDuration(2);
		aria.setPartnerBuffMaxDuration(2);
		
		aria.normalAttack(); // +3
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 6, "failed");
	}
	
	@Test
	@DisplayName("Partner Buff Active: skill attack")
	void skBasicPartnerBugg() {
		aria.setPartnerBuffDmg(3); // +3
		aria.setPartnerBuffDuration(2);
		aria.setPartnerBuffMaxDuration(2);
		
		aria.skillAttack(); // +4
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 7, "failed");
	}
	
	@Test
	@DisplayName("Partner Buff Active: ultimate attack")
	void ultBasicPartnerBugg() {
		aria.setPartnerBuffDmg(3); // +3
		aria.setPartnerBuffDuration(2);
		aria.setPartnerBuffMaxDuration(2);
		
		for (int i = 0; i <= 4; i++) {
			aria.gainEnergy();
		}
		aria.ultimateAttack(); // +2
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 5, "failed");
	}
	
}
