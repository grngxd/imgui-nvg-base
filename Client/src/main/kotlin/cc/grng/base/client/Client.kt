// Client class
package cc.grng.base.client

import cc.grng.base.bridge.BridgeManager
import cc.grng.base.bridge.Reference
import cc.grng.base.client.api.mod.ModManager
import cc.grng.base.client.api.ui.impl.TestClientScreen
import cc.grng.edge.event.EventManager
import cc.grng.edge.event.EventPriority
import cc.grng.edge.event.Subscribe
import cc.grng.edge.event.impl.imgui.ImGuiRenderEvent
import cc.grng.edge.event.impl.imgui.ImGuiRenderLevel
import cc.grng.edge.event.impl.tick.TickEvent
import cc.grng.imgu.IMGU
import cc.grng.imgu.IMGUStyle
import imgui.flag.ImGuiCol
import lombok.Getter
import org.lwjgl.glfw.GLFW
import org.nvgu.NVGU
import java.awt.Color

class Client {
    companion object {
        @Getter val instance = Client()
        @Getter val bridgeManager = BridgeManager.instance
    }

    @field:Getter
    lateinit var i: IMGU

    @Getter
    val u = NVGU()
        .create()
        .createFont("Rethink-Medium", Client::class.java.getResourceAsStream("/fonts/Rethink-Medium.ttf"))

    val version = "1.0-A1"

    fun start() {
        EventManager.instance.register(this)
        bridgeManager.start()
        println("Client started! (v$version)")

        Reference.Minecraft().`bridge$setDisplayTitle`("Client Base v$version (${Reference.Minecraft().`bridge$getSessionInfoMap`()!!["X-Minecraft-Version"]})")

        instance.i = IMGU(Reference.Display().`bridge$getHandle`())
            .createFont(
                "Rethink-Medium",
                Client::class.java.getResourceAsStream("/fonts/Rethink-Medium.ttf")!!,
                hashMapOf<String, Float>(
                    "medium" to 16f,
                )
            )
            .createImage(
                "logo",
                Client::class.java.getResourceAsStream("/assets/modid/icon.png")!!
            )
            .create()

        instance.i.setStyle(
            IMGUStyle().apply {
                windowPadding = floatArrayOf(17.5f, 17.5f)
                windowRounding = 12.5f
                colors = hashMapOf(
                    ImGuiCol.WindowBg to Color.decode("#080F0F"),
                )

            }
        )

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