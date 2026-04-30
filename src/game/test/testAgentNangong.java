package game.test;

import game.model.*;
import game.model.agentSubClass.*;
import game.model.enemySubClass.*;
import game.controller.*;
import game.view.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class testAgentNangong {
	private agent nangong;
	private agent partner;
	private gameController control;
	private String naInfo;
	private String skInfo;
	private String ultInfo;
	private enemy mcCuen;
	
	@BeforeEach
	void setUp() throws Exception {
		nangong = new agentNangong();
		partner = new agentSunna();
		control = new gameController(new gameView());
		nangong.setAgentControl(control);
		naInfo = nangong.getNormalAttackInfo();
		skInfo = nangong.getSkillAttackInfo();
		ultInfo = nangong.getUltimateAttackInfo();
		mcCuen = new enemyMcCuen();
		control.enemy = mcCuen;
		control.activeCharacter = nangong;
		control.offCharacter = partner;
	}
	
	@Test
	@DisplayName("getting na Info")
	void getNAInfo() {
		assertEquals(naInfo, nangong.getNormalAttackInfo(), "failed");
	}
	
	@Test
	@DisplayName("getting sk Info")
	void getSKInfo() {
		assertEquals(skInfo, nangong.getSkillAttackInfo(), "failed");
	}
	
	@Test
	@DisplayName("getting ult Info")
	void getUltInfo() {
		assertEquals(ultInfo, nangong.getUltimateAttackInfo(), "failed");
	}
	
	@Test
	@DisplayName("normal attack basic")
	void naBasic() {
		nangong.normalAttack();
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, nangong.getNADmg(), "failed");
	}
	
	@Test
	@DisplayName("skill attack basic")
	void skBasic() {
		nangong.skillAttack(); // + 3 + 1(stun)
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, nangong.getSKDmg() + 1, "failed");
	}
	
	@Test
	@DisplayName("ultimate attack basic")
	void ultBasic() {
		for (int i = 0; i <= 4; i++) {
			nangong.gainEnergy();
		}
		nangong.ultimateAttack(); // + 3 + 1(stun)
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, nangong.getUltDmg()+1, "failed");
	}

	@Test
	@DisplayName("normal attack basic w/ stun")
	void naBasicStun() {
		control.stunEnemy(3);
		nangong.normalAttack(); // + 2 + 1(stun)
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg , nangong.getNADmg() + 1, "failed");
	}
	
	@Test
	@DisplayName("Partner Buff Active: normal attack")
	void naBasicPartnerBugg() {
		nangong.setPartnerBuffDmg(3); // +3
		nangong.setPartnerBuffDuration(2);
		nangong.setPartnerBuffMaxDuration(2);
		
		nangong.normalAttack(); // +2
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 5, "failed");
	}
	
	@Test
	@DisplayName("Partner Buff Active: skill attack")
	void skBasicPartnerBugg() {
		nangong.setPartnerBuffDmg(3); // +3
		nangong.setPartnerBuffDuration(2);
		nangong.setPartnerBuffMaxDuration(2);
		
		nangong.skillAttack(); // +3 + 1(stun)
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 7, "failed");
	}
	
	@Test
	@DisplayName("Partner Buff Active: ultimate attack")
	void ultBasicPartnerBugg() {
		nangong.setPartnerBuffDmg(3); // +3
		nangong.setPartnerBuffDuration(2);
		nangong.setPartnerBuffMaxDuration(2);
		
		for (int i = 0; i <= 4; i++) {
			nangong.gainEnergy();
		}
		nangong.ultimateAttack(); // +3 + 1(stun)
		int dmg = control.enemy.getMaxHealth() - control.enemy.getHealth();
		assertEquals(dmg, 7, "failed");
	} 
}
