package fr.loudo.narrativecraftemotecraft.recording.actions;

import fr.loudo.narrativecraft.api.playback.IPlaybackContext;
import fr.loudo.narrativecraft.api.playback.IPlaybackSession;
import fr.loudo.narrativecraft.api.recording.action.AbstractAction;
import fr.loudo.narrativecraft.api.recording.action.ActionResult;
import fr.loudo.narrativecraftemotecraft.Util;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import java.io.IOException;
import net.minecraft.server.level.ServerPlayer;

public class StopEmoteAction extends AbstractAction {

    public static final String ID = "emotecraft-stop-animation";

    public StopEmoteAction(int tick) {
        super(tick);
    }

    @Override
    public void write(Writer writer) throws IOException {}

    @Override
    public void read(Reader reader) throws IOException {}

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public ActionResult execute(IPlaybackContext context, IPlaybackSession session) {
        if (!(context.getEntity() instanceof ServerPlayer player)) return ActionResult.IGNORED;

        ServerEmoteAPI.forcePlayEmote(player.getUUID(), null);

        Util.removePlayerId(player, player.level().getServer());

        return ActionResult.OK;
    }
}
