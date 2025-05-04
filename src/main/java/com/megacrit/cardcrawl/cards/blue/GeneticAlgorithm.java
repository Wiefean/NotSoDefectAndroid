package com.megacrit.cardcrawl.cards.blue;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GeneticAlgorithm extends AbstractCard {
    public static final String ID = "Genetic Algorithm";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("NotSoDefect:GeneticAlgorithm");;

    public GeneticAlgorithm() {
        super("Genetic Algorithm", cardStrings.NAME, "blue/skill/genetic_algorithm", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.misc = 1;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = this.misc;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new IncreaseMiscAction(this.uuid, this.misc, this.magicNumber));
        this.addToBot(new GainBlockAction(p, p, this.block));
    }

    public void applyPowers() {
        this.baseBlock = this.misc;
        super.applyPowers();
        this.initializeDescription();
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
        return new GeneticAlgorithm();
    }
}
