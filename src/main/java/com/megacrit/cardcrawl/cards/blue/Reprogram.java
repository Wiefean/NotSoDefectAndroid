package com.megacrit.cardcrawl.cards.blue;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import notSoDefect.NotSoDefect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Reprogram extends AbstractCard {
  public static final String ID = "Reprogram";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("NotSoDefect:Reprogram");;

  public Reprogram() {
    super(ID, cardStrings.NAME, "blue/skill/reprogram", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
    this.baseMagicNumber = 1;
    this.magicNumber = this.baseMagicNumber;
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (p.hasPower("Focus")) {
      int focus = (p.getPower("Focus")).amount;

      this.addToBot(new ApplyPowerAction(p, p, new FocusPower(p, -focus), -focus));

      if (focus < 0){
        focus = -focus;
      }

      this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, focus), focus));
      this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, focus), focus));
    }else{
      this.addToBot(new ApplyPowerAction(p, p, new FocusPower(p, this.magicNumber), this.magicNumber));
    }
  }

  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeMagicNumber(1);
      this.cost = 1;
      this.costForTurn = 1;
      this.upgradedCost = true;
      this.exhaust = false;
      this.selfRetain = true;
      this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
      this.initializeDescription();
    }

  }

  public AbstractCard makeCopy() {
    return new Reprogram();
  }

}