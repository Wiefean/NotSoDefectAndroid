package notSoDefect.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import java.util.Iterator;

public class DarkOrbEvokeAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.1F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private boolean muteSfx = false;

    public DarkOrbEvokeAction(DamageInfo info, AbstractGameAction.AttackEffect effect) {
        AbstractMonster weakestMonster = null;

        Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();
        while(var1.hasNext()) {
            AbstractMonster m = (AbstractMonster)var1.next();
            if (!m.isDeadOrEscaped()) {
                 if (m.hasPower("Lockon")) {
                    weakestMonster = m;
                }
            }
        }

        if (weakestMonster == null) {
            Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();
            while (var2.hasNext()) {
                AbstractMonster m = (AbstractMonster) var2.next();
                if (!m.isDeadOrEscaped()) {
                    if (weakestMonster == null) {
                        weakestMonster = m;
                    } else if (m.currentHealth < weakestMonster.currentHealth) {
                        weakestMonster = m;
                    }
                }
            }
        }

        this.info = info;
        this.setValues(weakestMonster, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
        this.duration = 0.1F;
    }

    public void update() {
        if ((!this.shouldCancelAction() || this.info.type == DamageType.THORNS) && this.target != null) {
            if (this.duration == 0.1F) {
                this.info.output = AbstractOrb.applyLockOn(this.target, this.info.base);
                if (this.info.type != DamageType.THORNS && (this.info.owner.isDying || this.info.owner.halfDead)) {
                    this.isDone = true;
                    return;
                }

                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect, this.muteSfx));
            }

            this.tickDuration();
            if (this.isDone) {
                if (this.attackEffect == AttackEffect.POISON) {
                    this.target.tint.color = Color.CHARTREUSE.cpy();
                    this.target.tint.changeColor(Color.WHITE.cpy());
                } else if (this.attackEffect == AttackEffect.FIRE) {
                    this.target.tint.color = Color.RED.cpy();
                    this.target.tint.changeColor(Color.WHITE.cpy());
                }

                this.target.damage(this.info);
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }

                if (!Settings.FAST_MODE) {
                    this.addToTop(new WaitAction(0.1F));
                }
            }

        } else {
            this.isDone = true;
        }
    }
}
