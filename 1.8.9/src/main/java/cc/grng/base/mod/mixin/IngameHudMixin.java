package cc.grng.base.mod.mixin;

import cc.grng.base.client.Client;
import cc.grng.base.client.api.event.impl.hud.HudRenderEvent;
import cc.grng.base.client.api.gl.GLStateHelper;
import cc.grng.edge.event.EventManager;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class IngameHudMixin {
    // @Inject(at = @At("HEAD"), method = "renderBossBar")
    // private void edge$renderBossBar(CallbackInfo ci) {
    //     EventManager.Companion.getInstance().fire(new ImGuiRenderEvent(ImGuiRenderLevel.HUD));
    // }
    // @Inject(at = @At("HEAD"), method = "render")
    // private void edge$render(CallbackInfo ci) {
    // //        MinecraftClient.getInstance().profiler.push("edge_render");
    // //        UIManager.Companion.getInstance().render();
    // //        MinecraftClient.getInstance().profiler.pop();
    //     // EventManager.Companion.getInstance().fire(new ImGuiRenderEvent(ImGuiRenderLevel.HUD));
    // }

    @Inject(at = @At("HEAD"), method = "setDefaultTitleFade")
    private void mixin$init(CallbackInfo ci) {
        EventManager.Companion.getInstance().register(this);
    }

    GLStateHelper glStateHelper = new GLStateHelper();
    @Inject(at = @At("HEAD"), method = "render")
    private void mixin$render(CallbackInfo ci) {
        EventManager.Companion.getInstance().fire(new HudRenderEvent());
    }
}
