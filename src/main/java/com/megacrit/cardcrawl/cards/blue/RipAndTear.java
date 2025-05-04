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
import notSoDefect.actions.RipAndTearAction;


public class RipAndTear extends AbstractCard {
    public static final String ID = "Rip and Tear";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("NotSoDefect:RipAndTear");

    public RipAndTear(int upgrades) {
        super(ID, cardStrings.NAME, "blue/attack/rip_and_tear", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
    }

    public RipAndTear() {
        super(ID, cardStrings.NAME, "blue/attack/rip_and_tear", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = 0;
    }

    public void upgrade() {
        if (this.timesUpgraded % 2 == 0) {
            this.upgradeMagicNumber(1);
        }else{
            this.upgradeDamage(1);
        }
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    public void triggerWhenDrawn() {
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new RipAndTearAction(this));
        }
    }

    public boolean canUpgrade() {
        return true;
    }

    public AbstractCard makeCopy() {
        return new RipAndTear(this.timesUpgraded);
    }

}