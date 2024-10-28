package cc.grng.base.mod.mixin.client.gui.hud;

import cc.grng.base.client.api.event.impl.hud.HudRenderEvent;
import cc.grng.base.client.api.gl.GLStateHelper;
import cc.grng.edge.event.EventManager;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(at = @At("HEAD"), method = "setDefaultTitleFade")
    private void mixin$init(CallbackInfo ci) {
        EventManager.Companion.getInstance().register(this);
    }

    GLStateHelper glStateHelper = new GLStateHelper();
    @Inject(at = @At("HEAD"), method = "render")
    private void mixin$render(CallbackInfo ci) {
        EventManager.Companion.getInstance().fire(new HudRenderEvent()  );
    }
}
