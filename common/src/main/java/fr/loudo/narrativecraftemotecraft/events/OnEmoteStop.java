package fr.loudo.narrativecraftemotecraft.events;

import fr.loudo.narrativecraft.api.NarrativeCraftAPI;
import fr.loudo.narrativecraft.api.recording.IRecording;
import fr.loudo.narrativecraftemotecraft.recording.actions.StopEmoteAction;
import java.util.UUID;

public class OnEmoteStop {
    public static void emoteStop(UUID emoteId, UUID playerId) {
        IRecording recording =
                NarrativeCraftAPI.getInstance().getRecordingManager().getRecording(playerId);
        if (recording == null || !recording.isRecording()) return;

        recording.addAction(new StopEmoteAction(recording.getTick()), playerId);
    }
}
