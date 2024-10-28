package cc.grng.base.client.api.ui.impl

import cc.grng.base.bridge.Reference
import cc.grng.base.client.api.ui.ClientScreen
import imgui.flag.ImGuiCond
import imgui.flag.ImGuiWindowFlags
import org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT
import org.lwjgl.opengl.GL11.glClear
import org.nvgu.NVGU
import org.nvgu.util.Alignment
import org.nvgu.util.Border
import org.nvgu.util.NVGUColour
import java.awt.Color
import java.awt.Rectangle

class TestClientScreen: ClientScreen("Test", true) {

    override fun init() {
        super.init()
    }

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        super.render(mouseX, mouseY, delta)
        u.frame(width, height) {
            u.text(
                "this is rendered with nvg!",
                ((width + height) / 100f),
                (width + height) / 100f,
                Color.decode("#FFFFFF"),
                "Rethink-Medium",
                (width + height) / 100,
                Alignment.LEFT_TOP
            )
        }

        i.render() {
            i.pushFont("Rethink-Medium", "medium") {
                i.window(
                    "Test GUI",
                    ImGuiWindowFlags.NoCollapse
                            or ImGuiWindowFlags.NoDecoration,
                    Pair(width / 2f, height / 2f), Pair(0.5f, 0.5f),
                    ImGuiCond.Always
                ) {
                    i.imageButton(
                        "logo",
                        Pair(100f, 100f),
                    ) {
                        //Reference.Minecraft().`bridge$setClientScreen`(AnotherClientScreen())
                        Reference.Minecraft().`bridge$setCurrentScreen`(null)
                    }
                }
            }
        }
    }

    override fun destroy() {
        super.destroy()
    }
}