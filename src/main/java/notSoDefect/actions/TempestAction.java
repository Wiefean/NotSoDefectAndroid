package notSoDefect.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class TempestAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int energyOnUse = -1;

    public TempestAction(AbstractPlayer p, int energyOnUse) {
        this.p = p;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = 2 * this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for(int i = 0; i < effect; ++i) {
                AbstractOrb orb = new Lightning();
                this.addToBot(new ChannelAction(orb));
            }

            this.p.energy.use(EnergyPanel.totalCount);
        }

        this.isDone = true;
    }
}