package cc.grng.base.mod.impl;

import cc.grng.base.bridge.bridges.lwjgl.MouseBridge;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class MouseImpl implements MouseBridge {
    @Override
    public int bridge$getX() {
        return Mouse.getX();
    }

    @Override
    public int bridge$getY() {
        return Display.getHeight() - Mouse.getY();
    }

    @Override
    public int bridge$getDX() {
        return Mouse.getDX();
    }

    @Override
    public int bridge$getDY() {
        return Mouse.getDY();
    }

    @Override
    public int bridge$getDWheel() {
        return Mouse.getDWheel();
    }

    @Override
    public boolean bridge$isButtonDown(int button) {
        return Mouse.isButtonDown(button);
    }
}
