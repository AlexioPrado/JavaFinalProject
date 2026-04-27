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
    public String getNormalAttackInfo(){
        String naInfo = "Aria takes a leap and plunges down\n| toward the enemy, dealing 3 dmg.\n| If the enemy is  stunned, deal an\n| additonal 3 dmg.";
        return naInfo;
    }

    @Override
    public String getSkillAttackInfo(){
        String skInfo = "Aria kicks the enemy swiftly, dealing\n| 4 dmg. Aria\\'s partner, astonished\n| by her skills, will gain\n| 1 energy.";
        return skInfo;
    }
    
    @Override
    public String getUltimateAttackInfo(){
        String ultInfo = "Aria leaps into the sky, shooting \n| her bow to the enemy, dealing\n| 2 dmg. For the next 2 turns,\n| Perfect Pitch dmg is increased by 3.";
        return ultInfo;
    }

    //@Override
    //public void normalAttack(Enemy enemy){
    //    int totalAttack = naDmg;
    //    if (enemy.isStun()){
    //        totalAttack += 3;
    //    }
    //    if (naBuffActive && naBuffDuration >= 1){
    //        totalAttack += 3;
    //        naBuffDuration -= 1;
    //    }
    //    if (naBuffDuration == 0) {
    //        naBuffActive = !naBuffActive;
    //        naBuffDuration = 2;
    //    }
//
    //    enemy.takeDamage(totalAttack);
        
    //    gainEnergy();
    //}
    
    //@Override
    //public void skillAttack(Enemy enemy){
    //    int totalAttack = skDmg;
    //    if (enemy.isStun()){
    //        totalAttack += 1;
    //    }
//
    //    enemy.takeDamage(totalAttack);
//
    //    gainEnergy();
    //    //partner.gainEnergy();
    //}
    
    //@Override
    //public void ultimateAttack(Enemy enemy){
    //    int totalAttack = ultDmg;
    //    
    //    if (enemy.isStun()){
    //        totalAttack += 1;
    //    }
    //
    //    enemy.takeDamage(totalAttack);
    //    
    //    useEnergy();
    //    naBuffActive = !naBuffActive;
    //}
}