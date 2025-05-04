package notSoDefect.powers;

import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NoBlockPower extends AbstractPower {
    public static final String POWER_ID = "NotSoDefect:NoBlock";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public NoBlockPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.type = PowerType.DEBUFF;
        this.owner = owner;
        this.updateDescription();
        this.loadRegion("noBlock");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atStartOfTurn() {
        if (this.owner != null && this.owner.currentBlock >0) {
            this.flash();
            this.addToBot(new RemoveAllBlockAction(this.owner, this.owner));
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (this.owner != null && this.owner.currentBlock >0) {
            this.flash();
            this.addToBot(new RemoveAllBlockAction(this.owner, this.owner));
        }
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (this.owner != null && this.owner.currentBlock > 0) {
            this.flash();
            this.addToBot(new RemoveAllBlockAction(this.owner, this.owner));
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (this.owner != null && this.owner.currentBlock > 0) {
            this.flash();
            this.addToBot(new RemoveAllBlockAction(this.owner, this.owner));
        }
    }


    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
