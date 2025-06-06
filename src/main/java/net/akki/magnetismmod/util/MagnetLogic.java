package net.akki.magnetismmod.util;

import java.util.List;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MagnetLogic {

    public static void pullIronItems(World world, BlockPos magnetPos, double range, double stepDistance) {
        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class,
                new Box(magnetPos).expand(range),
                item -> item.isAlive() && item.getStack().getItem() == Items.IRON_INGOT);

        Vec3d center = Vec3d.ofCenter(magnetPos);

        for (ItemEntity item : items) {
            Vec3d itemPos = item.getPos();
            Vec3d direction = center.subtract(itemPos);
            double distance = direction.length();

            // Stop moving if already very close
            if (distance < 0.1) continue;

            Vec3d moveStep = direction.normalize().multiply(stepDistance);
            Vec3d newPos = itemPos.add(moveStep);
            item.updatePosition(newPos.x, newPos.y, newPos.z);
        }
    }
}