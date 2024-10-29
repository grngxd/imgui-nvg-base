package cc.grng.base.client.api.ui

import cc.grng.base.bridge.Reference
import cc.grng.base.bridge.bridges.minecraft.ClientScreenBridge
import cc.grng.base.client.Client
import org.nvgu.NVGU

abstract class ClientScreen(val name: String = "ImGUI", var pauseGame: Boolean = false): ClientScreenBridge {
    val id = name.hashCode()
    var width = Reference.Display().`bridge$getWidth`()
        get() = Reference.Display().`bridge$getWidth`()
    var height = Reference.Display().`bridge$getHeight`()
        get() = Reference.Display().`bridge$getHeight`()

    var i = Client.instance.i
    val u = Client.instance.u

    open fun init() {}
    open fun render(mouseX: Int, mouseY: Int, delta: Float) {
        width = Reference.Display().`bridge$getWidth`()
        height = Reference.Display().`bridge$getHeight`()
    }

    open fun mouseClicked(x: Int, y: Int, button: Int) {}
    open fun mouseReleased(x: Int, y: Int, button: Int) {}

    open fun keyPressed(id: Char, key: Int) {}

    open fun destroy() {}

    override fun getScreen(): ClientScreen {
        return this
    }
}