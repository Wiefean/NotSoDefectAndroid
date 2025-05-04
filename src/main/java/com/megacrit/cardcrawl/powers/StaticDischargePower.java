package com.megacrit.cardcrawl.powers;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.Lightning;
import notSoDefect.actions.TriggerLightningPassive;

public class StaticDischargePower extends AbstractPower {
    public static final String POWER_ID = "StaticDischarge";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("NotSoDefect:StaticDischarge");;

    public StaticDischargePower(AbstractCreature owner, int lightningAmount) {
        this.name = powerStrings.NAME;
        this.ID = "StaticDischarge";
        this.owner = owner;
        this.amount = lightningAmount;
        this.updateDescription();
        this.loadRegion("static_discharge");
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != this.owner) {
            this.flash();
           this.addToBot(new TriggerLightningPassive());
        }

        if (info.type != DamageType.THORNS && info.type != DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {
            this.flash();

            for(int i = 0; i < this.amount; ++i) {
                this.addToTop(new ChannelAction(new Lightning()));
            }
        }

        return damageAmount;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}