package cc.grng.base.bridge

import cc.grng.base.bridge.bridges.lwjgl.DisplayBridge
import cc.grng.base.bridge.bridges.minecraft.MinecraftClientBridge

class Reference {
    companion object {
        fun Minecraft(): MinecraftClientBridge {
            return BridgeManager.instance.minecraftClientBridge
        }

        fun Display(): DisplayBridge {
            return BridgeManager.instance.displayBridge
        }
    }
}