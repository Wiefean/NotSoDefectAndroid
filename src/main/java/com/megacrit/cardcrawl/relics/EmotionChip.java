package com.megacrit.cardcrawl.relics;

import com.megacrit.cardcrawl.actions.defect.ImpulseAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

public class EmotionChip extends AbstractRelic {
    public static final String ID = "Emotion Chip";

    public EmotionChip() {
        super("Emotion Chip", "emotionChip.png", RelicTier.RARE, LandingSound.CLINK);
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        String add;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            add = " NL #y激发 充能球时，有 #b50% 概率回复 #b1 点生命。";
        } else {
            add = " NL When #Evoking a Orb, #b50% chance to heal #b1 HP.";
        }
        return  this.DESCRIPTIONS[0] + add;
    }

    public void atTurnStart() {
        if (this.pulse) {
            this.pulse = false;
            this.flash();
            this.addToBot(new ImpulseAction());
        }

    }

    public void wasHPLost(int damageAmount) {
        if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && damageAmount > 0) {
            this.flash();
            if (!this.pulse) {
                this.beginPulse();
                this.pulse = true;
            }
        }

    }

    public void onVictory() {
        this.pulse = false;
    }

    public AbstractRelic makeCopy() {
        return new EmotionChip();
    }
}
