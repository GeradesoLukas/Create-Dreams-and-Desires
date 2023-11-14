package uwu.lopyluna.create_dd.item.ItemProperties.milkshakes;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class HotChocolate extends Item {
    public HotChocolate(Properties pProperties) {
        super(pProperties);
    }

    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        Player playerentity = entity instanceof Player ? (Player) entity : null;
        if (playerentity instanceof ServerPlayer)
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) playerentity, stack);

        if (!world.isClientSide)
            entity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 60 * 20, 1, false, false, false));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 90 * 20, 1, false, false, false));
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 45 * 20, 1, false, false, false));
            entity.hurt(DamageSource.DRY_OUT, entity.getHealth() * 0.5f);

        if (playerentity != null) {
            playerentity.awardStat(Stats.ITEM_USED.get(this));
            playerentity.getFoodData().eat(2, 1.75F);
            stack.shrink(1);
        }

        if (stack.isEmpty())
            return new ItemStack(Items.GLASS_BOTTLE);
        if (!(stack.isEmpty())) {
            assert playerentity != null;
            playerentity.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
        }


        return stack;
    }

    public int getUseDuration(ItemStack p_77626_1_) {
        return 42;
    }

    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.DRINK;
    }

    public InteractionResultHolder<ItemStack> use(Level p_77659_1_, Player p_77659_2_, InteractionHand p_77659_3_) {
        p_77659_2_.startUsingItem(p_77659_3_);
        return InteractionResultHolder.success(p_77659_2_.getItemInHand(p_77659_3_));
    }

}
