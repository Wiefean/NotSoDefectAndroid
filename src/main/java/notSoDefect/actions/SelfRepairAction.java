package notSoDefect.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class SelfRepairAction extends AbstractGameAction {
    private boolean shouldDraw;
    private AbstractPlayer p;

    public SelfRepairAction(boolean shouldDraw) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.shouldDraw = shouldDraw;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.group.size() <= 0) {
                this.isDone = true;
            } else {
                CardGroup cards = new CardGroup(CardGroupType.UNSPECIFIED);
                Iterator var2 = this.p.hand.group.iterator();

                while(var2.hasNext()) {
                    AbstractCard c = (AbstractCard)var2.next();
                    if (c.type == CardType.STATUS || c.type == CardType.CURSE) {
                        cards.addToTop(c);
                    }
                }

                if (cards.size() > 0) {
                    cards.shuffle();
                    AbstractCard card = ((AbstractCard)cards.group.get(0));
                    this.addToTop(new ExhaustSpecificCardAction(card, this.p.hand));

                    if (this.shouldDraw) {
                        this.addToBot(new DrawCardAction(1));
                    }
                }

                this.isDone = true;
            }
        } else {
            this.tickDuration();
        }
    }
}

