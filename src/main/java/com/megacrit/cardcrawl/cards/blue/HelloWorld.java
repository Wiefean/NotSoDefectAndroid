package com.megacrit.cardcrawl.cards.blue;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import notSoDefect.NotSoDefect;
import notSoDefect.powers.HelloPower;

public class HelloWorld extends AbstractCard {
  public static final String ID = "Hello World";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("NotSoDefect:HelloWorld");
  
  public HelloWorld() {
    super(ID, cardStrings.NAME, "blue/power/hello_world", 1, cardStrings.DESCRIPTION, CardType.POWER, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    this.addToBot(new ApplyPowerAction(p, p, new HelloPower(p, 1), 1));
  }

  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.isInnate = true;
      this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
      this.initializeDescription();
    }
  }
  
  public AbstractCard makeCopy() {
    return new HelloWorld();
  }
}
