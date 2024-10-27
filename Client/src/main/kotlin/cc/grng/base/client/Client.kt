// Client class
package cc.grng.base.client

import cc.grng.base.bridge.BridgeManager
import cc.grng.base.bridge.Reference
import cc.grng.base.client.api.mod.ModManager
import cc.grng.base.client.api.ui.impl.TestClientScreen
import cc.grng.edge.event.EventManager
import cc.grng.edge.event.EventPriority
import cc.grng.edge.event.Subscribe
import cc.grng.edge.event.impl.tick.TickEvent
import cc.grng.imgu.IMGU
import lombok.Getter
import org.lwjgl.glfw.GLFW

class Client {
    companion object {
        @Getter val instance = Client()
        @Getter val bridgeManager = BridgeManager.instance
    }

    @field:Getter
    lateinit var imgu: IMGU

    val version = "1.0-A1"

    fun start() {
        EventManager.instance.register(this)
        bridgeManager.start()
        println("Client started! (v$version)")

        instance.imgu = IMGU(Reference.Display().`bridge$getHandle`()).create()

        ModManager.instance.start()
    }

    fun stop() {
        println("Client stopped! (v$version)")
        bridgeManager.stop()
        EventManager.instance.unregister(this)
    }

    @Subscribe(target = TickEvent::class, priority = EventPriority.REALTIME)
    fun `event$tick`(event: TickEvent) {
        if (GLFW.glfwGetKey(Reference.Display().`bridge$getHandle`(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
            Reference.Minecraft().`bridge$setClientScreen`(
                TestClientScreen()
            )
        }
    }
}