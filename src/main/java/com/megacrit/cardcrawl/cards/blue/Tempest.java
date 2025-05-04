package com.megacrit.cardcrawl.cards.blue;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import notSoDefect.actions.TempestAction;

public class Tempest extends AbstractCard {
    public static final String ID = "Tempest";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("NotSoDefect:Tempest");

    public Tempest() {
        super(ID, cardStrings.NAME, "blue/skill/tempest", -1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 3;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TempestAction(p, this.energyOnUse));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.exhaust = false;
            this.initializeDescription();
        }

    }

    public AbstractCard makeCopy() {
        return new Tempest();
    }
}