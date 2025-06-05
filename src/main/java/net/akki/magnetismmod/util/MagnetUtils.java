package net.akki.magnetismmod.util;

import java.util.List;
import net.minecraft.class_1542;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;

public class MagnetUtils {
    public static void attractItems(class_1937 world, class_2338 pos, double range, double strength) {
        List<class_1542> items = world.method_8390(class_1542.class,
                new net.minecraft.class_238(pos).method_1014(range),
                item -> item.method_5805());

        for (class_1542 item : items) {
            class_243 pull = class_243.method_24953(pos).method_1020(item.method_19538()).method_1029().method_1021(strength);
            item.method_18799(item.method_18798().method_1019(pull));
            item.field_6037 = true;
        }
    }
}
