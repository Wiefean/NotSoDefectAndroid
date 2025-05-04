package notSoDefect.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.RipAndTearEffect;

public class RipAndTearAction extends AttackDamageRandomEnemyAction {
    public RipAndTearAction(AbstractCard card) {
        super(card);
    }

    public void update() {
        if (!Settings.FAST_MODE) {
            this.addToTop(new WaitAction(0.025F));
        }

        super.update();
        if (Settings.FAST_MODE) {
            this.addToTop(new WaitAction(0.005F));
        } else {
            this.addToTop(new WaitAction(0.01F));
        }

        if (this.target != null) {
            this.addToTop(new VFXAction(new RipAndTearEffect(this.target.hb.cX, this.target.hb.cY, Color.RED, Color.GOLD)));
        }

    }
}
