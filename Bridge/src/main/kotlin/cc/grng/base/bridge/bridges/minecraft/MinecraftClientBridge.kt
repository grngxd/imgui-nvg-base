package cc.grng.base.bridge.bridges.minecraft

import cc.grng.base.bridge.Bridge

interface MinecraftClientBridge : Bridge {
    fun `bridge$getInstance`(): Any?

    fun `bridge$getDebugFps`(): Int

    fun `bridge$getSessionInfoMap`(): Map<String?, String?>?

    fun `bridge$getCurrentScreen`(): Any?
    fun `bridge$getHandle`(): Long?

    fun `bridge$setDisplayTitle`(title: String?)
    fun `bridge$push`(name: String?)
    fun `bridge$pop`()
    fun `bridge$getPlayer`(): Any?

    fun `bridge$setCurrentScreen`(screen: ScreenBridge?)

    fun `bridge$setClientScreen`(screen: ScreenBridge) {
        `bridge$setCurrentScreen`(
            `bridge$createClientScreen`(screen)
        )
    }

    fun `bridge$createClientScreen`(screen: ScreenBridge): ScreenBridge
}
