package cc.grng.base.client.api.ui.impl

import cc.grng.base.bridge.Reference
import cc.grng.base.client.api.ui.ClientScreen
import imgui.flag.ImGuiWindowFlags
import org.nvgu.NVGU
import org.nvgu.util.Alignment
import org.nvgu.util.Border
import org.nvgu.util.NVGUColour
import java.awt.Color
import java.awt.Rectangle

class TestClientScreen: ClientScreen("Test", true) {

    override fun init() {
        u.create()
        super.init()
    }

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        super.render(mouseX, mouseY, delta)
        u.frame(width, height) {
            u.roundedRectangle(
                Rectangle(
                (width + height) / 50,
                (width + height) / 50,
                (width + height) / 10,
                (width + height) / 20,
                ),
                (width + height) / 100f,
                NVGUColour.decode("#F79256")
            )
        }

        i.render() {
            i.window("Hello, World!", ImGuiWindowFlags.NoCollapse) {
                i.text("Hello, World!")
                i.button("Close") {
                    Reference.Minecraft().`bridge$setCurrentScreen`(null)
                }
            }
        }
    }

    override fun destroy() {
        super.destroy()
    }
}