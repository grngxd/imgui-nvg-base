package cc.grng.base.bridge
import cc.grng.base.bridge.bridges.lwjgl.DisplayBridge
import cc.grng.base.bridge.bridges.lwjgl.MouseBridge
import cc.grng.base.bridge.bridges.minecraft.MinecraftClientBridge
import lombok.Getter
import lombok.Setter

@Getter @Setter
class BridgeManager {
    companion object {
        val instance = BridgeManager()
    }

    lateinit var minecraftClientBridge: MinecraftClientBridge;
    lateinit var displayBridge: DisplayBridge;
    lateinit var mouseBridge: MouseBridge;



    fun start() {
        println("Bridge started!")
    }

    fun stop() {
        println("Bridge stopped!")
    }
}