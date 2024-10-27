package cc.grng.edge.event.impl.imgui

import cc.grng.edge.event.Event

class ImGuiRenderEvent(val level: ImGuiRenderLevel) : Event()
enum class ImGuiRenderLevel {
    START_RENDER,
    HUD,
    GUI,
    END_RENDER,
}