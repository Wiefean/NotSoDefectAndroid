package notSoDefect.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustStatusAction extends AbstractGameAction {
    public ExhaustStatusAction() {
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractPlayer p = AbstractDungeon.player;
            int i = 0;
            AbstractCard c;

            //抽牌堆
            while(i < p.drawPile.size()) {
                c = (AbstractCard)p.drawPile.group.get(i);
                if (c.type == AbstractCard.CardType.STATUS) {
                    p.drawPile.removeCard(c);
                    p.limbo.addToTop(c);
                    c.targetDrawScale = 0.5F;
                    c.setAngle(0.0F, true);
                    c.target_x = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_WIDTH, (float)Settings.WIDTH - AbstractCard.IMG_WIDTH);
                    c.target_y = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_HEIGHT, (float)Settings.HEIGHT - AbstractCard.IMG_HEIGHT);
                    this.addToBot(new ExhaustSpecificCardAction(c, p.limbo));
                    this.addToBot(new WaitAction(0.005F));
                } else {
                    ++i;
                }
            }

            i = 0;

            //弃牌堆
            while(i < p.discardPile.size()) {
                c = (AbstractCard)p.discardPile.group.get(i);
                if (c.type == AbstractCard.CardType.STATUS) {
                    p.discardPile.removeCard(c);
                    p.limbo.addToTop(c);
                    c.targetDrawScale = 0.5F;
                    c.setAngle(0.0F, true);
                    c.target_x = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_WIDTH, (float)Settings.WIDTH - AbstractCard.IMG_WIDTH);
                    c.target_y = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_HEIGHT, (float)Settings.HEIGHT - AbstractCard.IMG_HEIGHT);
                    this.addToBot(new ExhaustSpecificCardAction(c, p.limbo));
                    this.addToBot(new WaitAction(0.005F));
                } else {
                    ++i;
                }
            }

            i = 0;

            //手牌
            while(i < p.hand.size()) {
                c = (AbstractCard)p.hand.group.get(i);
                if (c.type == AbstractCard.CardType.STATUS) {
                    p.hand.removeCard(c);
                    p.limbo.addToTop(c);
                    c.targetDrawScale = 0.5F;
                    c.setAngle(0.0F, true);
                    c.target_x = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_WIDTH, (float)Settings.WIDTH - AbstractCard.IMG_WIDTH);
                    c.target_y = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_HEIGHT, (float)Settings.HEIGHT - AbstractCard.IMG_HEIGHT);
                    this.addToBot(new ExhaustSpecificCardAction(c, p.limbo));
                    this.addToBot(new WaitAction(0.005F));
                } else {
                    ++i;
                }
            }

            this.isDone = true;
        }
    }
}

