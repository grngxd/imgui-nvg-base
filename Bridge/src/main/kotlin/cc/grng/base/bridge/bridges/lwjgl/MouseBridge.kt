package cc.grng.base.bridge.bridges.lwjgl

import cc.grng.base.bridge.Bridge

interface MouseBridge: Bridge {
    fun `bridge$getX`(): Int
    fun `bridge$getY`(): Int
    fun `bridge$getDX`(): Int
    fun `bridge$getDY`(): Int
    fun `bridge$getDWheel`(): Int
    fun `bridge$isButtonDown`(button: Int): Boolean
}