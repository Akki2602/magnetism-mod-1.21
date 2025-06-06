package net.akki.magnetismmod.item.custom;

import net.akki.magnetismmod.util.MagnetUseHandler;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.ItemEntity;
import java.util.List;

public class MagnetIngotItem extends Item {
    public MagnetIngotItem(Settings settings) {
        super(settings);
    }

    // Called when the player right-clicks to use the item
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand); // Start using the item
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    // Determines how long the item can be used — 200 ticks = 10 seconds
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 200;
    }

    // Called every tick while the item is being used (right-click is held)
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world.isClient || !(user instanceof PlayerEntity player)) return;

        int heldTicks = getMaxUseTime(stack) - remainingUseTicks;

        // Scale range and pull strength with how long it's held (clamped to max values)
        double range = Math.min(6.0 + heldTicks * 0.2, 20.0);         // Up to 20 block radius
        double pullStrength = Math.min(0.15 + heldTicks * 0.01, 1.0); // Up to 1.0 pull velocity

        // Find nearby dropped IRON_INGOT items
        List<ItemEntity> items = world.getEntitiesByClass(
                ItemEntity.class,
                player.getBoundingBox().expand(range),
                item -> item.isAlive() && isIronItem(item)
        );

        for (ItemEntity item : items) {
            Vec3d pullDirection = player.getPos().subtract(item.getPos()).normalize().multiply(pullStrength);
            item.setVelocity(item.getVelocity().add(pullDirection));
        }
    }

    // Optional: you can add effects when the player stops using the item
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        // Add optional burst, particles, or cooldown logic here
    }

    private static boolean isIronItem(ItemEntity itemEntity) {
        String itemName = itemEntity.getStack().getItem().toString().toLowerCase();
        return itemName.contains("iron") || itemName.contains("magnet");
    }
}