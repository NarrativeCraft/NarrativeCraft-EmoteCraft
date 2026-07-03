package fr.loudo.narrativecraftemotecraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NarrativeCraftEmoteCraft.MOD_ID)
public class NarrativeCraftEmoteCraftForge {

    public NarrativeCraftEmoteCraftForge(FMLJavaModLoadingContext context) {
        context.getModEventBus().addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        CommonClass.init();
    }
}
