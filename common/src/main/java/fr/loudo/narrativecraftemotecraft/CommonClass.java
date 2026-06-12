package fr.loudo.narrativecraftemotecraft;

import fr.loudo.narrativecraft.api.AddonContext;
import fr.loudo.narrativecraft.api.NarrativeCraftAPI;

public class CommonClass {

    public static void init() {
        AddonContext addonContext = NarrativeCraftAPI.getInstance()
                .createAddon(
                        NarrativeCraftEmoteCraft.MOD_ID,
                        NarrativeCraftEmoteCraft.MOD_NAME,
                        "Add EmoteCraft compatibility to NarrativeCraft",
                        "LOUDO",
                        "https://modrinth.com/mod/narrativecraft-emotecraft",
                        NarrativeCraftAPI.VERSION);

        NarrativeCraftEmoteCraft.registerEvents(addonContext);
        NarrativeCraftEmoteCraft.registerActions(addonContext);
        NarrativeCraftEmoteCraft.registerInkAction(addonContext);
    }
}
