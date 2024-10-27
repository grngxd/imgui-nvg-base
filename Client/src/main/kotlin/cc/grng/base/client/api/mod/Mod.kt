package cc.grng.base.client.api.mod

import cc.grng.base.bridge.Reference
import cc.grng.base.client.Client
import org.lwjgl.opengl.GL.destroy
import org.nvgu.NVGU

open class Mod(
    val name: String = "",
    val aliases: Array<String> = arrayOf(),
    val version: String = "",
    val description: String = "",
    val author: String = "",
    val enabledByDefault: Boolean = false
) {
    val id: String = name.lowercase().replace(" ", "_").plus("+").plus(version)
    var enabled: Boolean = enabledByDefault

    var width = Reference.Display().`bridge$getWidth`()
    var height = Reference.Display().`bridge$getHeight`()

    val i = Client.instance.imgu
    val u = NVGU()

    open fun start() {
        enabled = true
    }

    open fun stop() {
        enabled = false
    }

    open fun render() {
    }
}