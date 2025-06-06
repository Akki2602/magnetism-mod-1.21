package net.akki.magnetismmod.item.custom;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

public class RepulsionIngotItem extends class_1792 {
    public RepulsionIngotItem(class_1793 settings) {
        super(settings);
    }

    @Override
    public void method_7888(class_1799 stack, class_1937 world, class_1297 entity, int slot, boolean selected) {
        if (!world.field_9236 && entity instanceof class_1657 player && selected) {
            double range = 6.0;
            double pushStrength = 0.4;

            List<class_1297> entities = world.method_8333(player, player.method_5829().method_1014(range), e -> e.method_5805() && e != player);

            for (class_1297 target : entities) {
                class_243 direction = target.method_19538().method_1020(player.method_19538());
                if (direction.method_1027() == 0) continue;

                class_243 pushVector = direction.method_1029().method_1021(pushStrength);
                target.method_18799(target.method_18798().method_1019(pushVector));
                target.field_6037 = true;
            }
        }
    }
}
