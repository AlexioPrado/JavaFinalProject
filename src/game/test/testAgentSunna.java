package game.test;

import game.model.*;
import game.model.agentSubClass.*;
import game.model.enemySubClass.*;
import game.controller.*;
import game.view.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class testAgentSunna {
	private agent sunna;
	private agent partner;
	private gameController control;
	private String naInfo;
	private String skInfo;
	private String ultInfo;
	private enemy keller;

	@BeforeEach
	void setUp() throws Exception {
		sunna = new agentSunna();
		partner = new agentAria();
		control = new gameController(new gameView());
		sunna.setAgentControl(control);
		naInfo = sunna.getNormalAttackInfo();
		skInfo = sunna.getSkillAttackInfo();
		ultInfo = sunna.getUltimateAttackInfo();
		keller = new enemyMcCuen();
		control.enemy = keller;
		control.activeCharacter = sunna;
		control.offCharacter = partner;
	}
	
	@Test
	@DisplayName("getting na Info")
	void getNAInfo() {
		assertEquals(naInfo, sunna.getNormalAttackInfo(), "failed");
	}
	
	@Test
	@DisplayName("getting sk Info")
	void getSKInfo() {
		assertEquals(skInfo, sunna.getSkillAttackInfo(), "failed");
	}
	
	@Test
	@DisplayName("getting ult Info")
	void getUltInfo() {
		assertEquals(ultInfo, sunna.getUltimateAttackInfo(), "failed");
	}
	
	@Test
	@DisplayName("normal attack basic")
	void naBasic() {
		sunna.normalAttack();
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, sunna.getNADmg(), "failed");
	}
	
	@Test
	@DisplayName("skill attack basic")
	void skBasic() {
		sunna.skillAttack(); // + 3 + 3(Partner Buff)
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, sunna.getSKDmg() + 3, "failed");
	}
	
	@Test
	@DisplayName("ultimate attack basic")
	void ultBasic() {
		for (int i = 0; i <= 2; i++) {
			sunna.gainEnergy();
		}
		sunna.ultimateAttack(); // + 2
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, sunna.getUltDmg(), "failed");
	}

	@Test
	@DisplayName("normal attack basic w/ stun")
	void naBasicStun() {
		control.stunEnemy(3);
		sunna.normalAttack(); // + 2 + 1(stun)
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg , sunna.getNADmg() + 1, "failed");
	}
	
	@Test
	@DisplayName("skill attack basic w/ stun")
	void skBasicStun() {
		control.stunEnemy(3);
		sunna.skillAttack(); // + 3 + 1(stun) + 3(Partner Buff)
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg , sunna.getSKDmg() + 4, "failed");
	}
	
	@Test
	@DisplayName("ultimate attack basic w/ stun")
	void ultBasicStun() {
		control.stunEnemy(3);
		for (int i = 0; i <= 2; i++) {
			sunna.gainEnergy();
		}
		sunna.ultimateAttack(); // + 2 + 1(stun)
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg , sunna.getUltDmg() + 1, "failed");
	}
	
	@Test
	@DisplayName("Partner Buff Active: normal attack")
	void naBasicPartnerBugg() {
		sunna.setPartnerBuffDmg(3); // +3
		sunna.setPartnerBuffDuration(2);
		sunna.setPartnerBuffMaxDuration(2);
		
		sunna.normalAttack(); // +1
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, sunna.getNADmg() + 3, "failed");
	}
	
	@Test
	@DisplayName("Partner Buff Active: ultimate attack")
	void ultBasicPartnerBugg() {
		sunna.setPartnerBuffDmg(3); // +3
		sunna.setPartnerBuffDuration(2);
		sunna.setPartnerBuffMaxDuration(2);
		for (int i = 0; i <= 2; i++) {
			sunna.gainEnergy();
		}
		sunna.ultimateAttack(); // + 2
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, sunna.getUltDmg() + 3, "failed");
	}
}
