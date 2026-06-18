package fr.loudo.narrativecraftemotecraft.recording.actions;

import com.zigythebird.playeranimcore.animation.Animation;
import fr.loudo.narrativecraft.api.playback.IPlaybackContext;
import fr.loudo.narrativecraft.api.playback.IPlaybackSession;
import fr.loudo.narrativecraft.api.recording.action.AbstractAction;
import fr.loudo.narrativecraft.api.recording.action.ActionResult;
import fr.loudo.narrativecraftemotecraft.Util;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import io.github.kosmx.emotes.server.serializer.UniversalEmoteSerializer;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.server.level.ServerPlayer;

public class PlayEmoteAction extends AbstractAction {

    public static final String ID = "emotecraft-play-animation";

    private Animation animation;

    public PlayEmoteAction(int tick, Animation animation) {
        super(tick);
        this.animation = animation;
    }

    public PlayEmoteAction(int tick) {
        super(tick);
    }

    @Override
    public void write(Writer writer) throws IOException {
        writer.addUUID(animation.uuid());
    }

    @Override
    public void read(Reader reader) throws IOException {
        UUID animationId = reader.readUUID();
        animation = UniversalEmoteSerializer.getEmote(animationId);
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public ActionResult execute(IPlaybackContext context, IPlaybackSession session) {
        if (animation == null) return ActionResult.ERROR;
        if (!(context.getEntity() instanceof ServerPlayer player)) return ActionResult.IGNORED;

        Util.addPlayerId(player, player.level().getServer());

        ServerEmoteAPI.forcePlayEmote(player.getUUID(), animation);

        return ActionResult.OK;
    }
}
