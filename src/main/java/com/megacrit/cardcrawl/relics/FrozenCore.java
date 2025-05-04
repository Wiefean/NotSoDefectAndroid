package com.megacrit.cardcrawl.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

import java.util.Iterator;

public class FrozenCore extends AbstractRelic {
    public static final String ID = "FrozenCore";

    public FrozenCore() {
        super("FrozenCore", "frozenOrb.png", RelicTier.BOSS, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        String add;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            add = " NL 回合开始时， 使用 #y冰霜 充能球的被动效果。";
        } else {
            add = " NL At the start of your turn, trigger the passive ability of #yFrost.";
        }
        return this.DESCRIPTIONS[0] + add;
    }

    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        boolean hasFrost = false;

        Iterator var1 = p.orbs.iterator();

        while(var1.hasNext()) {
            AbstractOrb o = (AbstractOrb)var1.next();
            if (o instanceof Frost) {
                o.onStartOfTurn();
                o.onEndOfTurn();
                hasFrost = true;
            }
        }

        if (p.hasRelic("Cables") && !(p.orbs.get(0) instanceof EmptyOrbSlot) && p.orbs.get(0) instanceof Frost) {
            ((AbstractOrb)p.orbs.get(0)).onStartOfTurn();
            ((AbstractOrb)p.orbs.get(0)).onEndOfTurn();
        }

        if (hasFrost) {
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(p, this));
        }
    }

    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.hasEmptyOrb()) {
            this.flash();
            AbstractDungeon.player.channelOrb(new Frost());
        }
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("Cracked Core");
    }

    public AbstractRelic makeCopy() {
        return new FrozenCore();
    }
}
