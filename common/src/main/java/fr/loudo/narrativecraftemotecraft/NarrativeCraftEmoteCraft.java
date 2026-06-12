package fr.loudo.narrativecraftemotecraft;

import fr.loudo.narrativecraft.api.AddonContext;
import fr.loudo.narrativecraft.api.events.story.StoryEndEvent;
import fr.loudo.narrativecraftemotecraft.events.OnEmotePlay;
import fr.loudo.narrativecraftemotecraft.events.OnEmoteStop;
import fr.loudo.narrativecraftemotecraft.events.OnStoryStop;
import fr.loudo.narrativecraftemotecraft.inkTag.EmoteInkAction;
import fr.loudo.narrativecraftemotecraft.recording.actions.PlayEmoteAction;
import fr.loudo.narrativecraftemotecraft.recording.actions.StopEmoteAction;
import io.github.kosmx.emotes.api.events.server.ServerEmoteEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NarrativeCraftEmoteCraft {
    public static final String MOD_ID = "narrativecraft_emotecraft";
    public static final String MOD_NAME = "NarrativeCraft-EmoteCraft";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void registerEvents(AddonContext addonContext) {
        ServerEmoteEvents.EMOTE_PLAY.register(OnEmotePlay::emotePlay);
        ServerEmoteEvents.EMOTE_STOP_BY_USER.register(OnEmoteStop::emoteStop);

        addonContext.registerEvent(StoryEndEvent.class, new OnStoryStop());
    }

    public static void registerActions(AddonContext addonContext) {
        addonContext.registerRecordingAction(PlayEmoteAction.ID, PlayEmoteAction::new);
        addonContext.registerRecordingAction(StopEmoteAction.ID, StopEmoteAction::new);
    }

    public static void registerInkAction(AddonContext addonContext) {
        addonContext.registerInkAction(EmoteInkAction.class, EmoteInkAction::new);
    }
}
