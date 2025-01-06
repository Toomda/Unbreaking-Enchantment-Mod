package unbreakable.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import unbreakable.mod.Unbreakable;
import unbreakable.screenhandler.UnbreakableEnchanterScreenHandler;

public class UnbreakableEnchanterScreen extends HandledScreen<UnbreakableEnchanterScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Unbreakable.MOD_ID, "textures/gui/container/unbreakable_enchanter.png");

    public UnbreakableEnchanterScreen(UnbreakableEnchanterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
