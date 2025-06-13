package dev.onechris.cdnpackloader;

import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineResourcePacksEvent;
import org.geysermc.geyser.api.extension.Extension;
import org.geysermc.geyser.api.pack.PackCodec;
import org.geysermc.geyser.api.pack.ResourcePack;
import org.geysermc.geyser.api.pack.option.PriorityOption;
import org.geysermc.geyser.api.pack.option.ResourcePackOption;

import java.util.Map;

public class CdnPackLoader implements Extension {

    Map<String, ResourcePackOption<?>> packs = Map.of(
        "https://cdn.mineville.org/packs/MV.mcpack", PriorityOption.HIGH,
        "https://cdn.mineville.org/packs/BlockHero%20-%20Low%20Fire%20Pack.mcpack", PriorityOption.NORMAL,
        "https://cdn.mineville.org/packs/BlockHero%20-%20UI%20Pack.mcpack", PriorityOption.NORMAL,
        "https://cdn.mineville.org/packs/Mineville%20-%20Store%20Button.mcpack", PriorityOption.NORMAL,
        "https://cdn.mineville.org/packs/meg.mcpack", PriorityOption.NORMAL,
        "https://cdn.mineville.org/packs/pack.mcpack", PriorityOption.NORMAL
    );

    @Subscribe
    public void onPackLoad(GeyserDefineResourcePacksEvent event) {
        logger().info("Enabling cdn pack loader...");

        try {
            for (Map.Entry<String, ResourcePackOption<?>> pack : packs.entrySet()) {
                PackCodec codec = PackCodec.url(pack.getKey());
                event.register(ResourcePack.create(codec), pack.getValue());
            }
        } catch (Throwable e) {
            logger().info(e.getMessage());
            e.printStackTrace();
        }
    }
}
