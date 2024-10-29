package cc.grng.base.mod.api.ui

import cc.grng.base.bridge.Reference
import cc.grng.base.bridge.bridges.minecraft.ClientScreenBridge
import cc.grng.base.bridge.bridges.minecraft.ScreenBridge
import cc.grng.base.client.api.gl.GLStateHelper
import cc.grng.base.client.api.ui.ClientScreen
import cc.grng.edge.event.EventManager
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen

class ClientScreenWrapper(private val screen: ClientScreen): Screen(), ClientScreenBridge, ScreenBridge {

    private val glHelper = GLStateHelper()

    override fun init(client: MinecraftClient?, width: Int, height: Int) {
        // without this, the screen class will freak out, and you'll never know why
        super.init(client, width, height)
        screen.init()
    }

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        super.render(mouseX, mouseY, delta)
        glHelper.backup()
            Reference.Minecraft().`bridge$push`("${screen.name} (${screen.id})")
                super.render(mouseX, mouseY, delta)
                screen.render(mouseX, mouseY, delta)
            Reference.Minecraft().`bridge$pop`()
        glHelper.restore()
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
        screen.mouseClicked(mouseX, mouseY, button)
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, button: Int) {
        screen.mouseReleased(mouseX, mouseY, button)
        return super.mouseReleased(mouseX, mouseY, button)
    }

    override fun keyPressed(id: Char, key: Int) {
        screen.keyPressed(id, key)
        return super.keyPressed(id, key)
    }

    override fun removed() {
        super.removed()
        screen.destroy()
    }

    override fun shouldPauseGame(): Boolean {
        return screen.pauseGame
    }

    override fun getScreen(): Any {
        return screen
    }
}