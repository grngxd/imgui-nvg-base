package cc.grng.base.bridge.bridges.lwjgl

import cc.grng.base.bridge.Bridge

interface DisplayBridge: Bridge {
    fun `bridge$getWidth`(): Int
    fun `bridge$getHeight`(): Int
    fun `bridge$getHandle`(): Long
}