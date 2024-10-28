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
    val id: String = name.lowercase().replace(" ", "_").plus("+").plus(version)
    var enabled: Boolean = enabledByDefault

    var width = Reference.Display().`bridge$getWidth`()
        get() = Reference.Display().`bridge$getWidth`()
    var height = Reference.Display().`bridge$getHeight`()
        get() = Reference.Display().`bridge$getHeight`()

    val x = 0;
    val y = 0;

    val i = Client.instance.i
    val u = Client.instance.u

    open fun start() {
        enabled = true
    }

    open fun stop() {
        enabled = false
    }

    open fun render() {
    }
}