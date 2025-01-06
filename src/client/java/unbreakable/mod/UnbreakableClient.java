package unbreakable.mod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenHandlerType;
import unbreakable.init.ScreenHandlerTypeInit;
import unbreakable.screen.UnbreakableEnchanterScreen;

public class UnbreakableClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HandledScreens.register(ScreenHandlerTypeInit.UNBREAKABLE_ENCHANTER, UnbreakableEnchanterScreen::new);
	}
}