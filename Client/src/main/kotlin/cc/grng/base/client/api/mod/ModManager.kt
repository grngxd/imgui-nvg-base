package cc.grng.base.client.api.mod
import cc.grng.base.client.api.event.impl.hud.HudRenderEvent
import cc.grng.base.client.api.mod.impl.*
import cc.grng.edge.event.EventManager
import cc.grng.edge.event.EventPriority
import cc.grng.edge.event.Subscribe
import cc.grng.edge.event.impl.tick.TickEvent

class ModManager {
    companion object {
        val instance = ModManager()
    }

    private val FPSMod = FPSMod()

    val mods = ArrayList<Mod>()
    val enabledMods: ArrayList<Mod>
        get() = mods.filter { it.enabled } as ArrayList<Mod>

    fun start() {
        EventManager.instance.register(this)

        for (field in this.javaClass.declaredFields) {
            if (field.type.superclass == Mod::class.java) {
                val mod = field.get(this) as Mod
                mods.add(mod)
                // mod.enable()
                mod.start()
            }
        }
    }

    @Subscribe(target = HudRenderEvent::class, priority = EventPriority.REALTIME)
    fun `event$HudRenderEvent`(event: HudRenderEvent) {
        enabledMods.forEach { it.render() }
    }
}