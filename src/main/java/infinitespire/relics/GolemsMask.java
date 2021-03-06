package infinitespire.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import infinitespire.InfiniteSpire;
import infinitespire.abstracts.Relic;
import infinitespire.powers.GolemPower;

public class GolemsMask extends Relic{
	public static final String ID = InfiniteSpire.createID("Golems Mask");
	public static final String NAME = "Golem's Mask";
	
	private boolean isFirstTurn;
	
	public GolemsMask() {
		super(ID, "golemsstart", RelicTier.RARE, LandingSound.SOLID);
	}

	@Override
	public AbstractRelic makeCopy() {
		return new GolemsMask();
	}
	
	@Override
	public void atBattleStart() {
		isFirstTurn = true;
	}

	@Override
	public void atTurnStart() {
		if(isFirstTurn) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GolemPower(AbstractDungeon.player)));
			isFirstTurn = false;
		}
		
		this.flash();
	}
	
	
}
