package unbreakable.init;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import unbreakable.mod.Unbreakable;
import unbreakable.network.BlockPosPayload;
import unbreakable.screenhandler.UnbreakableEnchanterScreenHandler;

public class ScreenHandlerTypeInit {
    public static final ScreenHandlerType<UnbreakableEnchanterScreenHandler> UNBREAKABLE_ENCHANTER = register("unbreakable_enchanter", UnbreakableEnchanterScreenHandler::new, BlockPosPayload.PACKET_CODED);

    public static <T extends ScreenHandler, D extends CustomPayload>ExtendedScreenHandlerType<T, D>
    register(String name,
             ExtendedScreenHandlerType.ExtendedFactory<T,D> factory,
             PacketCodec<? super RegistryByteBuf, D> codec){
        return Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Unbreakable.MOD_ID, name), new ExtendedScreenHandlerType<>(factory, codec));
    }

    public static void load() {}
}
