package net.akki.magnetismmod.util;

import java.util.List;
import net.minecraft.class_1542;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;

public class MagnetLogic {

    public static void pullIronItems(class_1937 world, class_2338 magnetPos, double range, double stepDistance) {
        List<class_1542> items = world.method_8390(class_1542.class,
                new net.minecraft.class_238(magnetPos).method_1014(range),
                item -> item.method_5805() && item.method_6983().method_7909() == class_1802.field_8620);

        class_243 center = class_243.method_24953(magnetPos);

        for (class_1542 item : items) {
            class_243 itemPos = item.method_19538();
            class_243 direction = center.method_1020(itemPos);
            double distance = direction.method_1033();

            // Stop moving if already very close
            if (distance < 0.1) continue;

            class_243 moveStep = direction.method_1029().method_1021(stepDistance);
            class_243 newPos = itemPos.method_1019(moveStep);
            item.method_30634(newPos.field_1352, newPos.field_1351, newPos.field_1350);
        }
    }
}
