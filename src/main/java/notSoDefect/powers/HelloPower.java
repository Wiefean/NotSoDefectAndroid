package notSoDefect.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import notSoDefect.actions.MakeTempCardInHandAction;

public class HelloPower extends AbstractPower {
    public static final String POWER_ID = "NotSoDefect:Hello";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public HelloPower(AbstractCreature owner, int cardAmt) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = cardAmt;
        this.updateDescription();
        this.loadRegion("hello");
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();

            for(int i = 0; i < this.amount; ++i) {
                AbstractCard card = AbstractDungeon.getCard(CardRarity.COMMON, AbstractDungeon.cardRandomRng).makeCopy();
                card.upgrade();

                if (!card.isEthereal) {
                    String ethereal;
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        ethereal = " 虚无 。";
                    } else {
                        ethereal = " Ethereal .";
                    }
                    card.rawDescription = ethereal + " NL " + card.rawDescription;
                    card.initializeDescription();
                }

                card.isEthereal = true;
                this.addToBot(new MakeTempCardInHandAction(card));
            }
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
