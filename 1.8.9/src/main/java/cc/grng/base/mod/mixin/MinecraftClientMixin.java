package cc.grng.base.mod.mixin;

import cc.grng.base.bridge.BridgeManager;
import cc.grng.base.bridge.bridges.minecraft.MinecraftClientBridge;
import cc.grng.base.bridge.bridges.minecraft.ScreenBridge;
import cc.grng.base.client.Client;
import cc.grng.base.client.api.ui.ClientScreen;
import cc.grng.base.mod.api.ui.ClientScreenWrapper;
import cc.grng.edge.event.EventManager;
import cc.grng.edge.event.impl.tick.PreTickEvent;
import cc.grng.edge.event.impl.tick.TickEvent;
import com.google.common.collect.Maps;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements MinecraftClientBridge {
	@Final
	@Shadow
	public Profiler profiler;
	@Shadow public ClientPlayerEntity player;

	/*@Inject(at = @At("TAIL"), method = "<init>")
	private void client$init(CallbackInfo info) {
		Client.Companion.getInstance().start();
		Client.Companion.getBridgeManager().setMinecraftClientBridge(this);
	}*/

	@Inject(at = @At("TAIL"), method = "initializeGame")
	private void edge$initializeGame(CallbackInfo info) {
		BridgeManager.Companion.getInstance().setMinecraftClientBridge(this);
		Client.Companion.getInstance().start();
	}

	@Inject(at = @At("TAIL"), method = "stop")
	private void client$stop(CallbackInfo info) {
		Client.Companion.getInstance().stop();
	}

	@Inject(at = @At("HEAD"), method = "tick")
	private void client$tick(CallbackInfo info) {
		EventManager.Companion.getInstance().fire(new TickEvent());
	}

	@Inject(at = @At("HEAD"), method = "runGameLoop")
	private void client$runGameLoop(CallbackInfo info) {
		EventManager.Companion.getInstance().fire(new PreTickEvent());
	}

	/////////////////////////////////////////////// BRIDGE ///////////////////////////////////////////////

	@Override
	public Object bridge$getInstance() {
		return MinecraftClient.getInstance();
	}

	@Override
	public int bridge$getDebugFps() {
		return MinecraftClient.getCurrentFps();
	}

	/* public static Map<String, String> getSessionInfoMap() {
		Map<String, String> map = Maps.<String, String>newHashMap();
		map.put("X-Minecraft-Username", getInstance().getSession().getUsername());
		map.put("X-Minecraft-UUID", getInstance().getSession().getUuid());
		map.put("X-Minecraft-Version", "1.8.9");
		return map;
	} */
	@Override
	public Map<String, String> bridge$getSessionInfoMap() {
		return MinecraftClient.getSessionInfoMap();
	}

	@Override
	public Object bridge$getCurrentScreen() {
		return MinecraftClient.getInstance().currentScreen;
	}

	@Override
	public void bridge$setCurrentScreen(ScreenBridge screen) {
		MinecraftClient.getInstance().setScreen((Screen) screen);
	}

	@Override
	public @NotNull ScreenBridge bridge$createClientScreen(ScreenBridge screen) {
		return new ClientScreenWrapper((ClientScreen) screen);
	}

	@Override
	public void bridge$setClientScreen(@Nullable ScreenBridge screen) {
		MinecraftClient.getInstance().setScreen((Screen) bridge$createClientScreen(screen));
	}

	@Override
	public Long bridge$getHandle() {
		return Display.getHandle();
	}

	@Override
	public void bridge$setDisplayTitle(String title) {
		Display.setTitle(title);
	}

	@Override
	public void bridge$push(String name) {
		profiler.push(name);
	}

	@Override
	public void bridge$pop() {
		profiler.pop();
	}

	@Override
	public Object bridge$getPlayer() {
		return player;
	}
}
