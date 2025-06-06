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
import net.minecraft.util.*;
import java.util.List;

public class MagnetIngotItem extends class_1792 {
    public MagnetIngotItem(class_1793 settings) {
        super(settings);
    }

    // Called when the player right-clicks to use the item
    @Override
    public class_1271<class_1799> method_7836(class_1937 world, class_1657 user, class_1268 hand) {
        user.method_6019(hand); // Start using the item
        return class_1271.method_22428(user.method_5998(hand));
    }

    // Determines how long the item can be used — 200 ticks = 10 seconds

    public int getMaxUseTime(class_1799 stack) {
        return 200;
    }

    // Called every tick while the item is being used (right-click is held)
    @Override
    public void method_7852(class_1937 world, class_1309 user, class_1799 stack, int remainingUseTicks) {
        if (world.field_9236 || !(user instanceof class_1657 player)) return;

        int heldTicks = getMaxUseTime(stack) - remainingUseTicks;

        // Scale range and pull strength with how long it's held (clamped to max values)
        double range = Math.min(6.0 + heldTicks * 0.2, 20.0);        // Up to 20 block radius
        double pullStrength = Math.min(0.15 + heldTicks * 0.01, 1.0); // Up to 1.0 pull velocity

        // Find nearby dropped IRON_INGOT items
        List<class_1542> items = world.method_8390(
                class_1542.class,
                player.method_5829().method_1014(range),
                item -> item.method_5805() && isIronItem(item)


        );

        for (class_1542 item : items) {
            class_243 pullDirection = player.method_19538().method_1020(item.method_19538()).method_1029().method_1021(pullStrength);
            item.method_18799(item.method_18798().method_1019(pullDirection));
        }
    }

    // Optional: you can add effects when the player stops using the item
    @Override
    public void method_7840(class_1799 stack, class_1937 world, class_1309 user, int remainingUseTicks) {
        // Add optional burst, particles, or cooldown logic here
    }

    private static boolean isIronItem(class_1542 itemEntity) {
        String itemName = itemEntity.method_6983().method_7909().toString().toLowerCase();
        return itemName.contains("iron") || itemName.contains("magnet");
    }

}
