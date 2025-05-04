package com.megacrit.cardcrawl.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

public class CrackedCore extends AbstractRelic {
    public static final String ID = "Cracked Core";

    public CrackedCore() {
        super("Cracked Core", "crackedOrb.png", RelicTier.STARTER, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        String add;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            add = " NL 每回合首次 #y激发 充能球时，获得 #b4 点 #y格挡 。";
        } else {
            add = " NL For each turn, when #yEvoking Orbs for the first time, gain #b4 #yBlock.";
        }
        return this.DESCRIPTIONS[0] + add;
    }

    public void atPreBattle() {
        this.grayscale = false;
        AbstractDungeon.player.channelOrb(new Lightning());
    }

    public void onPlayerEndTurn() {
        this.grayscale = false;
    }

    public AbstractRelic makeCopy() {
        return new CrackedCore();
    }
}
