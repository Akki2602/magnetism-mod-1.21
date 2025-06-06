package net.akki.magnetismmod.item.custom;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;

public class EntityMagnetIngotItem extends Item {
    public EntityMagnetIngotItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient() || !(entity instanceof PlayerEntity player)) return;
        if (!selected) return;

        double range = 10.0;
        double pullStrength = 0.3;

        List<LivingEntity> targets = world.getEntitiesByClass(LivingEntity.class,
                player.getBoundingBox().expand(range),
                e -> e.isAlive() && !e.isSpectator() && e != player);

        for (LivingEntity target : targets) {
            Vec3d direction = player.getPos().subtract(target.getPos());
            if (direction.lengthSquared() == 0) continue;
            Vec3d pull = direction.normalize().multiply(pullStrength);
            target.setVelocity(target.getVelocity().add(pull));
            target.velocityModified = true;
        }
    }
}