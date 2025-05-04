package com.megacrit.cardcrawl.cards.blue;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import notSoDefect.NotSoDefect;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AutoShields extends AbstractCard {
    public static final String ID = "Auto Shields";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("NotSoDefect:AutoShields");;

    public AutoShields() {
        super(ID, cardStrings.NAME, "blue/skill/auto_shields", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            this.addToBot(new DrawCardAction(p, 1));
        }
    }

    public void triggerWhenDrawn() {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 11));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

    public AbstractCard makeCopy() {
        return new AutoShields();
    }

}