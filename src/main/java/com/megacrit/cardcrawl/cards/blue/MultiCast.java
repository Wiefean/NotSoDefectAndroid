package com.megacrit.cardcrawl.cards.blue;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import notSoDefect.actions.MulticastAction;

public class MultiCast extends AbstractCard {
    public static final String ID = "Multi-Cast";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("NotSoDefect:MultiCast");

    public MultiCast() {
        super(ID, cardStrings.NAME, "blue/skill/multicast", -1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.RARE, CardTarget.NONE);
        this.showEvokeValue = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MulticastAction(p, this.energyOnUse, this.upgraded));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

    public AbstractCard makeCopy() {
        return new MultiCast();
    }
}
