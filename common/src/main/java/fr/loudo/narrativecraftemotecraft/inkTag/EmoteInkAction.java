package fr.loudo.narrativecraftemotecraft.inkTag;

import com.zigythebird.playeranimcore.animation.Animation;
import fr.loudo.narrativecraft.api.NarrativeCraftAPI;
import fr.loudo.narrativecraft.api.inkAction.InkAction;
import fr.loudo.narrativecraft.api.inkAction.InkActionResult;
import fr.loudo.narrativecraft.api.inkAction.InkCommand;
import fr.loudo.narrativecraft.api.inkAction.Side;
import fr.loudo.narrativecraft.api.inkAction.syntax.ParsedCommand;
import fr.loudo.narrativecraft.api.managers.ICharacterManager;
import fr.loudo.narrativecraft.api.narrative.IStoryHandler;
import fr.loudo.narrativecraft.api.narrative.character.ICharacter;
import fr.loudo.narrativecraft.api.narrative.scene.IScene;
import fr.loudo.narrativecraft.api.session.IPlayerSession;
import fr.loudo.narrativecraftemotecraft.mixin.PlayerListFields;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import io.github.kosmx.emotes.common.tools.UUIDMap;
import io.github.kosmx.emotes.mc.McUtils;
import io.github.kosmx.emotes.server.serializer.UniversalEmoteSerializer;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

@InkCommand(
        keyword = "emote",
        description = "Play an emote animation on a character using EmoteCraft.",
        syntax = "emote <action:string> <emoteName:string> <characterName:string>",
        side = Side.SERVER)
public class EmoteInkAction extends InkAction {

    private String action;
    private ICharacter characterStory;
    private Animation animation;

    @Override
    protected InkActionResult doValidate(ParsedCommand cmd, IScene scene) {
        action = cmd.getString("action");
        if (!action.equals("play") && !action.equals("stop")) {
            return InkActionResult.error("Action is either 'play' or 'stop'");
        }

        String emoteName = cmd.getString("emoteName");
        animation = getEmote(emoteName, UniversalEmoteSerializer.getLoadedEmotes());
        if (animation == null) {
            return InkActionResult.error(String.format("Emote '%s' does not exists.", emoteName));
        }

        String characterName = cmd.getString("characterName");
        ICharacterManager characterManager = NarrativeCraftAPI.getInstance().getCharacterManager();
        characterStory = characterManager.resolveCharacter(characterName, scene);
        if (characterStory == null) {
            return InkActionResult.error(String.format("Character %s does not exists", characterName));
        }

        return InkActionResult.ok();
    }

    @Override
    protected InkActionResult doExecute(IPlayerSession playerSession) {

        IStoryHandler storyHandler = playerSession.getStoryHandler();
        if (storyHandler == null) return InkActionResult.ignored();

        Entity entity = storyHandler.getEntityFromCharacter(characterStory);
        if (!(entity instanceof ServerPlayer player)) return InkActionResult.ignored();

        Map<UUID, ServerPlayer> playerMap =
                ((PlayerListFields) player.level().getServer().getPlayerList()).getPlayersByUUID();
        playerMap.put(player.getUUID(), player);

        if (action.equals("play")) {
            ServerEmoteAPI.forcePlayEmote(player.getUUID(), animation);
        } else if (action.equals("stop")) {
            ServerEmoteAPI.forcePlayEmote(player.getUUID(), null);
        }

        return InkActionResult.ok();
    }

    private Animation getEmote(String emoteName, UUIDMap<Animation> emotes) {
        for (Animation animation : emotes) {
            String name = McUtils.fromJson(animation.data().getRaw("name")).getString();
            if (name.equalsIgnoreCase(emoteName)) {
                return animation;
            }
        }
        return null;
    }
}
