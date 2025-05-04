package com.megacrit.cardcrawl.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

import java.util.Iterator;

public class SymbioticVirus extends AbstractRelic {
    public static final String ID = "Symbiotic Virus";

    public SymbioticVirus() {
        super("Symbiotic Virus", "virus.png", RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        String add;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            add = "失去 #b1 个充能球栏位。 NL 回合开始时， #y激发 所有非 #y黑暗 充能球，并 #y生成 等量的 黑暗 充能球。 NL #y激发 #y黑暗 充能球时，获得 #b6 点 #y格挡 。";
        } else {
            add = "Lose #b1 Orb slot. NL At the start of your turn, #Evoke all non- #yDark Orbs, and #yChannel equal #yDark Orbs. NL When #yEvoking #yDark , gain #b6 #yBlock .";
        }
        return add;
    }

    public void atPreBattle() {
        AbstractDungeon.player.channelOrb(new Dark());
        this.addToBot(new DecreaseMaxOrbAction(1));
    }

    public void atTurnStartPostDraw() {
        AbstractPlayer p = AbstractDungeon.player;
        int transformed = 0;
        Iterator var1 = p.orbs.iterator();

        for (int i = 0; i < p.orbs.size(); ++i) {
            AbstractOrb o =  p.orbs.get(i);
            if (o != null && !(o instanceof EmptyOrbSlot) && !(o instanceof Dark)) {
                o.onEvoke();
                p.orbs.set(i, new EmptyOrbSlot());
                ++transformed;
            }
        }

        if (transformed > 0) {
            this.flash();
            for (int i = 0; i <transformed; ++i) {
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Dark()));
            }
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(p, this));
        }
    }

    public AbstractRelic makeCopy() {
        return new SymbioticVirus();
    }
}
