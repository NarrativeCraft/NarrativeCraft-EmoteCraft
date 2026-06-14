package fr.loudo.narrativecraftemotecraft.events;

import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import fr.loudo.narrativecraft.api.NarrativeCraftAPI;
import fr.loudo.narrativecraft.api.recording.IRecording;
import fr.loudo.narrativecraftemotecraft.recording.actions.PlayEmoteAction;
import java.util.UUID;

public class OnEmotePlay {
    public static void emotePlay(KeyframeAnimation animation, UUID playerId) {
        IRecording recording =
                NarrativeCraftAPI.getInstance().getRecordingManager().getRecording(playerId);
        if (recording == null || !recording.isRecording()) return;

        recording.addAction(new PlayEmoteAction(recording.getTick(), animation), playerId);
    }
}
