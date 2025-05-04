package notSoDefect.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MulticastAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int energyOnUse = -1;
    private boolean upgraded;

    public MulticastAction(AbstractPlayer p, int energyOnUse, boolean upgraded) {
        this.p = p;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
    }

    public void update() {
        if (AbstractDungeon.player.hasOrb()) {
            int effect = EnergyPanel.totalCount;
            if (this.energyOnUse != -1) {
                effect = this.energyOnUse;
            }

            if (this.p.hasRelic("Chemical X")) {
                effect += 2;
                this.p.getRelic("Chemical X").flash();
            }

            if (effect > 0) {
                for(int i = 0; i < effect - 1; ++i) {
                    this.addToBot(new EvokeWithoutRemovingOrbAction(1));
                }

                this.addToBot(new AnimateOrbAction(1));
                this.addToBot(new EvokeOrbAction(1));

                this.p.energy.use(EnergyPanel.totalCount);

                int e = effect - 2;
                if (this.upgraded) {
                    ++e;
                }
                if (e > 0) {
                    this.addToBot(new GainEnergyAction(e));
                }
            }
        }

        this.isDone = true;
    }
}
