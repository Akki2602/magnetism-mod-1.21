package net.akki.magnetismmod.item.custom;

import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

public class EntityMagnetIngotItem extends class_1792 {
    public EntityMagnetIngotItem(class_1793 settings) {
        super(settings);
    }

    @Override
    public void method_7888(class_1799 stack, class_1937 world, net.minecraft.class_1297 entity, int slot, boolean selected) {
        if (world.field_9236 || !(entity instanceof class_1657 player)) return;
        if (!selected) return;

        double range = 10.0;
        double pullStrength = 0.3;

        List<class_1309> targets = world.method_8390(class_1309.class,
                player.method_5829().method_1014(range),
                e -> e.method_5805() && !e.method_7325() && e != player);

        for (class_1309 target : targets) {
            class_243 direction = player.method_19538().method_1020(target.method_19538());
            if (direction.method_1027() == 0) continue;
            class_243 pull = direction.method_1029().method_1021(pullStrength);
            target.method_18799(target.method_18798().method_1019(pull));
            target.field_6037 = true;
        }
    }
}
