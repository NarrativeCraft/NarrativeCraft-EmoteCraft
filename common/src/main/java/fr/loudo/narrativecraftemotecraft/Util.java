package fr.loudo.narrativecraftemotecraft;

import fr.loudo.narrativecraftemotecraft.mixin.PlayerListFields;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class Util {

    public static void addPlayerId(ServerPlayer player, MinecraftServer server) {
        Map<UUID, ServerPlayer> playerMap = ((PlayerListFields) server.getPlayerList()).getPlayersByUUID();
        if (playerMap.containsKey(player.getUUID())) return;

        playerMap.put(player.getUUID(), player);
    }

    public static void removePlayerId(ServerPlayer player, MinecraftServer server) {
        Map<UUID, ServerPlayer> playerMap = ((PlayerListFields) server.getPlayerList()).getPlayersByUUID();

        playerMap.remove(player.getUUID());
    }
}
