package fr.loudo.narrativecraftemotecraft.events;

import fr.loudo.narrativecraft.api.events.EventListener;
import fr.loudo.narrativecraft.api.events.story.StoryEndEvent;
import fr.loudo.narrativecraft.api.narrative.IStoryHandler;
import fr.loudo.narrativecraftemotecraft.Util;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class OnStoryStop implements EventListener<StoryEndEvent> {
    @Override
    public void handle(StoryEndEvent event) {
        IStoryHandler storyHandler = event.session().getStoryHandler();
        if (storyHandler == null) return;

        for (Entity entity : storyHandler.getCharacterEntities().values()) {
            if (!(entity instanceof ServerPlayer player)) continue;

            Util.removePlayerId(player, player.level().getServer());
        }
    }
}
