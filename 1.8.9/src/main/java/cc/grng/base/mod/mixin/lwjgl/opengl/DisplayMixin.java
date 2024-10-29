package cc.grng.base.mod.mixin.lwjgl.opengl;

import cc.grng.base.bridge.BridgeManager;
import cc.grng.base.bridge.bridges.lwjgl.DisplayBridge;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Display.class)
public class DisplayMixin implements DisplayBridge {

    @Inject(at = @At("TAIL"), method = "<init>")
    private void client$init(CallbackInfo ci) {
        BridgeManager.Companion.getInstance().setDisplayBridge(this);
    }

    @Inject(at = @At("TAIL"), method = "create", remap = false)
    private static void mixin$create(PixelFormat pixelFormat, CallbackInfo ci) {

    }

    @Inject(at = @At("TAIL"), method = "destroy", remap = false)
    private static void mixin$destroy(CallbackInfo ci) {
    }

    /////////////////////////////////////////////// BRIDGE ///////////////////////////////////////////////

    @Shadow private static int width;

    @Shadow private static int height;

    @Shadow private static long handle;

    @Override
    public int bridge$getWidth() {
        return width;
    }

    @Override
    public int bridge$getHeight() {
        return height;
    }

    @Override
    public long bridge$getHandle() {
        return handle;
    }

    @Override
    public void bridge$setDisplayTitle(@NotNull String title) {
        Display.setTitle(title);
    }
}
