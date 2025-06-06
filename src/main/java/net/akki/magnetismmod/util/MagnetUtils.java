package net.akki.magnetismmod.util;

import java.util.List;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.Identifier;

public class MagnetUtils {

    public static void attractItems(World world, BlockPos pos, double range, double strength) {
        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class,
                new Box(pos).expand(range),
                ItemEntity::isAlive);

        for (ItemEntity item : items) {
            Vec3d pull = Vec3d.ofCenter(pos).subtract(item.getPos()).normalize().multiply(strength);
            item.setVelocity(item.getVelocity().add(pull));
            item.velocityModified = true;
        }
    }
}