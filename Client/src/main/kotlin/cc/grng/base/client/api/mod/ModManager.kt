// ModManager.kt
package cc.grng.base.client.api.mod

import cc.grng.base.bridge.Reference
import cc.grng.base.client.Client
import cc.grng.base.client.api.event.impl.hud.HudRenderEvent
import cc.grng.base.client.api.gl.GLStateHelper
import cc.grng.base.client.api.mod.impl.FPSMod
import cc.grng.edge.event.EventManager
import cc.grng.edge.event.EventPriority
import cc.grng.edge.event.Subscribe
import org.nvgu.NVGU

class ModManager {
    companion object {
        val instance = ModManager()
    }

    private val f = FPSMod();

    val mods = ArrayList<Mod>()
    val enabledMods: ArrayList<Mod>
        get() = mods.filter { it.enabled } as ArrayList<Mod>

    fun start() {
        EventManager.instance.register(this)

        for (field in this.javaClass.declaredFields) {
            if (field.type.superclass == Mod::class.java) {
                val mod = field.get(this) as Mod
                mods.add(mod)
                mod.start()
            }
        }
    }

    val glStateHelper = GLStateHelper()

    @Subscribe(target = HudRenderEvent::class, priority = EventPriority.REALTIME)
    fun `event$HudRenderEvent`(event: HudRenderEvent) {
        glStateHelper.backup()
        Client.instance.u.frame(Reference.Display().`bridge$getWidth`(), Reference.Display().`bridge$getHeight`()) {
            enabledMods.forEach { it.render() }
        }
        glStateHelper.restore()
    }
}