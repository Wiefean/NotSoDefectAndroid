package com.megacrit.cardcrawl.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.ElectroPower;

public class Electrodynamics extends AbstractCard {
    public static final String ID = "Electrodynamics";
    private static final CardStrings cardStrings;

    public Electrodynamics() {
        super("Electrodynamics", cardStrings.NAME, "blue/power/electrodynamics", 2, cardStrings.DESCRIPTION, CardType.POWER, CardColor.BLUE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower("Electrodynamics")) {
            this.addToBot(new ApplyPowerAction(p, p, new ElectroPower(p)));
        }

        for(int i = 0; i < this.magicNumber; ++i) {
            AbstractOrb orb = new Lightning();
            this.addToBot(new ChannelAction(orb));
        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }

    }

    public AbstractCard makeCopy() {
        return new Electrodynamics();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Electrodynamics");
    }
}