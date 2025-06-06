package net.akki.magnetismmod.util;

import net.akki.magnetismmod.block.ModBlocks;
import net.akki.magnetismmod.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;

public class MagnetUseHandler {
    private static final int MAX_CHARGE = 200;
    private static final Map<UUID, Integer> playerChargeMap = new HashMap<>();

    public static void register() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                ItemStack activeItem = player.getActiveItem();

                if (player.isUsingItem()) {
                    UUID uuid = player.getUuid();
                    int charge = playerChargeMap.getOrDefault(uuid, 0);
                    if (charge < MAX_CHARGE) {
                        charge++;
                        playerChargeMap.put(uuid, charge);
                    }

                    if (activeItem.getItem() == ModItems.Magnet_Ingot) {
                        applyItemAttraction(player, charge);
                    } else if (activeItem.getItem() == ModItems.Repulsion_Ingot) {
                        applyItemRepulsion(player, charge);
                    } else if (activeItem.getItem() == ModItems.Entity_Magnet_Ingot) {
                        applyEntityAttraction(player, charge);
                    }
                } else {
                    playerChargeMap.remove(player.getUuid());
                }

                // Add block magnetism effect logic here if needed.
            }
        });
    }

    private static void applyItemAttraction(ServerPlayerEntity player, int charge) {
        World world = player.getWorld();
        double range = Math.min(6.0 + charge * 0.2, 20.0);
        double strength = Math.min(0.15 + charge * 0.01, 1.0);

        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class,
                player.getBoundingBox().expand(range),
                item -> item.isAlive() && isIronItem(item));

        for (ItemEntity item : items) {
            Vec3d pull = player.getPos().subtract(item.getPos()).normalize().multiply(strength);
            item.setVelocity(item.getVelocity().add(pull));
            item.velocityModified = true;
        }
    }

    private static void applyItemRepulsion(ServerPlayerEntity player, int charge) {
        World world = player.getWorld();
        double range = Math.min(6.0 + charge * 0.2, 20.0);
        double strength = Math.min(0.15 + charge * 0.01, 1.0);

        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class,
                player.getBoundingBox().expand(range),
                item -> item.isAlive() && isIronItem(item));

        for (ItemEntity item : items) {
            Vec3d push = item.getPos().subtract(player.getPos()).normalize().multiply(strength);
            item.setVelocity(item.getVelocity().add(push));
            item.velocityModified = true;
        }
    }

    private static void applyEntityAttraction(ServerPlayerEntity player, int charge) {
        World world = player.getWorld();
        double range = Math.min(6.0 + charge * 0.2, 20.0);
        double strength = Math.min(0.15 + charge * 0.01, 1.0);

        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class,
                player.getBoundingBox().expand(range),
                entity -> entity.isAlive() && !entity.equals(player));

        for (LivingEntity entity : entities) {
            Vec3d pull = player.getPos().subtract(entity.getPos()).normalize().multiply(strength);
            entity.setVelocity(entity.getVelocity().add(pull));
            entity.velocityModified = true;
        }
    }

    private static void pullItemsToPos(World world, BlockPos pos, double range, double strength) {
        Vec3d magnetCenter = Vec3d.ofCenter(pos);
        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class,
                new Box(pos).expand(range),
                ItemEntity::isAlive);

        for (ItemEntity item : items) {
            Vec3d direction = magnetCenter.subtract(item.getPos());
            if (direction.lengthSquared() == 0) continue;
            Vec3d pullVec = direction.normalize().multiply(strength);
            item.setVelocity(item.getVelocity().add(pullVec));
            item.velocityModified = true;
        }
    }

    private static boolean isIronItem(ItemEntity itemEntity) {
        String itemName = itemEntity.getStack().getItem().toString().toLowerCase();
        return itemName.contains("iron") || itemName.contains("magnet");
    }

    public static int getCharge(ServerPlayerEntity player) {
        return playerChargeMap.getOrDefault(player.getUuid(), 0);
    }
}