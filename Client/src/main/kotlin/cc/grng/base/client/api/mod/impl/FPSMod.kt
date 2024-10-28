package cc.grng.base.client.api.mod.impl

import cc.grng.base.bridge.Reference
import cc.grng.base.client.api.mod.Mod
import org.nvgu.util.Alignment
import org.nvgu.util.NVGUColour
import java.awt.Color
import java.awt.Rectangle

class FPSMod: Mod(
    name = "FPS Mod",
    aliases = arrayOf("fpsmod", "fps"),
    version = "1.0",
    description = "Displays the current frames per second.",
    author = "Grng",
    enabledByDefault = true
) {
    override fun render() {
        u.roundedRectangle(
            Rectangle(
                width - ((width + height) / 100) - ((width + height) / 20),
                (width + height) / 100,
                (width + height) / 20,
                (width + height) / 40,
            ),
            (width + height) / 200f,
            NVGUColour.decode("#E15A97")
        )
        u.text(
            "${Reference.Minecraft().`bridge$getDebugFps`()} FPS",
            (width - ((width + height) / 100) - ((width + height) / 40)).toFloat(),
            ((width + height) / 100 + (width + height) / 80).toFloat(),
            NVGUColour.decode("#FFFFFF"),
            "Rethink-Medium",
            (width + height) / 100,
            Alignment.CENTER_MIDDLE,
        )
    }
}