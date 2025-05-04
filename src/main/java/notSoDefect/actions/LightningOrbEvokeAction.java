package notSoDefect.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import java.util.Iterator;

public class LightningOrbEvokeAction extends AbstractGameAction {
    private DamageInfo info;
    private boolean hitAll;

    public LightningOrbEvokeAction(DamageInfo info, boolean hitAll) {
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.NONE;
        this.hitAll = hitAll;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (p.hasPower("NotSoDefect:Storm")) {
            int b = p.getPower("NotSoDefect:Storm").amount;
            if (b > 0) {
                this.addToBot(new GainBlockAction(p, p, b));
            }
            this.addToBot(new DrawCardAction(1));
        }

        if (!this.hitAll) {
            AbstractMonster m = null;
            Iterator var4 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var4.hasNext() && m == null) {
                AbstractMonster m2 = (AbstractMonster)var4.next();
                if (!m2.isDeadOrEscaped()) {
                    if (m2.hasPower("Lockon")) {
                        m = m2;
                    }
                }
            }

            if (m == null) {
                m = AbstractDungeon.getRandomMonster();
            }

            if (m != null) {
                float speedTime = 0.1F;
                if (!AbstractDungeon.player.orbs.isEmpty()) {
                    speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
                }

                if (Settings.FAST_MODE) {
                    speedTime = 0.0F;
                }

                this.info.output = AbstractOrb.applyLockOn(m, this.info.base);
                this.addToTop(new DamageAction(m, this.info, AttackEffect.NONE, true));
                this.addToTop(new VFXAction(new LightningEffect(m.drawX, m.drawY), speedTime));
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
            }
        } else {
            float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
            if (Settings.FAST_MODE) {
                speedTime = 0.0F;
            }

            this.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.info.base, true, true), DamageType.THORNS, AttackEffect.NONE));
            Iterator var5 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var5.hasNext()) {
                AbstractMonster m3 = (AbstractMonster)var5.next();
                if (!m3.isDeadOrEscaped() && !m3.halfDead) {
                    this.addToTop(new VFXAction(new LightningEffect(m3.drawX, m3.drawY), speedTime));
                }
            }

            this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
        }

        this.isDone = true;
    }
}
