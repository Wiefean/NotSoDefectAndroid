package notSoDefect;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.android.mods.BaseMod;
import com.megacrit.cardcrawl.android.mods.interfaces.*;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.util.Iterator;
import com.megacrit.cardcrawl.localization.*;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.relicRng;

//注册核心类·
public class NotSoDefect implements EditStringsSubscriber, EditKeywordsSubscriber {
  public static final String MOD_ID = "NotSoDefect";
  public NotSoDefect() {
    BaseMod.subscribe((ISubscriber)this);
  }

  public static void initialize() {
    new NotSoDefect();
  }

  //加载文本
  public void receiveEditStrings() {
    String lang;
    if (Settings.language == Settings.GameLanguage.ZHS) {
      lang = "zhs";
    } else {
      lang = "eng";
    }
    BaseMod.loadCustomStringsFile(MOD_ID, CardStrings.class, "localization/" + lang + "/cards.json");
    BaseMod.loadCustomStringsFile(MOD_ID, PowerStrings.class, "localization/" + lang + "/powers.json");
  }

  //关键词
  public void receiveEditKeywords() {
    BaseMod.addKeyword(null, "跟踪锁定", new String[] {"跟踪锁定"}, "从充能球受到的伤害增加 #b50% 。 #y闪电 充能球和 #y黑暗 充能球在被 #y激发 时优先攻击该单位。");
    BaseMod.addKeyword(null, "Lock-On", new String[] {"lock-on"}, "Take #b50% more damage from Orbs. #yLightning and #y Dark orbs take priority on here when #yEvoked .");
  }

  //激发充能球触发
  public static void EvokeOrbEvent() {
    Iterator var2 = AbstractDungeon.player.relics.iterator();
    AbstractRelic r;

    while (var2.hasNext()) {
      r = (AbstractRelic)var2.next();

      //破损核心
      if (r.relicId.equals("Cracked Core")) {
        if (!r.grayscale) {
          r.flash();
          r.grayscale = true;
          AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 4));
        }
      }

      //情感芯片
      if (r.relicId.equals("Emotion Chip")) {
        if (relicRng.random(100) < 50) {
          r.flash();
          AbstractDungeon.actionManager.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
        }
      }

    }
  }

}

