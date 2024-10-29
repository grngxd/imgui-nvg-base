package cc.grng.base.client.api.ui.impl

import cc.grng.base.bridge.Reference
import cc.grng.base.client.api.mod.Mod
import cc.grng.base.client.api.mod.ModManager
import cc.grng.base.client.api.ui.ClientScreen
import imgui.flag.ImGuiCond
import imgui.flag.ImGuiWindowFlags
import org.nvgu.util.Alignment
import org.nvgu.util.Border
import java.awt.Color
import java.awt.Rectangle

class TestClientScreen : ClientScreen("Test", true) {

    private var selectedMod: Mod? = null
    private var modOffsetX: Int = 0
    private var modOffsetY: Int = 0

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

            for (mod in ModManager.instance.enabledMods) {
                mod.x = mod.x.coerceIn(0, width - mod.width)
                mod.y = mod.y.coerceIn(0, height - mod.height)

                u.rectangleBorder(
                    Rectangle(mod.x, mod.y, mod.width, mod.height),
                    2f,
                    Color.decode("#FF00FF"),
                    Border.OUTSIDE
                )

                if (mod == selectedMod) {
                    if (Reference.Mouse().`bridge$isButtonDown`(0)) {
                        mod.x = (Reference.Mouse().`bridge$getX`() - modOffsetX).coerceIn(0, width - mod.width)
                        mod.y = (Reference.Mouse().`bridge$getY`() - modOffsetY).coerceIn(0, height - mod.height)
                    } else {
                        selectedMod = null
                    }
                }
            }
        }

        i.render {
            i.pushFont("Rethink-Medium", "medium") {
                i.window(
                    "Test GUI",
                    ImGuiWindowFlags.NoCollapse or ImGuiWindowFlags.NoDecoration,
                    Pair(width / 2f, height / 2f), Pair(0.5f, 0.5f),
                    ImGuiCond.Always
                ) {
                    i.imageButton("logo", Pair(100f, 100f)) {
                        Reference.Minecraft().`bridge$setCurrentScreen`(null)
                    }
                }
            }
        }
    }

    override fun mouseClicked(x: Int, y: Int, button: Int) {
        if (button == 0) {
            for (mod in ModManager.instance.enabledMods) {
                if (mod.hovered) {
                    selectedMod = mod
                    modOffsetX = Reference.Mouse().`bridge$getX`() - mod.x
                    modOffsetY = Reference.Mouse().`bridge$getY`() - mod.y
                    break
                }
            }
        }
    }

    override fun destroy() {
        super.destroy()
    }
}