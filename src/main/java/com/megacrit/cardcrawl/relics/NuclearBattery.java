package com.megacrit.cardcrawl.relics;

import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Plasma;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

public class NuclearBattery extends AbstractRelic {
    public static final String ID = "Nuclear Battery";

    public NuclearBattery() {
        super("Nuclear Battery", "battery.png", RelicTier.BOSS, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        String add;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            add = " NL 获得 #b1 个充能球栏位。";
        } else {
            add = " NL Gain #b1 Orb slot.";
        }
        return  this.DESCRIPTIONS[0] + add;
    }

    public void atPreBattle() {
        AbstractDungeon.player.channelOrb(new Plasma());
        this.addToBot(new IncreaseMaxOrbAction(1));
    }

    public AbstractRelic makeCopy() {
        return new NuclearBattery();
    }
}
