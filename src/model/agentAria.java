/**
 * Marcus Alexio Prado
 * Course: Adv Java
 * Date: 4/26/26
 * Last Modified: 4/27/26
 * 
 */

public class agentAria extends agent{
    private boolean naBuffActive = false;
    private int naBuffDuration = 2;

    public agentAria(){
        this.name = "Aria";
        this.maxHealth = 25;
        this.health = 25;
        this.maxEnergy = 4;
        this.energy = 0;
        this.naName = "Perfect Pitch";
        this.skName = "Fall Into Delusion";
        this.ultName = "100% Energy";
        this.naDmg = 3;
        this.skDmg = 4;
        this.ultDmg = 2;
        this.partnerBuff = false;
        this.partnerBuffDmg = 0;
        this.partnerBuffDuration = 0;
        this.partnerBuffMaxDuration = 0;
    }

    @Override
    public string getNormalAttackInfo(){
        String naInfo = "Aria takes a leap and plunges\ndown toward the enemy, dealing\n 3 dmg. If the enemy is \nstunned, deal an additonal 3 dmg.";
        return naInfo;
    }

    @Override
    public string getSkillAttackInfo(){
        String skInfo = "Aria kicks the enemy\n swiftly, dealing 4 dmg.\n Aria\'s partner, astonished by\n her skills, will gain\n 1 energy.";
        return skInfo;
    }
    
    @Override
    public string getUltimateAttackInfo(){
        String ultInfo = "Aria leaps into the sky,\n shooting her bow to the enemy,\n dealing 2 dmg. For the next\n 2 turns, Perfect Pitch dmg is \n increased by 3.";
        return ultInfo;
    }

    @Override
    public void normalAttack(Enemy enemy, agent partner){
        Int totalAttack = naDmg;
        if (enemy.isStun()){
            totalAttack += 3;
        }
        if (naBuffActive && naBuffDuration >= 1){
            totalAttack += 3;
            naBuffDuration -= 1;
        }
        if (naBuffDuration == 0) {
            naBuffActive = !naBuffActive;
            naBuffDuration = 2;
        }
        if (partner.getPartnerBuff() && partner.getPartnerBuffDuration() >= 1){
            totalAttack += partner.getPartnerBuffDmg();
            partner.getPartnerBuffDuration() -= 1;
        }

        enemy.takeDamage(totalAttack);
        
        gainEnergy();
    }
    
    @Override
    public void skillAttack(Enemy enemy, agent partner){
        Int totalAttack = skDmg;
        if (enemy.isStun()){
            totalAttack += 1;
        }

        enemy.takeDamage(totalAttack);

        gainEnergy();
        partner.gainEnergy();
    }
    
    @Override
    public void ultimateAttack(Enemy enemy, agent partner){
        Int totalAttack = ultDmg;
        
        if (enemy.isStun()){
            totalAttack += 1;
        }

        enemy.takeDamage(totalAttack);
        
        useEnergy();
        naBuffActive = !naBuffActive;
    }
}