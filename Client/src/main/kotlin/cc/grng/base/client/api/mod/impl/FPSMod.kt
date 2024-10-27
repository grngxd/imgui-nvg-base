package cc.grng.base.client.api.mod.impl

import cc.grng.base.bridge.Reference
import cc.grng.base.client.api.mod.Mod
import java.awt.Color

class FPSMod: Mod(
    name = "FPS Mod",
    aliases = arrayOf("fpsmod", "fps"),
    version = "1.0",
    description = "Displays the current frames per second.",
    author = "Grng",
    enabledByDefault = true
) {
    override fun render() {
        u.frame(width, height) {
            u.text("FPS: ${Reference.Minecraft().`bridge$getDebugFps`()}", 10f, 10f, Color.decode("#F79256"))
        }
    }
}