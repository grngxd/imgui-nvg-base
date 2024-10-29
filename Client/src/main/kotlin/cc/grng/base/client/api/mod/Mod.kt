package cc.grng.base.client.api.mod

import cc.grng.base.bridge.Reference
import cc.grng.base.client.Client

open class Mod(
    val name: String = "",
    val aliases: Array<String> = arrayOf(),
    val version: String = "",
    val description: String = "",
    val author: String = "",
    val enabledByDefault: Boolean = false
) {
    val id: String = name.lowercase().replace(" ", "_") + "+" + version
    var enabled: Boolean = enabledByDefault
        set(value) {
            if (value) start() else stop()
            field = value
        }

    var screenWidth: Int
        get() = Reference.Display().`bridge$getWidth`()
        private set(_) {}
    var screenHeight: Int
        get() = Reference.Display().`bridge$getHeight`()
        private set(_) {}

    var width = 0
    var height = 0

    var x: Int = 0
    var y: Int = 0

    val i = Client.instance.i
    val u = Client.instance.u

    open fun start() {}

    open fun stop() {}

    open fun render() {}

    fun toggle() {
        enabled = !enabled
    }

    val hovered: Boolean
        get() = hovered(x, y, width, height)

    fun hovered(x: Int, y: Int, width: Int, height: Int): Boolean {
        val mouseX = Reference.Mouse().`bridge$getX`()
        val mouseY = Reference.Mouse().`bridge$getY`()
        return mouseX in x..(x + width) && mouseY in y..(y + height)
    }

    fun hovered(x: Int, y: Int, width: Int, height: Int, mouseX: Int, mouseY: Int): Boolean {
        return mouseX in x..(x + width) && mouseY in y..(y + height)
    }

    fun serialize() {}
}