package com.megacrit.cardcrawl.cards.blue;

import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Dualcast extends AbstractCard {
    public static final String ID = "Dualcast";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("NotSoDefect:Dualcast");;

    public Dualcast() {
        super("Dualcast", cardStrings.NAME, "blue/skill/dualcast", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.BASIC, CardTarget.NONE);
        this.showEvokeValue = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AnimateOrbAction(1));
        this.addToBot(new EvokeWithoutRemovingOrbAction(1));
        this.addToBot(new AnimateOrbAction(1));
        this.addToBot(new EvokeOrbAction(1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

    public AbstractCard makeCopy() {
        return new Dualcast();
    }
}
