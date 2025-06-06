package net.akki.magnetismmod.util;

import net.akki.magnetismmod.block.ModBlocks;
import net.akki.magnetismmod.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import java.util.*;

public class MagnetUseHandler {
    private static final int MAX_CHARGE = 200;
    private static final Map<UUID, Integer> playerChargeMap = new HashMap<>();

    public static void register() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            for (class_1657 player : server.method_3760().method_14571()) {
                class_1799 activeItem = player.method_6030();

                if (player.method_6115()) {
                    UUID uuid = player.method_5667();
                    int charge = playerChargeMap.getOrDefault(uuid, 0);
                    if (charge < MAX_CHARGE) {
                        charge++;
                        playerChargeMap.put(uuid, charge);
                    }

                    if (activeItem.method_7909() == ModItems.Magnet_Ingot) {
                        applyItemAttraction(player, charge);
                    } else if (activeItem.method_7909() == ModItems.Repulsion_Ingot) {
                        applyItemRepulsion(player, charge);
                    } else if (activeItem.method_7909() == ModItems.Entity_Magnet_Ingot) {
                        applyEntityAttraction(player, charge);
                    }
                } else {
                    playerChargeMap.remove(player.method_5667());
                }

                // Electromagnet block effect near the player
            }

        });
    }

    private static void applyItemAttraction(class_1657 player, int charge) {
        class_1937 world = player.method_37908();
        double range = Math.min(6.0 + charge * 0.2, 20.0);
        double strength = Math.min(0.15 + charge * 0.01, 1.0);

        List<class_1542> items = world.method_8390(class_1542.class,
                player.method_5829().method_1014(range),
                item -> item.method_5805() && isIronItem(item));

        for (class_1542 item : items) {
            class_243 pull = player.method_19538().method_1020(item.method_19538()).method_1029().method_1021(strength);
            item.method_18799(item.method_18798().method_1019(pull));
            item.field_6037 = true;
        }
    }

    private static void applyItemRepulsion(class_1657 player, int charge) {
        class_1937 world = player.method_37908();
        double range = Math.min(6.0 + charge * 0.2, 20.0);
        double strength = Math.min(0.15 + charge * 0.01, 1.0);

        List<class_1542> items = world.method_8390(class_1542.class,
                player.method_5829().method_1014(range),
                item -> item.method_5805() && isIronItem(item));

        for (class_1542 item : items) {
            class_243 push = item.method_19538().method_1020(player.method_19538()).method_1029().method_1021(strength);
            item.method_18799(item.method_18798().method_1019(push));
            item.field_6037 = true;
        }
    }

    private static void applyEntityAttraction(class_1657 player, int charge) {
        class_1937 world = player.method_37908();
        double range = Math.min(6.0 + charge * 0.2, 20.0);
        double strength = Math.min(0.15 + charge * 0.01, 1.0);

        List<class_1309> entities = world.method_8390(class_1309.class,
                player.method_5829().method_1014(range),
                entity -> entity.method_5805() && !entity.equals(player));

        for (class_1309 entity : entities) {
            class_243 pull = player.method_19538().method_1020(entity.method_19538()).method_1029().method_1021(strength);
            entity.method_18799(entity.method_18798().method_1019(pull));
            entity.field_6037 = true;
        }
    }




    private static void pullItemsToPos(class_1937 world, class_2338 pos, double range, double strength) {
        class_243 magnetCenter = class_243.method_24953(pos);
        List<class_1542> items = world.method_8390(class_1542.class,
                new net.minecraft.class_238(pos).method_1014(range),
                item -> item.method_5805());

        for (class_1542 item : items) {
            class_243 direction = magnetCenter.method_1020(item.method_19538());
            if (direction.method_1027() == 0) continue;
            class_243 pullVec = direction.method_1029().method_1021(strength);
            item.method_18799(item.method_18798().method_1019(pullVec));
            item.field_6037 = true;
        }
    }


    private static boolean isIronItem(class_1542 itemEntity) {
        String itemName = itemEntity.method_6983().method_7909().toString().toLowerCase();
        return itemName.contains("iron") || itemName.contains("magnet");
    }

    public static int getCharge(class_1657 player) {
        return playerChargeMap.getOrDefault(player.method_5667(), 0);
    }
}
