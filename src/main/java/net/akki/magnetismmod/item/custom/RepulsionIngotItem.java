package net.akki.magnetismmod.item.custom;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.Identifier;

public class RepulsionIngotItem extends Item {
    public RepulsionIngotItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player && selected) {
            double range = 6.0;
            double pushStrength = 0.4;

            List<Entity> entities = world.getOtherEntities(
                    player,
                    player.getBoundingBox().expand(range),
                    e -> e.isAlive() && e != player
            );

            for (Entity target : entities) {
                Vec3d direction = target.getPos().subtract(player.getPos());
                if (direction.lengthSquared() == 0) continue;

                Vec3d pushVector = direction.normalize().multiply(pushStrength);
                target.setVelocity(target.getVelocity().add(pushVector));
                target.velocityModified = true; // forces client to update velocity
            }
        }
    }
}