package cc.grng.base.client.api.mod.impl

import cc.grng.base.bridge.Reference
import cc.grng.base.client.api.mod.Mod
import org.nvgu.util.Alignment
import org.nvgu.util.NVGUColour
import java.awt.Color
import java.awt.Rectangle

class FPSMod : Mod(
    name = "FPS Mod",
    aliases = arrayOf("fpsmod", "fps"),
    version = "1.0",
    description = "Displays the current frames per second.",
    author = "Grng",
    enabledByDefault = true
) {

    override fun render() {
        val fpsText = "${Reference.Minecraft().`bridge$getDebugFps`()} FPS"
        val textSize = ((screenWidth + screenHeight) / 100f).toInt()
        val textWidth = u.textWidth(fpsText, "Rethink-Medium", textSize)
        val textHeight = u.textHeight("Rethink-Medium", textSize)
        val padding = (screenWidth + screenHeight) / 80

        width = textWidth.toInt() + padding
        height = textHeight.toInt() + padding

        u.roundedRectangle(
            Rectangle(x, y, width, height),
            padding / 3f,
            NVGUColour.decode("#E15A97")
        )
        u.text(
            fpsText,
            (x + padding / 2).toFloat(),
            (y + padding / 2).toFloat(),
            NVGUColour.decode("#FFFFFF"),
            "Rethink-Medium",
            textSize,
            Alignment.LEFT_TOP
        )
    }
}